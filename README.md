# Яндекс Карты - Базовый API

Этот проект демонстрирует базовые функции работы с API Яндекс Карт (MapKit) для создания интерактивных карт на веб-страницах.

## 🚀 Возможности

### Основные функции:
- ✅ Инициализация карты с настройками
- ✅ Добавление маркеров (Placemark)
- ✅ Создание полигонов (Polygon)
- ✅ Рисование линий (Polyline)
- ✅ Управление зумом и центром карты
- ✅ Обработка событий карты
- ✅ Геокодирование адресов
- ✅ Расчет расстояний между точками
- ✅ Клики по карте с добавлением маркеров

### Дополнительные возможности:
- 🎨 Случайные цвета для объектов
- 📱 Адаптивный дизайн
- 🔄 Автоматическое обновление информации
- 📊 Счетчик объектов на карте
- 🎯 Информационная панель

## 📋 Требования

- Веб-браузер с поддержкой JavaScript ES6+
- API ключ Яндекс Карт
- Локальный веб-сервер (для корректной работы)

## 🛠️ Установка и настройка

### 1. Получение API ключа

1. Перейдите на [Яндекс.Разработчики](https://developer.tech.yandex.ru/)
2. Создайте новое приложение
3. Получите API ключ для JavaScript API
4. Замените `YOUR_API_KEY` в файле `index.html` на ваш ключ

```html
<script src="https://api-maps.yandex.ru/2.1/?apikey=ВАШ_API_КЛЮЧ&lang=ru_RU"></script>
```

### 2. Запуск проекта

#### Вариант 1: Локальный сервер (рекомендуется)
```bash
# Python 3
python -m http.server 8000

# Node.js
npx http-server

# PHP
php -S localhost:8000
```

#### Вариант 2: Открытие файла
Просто откройте `index.html` в браузере (некоторые функции могут не работать)

### 3. Открытие в браузере
Перейдите по адресу: `http://localhost:8000`

## 📖 Использование

### Базовые операции

#### Добавление маркера
```javascript
// Через кнопку
document.getElementById('addMarker').click();

// Программно
window.mapKit.addRandomMarker();
```

#### Добавление полигона
```javascript
// Через кнопку
document.getElementById('addPolygon').click();

// Программно
window.mapKit.addRandomPolygon();
```

#### Добавление линии
```javascript
// Через кнопку
document.getElementById('addPolyline').click();

// Программно
window.mapKit.addRandomPolyline();
```

#### Очистка всех объектов
```javascript
// Через кнопку
document.getElementById('clearAll').click();

// Программно
window.mapKit.clearAll();
```

### Продвинутые функции

#### Поиск по адресу
```javascript
// Поиск и центрирование карты
const coords = await window.mapKit.searchByAddress('Москва, Красная площадь');
```

#### Расчет расстояния
```javascript
const point1 = [55.7558, 37.6176]; // Москва
const point2 = [59.9311, 30.3609]; // Санкт-Петербург
const distance = window.mapKit.calculateDistance(point1, point2);
console.log(`Расстояние: ${distance} метров`);
```

#### Получение центра карты
```javascript
const center = window.mapKit.getCenter();
console.log('Центр карты:', center);
```

#### Установка зума
```javascript
// Случайный зум
window.mapKit.setRandomZoom();

// Конкретный зум
window.mapKit.map.setZoom(15);
```

## 🏗️ Архитектура

### Основной класс: `YandexMapKit`

```javascript
class YandexMapKit {
    constructor() {
        this.map = null;           // Экземпляр карты
        this.objects = [];         // Все объекты на карте
        this.markers = [];         // Маркеры
        this.polygons = [];        // Полигоны
        this.polylines = [];       // Линии
        this.isInitialized = false; // Статус инициализации
    }
}
```

### Структура файлов:
```
workshop/
├── index.html          # Основная HTML страница
├── styles.css          # Стили и CSS
├── mapkit.js           # Основной JavaScript API
└── README.md           # Документация
```

## 🎨 Кастомизация

### Изменение стилей объектов

#### Маркеры
```javascript
const marker = new ymaps.Placemark(coords, {
    balloonContent: 'Текст в балуне'
}, {
    preset: 'islands#blueDotIcon',  // Иконка
    iconColor: '#ff0000'            // Цвет иконки
});
```

#### Полигоны
```javascript
const polygon = new ymaps.Polygon([points], {
    balloonContent: 'Описание полигона'
}, {
    fillColor: '#ff0000',           // Цвет заливки
    strokeColor: '#000000',         // Цвет границы
    strokeWidth: 2,                 // Толщина границы
    fillOpacity: 0.6                // Прозрачность заливки
});
```

#### Линии
```javascript
const polyline = new ymaps.Polyline([points], {
    balloonContent: 'Описание линии'
}, {
    strokeColor: '#ff0000',         // Цвет линии
    strokeWidth: 3                  // Толщина линии
});
```

### Изменение настроек карты

```javascript
this.map = new ymaps.Map('map', {
    center: [55.7558, 37.6176],    // Центр карты
    zoom: 10,                       // Начальный зум
    controls: [                     // Элементы управления
        'zoomControl',              // Кнопки зума
        'fullscreenControl',        // Полноэкранный режим
        'geolocationControl'        // Определение местоположения
    ]
});
```

## 🔧 Отладка

### Консоль браузера
Откройте Developer Tools (F12) и перейдите на вкладку Console для просмотра логов:

```
Инициализация Яндекс Карт...
Карта успешно инициализирована
Добавлен маркер: [55.7558, 37.6176]
```

### Проверка API
Убедитесь, что API Яндекс Карт загружен:
```javascript
console.log(typeof ymaps); // Должно вывести "function"
```

## 🚨 Частые проблемы

### 1. Карта не отображается
- Проверьте API ключ
- Убедитесь, что используется локальный сервер
- Проверьте консоль на ошибки

### 2. Функции не работают
- Дождитесь полной загрузки карты
- Проверьте статус `isInitialized`
- Убедитесь, что все элементы DOM загружены

### 3. Ошибки CORS
- Используйте локальный веб-сервер
- Не открывайте файл напрямую в браузере

## 📚 Дополнительные ресурсы

- [Документация Яндекс Карт](https://yandex.ru/dev/maps/jsapi/)
- [Примеры использования](https://yandex.ru/dev/maps/jsapi/doc/2.1/examples/)
- [Справочник API](https://yandex.ru/dev/maps/jsapi/doc/2.1/ref/reference/)

## 🤝 Вклад в проект

Если у вас есть идеи по улучшению или вы нашли ошибки:

1. Создайте Issue с описанием проблемы
2. Предложите Pull Request с исправлениями
3. Добавьте новые функции в API

## 📄 Лицензия

Этот проект создан для образовательных целей. Используйте свободно для изучения API Яндекс Карт.

---

**Создано с ❤️ для изучения Яндекс Карт**

# 🗺️ Yandex Maps Workshop

<div align="center">

![Yandex Maps](https://img.shields.io/badge/Yandex_Maps-FF0000?style=for-the-badge&logo=yandex&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Compose](https://img.shields.io/badge/Compose-4285F4?style=for-the-badge&logo=jetpack-compose&logoColor=white)
![Multiplatform](https://img.shields.io/badge/Multiplatform-00C853?style=for-the-badge&logo=kotlin&logoColor=white)

**Современное картографическое приложение с ИИ-функциями на базе Yandex MapKit и YaGPT**

[🚀 Быстрый старт](#-быстрый-старт) • [📱 Функции](#-функции) • [🛠 Технологии](#-технологии) • [📖 Документация](#-документация)

</div>

---

## 🌟 **О проекте**

**Yandex Maps Workshop** — это инновационное картографическое приложение, демонстрирующее возможности современных технологий разработки. Проект объединяет мощь Yandex MapKit, элегантность Compose Multiplatform и интеллект YaGPT для создания уникального пользовательского опыта.

### 🎯 **Ключевые особенности:**
- 🗺️ **Интерактивные карты** с плавными анимациями
- 🤖 **ИИ-генерация описаний** мест с помощью YaGPT
- 📱 **Кроссплатформенность** на базе Kotlin Multiplatform
- 🎨 **Современный UI** с Material Design 3
- 🔍 **Умный поиск** и геолокация
- ✨ **Пользовательские метки** с возможностью создания

---

## 🚀 **Быстрый старт**

### 📋 **Предварительные требования**

- **Android Studio** (последняя версия)
- **JDK 17+**
- **Android SDK** (API 26+)
- **Git**

### 🔑 **Настройка API ключей**

1. **Создайте файл `local.properties`** в корне проекта:
```properties
sdk.dir=C\:\\Users\\YourUsername\\AppData\\Local\\Android\\Sdk
```

2. **Обновите `gradle.properties`** с вашими ключами:
```properties
folderId=your_folder_id_here
gptToken=your_yagpt_token_here
mapkitToken=your_mapkit_token_here
```

### 🏗️ **Сборка проекта**

```bash
# Клонирование репозитория
git clone https://github.com/kramlex/MapsWorkShop.git
cd MapsWorkShop

# Сборка для Android
./gradlew :composeApp:assembleDebug

# Или для Windows
.\gradlew.bat :composeApp:assembleDebug
```

### 📱 **Запуск на устройстве**

1. Подключите Android устройство или запустите эмулятор
2. Выполните: `./gradlew :composeApp:installDebug`
3. Запустите приложение на устройстве

---

## 📱 **Функции**

### 🎞 **Анимации и навигация**

#### ✈️ **Полет к Исаакиевскому собору**
- **Эффект**: Многоэтапная анимация "полета" над картой
- **Длительность**: 3 секунды плавного перехода
- **Масштаб**: От мирового обзора до детального вида (zoom 3.0 → 16.0)

#### 📍 **Быстрые переходы**
- **К Исаакию**: Мгновенный переход с плавной анимацией
- **🏛️ СПб**: Переход к центру Санкт-Петербурга
- **🏰 Москва**: Переход к центру Москвы

### 👆 **Интерактивность карты**

#### 🎯 **Создание меток**
- **Одиночное нажатие**: Создание обычной метки
- **Долгое нажатие**: Создание "длинной" метки
- **Автоматическое именование**: "Метка 1", "Метка 2" и т.д.

#### 🗑️ **Управление метками**
- **Просмотр**: Список последних 3 меток
- **Навигация**: Переход к метке по нажатию
- **Очистка**: Удаление всех пользовательских меток

### 🔎 **Поиск и геолокация**

#### 🔍 **Поисковая панель**
- **Расположение**: Верхняя центральная часть экрана
- **Функции**: Поиск мест, отображение результатов
- **Результаты**: До 5 найденных мест с описаниями

#### 🤖 **YaGPT интеграция**
- **Автогенерация**: Описания для найденных мест
- **Контекст**: Учет названия и адреса места
- **Стиль**: Туристические описания с историческими фактами

---

## 🛠 **Технологии**

### 🏗️ **Архитектура**
```
MapsWorkShop/
├── 📱 composeApp/          # Основное приложение
├── 🔧 common/              # Общая логика
├── 🗺️ mapkit-bindings/     # Yandex MapKit KMP
├── 🔗 mapkit-interop/      # Межплатформенные адаптеры
└── 📚 docs/                # Документация
```

### 🎨 **UI Framework**
- **Compose Multiplatform** - современный декларативный UI
- **Material Design 3** - последние принципы дизайна
- **Responsive Layout** - адаптация под разные экраны

### 🗺️ **Картографические сервисы**
- **Yandex MapKit KMP** - нативные карты для всех платформ
- **Геолокация** - определение координат и адресов
- **Анимации** - плавные переходы камеры и масштабирование

### 🤖 **Искусственный интеллект**
- **YaGPT API** - генерация описаний мест
- **Контекстный анализ** - учет названия и местоположения
- **Туристический контент** - специализированные описания

### 🌐 **Сетевое взаимодействие**
- **Ktor Client** - HTTP клиент для API запросов
- **Kotlinx Serialization** - работа с JSON
- **Coroutines** - асинхронные операции

---

## 📖 **Документация**

### 🏗️ **Архитектура приложения**

#### **Основные компоненты:**

```kotlin
@Composable
fun App() {
    // 1. Инициализация MapKit
    rememberAndInitializeMapKit(apiKey = BuildKonfig.mapkitToken)
    
    // 2. Состояние приложения
    val mapScreenMutableState = remember { MapScreenMutableState() }
    
    // 3. Основные панели
    MapWithPlacemarks(...)
    AnimationControlPanel(...)
    UserPlacemarksPanel(...)
    SearchPanel(...)
}
```

#### **Управление состоянием:**

```kotlin
class MapScreenMutableState {
    val mapState: MapState                    // Состояние карты
    private val _userPlacemarks: MutableState // Пользовательские метки
    private val _searchResults: MutableState  // Результаты поиска
    private val _isSearching: MutableState   // Статус поиска
}
```

### 🗺️ **Работа с картами**

#### **Инициализация карты:**
```kotlin
@Composable
fun Map(state: MapState) {
    // Настройка начальной позиции
    LaunchedEffect(Unit) {
        // Мировой обзор → Исаакиевский собор
        state.map?.let { mapWindow ->
            // 1. Установка мирового обзора
            val worldPosition = CameraPositionFactory.create(
                target = PointFactory.create(55.0, 37.0),
                zoom = 3.0f
            )
            mapWindow.map.move(worldPosition)
            
            // 2. Анимированный переход к собору
            Handler(Looper.getMainLooper()).postDelayed({
                val animation = AnimationFactory.create(
                    type = AnimationType.SMOOTH, 
                    duration = 3.0f
                )
                val targetPosition = CameraPositionFactory.create(
                    target = PointFactory.create(59.9343, 30.3061),
                    zoom = 16.0f
                )
                mapWindow.map.move(targetPosition, animation, null)
            }, 1000)
        }
    }
}
```

#### **Анимации перемещения:**
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
```

### 🤖 **YaGPT интеграция**

#### **Структура запроса:**
```kotlin
@Serializable
data class YaGPTRequest(
    val modelUri: String,           // "gpt://{folderId}/yandexgpt-lite"
    val completionOptions: CompletionOptions,
    val messages: List<Message>     // system + user prompts
)
```

#### **Генерация описания:**
```kotlin
suspend fun generatePlaceDescription(placeName: String, address: String?): String {
    val systemPrompt = """
        Ты - эксперт по туризму и истории. Создай интересное и информативное 
        описание места для туристического приложения.
        Описание должно быть:
        - Кратким (до 3-4 предложений)
        - Интересным и познавательным
        - Содержать исторические факты или интересные особенности
        - Подходящим для туристов
    """.trimIndent()
    
    val userPrompt = "Создай описание для места: $placeName (адрес: $address)"
    
    // Отправка запроса к YaGPT API
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
```

### 🔍 **Поисковая система**

#### **Контроллер поиска:**
```kotlin
class SearchController(
    private val onSearchResults: (List<SearchResult>) -> Unit,
    private val onSearchError: (String) -> Unit
) {
    fun search(query: String, center: Point) {
        // Демонстрационные результаты
        val mockResults = listOf(
            SearchResult(
                id = "mock_1",
                name = "Результат поиска: $query",
                point = center,
                address = "Адрес не найден",
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

---

## 🎨 **UI компоненты**

### 🎮 **Панель анимаций (AnimationControlPanel)**

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
        Text("🎞 Анимации", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        
        Button(onClick = { mapState.flyToIsaacCathedral() }) {
            Text("✈️ Полет к Исаакию")
        }
        
        Button(onClick = { mapState.moveToIsaacCathedral() }) {
            Text("📍 К Исаакию")
        }
        
        Button(onClick = { mapState.moveToStPetersburg() }) {
            Text("🏛️ СПб")
        }
        
        Button(onClick = { mapState.moveToMoscow() }) {
            Text("🏰 Москва")
        }
    }
}
```

### 👆 **Панель пользовательских меток (UserPlacemarksPanel)**

```kotlin
@Composable
fun UserPlacemarksPanel(
    mapScreenMutableState: MapScreenMutableState,
    modifier: Modifier = Modifier
) {
    val userPlacemarks = mapScreenMutableState.getUserPlacemarks()
    
    if (userPlacemarks.isNotEmpty()) {
        Column(modifier = modifier.padding(16.dp)) {
            Text("👆 Мои метки (${userPlacemarks.size})")
            
            Button(onClick = { mapScreenMutableState.clearUserPlacemarks() }) {
                Text("🗑️ Очистить")
            }
            
            Button(onClick = { 
                userPlacemarks.forEach { placemark ->
                    mapScreenMutableState.generateDescriptionForUserPlacemark(placemark)
                }
            }) {
                Text("🤖 Описания")
            }
            
            // Последние 3 метки
            userPlacemarks.takeLast(3).forEach { userPlacemark ->
                Button(onClick = { 
                    mapScreenMutableState.mapState.moveToPoint(userPlacemark.point, 18.0f, 2.0f)
                }) {
                    Text("📍 ${userPlacemark.title}")
                }
            }
        }
    } else {
        Text("💡 Нажмите на карту\nчтобы добавить метку!")
    }
}
```

### 🔎 **Поисковая панель (SearchPanel)**

```kotlin
@Composable
fun SearchPanel(
    mapScreenMutableState: MapScreenMutableState,
    modifier: Modifier = Modifier
) {
    var searchText by remember { mutableStateOf("") }
    val searchResults = mapScreenMutableState.searchResults
    val isSearching = mapScreenMutableState.isSearching
    
    Card(
        modifier = modifier.padding(16.dp).fillMaxWidth(0.8f),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("🔎 Поиск мест", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    placeholder = { Text("Введите название места...") },
                    modifier = Modifier.weight(1f),
                    leadingIcon = { Icon(Icons.Default.Search, "Поиск") }
                )
                
                Button(
                    onClick = { 
                        if (searchText.isNotBlank()) {
                            mapScreenMutableState.performSearch(searchText)
                        }
                    },
                    enabled = searchText.isNotBlank() && !isSearching
                ) {
                    if (isSearching) {
                        CircularProgressIndicator(modifier = Modifier.size(16.dp))
                    } else {
                        Text("Найти")
                    }
                }
            }
            
            // Результаты поиска с описаниями от YaGPT
            if (searchResults.isNotEmpty()) {
                LazyColumn(modifier = Modifier.heightIn(max = 200.dp)) {
                    items(searchResults.take(5)) { result ->
                        SearchResultItem(
                            result = result,
                            onClick = {
                                mapScreenMutableState.mapState.moveToPoint(result.point, 16.0f, 2.0f)
                            }
                        )
                    }
                }
            }
        }
    }
}
```

---

## 🔧 **Конфигурация**

### 📁 **Структура проекта**

```
MapsWorkShop/
├── 📱 composeApp/                          # Основное приложение
│   ├── src/
│   │   ├── androidMain/                    # Android-специфичный код
│   │   │   └── kotlin/.../Map.android.kt  # Android реализация карты
│   │   ├── commonMain/                     # Общий код для всех платформ
│   │   │   └── kotlin/.../App.kt          # Основная логика приложения
│   │   └── iosMain/                        # iOS-специфичный код
│   ├── build.gradle.kts                    # Конфигурация сборки
│   └── srcSet/                             # Настройки исходных наборов
├── 🔧 common/                              # Общие модули
├── 🗺️ mapkit-bindings/                     # Yandex MapKit KMP
├── 🔗 mapkit-interop/                      # Межплатформенные адаптеры
├── 📚 docs/                                # Документация
├── 🏗️ build.gradle.kts                     # Корневая конфигурация
├── 📋 gradle.properties                    # Свойства Gradle
├── 🔑 local.properties                     # Локальные настройки
└── 📖 README.md                            # Этот файл
```

### ⚙️ **Настройки Gradle**

#### **Основные зависимости:**
```kotlin
sourceSets.commonMain.dependencies {
    // Compose Multiplatform
    implementation(compose.runtime)
    implementation(compose.foundation)
    implementation(compose.material3)
    implementation(compose.ui)
    
    // Ktor для HTTP запросов
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.json)
    implementation(libs.ktor.client.logging)
    
    // Сериализация
    implementation(libs.kotlinx.serialization)
}
```

#### **Android-специфичные зависимости:**
```kotlin
sourceSets.androidMain.dependencies {
    implementation(compose.preview)
    implementation(libs.androidx.activity.compose)
    
    // Ktor Android клиент
    implementation(libs.ktor.client.okhttp)
}
```

### 🔑 **API ключи и токены**

#### **gradle.properties:**
```properties
# Yandex Cloud
folderId=b1g334ut7mfp3stm8gnk

# YaGPT API
gptToken=t1.9euelZrJk5zNnM_GnsvMncmTzsqMl-3rnpWajZybjc-YicaJzczPlouXj5Hl8_d8J1o6-e9qJCw8_N3z9zxWVzr572okLDz8zef1656VmpuYkZGTlZzKmYvLjpLHy8mM7_zF656VmpuYkZGTlZzKmYvLjpLHy8mM.X4DV0UomYuFRk2M1DAsFT-vCnf8yipedm1UoRkLVxDoQVUevaZnSFIaWH67Vn3aIRrnWOQMgoM2sdTeoE1ikAg

# Yandex MapKit
mapkitToken=e049cdec-2adc-4569-a841-3311fe521913
```

#### **local.properties:**
```properties
# Android SDK путь
sdk.dir=C\:\\Users\\YourUsername\\AppData\\Local\\Android\\Sdk
```

---

## 🚀 **Развертывание**

### 📱 **Android**

#### **Debug сборка:**
```bash
./gradlew :composeApp:assembleDebug
```

#### **Release сборка:**
```bash
./gradlew :composeApp:assembleRelease
```

#### **Установка на устройство:**
```bash
./gradlew :composeApp:installDebug
```

### 🍎 **iOS (в разработке)**

```bash
./gradlew :composeApp:iosSimulatorArm64Test
```

### 🌐 **Web (планируется)**

```bash
./gradlew :composeApp:jsBrowserDevelopmentRun
```

---

## 🧪 **Тестирование**

### 🔍 **Проверка функциональности**

1. **Карта и анимации:**
   - Запуск приложения → автоматический переход к Исаакиевскому собору
   - Тестирование кнопок анимаций
   - Проверка плавности переходов

2. **Интерактивность:**
   - Создание меток нажатием на карту
   - Управление пользовательскими метками
   - Навигация между метками

3. **Поиск и YaGPT:**
   - Ввод поисковых запросов
   - Проверка генерации описаний
   - Отображение результатов поиска

### 🐛 **Отладка**

#### **Логи приложения:**
```bash
adb logcat | grep "MapsWorkShop"
```

#### **Проверка API:**
```bash
# Тест YaGPT API
curl -X POST "https://llm.api.cloud.yandex.net/foundationModels/v1/completion" \
  -H "Authorization: Api-Key YOUR_TOKEN" \
  -H "x-folder-id: YOUR_FOLDER_ID" \
  -H "Content-Type: application/json" \
  -d '{"modelUri":"gpt://YOUR_FOLDER_ID/yandexgpt-lite",...}'
```

---

## 🤝 **Вклад в проект**

### 📝 **Как внести свой вклад**

1. **Fork** репозитория
2. Создайте **feature branch** (`git checkout -b feature/AmazingFeature`)
3. **Commit** изменения (`git commit -m 'Add some AmazingFeature'`)
4. **Push** в branch (`git push origin feature/AmazingFeature`)
5. Откройте **Pull Request**

### 🐛 **Сообщение об ошибках**

Используйте [GitHub Issues](https://github.com/kramlex/MapsWorkShop/issues) для:
- Сообщений об ошибках
- Предложений новых функций
- Вопросов по использованию

### 📋 **Шаблон issue**

```markdown
## 🐛 Описание ошибки
Краткое описание проблемы

## 🔍 Шаги для воспроизведения
1. Откройте приложение
2. Выполните действие X
3. Ошибка происходит в Y

## 📱 Окружение
- Устройство: [например, Samsung Galaxy S21]
- Android версия: [например, 13]
- Версия приложения: [например, 1.0.0]

## 📸 Скриншоты
Добавьте скриншоты если применимо

## 💻 Логи
```
adb logcat | grep "MapsWorkShop"
```
```

---

## 📚 **Дополнительные ресурсы**

### 🔗 **Полезные ссылки**

- **Yandex MapKit**: [Документация](https://yandex.ru/dev/maps/mapkit/)
- **YaGPT**: [API документация](https://cloud.yandex.ru/docs/foundation-models/)
- **Compose Multiplatform**: [Руководство](https://www.jetbrains.com/lp/compose-multiplatform/)
- **Kotlin Multiplatform**: [Документация](https://kotlinlang.org/docs/multiplatform.html)

### 📖 **Рекомендуемая литература**

- "Kotlin Multiplatform by Example" - практические примеры
- "Compose Multiplatform: From Android to iOS" - руководство по UI
- "Yandex MapKit Developer Guide" - работа с картами

### 🎓 **Обучающие материалы**

- [Kotlin Multiplatform Tutorial](https://kotlinlang.org/docs/multiplatform-tutorial.html)
- [Compose Multiplatform Examples](https://github.com/JetBrains/compose-multiplatform)
- [Yandex Cloud Learning Path](https://cloud.yandex.ru/docs/tutorials/)

---

## 📄 **Лицензия**

Этот проект распространяется под лицензией **MIT**. См. файл [LICENSE](LICENSE) для подробностей.

---

## 👨‍💻 **Авторы**

- **Основной разработчик**: [kramlex](https://github.com/kramlex)
- **UI/UX дизайн**: MapsWorkShop Team
- **Документация**: AI Assistant

---

## 🙏 **Благодарности**

- **Yandex** за предоставление MapKit и YaGPT API
- **JetBrains** за Kotlin и Compose Multiplatform
- **Сообщество** за вклад в развитие проекта

---

<div align="center">

**⭐ Если проект вам понравился, поставьте звездочку! ⭐**

**🚀 Следите за обновлениями и новыми функциями! 🚀**

</div>

---

*Последнее обновление: Декабрь 2024*