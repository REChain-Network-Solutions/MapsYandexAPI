# 🏗️ Техническая документация Yandex Maps Workshop

## 📋 Содержание

1. [Архитектура приложения](#архитектура-приложения)
2. [Компоненты системы](#компоненты-системы)
3. [API документация](#api-документация)
4. [Состояния и жизненный цикл](#состояния-и-жизненный-цикл)
5. [Обработка ошибок](#обработка-ошибок)
6. [Производительность](#производительность)
7. [Безопасность](#безопасность)

---

## 🏗️ Архитектура приложения

### Общая структура

```
┌─────────────────────────────────────────────────────────────┐
│                        UI Layer                            │
├─────────────────────────────────────────────────────────────┤
│                    Business Logic                          │
├─────────────────────────────────────────────────────────────┤
│                     Data Layer                             │
├─────────────────────────────────────────────────────────────┤
│                  External Services                         │
└─────────────────────────────────────────────────────────────┘
```

### Компонентная архитектура

```kotlin
@Composable
fun App() {
    // 1. Инициализация сервисов
    val platformContext = rememberPlatformContext()
    val app = remember { CommonApp(...) }
    
    // 2. Инициализация MapKit
    rememberAndInitializeMapKit(apiKey = BuildKonfig.mapkitToken)
        .bindToLifecycleOwner()
    
    // 3. Состояние приложения
    val mapScreenMutableState = remember { MapScreenMutableState() }
    
    // 4. Основные компоненты
    MaterialTheme {
        Box {
            MapWithPlacemarks(...)
            AnimationControlPanel(...)
            UserPlacemarksPanel(...)
            SearchPanel(...)
        }
    }
}
```

### Слои приложения

#### **Presentation Layer (UI)**
- **Compose Multiplatform** - декларативный UI
- **Material Design 3** - дизайн система
- **Responsive Layout** - адаптивная верстка

#### **Business Logic Layer**
- **MapScreenMutableState** - управление состоянием
- **SearchController** - логика поиска
- **YaGPTController** - интеграция с ИИ

#### **Data Layer**
- **MapState** - состояние карты
- **UserPlacemark** - пользовательские метки
- **SearchResult** - результаты поиска

#### **External Services Layer**
- **Yandex MapKit** - картографические сервисы
- **YaGPT API** - генерация описаний
- **HTTP Client** - сетевые запросы

---

## 🔧 Компоненты системы

### MapState

Управляет состоянием карты и анимациями.

```kotlin
class MapState(val coordinates: Point? = null) {
    var map by mutableStateOf<MapWindow?>(null)
    var onMapTap: ((Point) -> Unit)? = null
    var onMapLongTap: ((Point) -> Unit)? = null
    
    // Анимации перемещения
    fun moveToPoint(point: Point, zoom: Float = 16.0f, duration: Float = 2.0f)
    fun flyToIsaacCathedral()
    fun moveToIsaacCathedral()
    fun moveToStPetersburg()
    fun moveToMoscow()
    
    // Настройка обработчиков
    fun setupInputListener()
}
```

### MapScreenMutableState

Центральное состояние приложения.

```kotlin
class MapScreenMutableState {
    val mapState by mutableStateOf(MapState())
    private var collection: MapObjectCollection? by mutableStateOf(null)
    private val placemarkObjects = mutableMapOf<String, PlacemarkMapObject>()
    
    // Пользовательские метки
    private val _userPlacemarks = mutableStateOf<List<UserPlacemark>>(emptyList())
    
    // Поиск
    private val _searchResults = mutableStateOf<List<SearchResult>>(emptyList())
    private val _isSearching = mutableStateOf(false)
    private val _searchQuery = mutableStateOf("")
    
    // Контроллеры
    private val yaGPTController = YaGPTController(...)
    private val searchController = SearchController(...)
    
    // Методы управления
    fun addUserPlacemark(point: Point, title: String)
    fun removeUserPlacemark(id: String)
    fun clearUserPlacemarks()
    fun performSearch(query: String)
    fun generateDescriptionForUserPlacemark(placemark: UserPlacemark)
}
```

### YaGPTController

Управляет взаимодействием с YaGPT API.

```kotlin
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
        address: String?
    ): String {
        // Формирование промпта
        val systemPrompt = """
            Ты - эксперт по туризму и истории. Создай интересное и 
            информативное описание места для туристического приложения.
        """.trimIndent()
        
        val userPrompt = "Создай описание для места: $placeName (адрес: $address)"
        
        // Создание запроса
        val request = YaGPTRequest(
            modelUri = "gpt://$folderId/yandexgpt-lite",
            completionOptions = CompletionOptions(
                temperature = 0.7,
                maxTokens = "500"
            ),
            messages = listOf(
                Message("system", systemPrompt),
                Message("user", userPrompt)
            )
        )
        
        // Отправка запроса
        val response = httpClient.post("https://llm.api.cloud.yandex.net/foundationModels/v1/completion") {
            headers {
                append("Authorization", "Api-Key $apiKey")
                append("x-folder-id", folderId)
            }
            contentType(ContentType.Application.Json)
            setBody(json.encodeToString(YaGPTRequest.serializer(), request))
        }
        
        // Обработка ответа
        return json.decodeFromString<YaGPTResponse>(response.body())
            .result.alternatives.firstOrNull()?.message?.text 
            ?: "Не удалось получить описание"
    }
}
```

### SearchController

Управляет поиском мест (текущая реализация - mock).

```kotlin
class SearchController(
    private val onSearchResults: (List<SearchResult>) -> Unit,
    private val onSearchError: (String) -> Unit
) {
    fun search(query: String, center: Point) {
        // Создание mock результатов
        val mockResults = listOf(
            SearchResult(
                id = "mock_1",
                name = "Результат поиска: $query",
                point = center,
                address = "Адрес не найден",
                category = "demo"
            ),
            SearchResult(
                id = "mock_2", 
                name = "Другой результат для: $query",
                point = PointFactory.create(
                    center.latitude + 0.001,
                    center.longitude + 0.001
                ),
                address = "Тестовый адрес",
                category = "demo"
            )
        )
        
        // Имитация задержки поиска
        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            onSearchResults(mockResults)
        }
    }
    
    fun cancelSearch() {
        // Отмена текущего поиска
    }
}
```

---

## 📡 API документация

### YaGPT API

#### **Endpoint**
```
POST https://llm.api.cloud.yandex.net/foundationModels/v1/completion
```

#### **Headers**
```http
Authorization: Api-Key {YOUR_API_KEY}
x-folder-id: {YOUR_FOLDER_ID}
Content-Type: application/json
```

#### **Request Body**
```json
{
  "modelUri": "gpt://{folderId}/yandexgpt-lite",
  "completionOptions": {
    "stream": false,
    "temperature": 0.7,
    "maxTokens": "500"
  },
  "messages": [
    {
      "role": "system",
      "text": "Ты - эксперт по туризму и истории..."
    },
    {
      "role": "user", 
      "text": "Создай описание для места: {placeName}"
    }
  ]
}
```

#### **Response**
```json
{
  "result": {
    "alternatives": [
      {
        "message": {
          "role": "assistant",
          "text": "Описание места..."
        },
        "status": "ALTERNATIVE_STATUS_FINAL"
      }
    ],
    "usage": {
      "inputTextTokens": "100",
      "completionTokens": "150", 
      "totalTokens": "250"
    },
    "modelVersion": "lite"
  }
}
```

### Yandex MapKit API

#### **Инициализация**
```kotlin
rememberAndInitializeMapKit(apiKey = BuildKonfig.mapkitToken)
    .bindToLifecycleOwner()
```

#### **Управление камерой**
```kotlin
// Создание позиции камеры
val position = CameraPositionFactory.create(
    target = PointFactory.create(lat, lon),
    zoom = 16.0f,
    azimuth = 0.0f,
    tilt = 0.0f
)

// Перемещение камеры
mapWindow.map.move(position)

// Анимированное перемещение
val animation = AnimationFactory.create(
    type = AnimationType.SMOOTH,
    duration = 2.0f
)
mapWindow.map.move(position, animation, null)
```

#### **Добавление меток**
```kotlin
val collection = mapWindow.map.mapObjects.addCollection()
val placemark = collection.addPlacemark()
placemark.geometry = PointFactory.create(lat, lon)
placemark.setIcon(image, style)
```

---

## 🔄 Состояния и жизненный цикл

### Жизненный цикл приложения

```kotlin
@Composable
fun App() {
    // 1. Инициализация
    val platformContext = rememberPlatformContext()
    val app = remember { CommonApp(...) }
    
    // 2. Настройка MapKit
    rememberAndInitializeMapKit(apiKey = BuildKonfig.mapkitToken)
        .bindToLifecycleOwner()
    
    // 3. Создание состояния
    val mapScreenMutableState = remember { MapScreenMutableState() }
    
    // 4. Настройка обработчиков
    LaunchedEffect(Unit) {
        mapScreenMutableState.mapState.onMapTap = { point ->
            mapScreenMutableState.addUserPlacemark(point, "Метка ${...}")
        }
        
        mapScreenMutableState.mapState.onMapLongTap = { point ->
            mapScreenMutableState.addUserPlacemark(point, "Длинная метка ${...}")
        }
    }
    
    // 5. Рендеринг UI
    MaterialTheme {
        Box {
            MapWithPlacemarks(...)
            AnimationControlPanel(...)
            UserPlacemarksPanel(...)
            SearchPanel(...)
        }
    }
}
```

### Управление состоянием карты

```kotlin
@Composable
fun MapWithPlacemarks(
    mapScreenMutableState: MapScreenMutableState,
    placemarks: List<PlacemarkViewState>,
    selectedPlacemarkId: String?
) {
    val pinIconFactory = PinIconFactory.create()
    Map(state = mapScreenMutableState.mapState)

    // Обработка стандартных меток
    LaunchedEffect(placemarks, selectedPlacemarkId) {
        val collection = mapScreenMutableState.collection()
        
        // Удаление старых меток
        val actualIds = placemarks.map(PlacemarkViewState::id).toSet()
        val existingIds = mapScreenMutableState.allPlacemarkObjects()
        val toRemoveIds = (existingIds - actualIds)
        
        toRemoveIds.forEach(mapScreenMutableState::removePlacemarkObject)
        
        // Обновление/создание меток
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
                if (obj.geometry != placemarkModel.position) {
                    obj.geometry = placemarkModel.position
                }
                obj.setIcon(image, pinStyle)
            }
        }
    }
    
    // Добавление метки Исаакиевского собора
    LaunchedEffect(Unit) {
        val collection = mapScreenMutableState.collection()
        
        if (mapScreenMutableState.getPlacemarkObject("isaac_cathedral_extra") == null) {
            val isaacCathedralPoint = PointFactory.create(59.9343, 30.3061)
            
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
```

---

## ⚠️ Обработка ошибок

### Обработка ошибок YaGPT

```kotlin
suspend fun generatePlaceDescription(placeName: String, address: String?): String {
    return try {
        // ... логика запроса ...
        
        val response = httpClient.post("https://llm.api.cloud.yandex.net/foundationModels/v1/completion") {
            // ... настройка запроса ...
        }
        
        val yaGPTResponse = json.decodeFromString<YaGPTResponse>(response.body())
        yaGPTResponse.result.alternatives.firstOrNull()?.message?.text 
            ?: "Не удалось получить описание"
            
    } catch (e: Exception) {
        println("Ошибка YaGPT: ${e.message}")
        "Описание будет добавлено позже..."
    }
}
```

### Обработка ошибок поиска

```kotlin
private val searchController = SearchController(
    onSearchResults = { results ->
        _searchResults.value = results
        _isSearching.value = false
        generateDescriptionsForResults(results)
    },
    onSearchError = { error ->
        println("Ошибка поиска: $error")
        _isSearching.value = false
        // TODO: Показать ошибку пользователю
    }
)
```

### Обработка ошибок карты

```kotlin
fun moveToPoint(point: Point, zoom: Float = 16.0f, duration: Float = 2.0f) {
    try {
        map?.let { mapWindow ->
            val animation = AnimationFactory.create(
                type = AnimationType.SMOOTH, 
                duration = duration
            )
            val position = CameraPositionFactory.create(
                target = point, 
                zoom = zoom
            )
            mapWindow.map.move(position, animation, null)
        }
    } catch (e: Exception) {
        println("Ошибка перемещения карты: ${e.message}")
    }
}
```

---

## ⚡ Производительность

### Оптимизация анимаций

```kotlin
// Использование плавных анимаций
val animation = AnimationFactory.create(
    type = AnimationType.SMOOTH,  // Плавная анимация
    duration = 2.0f               // Оптимальная длительность
)

// Цепочка анимаций для сложных переходов
fun flyToIsaacCathedral() {
    map?.let { mapWindow ->
        // 1. Быстрый переход к общему виду
        val overviewPosition = CameraPositionFactory.create(
            target = PointFactory.create(59.9343, 30.3061),
            zoom = 8.0f
        )
        mapWindow.map.move(overviewPosition, null, null)
        
        // 2. Плавное приближение к цели
        Handler(Looper.getMainLooper()).postDelayed({
            val targetPosition = CameraPositionFactory.create(
                target = PointFactory.create(59.9343, 30.3061),
                zoom = 16.0f
            )
            val animation = AnimationFactory.create(
                type = AnimationType.SMOOTH,
                duration = 3.0f
            )
            mapWindow.map.move(targetPosition, animation, null)
        }, 500)
    }
}
```

### Управление памятью

```kotlin
// Очистка ресурсов при удалении меток
fun removePlacemarkObject(id: String) {
    placemarkObjects[id]?.let { placemark ->
        collection()?.remove(placemark)
    }
    placemarkObjects.remove(id)
}

// Очистка всех пользовательских меток
fun clearUserPlacemarks() {
    _userPlacemarks.value.forEach { userPlacemark ->
        removePlacemarkObject(userPlacemark.id)
    }
    _userPlacemarks.value = emptyList()
}
```

### Асинхронные операции

```kotlin
// Использование корутин для тяжелых операций
private fun generateDescriptionsForResults(results: List<SearchResult>) {
    CoroutineScope(Dispatchers.Main).launch {
        results.forEach { result ->
            launch {
                try {
                    val description = yaGPTController.generatePlaceDescription(
                        placeName = result.name,
                        address = result.address
                    )
                    
                    // Обновление UI в главном потоке
                    val updatedResult = result.copy(description = description)
                    updateSearchResult(updatedResult)
                    
                } catch (e: Exception) {
                    println("Ошибка генерации описания: ${e.message}")
                }
            }
        }
    }
}
```

---

## 🔒 Безопасность

### Защита API ключей

```kotlin
// Использование BuildKonfig для безопасного хранения ключей
buildkonfig {
    val folderId: String by rootProject.extra
    val gptToken: String by rootProject.extra
    val mapkitToken: String by rootProject.extra
    
    defaultConfigs {
        buildConfigField(FieldSpec.Type.STRING, "folderId", folderId)
        buildConfigField(FieldSpec.Type.STRING, "gptToken", gptToken)
        buildConfigField(FieldSpec.Type.STRING, "mapkitToken", mapkitToken)
    }
}

// В коде
private val yaGPTController = YaGPTController(
    folderId = BuildKonfig.folderId,
    apiKey = BuildKonfig.gptToken
)
```

### Валидация входных данных

```kotlin
fun performSearch(query: String) {
    // Проверка на пустые запросы
    if (query.isBlank()) return
    
    // Ограничение длины запроса
    if (query.length > 100) {
        println("Слишком длинный поисковый запрос")
        return
    }
    
    _isSearching.value = true
    _searchQuery.value = query
    
    val center = PointFactory.create(59.9343, 30.3061)
    searchController.search(query, center)
}
```

### Безопасность сетевых запросов

```kotlin
// Использование HTTPS
val response = httpClient.post("https://llm.api.cloud.yandex.net/foundationModels/v1/completion") {
    // Проверка заголовков
    headers {
        append("Authorization", "Api-Key $apiKey")
        append("x-folder-id", folderId)
    }
    
    // Установка типа контента
    contentType(ContentType.Application.Json)
    
    // Валидация тела запроса
    setBody(json.encodeToString(YaGPTRequest.serializer(), request))
}
```

---

## 📊 Мониторинг и логирование

### Логирование операций

```kotlin
// Логирование создания меток
fun addUserPlacemark(point: Point, title: String = "Новая метка") {
    println("Создание пользовательской метки: $title в точке (${point.latitude}, ${point.longitude})")
    
    val userPlacemark = UserPlacemark(
        id = "user_${System.currentTimeMillis()}",
        point = point,
        title = title,
        description = "Метка добавлена пользователем"
    )
    
    _userPlacemarks.value = _userPlacemarks.value + userPlacemark
    println("Метка создана с ID: ${userPlacemark.id}")
}

// Логирование анимаций
fun moveToPoint(point: Point, zoom: Float = 16.0f, duration: Float = 2.0f) {
    println("Перемещение карты к точке (${point.latitude}, ${point.longitude}) с зумом $zoom")
    
    try {
        map?.let { mapWindow ->
            val animation = AnimationFactory.create(
                type = AnimationType.SMOOTH, 
                duration = duration
            )
            val position = CameraPositionFactory.create(
                target = point, 
                zoom = zoom
            )
            mapWindow.map.move(position, animation, null)
            println("Анимация запущена успешно")
        }
    } catch (e: Exception) {
        println("Ошибка при перемещении карты: ${e.message}")
    }
}
```

### Метрики производительности

```kotlin
// Измерение времени генерации описаний
private fun generateDescriptionsForResults(results: List<SearchResult>) {
    val startTime = System.currentTimeMillis()
    
    CoroutineScope(Dispatchers.Main).launch {
        val completedCount = results.count { result ->
            launch {
                try {
                    val description = yaGPTController.generatePlaceDescription(
                        placeName = result.name,
                        address = result.address
                    )
                    
                    val updatedResult = result.copy(description = description)
                    updateSearchResult(updatedResult)
                    
                } catch (e: Exception) {
                    println("Ошибка генерации описания для ${result.name}: ${e.message}")
                }
            }
            true
        }
        
        val endTime = System.currentTimeMillis()
        val totalTime = endTime - startTime
        
        println("Генерация описаний завершена: $completedCount из ${results.size} за ${totalTime}ms")
    }
}
```

---

## 🔮 Планы развития

### Краткосрочные цели (1-2 месяца)

- [ ] **Реальная интеграция с Yandex Search API**
- [ ] **Кэширование описаний YaGPT**
- [ ] **Офлайн режим для сохраненных меток**
- [ ] **Экспорт/импорт пользовательских меток**

### Среднесрочные цели (3-6 месяцев)

- [ ] **Поддержка iOS платформы**
- [ ] **Web версия приложения**
- [ ] **Расширенные анимации и эффекты**
- [ ] **Интеграция с социальными сетями**

### Долгосрочные цели (6+ месяцев)

- [ ] **AR/VR поддержка для меток**
- [ ] **Машинное обучение для персонализации**
- [ ] **Интеграция с IoT устройствами**
- [ ] **Мультиязычная поддержка**

---

*Документ обновлен: Декабрь 2024*
