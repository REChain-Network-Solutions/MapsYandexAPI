# 📚 Справочник API Яндекс Карт

## 🎯 Обзор API

Этот документ содержит полное описание всех доступных методов, свойств и событий API проекта Яндекс Карт.

## 🌐 Веб-API (JavaScript)

### Основной класс: `MapKit`

#### Конструктор
```javascript
const mapKit = new MapKit(config);
```

**Параметры:**
- `config` (Object) - Конфигурация карты

**Пример:**
```javascript
const config = {
    apiKey: 'YOUR_API_KEY',
    center: [55.7558, 37.6176],
    zoom: 10,
    container: 'map'
};

const mapKit = new MapKit(config);
```

#### Методы инициализации

##### `init()`
Инициализирует карту с заданными параметрами.

```javascript
mapKit.init();
```

**Возвращает:** Promise<void>

**Пример:**
```javascript
mapKit.init().then(() => {
    console.log('Карта успешно инициализирована');
}).catch(error => {
    console.error('Ошибка инициализации:', error);
});
```

#### Методы работы с объектами

##### `addMarker(coords, options?)`
Добавляет маркер на карту.

**Параметры:**
- `coords` (Array) - Координаты [широта, долгота]
- `options` (Object, опционально) - Дополнительные настройки

**Опции маркера:**
```javascript
{
    title: 'Название маркера',
    content: 'Описание маркера',
    color: '#FF0000',
    size: 'medium',
    icon: 'custom-icon.png'
}
```

**Пример:**
```javascript
// Простой маркер
mapKit.addMarker([55.7558, 37.6176]);

// Маркер с опциями
mapKit.addMarker([55.7558, 37.6176], {
    title: 'Красная площадь',
    content: 'Главная площадь Москвы',
    color: '#FF0000'
});
```

**Возвращает:** Object - Созданный маркер

##### `addRandomMarker()`
Добавляет маркер в случайном месте на карте.

```javascript
const marker = mapKit.addRandomMarker();
```

**Возвращает:** Object - Созданный маркер

##### `addPolygon(coords, options?)`
Добавляет полигон на карту.

**Параметры:**
- `coords` (Array) - Массив координат [[широта, долгота], ...]
- `options` (Object, опционально) - Дополнительные настройки

**Опции полигона:**
```javascript
{
    fillColor: '#FF0000',
    strokeColor: '#000000',
    strokeWidth: 2,
    opacity: 0.7
}
```

**Пример:**
```javascript
const coords = [
    [55.7558, 37.6176],
    [55.7558, 37.6276],
    [55.7458, 37.6276],
    [55.7458, 37.6176]
];

mapKit.addPolygon(coords, {
    fillColor: '#FF0000',
    strokeColor: '#000000'
});
```

**Возвращает:** Object - Созданный полигон

##### `addRandomPolygon()`
Добавляет полигон в случайном месте на карте.

```javascript
const polygon = mapKit.addRandomPolygon();
```

**Возвращает:** Object - Созданный полигон

##### `addPolyline(coords, options?)`
Добавляет линию на карту.

**Параметры:**
- `coords` (Array) - Массив координат [[широта, долгота], ...]
- `options` (Object, опционально) - Дополнительные настройки

**Опции линии:**
```javascript
{
    strokeColor: '#0000FF',
    strokeWidth: 3,
    opacity: 0.8
}
```

**Пример:**
```javascript
const coords = [
    [55.7558, 37.6176],
    [55.7558, 37.6276]
];

mapKit.addPolyline(coords, {
    strokeColor: '#0000FF',
    strokeWidth: 3
});
```

**Возвращает:** Object - Созданная линия

##### `addRandomPolyline()`
Добавляет линию в случайном месте на карте.

```javascript
const polyline = mapKit.addRandomPolyline();
```

**Возвращает:** Object - Созданная линия

#### Методы управления картой

##### `setCenter(coords)`
Устанавливает центр карты.

**Параметры:**
- `coords` (Array) - Координаты [широта, долгота]

**Пример:**
```javascript
mapKit.setCenter([59.9311, 30.3609]); // Санкт-Петербург
```

##### `getCenter()`
Получает текущий центр карты.

**Возвращает:** Array - Координаты центра [широта, долгота]

**Пример:**
```javascript
const center = mapKit.getCenter();
console.log(`Центр карты: ${center[0]}, ${center[1]}`);
```

##### `setZoom(level)`
Устанавливает уровень зума карты.

**Параметры:**
- `level` (Number) - Уровень зума (0-19)

**Пример:**
```javascript
mapKit.setZoom(15); // Увеличенный зум
```

##### `getZoom()`
Получает текущий уровень зума карты.

**Возвращает:** Number - Уровень зума

**Пример:**
```javascript
const zoom = mapKit.getZoom();
console.log(`Текущий зум: ${zoom}`);
```

##### `fitBounds(coords)`
Подгоняет карту под заданные координаты.

**Параметры:**
- `coords` (Array) - Массив координат [[широта, долгота], ...]

**Пример:**
```javascript
const bounds = [
    [55.7558, 37.6176],
    [59.9311, 30.3609]
];
mapKit.fitBounds(bounds);
```

#### Методы управления объектами

##### `removeObject(object)`
Удаляет объект с карты.

**Параметры:**
- `object` (Object) - Объект для удаления

**Пример:**
```javascript
const marker = mapKit.addRandomMarker();
// ... позже
mapKit.removeObject(marker);
```

##### `clearAll()`
Удаляет все объекты с карты.

```javascript
mapKit.clearAll();
```

##### `getObjects()`
Получает все объекты на карте.

**Возвращает:** Array - Массив объектов

**Пример:**
```javascript
const objects = mapKit.getObjects();
console.log(`На карте ${objects.length} объектов`);
```

#### Методы геокодирования

##### `geocode(address)`
Преобразует адрес в координаты.

**Параметры:**
- `address` (String) - Адрес для геокодирования

**Возвращает:** Promise<Array> - Координаты [широта, долгота]

**Пример:**
```javascript
mapKit.geocode('Москва, Красная площадь').then(coords => {
    console.log(`Координаты: ${coords[0]}, ${coords[1]}`);
    mapKit.setCenter(coords);
}).catch(error => {
    console.error('Ошибка геокодирования:', error);
});
```

##### `reverseGeocode(coords)`
Преобразует координаты в адрес.

**Параметры:**
- `coords` (Array) - Координаты [широта, долгота]

**Возвращает:** Promise<String> - Адрес

**Пример:**
```javascript
mapKit.reverseGeocode([55.7558, 37.6176]).then(address => {
    console.log(`Адрес: ${address}`);
}).catch(error => {
    console.error('Ошибка обратного геокодирования:', error);
});
```

#### Методы расчета расстояний

##### `calculateDistance(point1, point2)`
Рассчитывает расстояние между двумя точками.

**Параметры:**
- `point1` (Array) - Координаты первой точки [широта, долгота]
- `point2` (Array) - Координаты второй точки [широта, долгота]

**Возвращает:** Number - Расстояние в метрах

**Пример:**
```javascript
const moscow = [55.7558, 37.6176];
const spb = [59.9311, 30.3609];
const distance = mapKit.calculateDistance(moscow, spb);
console.log(`Расстояние: ${distance} метров`);
```

#### События карты

##### `on(event, callback)`
Подписывается на событие карты.

**Параметры:**
- `event` (String) - Название события
- `callback` (Function) - Функция-обработчик

**Доступные события:**
- `click` - Клик по карте
- `zoom` - Изменение зума
- `drag` - Перетаскивание карты
- `boundschange` - Изменение границ

**Пример:**
```javascript
mapKit.on('click', (event) => {
    const coords = event.get('coords');
    console.log(`Клик по координатам: ${coords[0]}, ${coords[1]}`);
});

mapKit.on('zoom', (event) => {
    const zoom = event.get('newZoom');
    console.log(`Новый зум: ${zoom}`);
});
```

##### `off(event, callback?)`
Отписывается от события карты.

**Параметры:**
- `event` (String) - Название события
- `callback` (Function, опционально) - Функция-обработчик (если не указана, отписывается от всех обработчиков события)

**Пример:**
```javascript
const handler = (event) => console.log('Событие');
mapKit.on('click', handler);

// Отписаться от конкретного обработчика
mapKit.off('click', handler);

// Отписаться от всех обработчиков события
mapKit.off('click');
```

## 📱 Мобильный API (Kotlin)

### Основной класс: `MapKit`

#### Интерфейс
```kotlin
expect class MapKit {
    fun init(config: MapConfig)
    fun addMarker(coords: Coordinates, options: MarkerOptions? = null): Marker
    fun addPolygon(coords: List<Coordinates>, options: PolygonOptions? = null): Polygon
    fun addPolyline(coords: List<Coordinates>, options: PolylineOptions? = null): Polyline
    fun removeObject(object: MapObject)
    fun clearAll()
    fun setCenter(coords: Coordinates)
    fun getCenter(): Coordinates
    fun setZoom(level: Int)
    fun getZoom(): Int
    fun fitBounds(coords: List<Coordinates>)
    fun on(event: MapEvent, callback: (MapEventData) -> Unit)
    fun off(event: MapEvent, callback: (MapEventData) -> Unit)?
}
```

#### Модели данных

##### `MapConfig`
```kotlin
data class MapConfig(
    val apiKey: String,
    val center: Coordinates,
    val zoom: Int,
    val language: String = "ru_RU",
    val mapType: MapType = MapType.NORMAL
)
```

##### `Coordinates`
```kotlin
data class Coordinates(
    val latitude: Double,
    val longitude: Double
)
```

##### `MarkerOptions`
```kotlin
data class MarkerOptions(
    val title: String? = null,
    val content: String? = null,
    val color: String = "#FF0000",
    val size: MarkerSize = MarkerSize.MEDIUM,
    val icon: String? = null
)
```

##### `PolygonOptions`
```kotlin
data class PolygonOptions(
    val fillColor: String = "#FF0000",
    val strokeColor: String = "#000000",
    val strokeWidth: Int = 2,
    val opacity: Double = 0.7
)
```

##### `PolylineOptions`
```kotlin
data class PolylineOptions(
    val strokeColor: String = "#0000FF",
    val strokeWidth: Int = 3,
    val opacity: Double = 0.8
)
```

#### События

##### `MapEvent`
```kotlin
enum class MapEvent {
    CLICK,
    ZOOM,
    DRAG,
    BOUNDS_CHANGE
}
```

##### `MapEventData`
```kotlin
sealed class MapEventData {
    data class ClickEvent(val coordinates: Coordinates) : MapEventData()
    data class ZoomEvent(val oldZoom: Int, val newZoom: Int) : MapEventData()
    data class DragEvent(val oldCenter: Coordinates, val newCenter: Coordinates) : MapEventData()
    data class BoundsChangeEvent(val bounds: Bounds) : MapEventData()
}
```

## 🔧 Утилиты

### Глобальные функции

#### `createCircle(center, radius)`
Создает круг с заданным радиусом.

**Параметры:**
- `center` (Array) - Центр круга [широта, долгота]
- `radius` (Number) - Радиус в метрах

**Возвращает:** Array - Массив координат круга

**Пример:**
```javascript
const center = [55.7558, 37.6176];
const radius = 5000; // 5 км
const circleCoords = createCircle(center, radius);
mapKit.addPolygon(circleCoords);
```

#### `generateRandomColor()`
Генерирует случайный цвет в формате HEX.

**Возвращает:** String - Цвет в формате #RRGGBB

**Пример:**
```javascript
const color = generateRandomColor();
console.log(`Случайный цвет: ${color}`);
```

#### `formatDistance(meters)`
Форматирует расстояние в читаемом виде.

**Параметры:**
- `meters` (Number) - Расстояние в метрах

**Возвращает:** String - Отформатированное расстояние

**Пример:**
```javascript
const distance = 1500;
const formatted = formatDistance(distance);
console.log(formatted); // "1.5 км"
```

## 📊 Примеры использования

### Базовый пример
```javascript
// Инициализация
const mapKit = new MapKit({
    apiKey: 'YOUR_API_KEY',
    center: [55.7558, 37.6176],
    zoom: 10,
    container: 'map'
});

// Запуск
mapKit.init().then(() => {
    // Добавление маркера
    const marker = mapKit.addMarker([55.7558, 37.6176], {
        title: 'Красная площадь',
        content: 'Главная площадь Москвы'
    });
    
    // Обработка кликов
    mapKit.on('click', (event) => {
        const coords = event.get('coords');
        mapKit.addMarker(coords);
    });
});
```

### Работа с полигонами
```javascript
// Создание треугольника
const triangle = [
    [55.7558, 37.6176],
    [55.7558, 37.6276],
    [55.7458, 37.6176]
];

const polygon = mapKit.addPolygon(triangle, {
    fillColor: '#FF0000',
    strokeColor: '#000000',
    opacity: 0.5
});
```

### Геокодирование и маршруты
```javascript
// Поиск адреса
mapKit.geocode('Москва, Тверская улица').then(coords => {
    mapKit.setCenter(coords);
    
    // Создание маркера
    mapKit.addMarker(coords, {
        title: 'Тверская улица',
        content: 'Главная улица Москвы'
    });
});
```

## 🚨 Обработка ошибок

### Типы ошибок
```javascript
class MapKitError extends Error {
    constructor(message, code, details) {
        super(message);
        this.name = 'MapKitError';
        this.code = code;
        this.details = details;
    }
}
```

### Обработка ошибок
```javascript
try {
    await mapKit.init();
} catch (error) {
    if (error instanceof MapKitError) {
        switch (error.code) {
            case 'INVALID_API_KEY':
                console.error('Неверный API ключ');
                break;
            case 'NETWORK_ERROR':
                console.error('Ошибка сети');
                break;
            default:
                console.error('Неизвестная ошибка:', error.message);
        }
    }
}
```

## 📈 Производительность

### Рекомендации
1. **Кластеризация маркеров** для больших объемов данных
2. **Ленивая загрузка** объектов карты
3. **Кэширование** результатов геокодирования
4. **Дебаунсинг** событий карты
5. **Оптимизация** размера полигонов

### Ограничения
- Максимум 1000 объектов на карте одновременно
- Размер полигона не более 1000 точек
- Частота событий не более 100 в секунду

## 🔒 Безопасность

### API ключи
- Храните ключи в безопасном месте
- Не включайте ключи в публичный код
- Используйте ограничения по доменам

### Валидация данных
- Проверяйте все входные координаты
- Ограничивайте размер пользовательских данных
- Используйте HTTPS для всех запросов
