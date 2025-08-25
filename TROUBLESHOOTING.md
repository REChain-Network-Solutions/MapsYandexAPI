# 🔧 Решение проблем

Этот документ содержит решения для наиболее распространенных проблем, которые могут возникнуть при работе с проектом Яндекс Карт.

## 📋 Содержание

- [Общие проблемы](#-общие-проблемы)
- [Проблемы веб-версии](#-проблемы-веб-версии)
- [Проблемы Android](#-проблемы-android)
- [Проблемы iOS](#-проблемы-ios)
- [Проблемы сборки](#-проблемы-сборки)
- [Проблемы API](#-проблемы-api)
- [Часто задаваемые вопросы](#-часто-задаваемые-вопросы)

## 🚨 Общие проблемы

### Карта не загружается

#### Симптомы
- Карта не отображается
- Появляется сообщение об ошибке
- Белый экран вместо карты

#### Возможные причины и решения

**1. Неверный API ключ**
```javascript
// Проверьте правильность ключа в config.js
const config = {
    apiKey: 'YOUR_ACTUAL_API_KEY', // Убедитесь, что ключ верный
    // ...
};
```

**2. Ограничения по домену**
- Проверьте настройки API ключа в [Яндекс.Разработчики](https://developer.tech.yandex.ru/)
- Убедитесь, что ваш домен добавлен в список разрешенных

**3. Превышение лимитов API**
- Проверьте текущее использование в панели разработчика
- Увеличьте лимиты при необходимости

**4. Проблемы с сетью**
```bash
# Проверьте доступность API
curl -I "https://api-maps.yandex.ru/2.1/?apikey=YOUR_KEY"
```

### Ошибки JavaScript

#### Симптомы
- Ошибки в консоли браузера
- Функции не работают
- Приложение "зависает"

#### Решения

**1. Проверьте консоль браузера**
```javascript
// Добавьте обработчик ошибок
window.addEventListener('error', (event) => {
    console.error('Ошибка JavaScript:', event.error);
});

window.addEventListener('unhandledrejection', (event) => {
    console.error('Необработанное отклонение Promise:', event.reason);
});
```

**2. Проверьте загрузку скриптов**
```html
<!-- Убедитесь, что все скрипты загружены -->
<script>
    window.addEventListener('load', () => {
        console.log('Все ресурсы загружены');
        if (typeof ymaps === 'undefined') {
            console.error('Yandex Maps API не загружен');
        }
    });
</script>
```

## 🌐 Проблемы веб-версии

### Карта не инициализируется

#### Ошибка: "ymaps is not defined"

**Причина:** API Яндекс Карт не загружен

**Решение:**
```html
<!-- Убедитесь, что скрипт загружается до вашего кода -->
<script src="https://api-maps.yandex.ru/2.1/?apikey=YOUR_KEY&lang=ru_RU"></script>
<script src="mapkit.js"></script>
```

**Альтернативное решение с проверкой загрузки:**
```javascript
function waitForYmaps() {
    return new Promise((resolve) => {
        if (typeof ymaps !== 'undefined') {
            resolve();
        } else {
            const checkInterval = setInterval(() => {
                if (typeof ymaps !== 'undefined') {
                    clearInterval(checkInterval);
                    resolve();
                }
            }, 100);
        }
    });
}

// Использование
waitForYmaps().then(() => {
    const mapKit = new MapKit(config);
    mapKit.init();
});
```

### Маркеры не отображаются

#### Симптомы
- Маркеры создаются, но не видны на карте
- Ошибки в консоли при добавлении маркеров

#### Решения

**1. Проверьте координаты**
```javascript
// Убедитесь, что координаты в правильном формате
const coords = [55.7558, 37.6176]; // [широта, долгота]

// Проверьте диапазон
if (coords[0] < -90 || coords[0] > 90) {
    console.error('Неверная широта:', coords[0]);
}
if (coords[1] < -180 || coords[1] > 180) {
    console.error('Неверная долгота:', coords[1]);
}
```

**2. Проверьте видимость карты**
```javascript
// Убедитесь, что карта видима
const mapElement = document.getElementById('map');
if (mapElement.offsetWidth === 0 || mapElement.offsetHeight === 0) {
    console.error('Карта не видима');
}
```

**3. Проверьте зум карты**
```javascript
// Установите подходящий зум
mapKit.setZoom(10); // Не слишком близко, не слишком далеко
```

### Полигоны не отображаются

#### Симптомы
- Полигоны создаются, но не видны
- Ошибки при создании полигонов

#### Решения

**1. Проверьте координаты полигона**
```javascript
// Минимум 3 точки для полигона
const coords = [
    [55.7558, 37.6176],
    [55.7558, 37.6276],
    [55.7458, 37.6176]
];

if (coords.length < 3) {
    console.error('Полигон должен содержать минимум 3 точки');
}
```

**2. Проверьте порядок координат**
```javascript
// Координаты должны быть в правильном порядке (по часовой стрелке)
const coords = [
    [55.7558, 37.6176], // Левый верхний угол
    [55.7558, 37.6276], // Правый верхний угол
    [55.7458, 37.6276], // Правый нижний угол
    [55.7458, 37.6176]  // Левый нижний угол
];
```

**3. Проверьте стили**
```javascript
// Убедитесь, что цвета видны на фоне карты
const polygon = mapKit.addPolygon(coords, {
    fillColor: '#FF0000',    // Красный цвет
    strokeColor: '#000000',  // Черная граница
    opacity: 0.7             // Прозрачность
});
```

### События не работают

#### Симптомы
- Клики по карте не обрабатываются
- События зума не срабатывают

#### Решения

**1. Проверьте подписку на события**
```javascript
// Убедитесь, что события подписаны после инициализации карты
mapKit.init().then(() => {
    // Подписываемся на события только после инициализации
    mapKit.on('click', (event) => {
        console.log('Клик по карте:', event.get('coords'));
    });
});
```

**2. Проверьте обработчики событий**
```javascript
// Добавьте логирование для отладки
mapKit.on('click', (event) => {
    console.log('Событие клика получено');
    try {
        const coords = event.get('coords');
        console.log('Координаты:', coords);
        // Ваша логика
    } catch (error) {
        console.error('Ошибка в обработчике события:', error);
    }
});
```

## 📱 Проблемы Android

### Приложение не собирается

#### Ошибка: "Could not resolve dependencies"

**Решение:**
```bash
# Очистите кэш Gradle
./gradlew clean

# Обновите зависимости
./gradlew --refresh-dependencies

# Синхронизируйте проект
./gradlew build
```

#### Ошибка: "SDK location not found"

**Решение:**
```bash
# Создайте local.properties
echo "sdk.dir=$ANDROID_HOME" > local.properties

# Или укажите путь вручную
echo "sdk.dir=C:\\Users\\YourUsername\\AppData\\Local\\Android\\Sdk" > local.properties
```

### Карта не отображается в приложении

#### Симптомы
- Пустой экран вместо карты
- Ошибки в логах

#### Решения

**1. Проверьте разрешения**
```xml
<!-- AndroidManifest.xml -->
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
```

**2. Проверьте API ключ**
```kotlin
// Убедитесь, что ключ указан правильно
val config = MapConfig(
    apiKey = BuildConfig.YANDEX_MAPS_API_KEY,
    center = Coordinates(55.7558, 37.6176),
    zoom = 10
)
```

**3. Проверьте логи**
```kotlin
// Добавьте логирование
Log.d("MapKit", "Инициализация карты...")
try {
    mapKit.init(config)
    Log.d("MapKit", "Карта успешно инициализирована")
} catch (error: Exception) {
    Log.e("MapKit", "Ошибка инициализации карты", error)
}
```

### Проблемы с производительностью

#### Симптомы
- Медленная работа приложения
- Задержки при взаимодействии с картой

#### Решения

**1. Оптимизируйте рендеринг**
```kotlin
// Используйте ViewGroup для группировки элементов
val mapContainer = findViewById<ViewGroup>(R.id.map_container)
mapContainer.setLayerType(View.LAYER_TYPE_HARDWARE, null)
```

**2. Кэшируйте данные**
```kotlin
// Кэшируйте результаты геокодирования
private val geocodeCache = mutableMapOf<String, Coordinates>()

fun geocode(address: String): Coordinates? {
    return geocodeCache[address] ?: run {
        val coords = performGeocoding(address)
        geocodeCache[address] = coords
        coords
    }
}
```

## 🍎 Проблемы iOS

### Приложение не собирается в Xcode

#### Ошибка: "No such module 'MapKit'"

**Решение:**
```bash
# Перейдите в папку iOS приложения
cd iosApp

# Установите зависимости
pod install

# Откройте .xcworkspace файл (не .xcodeproj)
open MapsYandexAPI.xcworkspace
```

#### Ошибка: "Signing issues"

**Решение:**
1. Откройте проект в Xcode
2. Выберите target приложения
3. Перейдите в "Signing & Capabilities"
4. Убедитесь, что выбран правильный Team
5. Проверьте Bundle Identifier

### Карта не отображается в iOS приложении

#### Симптомы
- Пустой экран вместо карты
- Ошибки в консоли Xcode

#### Решения

**1. Проверьте Info.plist**
```xml
<!-- Добавьте необходимые разрешения -->
<key>NSLocationWhenInUseUsageDescription</key>
<string>Приложению необходим доступ к местоположению для отображения карты</string>

<key>NSLocationAlwaysAndWhenInUseUsageDescription</key>
<string>Приложению необходим доступ к местоположению для отображения карты</string>
```

**2. Проверьте API ключ**
```swift
// Убедитесь, что ключ указан правильно
let config = MapConfig(
    apiKey: "YOUR_API_KEY",
    center: Coordinates(latitude: 55.7558, longitude: 37.6176),
    zoom: 10
)
```

**3. Проверьте делегаты**
```swift
// Убедитесь, что делегаты установлены
class MapViewController: UIViewController, MKMapViewDelegate {
    @IBOutlet weak var mapView: MKMapView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        mapView.delegate = self
    }
}
```

## 🔨 Проблемы сборки

### Gradle ошибки

#### Ошибка: "Java version mismatch"

**Решение:**
```bash
# Проверьте версию Java
java -version

# Убедитесь, что используется Java 11+
export JAVA_HOME=/path/to/java11
```

#### Ошибка: "Memory issues during build"

**Решение:**
```bash
# Увеличьте память для Gradle
export GRADLE_OPTS="-Xmx2048m -XX:MaxPermSize=512m"

# Или в gradle.properties
org.gradle.jvmargs=-Xmx2048m -XX:MaxPermSize=512m
```

### Kotlin Multiplatform ошибки

#### Ошибка: "Unresolved reference"

**Решение:**
```kotlin
// Убедитесь, что зависимости правильно указаны
kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib-common")
            }
        }
    }
}
```

#### Ошибка: "Platform-specific code not found"

**Решение:**
```kotlin
// Используйте expect/actual для платформо-специфичного кода
expect fun getPlatformName(): String

// Android реализация
actual fun getPlatformName(): String = "Android"

// iOS реализация
actual fun getPlatformName(): String = "iOS"
```

## 🔌 Проблемы API

### Ошибки геокодирования

#### Ошибка: "Geocoding failed"

**Причины и решения:**

**1. Неверный формат адреса**
```javascript
// Используйте правильный формат
const address = "Москва, Красная площадь, 1"; // Полный адрес

// Не используйте
const address = "Красная площадь"; // Слишком короткий
```

**2. Превышение лимитов**
```javascript
// Добавьте задержку между запросами
function geocodeWithDelay(address) {
    return new Promise((resolve) => {
        setTimeout(() => {
            mapKit.geocode(address).then(resolve);
        }, 1000); // 1 секунда задержки
    });
}
```

**3. Проблемы с сетью**
```javascript
// Добавьте повторные попытки
async function geocodeWithRetry(address, maxRetries = 3) {
    for (let i = 0; i < maxRetries; i++) {
        try {
            return await mapKit.geocode(address);
        } catch (error) {
            if (i === maxRetries - 1) throw error;
            await new Promise(resolve => setTimeout(resolve, 1000 * (i + 1)));
        }
    }
}
```

### Ошибки расчета расстояний

#### Ошибка: "Invalid coordinates"

**Решение:**
```javascript
// Валидируйте координаты перед расчетом
function validateCoordinates(coords) {
    if (!Array.isArray(coords) || coords.length !== 2) {
        throw new Error('Координаты должны быть массивом из двух элементов');
    }
    
    const [lat, lng] = coords;
    if (lat < -90 || lat > 90) {
        throw new Error('Широта должна быть в диапазоне -90..90');
    }
    if (lng < -180 || lng > 180) {
        throw new Error('Долгота должна быть в диапазоне -180..180');
    }
    
    return true;
}

// Использование
try {
    validateCoordinates(point1);
    validateCoordinates(point2);
    const distance = mapKit.calculateDistance(point1, point2);
} catch (error) {
    console.error('Ошибка валидации координат:', error.message);
}
```

## ❓ Часто задаваемые вопросы

### Q: Как изменить центр карты по умолчанию?

**A:** Измените настройки в `config.js`:
```javascript
const config = {
    map: {
        defaultCenter: [59.9311, 30.3609], // Санкт-Петербург
        defaultZoom: 12
    }
};
```

### Q: Как добавить кастомный маркер?

**A:** Используйте опции маркера:
```javascript
const marker = mapKit.addMarker([55.7558, 37.6176], {
    title: 'Мой маркер',
    content: 'Описание маркера',
    color: '#FF0000',
    icon: 'path/to/custom-icon.png'
});
```

### Q: Как обработать клик по маркеру?

**A:** Подпишитесь на событие клика:
```javascript
mapKit.on('click', (event) => {
    const coords = event.get('coords');
    const target = event.get('target');
    
    if (target && target.properties) {
        console.log('Клик по маркеру:', target.properties.get('title'));
    }
});
```

### Q: Как изменить язык карты?

**A:** Укажите язык в URL API:
```html
<script src="https://api-maps.yandex.ru/2.1/?apikey=YOUR_KEY&lang=en_US"></script>
```

### Q: Как добавить поиск по адресу?

**A:** Используйте геокодирование:
```javascript
const searchInput = document.getElementById('search');
searchInput.addEventListener('change', async () => {
    try {
        const coords = await mapKit.geocode(searchInput.value);
        mapKit.setCenter(coords);
        mapKit.addMarker(coords, { title: searchInput.value });
    } catch (error) {
        console.error('Адрес не найден:', error);
    }
});
```

### Q: Как сохранить состояние карты?

**A:** Используйте localStorage:
```javascript
// Сохранение
function saveMapState() {
    const state = {
        center: mapKit.getCenter(),
        zoom: mapKit.getZoom(),
        objects: mapKit.getObjects()
    };
    localStorage.setItem('mapState', JSON.stringify(state));
}

// Восстановление
function restoreMapState() {
    const state = JSON.parse(localStorage.getItem('mapState'));
    if (state) {
        mapKit.setCenter(state.center);
        mapKit.setZoom(state.zoom);
        // Восстановление объектов
    }
}
```

### Q: Как оптимизировать производительность при большом количестве маркеров?

**A:** Используйте кластеризацию:
```javascript
// Простая кластеризация
function clusterMarkers(markers, radius = 50) {
    const clusters = [];
    const processed = new Set();
    
    markers.forEach((marker, index) => {
        if (processed.has(index)) return;
        
        const cluster = [marker];
        processed.add(index);
        
        markers.forEach((otherMarker, otherIndex) => {
            if (index !== otherIndex && !processed.has(otherIndex)) {
                const distance = mapKit.calculateDistance(marker, otherMarker);
                if (distance <= radius) {
                    cluster.push(otherMarker);
                    processed.add(otherIndex);
                }
            }
        });
        
        if (cluster.length > 1) {
            clusters.push(cluster);
        }
    });
    
    return clusters;
}
```

## 📞 Получение дополнительной помощи

Если вы не нашли решение своей проблемы:

1. **Проверьте GitHub Issues** - возможно, проблема уже обсуждалась
2. **Создайте новый Issue** - опишите проблему подробно
3. **Присоединитесь к обсуждениям** - задайте вопрос сообществу
4. **Обратитесь к документации** - проверьте официальную документацию Яндекс Карт

### Полезные ресурсы

- [Документация Яндекс Карт](https://yandex.ru/dev/maps/)
- [GitHub Issues](https://github.com/your-username/MapsYandexAPI/issues)
- [GitHub Discussions](https://github.com/your-username/MapsYandexAPI/discussions)
- [Stack Overflow](https://stackoverflow.com/questions/tagged/yandex-maps)

---

**Надеемся, что это руководство помогло решить вашу проблему!** 🎉

Если у вас есть предложения по улучшению документации или вы нашли ошибку, создайте Issue или Pull Request.
