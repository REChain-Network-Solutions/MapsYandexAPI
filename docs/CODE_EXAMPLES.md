# 💻 Примеры кода Yandex Maps Workshop

<div align="center">

![Code Examples](https://img.shields.io/badge/Примеры_кода-FF6B6B?style=for-the-badge&logo=code&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Compose](https://img.shields.io/badge/Compose-4285F4?style=for-the-badge&logo=jetpack-compose&logoColor=white)

**Практические примеры кода для основных функций приложения**

[🗺️ Карта и анимации](#-карта-и-анимации) • [👆 Обработка нажатий](#-обработка-нажатий) • [🔍 Поиск](#-поиск) • [🤖 YaGPT](#-yagpt)

</div>

---

## 🗺️ Карта и анимации

### ✈️ **Полет к Исаакиевскому собору**

```kotlin
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

### 📍 **Быстрые переходы**

```kotlin
fun moveToPoint(point: Point, zoom: Float = 16.0f, duration: Float = 2.0f) {
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
}

fun moveToIsaacCathedral() {
    val isaacPoint = PointFactory.create(59.9343, 30.3061)
    moveToPoint(isaacPoint, 16.0f, 2.0f)
}

fun moveToStPetersburg() {
    val spbPoint = PointFactory.create(59.9311, 30.3609)
    moveToPoint(spbPoint, 10.0f, 1.5f)
}

fun moveToMoscow() {
    val moscowPoint = PointFactory.create(55.7558, 37.6176)
    moveToPoint(moscowPoint, 10.0f, 2.0f)
}
```

---

## 👆 Обработка нажатий

### 🎯 **Настройка обработчиков**

```kotlin
fun setupInputListener() {
    map?.let { mapWindow ->
        val inputListener = object : InputListener {
            override fun onMapTap(map: com.yandex.mapkit.kmp.map.Map, point: Point) {
                onMapTap?.invoke(point)
            }
            
            override fun onMapLongTap(map: com.yandex.mapkit.kmp.map.Map, point: Point) {
                onMapLongTap?.invoke(point)
            }
        }
        mapWindow.map.addInputListener(inputListener)
    }
}
```

### 📍 **Создание пользовательских меток**

```kotlin
fun addUserPlacemark(point: Point, title: String = "Новая метка") {
    val userPlacemark = UserPlacemark(
        id = "user_${System.currentTimeMillis()}",
        point = point,
        title = title,
        description = "Метка добавлена пользователем"
    )
    
    _userPlacemarks.value = _userPlacemarks.value + userPlacemark
}

fun removeUserPlacemark(id: String) {
    placemarkObjects[id]?.let { placemark ->
        collection()?.remove(placemark)
    }
    placemarkObjects.remove(id)
    _userPlacemarks.value = _userPlacemarks.value.filter { it.id != id }
}

fun clearUserPlacemarks() {
    _userPlacemarks.value.forEach { userPlacemark ->
        removePlacemarkObject(userPlacemark.id)
    }
    _userPlacemarks.value = emptyList()
}
```

---

## 🔍 Поиск

### 🔎 **Контроллер поиска**

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
}
```

### 📝 **Выполнение поиска**

```kotlin
fun performSearch(query: String) {
    if (query.isBlank()) return
    
    if (query.length > 100) {
        println("Слишком длинный поисковый запрос")
        return
    }
    
    _isSearching.value = true
    _searchQuery.value = query
    
    val center = PointFactory.create(59.9343, 30.3061)
    searchController.search(query, center)
}

private fun generateDescriptionsForResults(results: List<SearchResult>) {
    CoroutineScope(Dispatchers.Main).launch {
        results.forEach { result ->
            launch {
                try {
                    val description = yaGPTController.generatePlaceDescription(
                        placeName = result.name,
                        address = result.address
                    )
                    
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

## 🤖 YaGPT

### 🧠 **Контроллер YaGPT**

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
        val systemPrompt = """
            Ты - эксперт по туризму и истории. Создай интересное и 
            информативное описание места для туристического приложения.
            Описание должно быть:
            - Кратким (до 3-4 предложений)
            - Интересным и познавательным
            - Содержать исторические факты или интересные особенности
            - Подходящим для туристов
        """.trimIndent()
        
        val userPrompt = "Создай описание для места: $placeName (адрес: $address)"
        
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
        
        val response = httpClient.post("https://llm.api.cloud.yandex.net/foundationModels/v1/completion") {
            headers {
                append("Authorization", "Api-Key $apiKey")
                append("x-folder-id", folderId)
            }
            contentType(ContentType.Application.Json)
            setBody(json.encodeToString(YaGPTRequest.serializer(), request))
        }
        
        return json.decodeFromString<YaGPTResponse>(response.body())
            .result.alternatives.firstOrNull()?.message?.text 
            ?: "Не удалось получить описание"
    }
}
```

### 📊 **Модели данных**

```kotlin
@Serializable
data class YaGPTRequest(
    val modelUri: String,
    val completionOptions: CompletionOptions,
    val messages: List<Message>
)

@Serializable
data class CompletionOptions(
    val stream: Boolean = false,
    val temperature: Float = 0.7f,
    val maxTokens: String = "500"
)

@Serializable
data class Message(
    val role: String,
    val text: String
)

@Serializable
data class YaGPTResponse(
    val result: YaGPTResult
)

@Serializable
data class YaGPTResult(
    val alternatives: List<Alternative>,
    val usage: Usage,
    val modelVersion: String
)

@Serializable
data class Alternative(
    val message: Message,
    val status: String
)

@Serializable
data class Usage(
    val inputTextTokens: String,
    val completionTokens: String,
    val totalTokens: String
)
```

---

## 🎨 UI компоненты

### 🎮 **Панель анимаций**

```kotlin
@Composable
fun AnimationControlPanel(
    mapState: MapState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = "🎞 Анимации",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        Button(
            onClick = { mapState.flyToIsaacCathedral() },
            shape = CircleShape,
            modifier = Modifier.padding(vertical = 2.dp)
        ) {
            Text("✈️ Полет к Исаакию")
        }
        
        Button(
            onClick = { mapState.moveToIsaacCathedral() },
            shape = CircleShape,
            modifier = Modifier.padding(vertical = 2.dp)
        ) {
            Text("📍 К Исаакию")
        }
        
        Button(
            onClick = { mapState.moveToStPetersburg() },
            shape = CircleShape,
            modifier = Modifier.padding(vertical = 2.dp)
        ) {
            Text("🏛️ СПб")
        }
        
        Button(
            onClick = { mapState.moveToMoscow() },
            shape = CircleShape,
            modifier = Modifier.padding(vertical = 2.dp)
        ) {
            Text("🏰 Москва")
        }
    }
}
```

### 👆 **Панель пользовательских меток**

```kotlin
@Composable
fun UserPlacemarksPanel(
    mapScreenMutableState: MapScreenMutableState,
    modifier: Modifier = Modifier
) {
    val userPlacemarks = mapScreenMutableState.getUserPlacemarks()
    
    if (userPlacemarks.isNotEmpty()) {
        Column(
            modifier = modifier.padding(16.dp)
        ) {
            Text(
                text = "👆 Мои метки (${userPlacemarks.size})",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            Button(
                onClick = { mapScreenMutableState.clearUserPlacemarks() },
                shape = CircleShape,
                modifier = Modifier.padding(vertical = 2.dp)
            ) {
                Text("🗑️ Очистить")
            }
            
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
            
            userPlacemarks.takeLast(3).forEach { userPlacemark ->
                Button(
                    onClick = { 
                        mapScreenMutableState.mapState.moveToPoint(
                            userPlacemark.point, 
                            18.0f, 
                            2.0f
                        )
                    },
                    shape = CircleShape,
                    modifier = Modifier.padding(vertical = 1.dp)
                ) {
                    Text("📍 ${userPlacemark.title}")
                }
            }
        }
    } else {
        Column(
            modifier = modifier.padding(16.dp)
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
```

---

## 🔧 Утилиты и хелперы

### 📍 **Фабрика точек**

```kotlin
object PointFactory {
    fun create(latitude: Double, longitude: Double): Point {
        return Point(latitude, longitude)
    }
    
    fun createFromString(coordinates: String): Point? {
        return try {
            val parts = coordinates.split(",")
            if (parts.size == 2) {
                val lat = parts[0].trim().toDouble()
                val lon = parts[1].trim().toDouble()
                Point(lat, lon)
            } else null
        } catch (e: Exception) {
            null
        }
    }
    
    fun distance(point1: Point, point2: Point): Double {
        val lat1 = Math.toRadians(point1.latitude)
        val lat2 = Math.toRadians(point2.latitude)
        val deltaLat = Math.toRadians(point2.latitude - point1.latitude)
        val deltaLon = Math.toRadians(point2.longitude - point1.longitude)
        
        val a = sin(deltaLat / 2) * sin(deltaLat / 2) +
                cos(lat1) * cos(lat2) *
                sin(deltaLon / 2) * sin(deltaLon / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        
        return 6371 * c // Радиус Земли в километрах
    }
}
```

### 🎨 **Фабрика иконок**

```kotlin
object IconFactory {
    fun createPlacemarkIcon(type: PlacemarkType, isSelected: Boolean): Pair<ImageBitmap, IconStyle> {
        return when (type) {
            PlacemarkType.DEFAULT -> createDefaultIcon(isSelected)
            PlacemarkType.USER -> createUserIcon(isSelected)
            PlacemarkType.SEARCH -> createSearchIcon(isSelected)
        }
    }
    
    private fun createDefaultIcon(isSelected: Boolean): Pair<ImageBitmap, IconStyle> {
        val color = if (isSelected) Color.Red else Color.Blue
        val size = if (isSelected) 32.dp else 24.dp
        
        return createColoredCircle(color, size) to IconStyle().apply {
            anchor = Offset(0.5f, 0.5f)
        }
    }
    
    private fun createUserIcon(isSelected: Boolean): Pair<ImageBitmap, IconStyle> {
        val color = if (isSelected) Color.Green else Color.Orange
        val size = if (isSelected) 36.dp else 28.dp
        
        return createColoredCircle(color, size) to IconStyle().apply {
            anchor = Offset(0.5f, 0.5f)
        }
    }
    
    private fun createSearchIcon(isSelected: Boolean): Pair<ImageBitmap, IconStyle> {
        val color = if (isSelected) Color.Purple else Color.Magenta
        val size = if (isSelected) 30.dp else 26.dp
        
        return createColoredCircle(color, size) to IconStyle().apply {
            anchor = Offset(0.5f, 0.5f)
        }
    }
    
    private fun createColoredCircle(color: Color, size: Dp): ImageBitmap {
        // Создание простой цветной иконки
        return ImageBitmap(size.toPx(), size.toPx()).apply {
            // Заполнение цветом
        }
    }
}

enum class PlacemarkType {
    DEFAULT, USER, SEARCH
}
```

---

## 🚀 Расширенные примеры

### 🔄 **Асинхронная загрузка данных**

```kotlin
class DataLoader {
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    
    fun loadPlacemarksAsync(
        onProgress: (Float) -> Unit,
        onComplete: (List<Placemark>) -> Unit,
        onError: (String) -> Unit
    ) {
        scope.launch {
            try {
                onProgress(0.0f)
                
                // Загрузка данных
                val placemarks = loadPlacemarksFromApi()
                onProgress(0.5f)
                
                // Обработка данных
                val processedPlacemarks = processPlacemarks(placemarks)
                onProgress(0.8f)
                
                // Сохранение в кэш
                saveToCache(processedPlacemarks)
                onProgress(1.0f)
                
                withContext(Dispatchers.Main) {
                    onComplete(processedPlacemarks)
                }
                
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onError("Ошибка загрузки: ${e.message}")
                }
            }
        }
    }
    
    private suspend fun loadPlacemarksFromApi(): List<Placemark> {
        delay(1000) // Имитация API вызова
        return listOf(
            Placemark("1", 59.9343, 30.3061, "Исаакиевский собор"),
            Placemark("2", 59.9398, 30.3146, "Эрмитаж"),
            Placemark("3", 59.9341, 30.3358, "Казанский собор")
        )
    }
    
    private suspend fun processPlacemarks(placemarks: List<Placemark>): List<Placemark> {
        delay(500) // Имитация обработки
        return placemarks.map { it.copy(description = "Обработано: ${it.title}") }
    }
    
    private suspend fun saveToCache(placemarks: List<Placemark>) {
        delay(300) // Имитация сохранения
    }
}
```

### 🎯 **Обработка жестов**

```kotlin
@Composable
fun GestureHandler(
    onTap: (Point) -> Unit,
    onLongPress: (Point) -> Unit,
    onDoubleTap: (Point) -> Unit,
    content: @Composable () -> Unit
) {
    var lastTapTime by remember { mutableStateOf(0L) }
    var lastTapPoint by remember { mutableStateOf<Point?>(null) }
    
    Box(
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { offset ->
                        val point = screenToWorld(offset)
                        val currentTime = System.currentTimeMillis()
                        
                        if (lastTapPoint == point && 
                            currentTime - lastTapTime < 300) {
                            // Двойное нажатие
                            onDoubleTap(point)
                        } else {
                            // Одиночное нажатие
                            onTap(point)
                        }
                        
                        lastTapTime = currentTime
                        lastTapPoint = point
                    },
                    onLongPress = { offset ->
                        val point = screenToWorld(offset)
                        onLongPress(point)
                    }
                )
            }
    ) {
        content()
    }
}

private fun screenToWorld(offset: Offset): Point {
    // Конвертация экранных координат в мировые
    // Здесь должна быть логика MapKit
    return Point(0.0, 0.0)
}
```

---

## 📚 Дополнительные ресурсы

### 🔗 **Полезные ссылки**

- **Kotlin документация**: [kotlinlang.org](https://kotlinlang.org)
- **Compose документация**: [developer.android.com/jetpack/compose](https://developer.android.com/jetpack/compose)
- **Yandex MapKit**: [yandex.ru/dev/maps/mapkit](https://yandex.ru/dev/maps/mapkit)
- **YaGPT API**: [cloud.yandex.ru/docs/foundation-models](https://cloud.yandex.ru/docs/foundation-models)

### 📖 **Рекомендуемая литература**

- "Kotlin in Action" - Дмитрий Жемеров
- "Compose by Example" - Google Developers
- "Android Development with Kotlin" - Marcin Moskala

---

<div align="center">

**⭐ Если примеры были полезными, поставьте звездочку проекту! ⭐**

**🚀 Удачного кодирования! 🚀**

**💬 Делитесь своими примерами! 💬**

</div>

---

*Примеры обновлены: Декабрь 2024*
