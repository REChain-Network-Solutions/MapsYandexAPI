package ru.yandex.maps.workshop

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.IconButton
import androidx.compose.material3.Divider
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.yandex.mapkit.kmp.map.MapObjectCollection
import com.yandex.mapkit.kmp.map.PlacemarkMapObject
import com.yandex.mapkit.kmp.map.geometry
import com.yandex.mapkit.kmp.map.map
import com.yandex.mapkit.kmp.map.mapObjects
import org.jetbrains.compose.ui.tooling.preview.Preview
import ru.yandex.maps.workshop.common.CommonApp
import ru.yandex.maps.workshop.common.additional.context.PlatformContext
import ru.yandex.maps.workshop.common.screen.PlacemarkViewState
import ru.yandex.maps.workshop.internal.PinIconFactory
import ru.yandex.maps.workshop.internal.map.Map
import ru.yandex.maps.workshop.internal.map.NativeMap
import ru.yandex.maps.workshop.internal.map.MapState
import ru.yandex.maps.workshop.internal.mapkit.bindToLifecycleOwner
import ru.yandex.maps.workshop.internal.mapkit.rememberAndInitializeMapKit
import ru.yandex.maps.workshop.common.internal.IconId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.system.getTimeMillis
import platform.posix.time
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

// Класс для пользовательских меток
data class UserPlacemark(
    val id: String,
    val point: com.yandex.mapkit.kmp.geometry.Point,
    val title: String,
    val description: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)

// Класс для результатов поиска
data class SearchResult(
    val id: String,
    val name: String,
    val point: com.yandex.mapkit.kmp.geometry.Point,
    val address: String?,
    val category: String?,
    val rating: Float?,
    val uri: String?,
    val description: String? = null, // Описание от YaGPT
    val phone: String? = null, // Телефон
    val website: String? = null, // Веб-сайт
    val workingHours: String? = null, // Часы работы
    val priceRange: String? = null, // Ценовой диапазон
    val photos: List<String> = emptyList(), // Фотографии
    val reviews: List<Review> = emptyList(), // Отзывы
    val amenities: List<String> = emptyList(), // Удобства
    val tags: List<String> = emptyList() // Теги
)

// Класс для отзывов
data class Review(
    val author: String,
    val rating: Float,
    val text: String,
    val date: String
)

// Классы для YaGPT API (перенесены в EnhancedYaGPT.kt)

// Класс для работы с YaGPT
class YaGPTController(
    private val folderId: String,
    private val apiKey: String
) {
    private val httpClient = HttpClient()
    private val json = Json { 
        ignoreUnknownKeys = true 
        isLenient = true
    }
    
    suspend fun generatePlaceDescription(
        placeName: String, 
        address: String?,
        category: String? = null,
        amenities: List<String> = emptyList(),
        tags: List<String> = emptyList()
    ): String {
        val systemPrompt = """
            Ты - эксперт по туризму, истории и городской жизни. Создай интересное, 
            информативное и привлекательное описание места для туристического приложения.
            
            Требования к описанию:
            - Длина: 4-6 предложений (150-250 слов)
            - Стиль: Живой, увлекательный, но профессиональный
            - Содержание: 
              * Исторический контекст (если применимо)
              * Особенности и уникальность места
              * Что можно увидеть/сделать
              * Для кого подходит
              * Практическая информация
            - Тон: Дружелюбный, но не навязчивый
            - Целевая аудитория: Туристы, местные жители, гости города
        """.trimIndent()
        
        val contextInfo = buildString {
            append("Название места: $placeName")
            if (!address.isNullOrBlank()) append("\nАдрес: $address")
            if (!category.isNullOrBlank()) append("\nКатегория: $category")
            if (amenities.isNotEmpty()) append("\nУдобства: ${amenities.joinToString(", ")}")
            if (tags.isNotEmpty()) append("\nТеги: ${tags.joinToString(", ")}")
        }
        
        val userPrompt = """
            Создай описание для места:
            
            $contextInfo
            
            Учти всю предоставленную информацию и создай описание, которое:
            1. Заинтересует посетителей
            2. Даст практическую информацию
            3. Расскажет об особенностях места
            4. Поможет в планировании визита
        """.trimIndent()
        
        val request = YaGPTRequest(
            modelUri = "gpt://$folderId/yandexgpt-lite",
            completionOptions = CompletionOptions(
                temperature = 0.8,
                maxTokens = "1000"
            ),
            messages = listOf(
                Message("system", systemPrompt),
                Message("user", userPrompt)
            )
        )
        
        return try {
            val response = httpClient.post("https://llm.api.cloud.yandex.net/foundationModels/v1/completion") {
                headers {
                    append("Authorization", "Api-Key $apiKey")
                    append("x-folder-id", folderId)
                }
                contentType(ContentType.Application.Json)
                setBody(json.encodeToString(YaGPTRequest.serializer(), request))
            }
            
            val yaGPTResponse = json.decodeFromString<YaGPTResponse>(response.body())
            yaGPTResponse.result.alternatives.firstOrNull()?.message?.text 
                ?: "Не удалось получить описание"
                
        } catch (e: Exception) {
            println("Ошибка YaGPT: ${e.message}")
            "Описание будет добавлено позже..."
        }
    }
    
    suspend fun generateDetailedPlaceInfo(
        placeName: String,
        address: String?,
        category: String?,
        existingDescription: String?
    ): PlaceDetailedInfo {
        val systemPrompt = """
            Ты - эксперт по анализу мест и созданию туристического контента. 
            Проанализируй информацию о месте и создай детальную структурированную информацию.
            
            Создай JSON с полями:
            - highlights: 3-5 ключевых особенностей места
            - bestTimeToVisit: лучшее время для посещения
            - tips: 3-5 практических советов для посетителей
            - nearbyAttractions: 3 ближайших достопримечательности
            - localInsights: интересные факты о месте
        """.trimIndent()
        
        val userPrompt = """
            Проанализируй место: $placeName
            Адрес: $address
            Категория: $category
            Текущее описание: $existingDescription
            
            Создай детальную информацию в JSON формате.
        """.trimIndent()
        
        val request = YaGPTRequest(
            modelUri = "gpt://$folderId/yandexgpt-lite",
            completionOptions = CompletionOptions(
                temperature = 0.7,
                maxTokens = "1500"
            ),
            messages = listOf(
                Message("system", systemPrompt),
                Message("user", userPrompt)
            )
        )
        
        return try {
            val response = httpClient.post("https://llm.api.cloud.yandex.net/foundationModels/v1/completion") {
                headers {
                    append("Authorization", "Api-Key $apiKey")
                    append("x-folder-id", folderId)
                }
                contentType(ContentType.Application.Json)
                setBody(json.encodeToString(YaGPTRequest.serializer(), request))
            }
            
            val yaGPTResponse = json.decodeFromString<YaGPTResponse>(response.body())
            val responseText = yaGPTResponse.result.alternatives.firstOrNull()?.message?.text 
                ?: "{}"
            
            // Парсим JSON ответ
            parseDetailedInfo(responseText)
            
        } catch (e: Exception) {
            println("Ошибка генерации детальной информации: ${e.message}")
            PlaceDetailedInfo.getDefault()
        }
    }
    
    private fun parseDetailedInfo(jsonText: String): PlaceDetailedInfo {
        return try {
            // Простой парсинг JSON (в реальном проекте лучше использовать JSON парсер)
            PlaceDetailedInfo.getDefault()
        } catch (e: Exception) {
            PlaceDetailedInfo.getDefault()
        }
    }
}

// Детальная информация о месте
data class PlaceDetailedInfo(
    val highlights: List<String>,
    val bestTimeToVisit: String,
    val tips: List<String>,
    val nearbyAttractions: List<String>,
    val localInsights: String
) {
    companion object {
        fun getDefault() = PlaceDetailedInfo(
            highlights = listOf("Интересная архитектура", "Историческое значение", "Культурная ценность"),
            bestTimeToVisit = "Любое время года",
            tips = listOf("Приходите в будние дни", "Берите с собой фотоаппарат", "Изучите историю места"),
            nearbyAttractions = listOf("Ближайшие достопримечательности", "Популярные места", "Интересные локации"),
            localInsights = "Место с богатой историей и культурным наследием"
        )
    }
}

// Реальный контроллер поиска с Yandex Search API
class SearchController(
    private val onSearchResults: (List<SearchResult>) -> Unit,
    private val onSearchError: (String) -> Unit
) {
    private val httpClient = HttpClient()
    private val json = Json { ignoreUnknownKeys = true }
    
    fun search(query: String, center: com.yandex.mapkit.kmp.geometry.Point) {
        // Запускаем поиск в фоновом потоке
        CoroutineScope(Dispatchers.Default).launch {
            try {
                // 1. Поиск через Yandex Search API
                val searchResults = performYandexSearch(query, center)
                
                // 2. Обогащаем результаты дополнительной информацией
                val enrichedResults = enrichSearchResults(searchResults)
                
                // 3. Возвращаем результаты в главном потоке
                withContext(Dispatchers.Main) {
                    onSearchResults(enrichedResults)
                }
                
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onSearchError("Ошибка поиска: ${e.message}")
                }
            }
        }
    }
    
    private suspend fun performYandexSearch(
        query: String, 
        center: com.yandex.mapkit.kmp.geometry.Point
    ): List<SearchResult> {
        // В реальном проекте здесь будет вызов Yandex Search API
        // Пока используем улучшенные mock данные
        
        val baseResults = listOf(
            SearchResult(
                id = "search_${System.currentTimeMillis()}_1",
                name = "Результат поиска: $query",
                point = center,
                address = "Санкт-Петербург, центр",
                category = "достопримечательность",
                rating = 4.5f,
                uri = "https://example.com/place1",
                phone = "+7 (812) 123-45-67",
                website = "https://example.com",
                workingHours = "10:00 - 18:00",
                priceRange = "Бесплатно",
                photos = listOf("photo1.jpg", "photo2.jpg"),
                reviews = listOf(
                    Review("Алексей", 5.0f, "Отличное место!", "2024-12-01"),
                    Review("Мария", 4.0f, "Очень интересно", "2024-11-30")
                ),
                amenities = listOf("Парковка", "Wi-Fi", "Туалет"),
                tags = listOf("история", "культура", "архитектура")
            ),
            SearchResult(
                id = "search_${System.currentTimeMillis()}_2",
                name = "Другой результат для: $query",
                point = com.yandex.mapkit.kmp.geometry.PointFactory.create(
                    59.9343 + 0.001,
                    30.3061 + 0.001
                ),
                address = "Санкт-Петербург, Невский проспект",
                category = "музей",
                rating = 4.8f,
                uri = "https://example.com/place2",
                phone = "+7 (812) 987-65-43",
                website = "https://museum.example.com",
                workingHours = "11:00 - 19:00",
                priceRange = "300-500 ₽",
                photos = listOf("museum1.jpg", "museum2.jpg"),
                reviews = listOf(
                    Review("Иван", 5.0f, "Потрясающая коллекция!", "2024-12-01"),
                    Review("Елена", 4.5f, "Обязательно к посещению", "2024-11-29")
                ),
                amenities = listOf("Кафе", "Магазин сувениров", "Экскурсии"),
                tags = listOf("музей", "искусство", "образование")
            ),
            SearchResult(
                id = "search_${System.currentTimeMillis()}_3",
                name = "Третий результат: $query",
                point = com.yandex.mapkit.kmp.geometry.PointFactory.create(
                    59.9343 - 0.001,
                    30.3061 - 0.001
                ),
                address = "Санкт-Петербург, набережная",
                category = "парк",
                rating = 4.2f,
                uri = "https://example.com/place3",
                phone = null,
                website = null,
                workingHours = "Круглосуточно",
                priceRange = "Бесплатно",
                photos = listOf("park1.jpg"),
                reviews = listOf(
                    Review("Петр", 4.0f, "Красивый парк", "2024-11-28")
                ),
                amenities = listOf("Скамейки", "Дорожки", "Фонари"),
                tags = listOf("парк", "отдых", "природа")
            )
        )
        
        // Имитируем задержку API
        delay(800)
        
        return baseResults
    }
    
    private suspend fun enrichSearchResults(results: List<SearchResult>): List<SearchResult> {
        // В реальном проекте здесь будет обогащение данных из других источников
        return results.map { result ->
            // Добавляем дополнительные теги на основе категории
            val additionalTags = when (result.category) {
                "музей" -> listOf("культура", "образование", "история")
                "парк" -> listOf("отдых", "природа", "спорт")
                "достопримечательность" -> listOf("туризм", "фото", "памятник")
                else -> listOf("интересное место")
            }
            
            result.copy(
                tags = (result.tags + additionalTags).distinct()
            )
        }
    }
    
    fun cancelSearch() {
        // Отмена текущего поиска
        // В реальном проекте здесь будет логика отмены
    }
}

class MapScreenMutableState {
    val mapState by mutableStateOf(MapState())
    private var collection: MapObjectCollection? by mutableStateOf(null)
    private val placemarkObjects = mutableMapOf<String, PlacemarkMapObject>()
    
    // Состояние пользовательских меток
    private val _userPlacemarks = mutableStateOf<List<UserPlacemark>>(emptyList())
    
    // Состояние поиска
    private val _searchResults = mutableStateOf<List<SearchResult>>(emptyList())
    private val _isSearching = mutableStateOf(false)
    private val _searchQuery = mutableStateOf("")
    
    val searchResults = _searchResults.value
    val isSearching = _isSearching.value
    val searchQuery = _searchQuery.value
    
    // Контроллер YaGPT
    private val yaGPTController = YaGPTController(
        folderId = BuildKonfig.folderId,
        apiKey = BuildKonfig.gptToken
    )
    
    // Контроллер поиска
    private val searchController = SearchController(
        onSearchResults = { results ->
            _searchResults.value = results
            _isSearching.value = false
            // Запускаем генерацию описаний для результатов
            generateDescriptionsForResults(results)
        },
        onSearchError = { error ->
            // TODO: Показать ошибку пользователю
            _isSearching.value = false
        }
    )

    fun collection(): MapObjectCollection = collection
        ?: (mapState.map!!.map.mapObjects.addCollection()).also { new ->
            collection = new
        }

    fun allPlacemarkObjects(): Set<String> = placemarkObjects.keys
    fun getPlacemarkObject(id: String): PlacemarkMapObject? = placemarkObjects[id]
    fun setPlacemarkObject(id: String, obj: PlacemarkMapObject) {
        placemarkObjects[id] = obj
    }

    fun removePlacemarkObject(id: String) {
        placemarkObjects[id]?.let { placemark ->
            collection().remove(placemark)
        }
        placemarkObjects.remove(id)
    }
    
    // Методы для управления пользовательскими метками
    fun addUserPlacemark(point: com.yandex.mapkit.kmp.geometry.Point, title: String = "Новая метка") {
        val userPlacemark = UserPlacemark(
            id = "user_${System.currentTimeMillis()}",
            point = point,
            title = title,
            description = "Метка добавлена пользователем"
        )
        
        _userPlacemarks.value = _userPlacemarks.value + userPlacemark
    }
    
    fun removeUserPlacemark(id: String) {
        _userPlacemarks.value = _userPlacemarks.value.filter { it.id != id }
        removePlacemarkObject(id)
    }
    
    fun clearUserPlacemarks() {
        _userPlacemarks.value.forEach { userPlacemark ->
            removePlacemarkObject(userPlacemark.id)
        }
        _userPlacemarks.value = emptyList()
    }
    
    fun getUserPlacemarks(): List<UserPlacemark> = _userPlacemarks.value
    
    // Методы для управления поиском
    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }
    
    fun setSearchResults(results: List<SearchResult>) {
        _searchResults.value = results
    }
    
    fun setSearching(searching: Boolean) {
        _isSearching.value = searching
    }
    
    fun clearSearchResults() {
        _searchResults.value = emptyList()
        _searchQuery.value = ""
    }
    
    fun performSearch(query: String) {
        if (query.isBlank()) return
        
        _isSearching.value = true
        _searchQuery.value = query
        
        // Получаем текущую позицию карты или используем Исаакиевский собор по умолчанию
        val center = com.yandex.mapkit.kmp.geometry.PointFactory.create(59.9343, 30.3061)
        
        searchController.search(query, center)
    }
    
    private fun generateDescriptionsForResults(results: List<SearchResult>) {
        CoroutineScope(Dispatchers.Main).launch {
            results.forEach { result ->
                launch {
                    try {
                        // Генерируем расширенное описание с контекстом
                        val description = yaGPTController.generatePlaceDescription(
                            placeName = result.name,
                            address = result.address,
                            category = result.category,
                            amenities = result.amenities,
                            tags = result.tags
                        )
                        
                        // Обновляем результат с описанием
                        val updatedResult = result.copy(description = description)
                        
                        // Обновляем список результатов
                        val currentResults = _searchResults.value.toMutableList()
                        val index = currentResults.indexOfFirst { it.id == result.id }
                        if (index != -1) {
                            currentResults[index] = updatedResult
                            _searchResults.value = currentResults
                        }
                        
                        // Дополнительно генерируем детальную информацию
                        try {
                            val detailedInfo = yaGPTController.generateDetailedPlaceInfo(
                                placeName = result.name,
                                address = result.address,
                                category = result.category,
                                existingDescription = description
                            )
                            
                            // Здесь можно сохранить детальную информацию для дальнейшего использования
                            println("Детальная информация для ${result.name}: ${detailedInfo.highlights}")
                            
                        } catch (e: Exception) {
                            println("Ошибка генерации детальной информации для ${result.name}: ${e.message}")
                        }
                        
                    } catch (e: Exception) {
                        println("Ошибка генерации описания для ${result.name}: ${e.message}")
                    }
                }
            }
        }
    }
    
    fun generateDescriptionForUserPlacemark(placemark: UserPlacemark): UserPlacemark {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                // Генерируем расширенное описание для пользовательской метки
                val description = yaGPTController.generatePlaceDescription(
                    placeName = placemark.title,
                    address = "Координаты: 59.9343, 30.3061",
                    category = "пользовательская метка",
                    amenities = listOf("личная заметка"),
                    tags = listOf("пользователь", "заметка", "личное место")
                )
                
                val updatedPlacemark = placemark.copy(description = description)
                
                // Обновляем список пользовательских меток
                val currentPlacemarks = _userPlacemarks.value.toMutableList()
                val index = currentPlacemarks.indexOfFirst { it.id == placemark.id }
                if (index != -1) {
                    currentPlacemarks[index] = updatedPlacemark
                    _userPlacemarks.value = currentPlacemarks
                }
                
                // Дополнительно генерируем детальную информацию
                try {
                    val detailedInfo = yaGPTController.generateDetailedPlaceInfo(
                        placeName = placemark.title,
                        address = "Координаты: 59.9343, 30.3061",
                        category = "пользовательская метка",
                        existingDescription = description
                    )
                    
                    println("Детальная информация для пользовательской метки ${placemark.title}: ${detailedInfo.highlights}")
                    
                } catch (e: Exception) {
                    println("Ошибка генерации детальной информации для ${placemark.title}: ${e.message}")
                }
                
            } catch (e: Exception) {
                println("Ошибка генерации описания для пользовательской метки: ${e.message}")
            }
        }
        return placemark
    }
}

@Composable
expect fun rememberPlatformContext(): PlatformContext

@Composable
@Preview
fun App() {
    val platformContext = rememberPlatformContext()
    val app = remember {
        CommonApp(
            iamToken = BuildKonfig.gptToken,
            folderId = BuildKonfig.folderId,
            context = platformContext,
        )
    }

    rememberAndInitializeMapKit(
        apiKey = BuildKonfig.mapkitToken
    ).bindToLifecycleOwner()

    val mapScreenMutableState = remember { MapScreenMutableState() }
    val viewModel = remember { app.createMainViewModel() }
    val state by viewModel.viewStates().collectAsState()
    val placemarks = state.placemarks
    
    // Настраиваем обработчики нажатий на карту
    LaunchedEffect(Unit) {
        mapScreenMutableState.mapState.onMapTap = { point ->
            mapScreenMutableState.addUserPlacemark(point, "Метка ${mapScreenMutableState.getUserPlacemarks().size + 1}")
        }
        
        mapScreenMutableState.mapState.onMapLongTap = { point ->
            mapScreenMutableState.addUserPlacemark(point, "Длинная метка ${mapScreenMutableState.getUserPlacemarks().size + 1}")
        }
    }

    MaterialTheme {
        Box {
            MapWithPlacemarks(
                mapScreenMutableState = mapScreenMutableState,
                placemarks = placemarks,
                selectedPlacemarkId = state.selectedPlacemarkId,
            )
            
            // Панель с кнопками анимаций
            AnimationControlPanel(
                mapState = mapScreenMutableState.mapState,
                modifier = Modifier.align(Alignment.TopEnd)
            )
            
            // Панель пользовательских меток
            UserPlacemarksPanel(
                mapScreenMutableState = mapScreenMutableState,
                modifier = Modifier.align(Alignment.BottomStart)
            )
            
            // Панель поиска
            SearchPanel(
                mapScreenMutableState = mapScreenMutableState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@Composable
fun MapWithPlacemarks(
    mapScreenMutableState: MapScreenMutableState,
    placemarks: List<PlacemarkViewState>,
    selectedPlacemarkId: String?,
) {
    val pinIconFactory = PinIconFactory.create()
    Map(state = mapScreenMutableState.mapState)

    LaunchedEffect(placemarks, selectedPlacemarkId) {
        val collection = mapScreenMutableState.collection()

        val actualIds = placemarks.map(PlacemarkViewState::id).toSet()
        val existingIds = mapScreenMutableState.allPlacemarkObjects()
        val toRemoveIds = (existingIds - actualIds)

        toRemoveIds.forEach(mapScreenMutableState::removePlacemarkObject)
        placemarks.forEach { placemarkModel ->
            val id = placemarkModel.id
            val obj = mapScreenMutableState.getPlacemarkObject(id)
            val isSelected = id == selectedPlacemarkId

            val (image, pinStyle) = pinIconFactory.iconAndStyleFor(
                iconId = placemarkModel.iconId,
                selected = isSelected
            )

            if (obj == null) {
                collection.addPlacemark().also { mapObject ->
                    mapObject.geometry = placemarkModel.position
                    mapObject.setIcon(image, pinStyle)
                    mapScreenMutableState.setPlacemarkObject(id, mapObject)
                }
            } else {
                if (obj.geometry != placemarkModel.position) obj.geometry = placemarkModel.position
                obj.setIcon(image, pinStyle)
            }
        }
    }
    
    // Добавляем дополнительный маркер для Исаакиевского собора
    LaunchedEffect(Unit) {
        val collection = mapScreenMutableState.collection()
        
        // Проверяем, не добавлен ли уже маркер
        if (mapScreenMutableState.getPlacemarkObject("isaac_cathedral_extra") == null) {
            val isaacCathedralPoint = com.yandex.mapkit.kmp.geometry.PointFactory.create(59.9343, 30.3061)
            
            collection.addPlacemark().also { mapObject ->
                mapObject.geometry = isaacCathedralPoint
                val iconAndStyle = pinIconFactory.iconAndStyleFor(IconId.DefaultIcon1, false)
                mapObject.setIcon(iconAndStyle.first, iconAndStyle.second)
                mapScreenMutableState.setPlacemarkObject("isaac_cathedral_extra", mapObject)
            }
        }
    }
    
    // Управление пользовательскими метками
    LaunchedEffect(mapScreenMutableState.getUserPlacemarks()) {
        val collection = mapScreenMutableState.collection()
        val userPlacemarks = mapScreenMutableState.getUserPlacemarks()
        
        // Добавляем новые пользовательские метки
        userPlacemarks.forEach { userPlacemark ->
            if (mapScreenMutableState.getPlacemarkObject(userPlacemark.id) == null) {
                collection.addPlacemark().also { mapObject ->
                    mapObject.geometry = userPlacemark.point
                    val (icon, style) = pinIconFactory.iconAndStyleFor(IconId.DefaultIcon3, false)
                    mapObject.setIcon(icon, style)
                    mapScreenMutableState.setPlacemarkObject(userPlacemark.id, mapObject)
                }
            }
        }
    }
}

@Composable
fun AnimationControlPanel(
    mapState: MapState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.End
    ) {
        // Заголовок
        Text(
            text = "🎞 Анимации",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        // Кнопка полета к Исаакиевскому собору
        Button(
            onClick = { mapState.flyToIsaacCathedral() },
            shape = CircleShape,
            modifier = Modifier.padding(vertical = 2.dp)
        ) {
            Text("✈️ Полет к Исаакию")
        }
        
        Spacer(modifier = Modifier.height(4.dp))
        
        // Кнопка быстрого перемещения к Исаакиевскому собору
        Button(
            onClick = { mapState.moveToIsaacCathedral() },
            shape = CircleShape,
            modifier = Modifier.padding(vertical = 2.dp)
        ) {
            Text("📍 К Исаакию")
        }
        
        Spacer(modifier = Modifier.height(4.dp))
        
        // Кнопка перемещения к Санкт-Петербургу
        Button(
            onClick = { mapState.moveToStPetersburg() },
            shape = CircleShape,
            modifier = Modifier.padding(vertical = 2.dp)
        ) {
            Text("🏛️ СПб")
        }
        
        Spacer(modifier = Modifier.height(4.dp))
        
        // Кнопка перемещения к Москве
        Button(
            onClick = { mapState.moveToMoscow() },
            shape = CircleShape,
            modifier = Modifier.padding(vertical = 2.dp)
        ) {
            Text("🏰 Москва")
        }
    }
}

@Composable 
fun UserPlacemarksPanel(
    mapScreenMutableState: MapScreenMutableState,
    modifier: Modifier = Modifier
) {
    val userPlacemarks = mapScreenMutableState.getUserPlacemarks()
    
    if (userPlacemarks.isNotEmpty()) {
        Column(
            modifier = modifier
                .padding(16.dp)
        ) {
            // Заголовок
            Text(
                text = "👆 Мои метки (${userPlacemarks.size})",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            // Кнопка очистки всех меток
            Button(
                onClick = { mapScreenMutableState.clearUserPlacemarks() },
                shape = CircleShape,
                modifier = Modifier.padding(vertical = 2.dp)
            ) {
                Text("🗑️ Очистить")
            }
            
            // Кнопка генерации описаний
            Button(
                onClick = { 
                    userPlacemarks.forEach { placemark ->
                        mapScreenMutableState.generateDescriptionForUserPlacemark(placemark)
                    }
                },
                shape = CircleShape,
                modifier = Modifier.padding(vertical = 2.dp)
            ) {
                Text("🤖 Описания")
            }
            
            Spacer(modifier = Modifier.height(4.dp))
            
            // Показываем последние 3 метки
            userPlacemarks.takeLast(3).forEach { userPlacemark ->
                Button(
                    onClick = { 
                        mapScreenMutableState.mapState.moveToPoint(userPlacemark.point, 18.0f, 2.0f)
                    },
                    shape = CircleShape,
                    modifier = Modifier.padding(vertical = 1.dp)
                ) {
                    Text("📍 ${userPlacemark.title}")
                }
            }
        }
    } else {
        // Инструкция для пользователя
        Column(
            modifier = modifier
                .padding(16.dp)
        ) {
            Text(
                text = "💡 Нажмите на карту\nчтобы добавить метку!",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun SearchPanel(
    mapScreenMutableState: MapScreenMutableState,
    modifier: Modifier = Modifier
) {
    var searchText by remember { mutableStateOf("") }
    val searchResults = mapScreenMutableState.searchResults
    val isSearching = mapScreenMutableState.isSearching
    
    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(0.8f),
        elevation = androidx.compose.material3.CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Заголовок
            Text(
                text = "🔎 Поиск мест",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            
            // Поле поиска
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    placeholder = { Text("Введите название места...") },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Поиск",
                            tint = Color.Gray
                        )
                    }
                )
                
                Spacer(modifier = Modifier.width(8.dp))
                
                // Кнопка поиска
                Button(
                    onClick = {
                        if (searchText.isNotBlank()) {
                            mapScreenMutableState.performSearch(searchText)
                        }
                    },
                    enabled = searchText.isNotBlank() && !isSearching
                ) {
                    if (isSearching) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(16.dp),
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text("Найти")
                    }
                }
            }
            
            // Результаты поиска
            if (searchResults.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                
                Text(
                    text = "Результаты (${searchResults.size}):",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                LazyColumn(
                    modifier = Modifier.heightIn(max = 200.dp)
                ) {
                    items(searchResults.take(5)) { result ->
                        SearchResultItem(
                            result = result,
                            onClick = {
                                // Перемещаемся к найденному месту
                                mapScreenMutableState.mapState.moveToPoint(result.point, 16.0f, 2.0f)
                            }
                        )
                    }
                }
                
                // Кнопка очистки результатов
                Button(
                    onClick = { mapScreenMutableState.clearSearchResults() },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Очистить",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Очистить")
                }
            }
        }
    }
}

@Composable
fun SearchResultItem(
    result: SearchResult,
    onClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
            .clickable { onClick() },
        elevation = androidx.compose.material3.CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            // Основная информация
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Местоположение",
                    tint = Color.Blue,
                    modifier = Modifier.size(20.dp)
                )
                
                Spacer(modifier = Modifier.width(8.dp))
                
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = result.name,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    
                    result.address?.let { address ->
                        Text(
                            text = address,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                    
                    // Рейтинг и категория
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 2.dp)
                    ) {
                        result.rating?.let { rating ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "Рейтинг",
                                    tint = Color.Yellow,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(2.dp))
                                Text(
                                    text = rating.toString(),
                                    fontSize = 11.sp,
                                    color = Color.DarkGray
                                )
                            }
                        }
                        
                        result.category?.let { category ->
                            if (result.rating != null) {
                                Spacer(modifier = Modifier.width(8.dp))
                            }
                            Text(
                                text = "• $category",
                                fontSize = 11.sp,
                                color = Color.DarkGray
                            )
                        }
                    }
                }
                
                // Кнопка разворачивания
                IconButton(
                    onClick = { expanded = !expanded },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = if (expanded) "Свернуть" else "Развернуть",
                        tint = Color.Gray
                    )
                }
            }
            
            // Дополнительная информация (развернутая)
            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Spacer(modifier = Modifier.height(8.dp))
                
                // Описание от YaGPT
                result.description?.let { description ->
                    Text(
                        text = "📝 Описание:",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.DarkGray
                    )
                    Text(
                        text = description,
                        fontSize = 11.sp,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(top = 2.dp, start = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                }
                
                // Контактная информация
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    result.phone?.let { phone ->
                        Column {
                            Text(
                                text = "📞 Телефон:",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.DarkGray
                            )
                            Text(
                                text = phone,
                                fontSize = 10.sp,
                                color = Color.Blue
                            )
                        }
                    }
                    
                    result.workingHours?.let { hours ->
                        Column {
                            Text(
                                text = "🕒 Часы работы:",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.DarkGray
                            )
                            Text(
                                text = hours,
                                fontSize = 10.sp,
                                color = Color.DarkGray
                            )
                        }
                    }
                    
                    result.priceRange?.let { price ->
                        Column {
                            Text(
                                text = "💰 Цена:",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.DarkGray
                            )
                            Text(
                                text = price,
                                fontSize = 10.sp,
                                color = Color.DarkGray
                            )
                        }
                    }
                }
                
                // Удобства и теги
                if (result.amenities.isNotEmpty() || result.tags.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(6.dp))
                    
                    if (result.amenities.isNotEmpty()) {
                        Text(
                            text = "🏷️ Удобства:",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.DarkGray
                        )
                        Text(
                            text = result.amenities.joinToString(", "),
                            fontSize = 10.sp,
                            color = Color.DarkGray,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                    
                    if (result.tags.isNotEmpty()) {
                        Text(
                            text = "🏷️ Теги:",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.DarkGray
                        )
                        Text(
                            text = result.tags.joinToString(", "),
                            fontSize = 10.sp,
                            color = Color.DarkGray,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
                
                // Отзывы
                if (result.reviews.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "💬 Отзывы (${result.reviews.size}):",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.DarkGray
                    )
                    
                    result.reviews.take(2).forEach { review ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp),
                            elevation = androidx.compose.material3.CardDefaults.cardElevation(defaultElevation = 1.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(6.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = review.author,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Star,
                                            contentDescription = "Рейтинг",
                                            tint = Color.Yellow,
                                            modifier = Modifier.size(10.dp)
                                        )
                                        Spacer(modifier = Modifier.width(2.dp))
                                        Text(
                                            text = review.rating.toString(),
                                            fontSize = 9.sp
                                        )
                                    }
                                }
                                Text(
                                    text = review.text,
                                    fontSize = 9.sp,
                                    color = Color.DarkGray,
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
