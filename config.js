/**
 * Конфигурация для Яндекс Карт
 * Здесь можно настроить различные параметры карты
 */

const MapConfig = {
    // Основные настройки карты
    map: {
        // Центр карты по умолчанию (Москва)
        defaultCenter: [55.7558, 37.6176],
        
        // Начальный зум
        defaultZoom: 10,
        
        // Минимальный и максимальный зум
        minZoom: 3,
        maxZoom: 19,
        
        // Элементы управления
        controls: [
            'zoomControl',           // Кнопки зума
            'fullscreenControl',     // Полноэкранный режим
            'geolocationControl',    // Определение местоположения
            'searchControl',         // Поиск
            'trafficControl',        // Пробки
            'typeSelector'           // Переключатель типов карты
        ],
        
        // Тип карты по умолчанию
        type: 'yandex#map',
        
        // Доступные типы карт
        availableTypes: [
            'yandex#map',           // Схема
            'yandex#satellite',     // Спутник
            'yandex#hybrid',        // Гибрид
            'yandex#publicMap'      // Народная карта
        ]
    },
    
    // Настройки маркеров
    markers: {
        // Цвета по умолчанию
        colors: {
            default: '#4facfe',
            selected: '#ff6b6b',
            route: '#00ff00',
            destination: '#ff0000'
        },
        
        // Иконки по умолчанию
        presets: {
            default: 'islands#blueDotIcon',
            selected: 'islands#redDotIcon',
            route: 'islands#greenDotIcon',
            destination: 'islands#redDotIcon'
        },
        
        // Размер иконок
        iconSize: [32, 32],
        
        // Смещение иконок
        iconOffset: [-16, -16]
    },
    
    // Настройки полигонов
    polygons: {
        // Цвета по умолчанию
        colors: {
            fill: '#4facfe',
            stroke: '#000000',
            selected: '#ff6b6b'
        },
        
        // Прозрачность
        opacity: {
            fill: 0.6,
            stroke: 1.0
        },
        
        // Толщина границы
        strokeWidth: 2
    },
    
    // Настройки линий
    polylines: {
        // Цвета по умолчанию
        colors: {
            default: '#4facfe',
            route: '#00ff00',
            selected: '#ff6b6b'
        },
        
        // Толщина линий
        strokeWidth: 3,
        
        // Стиль линий
        strokeStyle: 'solid'
    },
    
    // Настройки кластеризации
    clustering: {
        // Включить кластеризацию по умолчанию
        enabled: true,
        
        // Максимальное расстояние для кластеризации
        maxDistance: 50,
        
        // Максимальное количество маркеров в кластере
        maxMarkers: 10,
        
        // Пресет для кластеров
        preset: 'islands#blueClusterIcons'
    },
    
    // Настройки геокодирования
    geocoding: {
        // Язык по умолчанию
        language: 'ru_RU',
        
        // Результаты поиска
        results: 10,
        
        // Автоматическое центрирование при поиске
        autoCenter: true,
        
        // Зум при поиске
        searchZoom: 15
    },
    
    // Настройки маршрутов
    routing: {
        // Тип маршрута по умолчанию
        type: 'auto',
        
        // Доступные типы маршрутов
        availableTypes: ['auto', 'pedestrian', 'transit', 'bicycle'],
        
        // Показывать пробки
        showTraffic: true,
        
        // Показывать альтернативные маршруты
        showAlternatives: true
    },
    
    // Настройки интерфейса
    ui: {
        // Тема оформления
        theme: 'light',
        
        // Доступные темы
        availableThemes: ['light', 'dark'],
        
        // Анимации
        animations: {
            enabled: true,
            duration: 300
        },
        
        // Информационные панели
        infoPanels: {
            showCoordinates: true,
            showZoom: true,
            showObjectCount: true
        }
    },
    
    // Настройки производительности
    performance: {
        // Лимит объектов на карте
        maxObjects: 1000,
        
        // Автоматическая очистка старых объектов
        autoCleanup: true,
        
        // Задержка обновления информации
        updateDelay: 100
    },
    
    // Настройки локализации
    localization: {
        // Язык интерфейса
        language: 'ru',
        
        // Доступные языки
        availableLanguages: ['ru', 'en', 'tr', 'uk'],
        
        // Тексты интерфейса
        texts: {
            ru: {
                addMarker: 'Добавить маркер',
                addPolygon: 'Добавить полигон',
                addPolyline: 'Добавить линию',
                clearAll: 'Очистить все',
                getCenter: 'Получить центр',
                setZoom: 'Установить зум',
                searchAddress: 'Поиск адреса',
                measureDistance: 'Измерить расстояние',
                createRoute: 'Создать маршрут',
                clustering: 'Кластеризация'
            },
            en: {
                addMarker: 'Add marker',
                addPolygon: 'Add polygon',
                addPolyline: 'Add polyline',
                clearAll: 'Clear all',
                getCenter: 'Get center',
                setZoom: 'Set zoom',
                searchAddress: 'Search address',
                measureDistance: 'Measure distance',
                createRoute: 'Create route',
                clustering: 'Clustering'
            }
        }
    }
};

// Экспорт конфигурации
if (typeof module !== 'undefined' && module.exports) {
    module.exports = MapConfig;
} else {
    window.MapConfig = MapConfig;
}

// Функция для получения настроек
function getMapConfig(path) {
    if (!path) return MapConfig;
    
    const keys = path.split('.');
    let result = MapConfig;
    
    for (const key of keys) {
        if (result && typeof result === 'object' && key in result) {
            result = result[key];
        } else {
            return undefined;
        }
    }
    
    return result;
}

// Функция для установки настроек
function setMapConfig(path, value) {
    if (!path) return false;
    
    const keys = path.split('.');
    const lastKey = keys.pop();
    let current = MapConfig;
    
    for (const key of keys) {
        if (!(key in current) || typeof current[key] !== 'object') {
            current[key] = {};
        }
        current = current[key];
    }
    
    current[lastKey] = value;
    return true;
}

// Глобальные функции для работы с конфигурацией
window.getMapConfig = getMapConfig;
window.setMapConfig = setMapConfig;
