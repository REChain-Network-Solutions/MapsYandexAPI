/**
 * Яндекс Карты - Базовый API
 * Основные функции для работы с картами
 */

class YandexMapKit {
    constructor() {
        this.map = null;
        this.objects = [];
        this.markers = [];
        this.polygons = [];
        this.polylines = [];
        this.isInitialized = false;
        
        this.init();
    }

    /**
     * Инициализация карты
     */
    init() {
        // Ждем загрузки API Яндекс Карт
        if (typeof ymaps !== 'undefined') {
            this.createMap();
        } else {
            // Если API еще не загружен, ждем
            window.addEventListener('load', () => {
                if (typeof ymaps !== 'undefined') {
                    this.createMap();
                } else {
                    console.error('API Яндекс Карт не загружен');
                }
            });
        }
    }

    /**
     * Создание карты
     */
    createMap() {
        try {
            // Создаем карту с центром в Москве
            this.map = new ymaps.Map('map', {
                center: [55.7558, 37.6176], // Москва
                zoom: 10,
                controls: ['zoomControl', 'fullscreenControl', 'geolocationControl']
            });

            this.isInitialized = true;
            this.setupEventListeners();
            this.updateInfo();
            
            console.log('Карта успешно инициализирована');
        } catch (error) {
            console.error('Ошибка при создании карты:', error);
        }
    }

    /**
     * Настройка обработчиков событий
     */
    setupEventListeners() {
        // Обработчики для кнопок
        document.getElementById('addMarker').addEventListener('click', () => this.addRandomMarker());
        document.getElementById('addPolygon').addEventListener('click', () => this.addRandomPolygon());
        document.getElementById('addPolyline').addEventListener('click', () => this.addRandomPolyline());
        document.getElementById('clearAll').addEventListener('click', () => this.clearAll());
        document.getElementById('getCenter').addEventListener('click', () => this.getCenter());
        document.getElementById('setZoom').addEventListener('click', () => this.setRandomZoom());

        // Обработчики событий карты
        this.map.events.add('boundschange', () => this.updateInfo());
        this.map.events.add('zoomchange', () => this.updateInfo());
    }

    /**
     * Добавление случайного маркера
     */
    addRandomMarker() {
        if (!this.isInitialized) return;

        const center = this.map.getCenter();
        const lat = center[0] + (Math.random() - 0.5) * 0.1;
        const lon = center[1] + (Math.random() - 0.5) * 0.1;

        const marker = new ymaps.Placemark([lat, lon], {
            balloonContent: `Маркер ${this.markers.length + 1}`
        }, {
            preset: 'islands#blueDotIcon'
        });

        this.map.geoObjects.add(marker);
        this.markers.push(marker);
        this.objects.push(marker);
        
        this.updateInfo();
        console.log(`Добавлен маркер: [${lat.toFixed(6)}, ${lon.toFixed(6)}]`);
    }

    /**
     * Добавление случайного полигона
     */
    addRandomPolygon() {
        if (!this.isInitialized) return;

        const center = this.map.getCenter();
        const radius = 0.01;
        const points = [];
        
        // Создаем многоугольник с случайными точками
        for (let i = 0; i < 5; i++) {
            const angle = (i / 5) * 2 * Math.PI;
            const lat = center[0] + radius * Math.cos(angle) + (Math.random() - 0.5) * 0.005;
            const lon = center[1] + radius * Math.sin(angle) + (Math.random() - 0.5) * 0.005;
            points.push([lat, lon]);
        }

        const polygon = new ymaps.Polygon([points], {
            balloonContent: `Полигон ${this.polygons.length + 1}`
        }, {
            fillColor: this.getRandomColor(),
            strokeColor: '#000000',
            strokeWidth: 2,
            fillOpacity: 0.6
        });

        this.map.geoObjects.add(polygon);
        this.polygons.push(polygon);
        this.objects.push(polygon);
        
        this.updateInfo();
        console.log(`Добавлен полигон с ${points.length} точками`);
    }

    /**
     * Добавление случайной линии
     */
    addRandomPolyline() {
        if (!this.isInitialized) return;

        const center = this.map.getCenter();
        const points = [];
        
        // Создаем линию с случайными точками
        for (let i = 0; i < 3; i++) {
            const lat = center[0] + (Math.random() - 0.5) * 0.1;
            const lon = center[1] + (Math.random() - 0.5) * 0.1;
            points.push([lat, lon]);
        }

        const polyline = new ymaps.Polyline([points], {
            balloonContent: `Линия ${this.polylines.length + 1}`
        }, {
            strokeColor: this.getRandomColor(),
            strokeWidth: 3
        });

        this.map.geoObjects.add(polyline);
        this.polylines.push(polyline);
        this.objects.push(polyline);
        
        this.updateInfo();
        console.log(`Добавлена линия с ${points.length} точками`);
    }

    /**
     * Очистка всех объектов
     */
    clearAll() {
        if (!this.isInitialized) return;

        this.map.geoObjects.removeAll();
        this.markers = [];
        this.polygons = [];
        this.polylines = [];
        this.objects = [];
        
        this.updateInfo();
        console.log('Все объекты удалены');
    }

    /**
     * Получение центра карты
     */
    getCenter() {
        if (!this.isInitialized) return;

        const center = this.map.getCenter();
        const coords = `[${center[0].toFixed(6)}, ${center[1].toFixed(6)}]`;
        
        // Показываем информацию в alert (можно заменить на более красивое уведомление)
        alert(`Центр карты: ${coords}`);
        console.log('Центр карты:', coords);
        
        return center;
    }

    /**
     * Установка случайного зума
     */
    setRandomZoom() {
        if (!this.isInitialized) return;

        const newZoom = Math.floor(Math.random() * 15) + 5; // Зум от 5 до 19
        this.map.setZoom(newZoom);
        
        console.log(`Установлен зум: ${newZoom}`);
    }

    /**
     * Обновление информации о карте
     */
    updateInfo() {
        if (!this.isInitialized) return;

        const center = this.map.getCenter();
        const zoom = this.map.getZoom();
        
        document.getElementById('centerCoords').textContent = 
            `[${center[0].toFixed(6)}, ${center[1].toFixed(6)}]`;
        document.getElementById('currentZoom').textContent = zoom;
        document.getElementById('objectCount').textContent = this.objects.length;
    }

    /**
     * Генерация случайного цвета
     */
    getRandomColor() {
        const colors = [
            '#FF6B6B', '#4ECDC4', '#45B7D1', '#96CEB4', '#FFEAA7',
            '#DDA0DD', '#98D8C8', '#F7DC6F', '#BB8FCE', '#85C1E9'
        ];
        return colors[Math.floor(Math.random() * colors.length)];
    }

    /**
     * Поиск по адресу
     */
    async searchByAddress(address) {
        if (!this.isInitialized) return null;

        try {
            const geocoder = ymaps.geocode(address);
            const result = await geocoder;
            
            if (result.geoObjects.getLength() > 0) {
                const coords = result.geoObjects.get(0).geometry.getCoordinates();
                this.map.setCenter(coords, 15);
                console.log(`Найден адрес: ${address} -> [${coords[0]}, ${coords[1]}]`);
                return coords;
            } else {
                console.log(`Адрес не найден: ${address}`);
                return null;
            }
        } catch (error) {
            console.error('Ошибка при поиске адреса:', error);
            return null;
        }
    }

    /**
     * Измерение расстояния между двумя точками
     */
    calculateDistance(point1, point2) {
        if (!this.isInitialized) return 0;

        try {
            const distance = ymaps.coordSystem.geo.getDistance(point1, point2);
            return Math.round(distance);
        } catch (error) {
            console.error('Ошибка при расчете расстояния:', error);
            return 0;
        }
    }

    /**
     * Получение информации об объекте по клику
     */
    enableClickInfo() {
        if (!this.isInitialized) return;

        this.map.events.add('click', (e) => {
            const coords = e.get('coords');
            console.log(`Клик по координатам: [${coords[0].toFixed(6)}, ${coords[1].toFixed(6)}]`);
            
            // Можно добавить маркер по клику
            const marker = new ymaps.Placemark(coords, {
                balloonContent: `Клик: [${coords[0].toFixed(6)}, ${coords[1].toFixed(6)}]`
            });
            
            this.map.geoObjects.add(marker);
            this.markers.push(marker);
            this.objects.push(marker);
            this.updateInfo();
        });
    }

    /**
     * Экспорт карты в изображение
     */
    exportToImage() {
        if (!this.isInitialized) return;

        try {
            // Создаем скриншот карты
            this.map.getBounds().then(bounds => {
                console.log('Границы карты:', bounds);
                // Здесь можно добавить логику экспорта
            });
        } catch (error) {
            console.error('Ошибка при экспорте:', error);
        }
    }
}

// Инициализация приложения
document.addEventListener('DOMContentLoaded', () => {
    console.log('Инициализация Яндекс Карт...');
    
    // Создаем экземпляр API
    window.mapKit = new YandexMapKit();
    
    // Включаем получение информации по клику
    setTimeout(() => {
        if (window.mapKit.isInitialized) {
            window.mapKit.enableClickInfo();
        }
    }, 1000);
});

// Глобальные функции для удобства использования
window.addMarker = () => window.mapKit?.addRandomMarker();
window.addPolygon = () => window.mapKit?.addRandomPolygon();
window.addPolyline = () => window.mapKit?.addRandomPolyline();
window.clearAll = () => window.mapKit?.clearAll();
window.getCenter = () => window.mapKit?.getCenter();
window.setZoom = () => window.mapKit?.setRandomZoom();
