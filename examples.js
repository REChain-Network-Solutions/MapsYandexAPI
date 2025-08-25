/**
 * Примеры использования API Яндекс Карт
 * Этот файл демонстрирует различные способы работы с картами
 */

// Примеры будут доступны после инициализации карты
document.addEventListener('DOMContentLoaded', () => {
    // Ждем инициализации карты
    setTimeout(() => {
        if (window.mapKit && window.mapKit.isInitialized) {
            setupExamples();
        }
    }, 2000);
});

function setupExamples() {
    console.log('Настройка примеров использования API...');
    
    // Добавляем кнопки для примеров
    addExampleButtons();
    
    // Демонстрируем базовые функции
    demonstrateBasicFunctions();
}

function addExampleButtons() {
    const controls = document.querySelector('.controls');
    
    // Кнопка для поиска адреса
    const searchButton = document.createElement('button');
    searchButton.textContent = 'Поиск адреса';
    searchButton.onclick = () => searchAddressExample();
    controls.appendChild(searchButton);
    
    // Кнопка для измерения расстояния
    const distanceButton = document.createElement('button');
    distanceButton.textContent = 'Измерить расстояние';
    distanceButton.onclick = () => distanceExample();
    controls.appendChild(distanceButton);
    
    // Кнопка для создания маршрута
    const routeButton = document.createElement('button');
    routeButton.textContent = 'Создать маршрут';
    routeButton.onclick = () => routeExample();
    controls.appendChild(routeButton);
    
    // Кнопка для кластеризации
    const clusterButton = document.createElement('button');
    clusterButton.textContent = 'Кластеризация';
    clusterButton.onclick = () => clusteringExample();
    controls.appendChild(clusterButton);
}

/**
 * Пример 1: Поиск по адресу
 */
async function searchAddressExample() {
    const address = prompt('Введите адрес для поиска:', 'Москва, Красная площадь');
    if (!address) return;
    
    try {
        const coords = await window.mapKit.searchByAddress(address);
        if (coords) {
            // Добавляем маркер в найденную точку
            const marker = new ymaps.Placemark(coords, {
                balloonContent: `Найденный адрес: ${address}`
            }, {
                preset: 'islands#redDotIcon'
            });
            
            window.mapKit.map.geoObjects.add(marker);
            window.mapKit.markers.push(marker);
            window.mapKit.objects.push(marker);
            window.mapKit.updateInfo();
            
            alert(`Адрес найден! Координаты: [${coords[0].toFixed(6)}, ${coords[1].toFixed(6)}]`);
        } else {
            alert('Адрес не найден. Попробуйте другой адрес.');
        }
    } catch (error) {
        console.error('Ошибка при поиске:', error);
        alert('Произошла ошибка при поиске адреса.');
    }
}

/**
 * Пример 2: Измерение расстояния между двумя точками
 */
function distanceExample() {
    // Очищаем предыдущие точки
    window.mapKit.clearAll();
    
    // Создаем две точки для измерения
    const point1 = [55.7558, 37.6176]; // Москва
    const point2 = [59.9311, 30.3609]; // Санкт-Петербург
    
    // Добавляем маркеры
    const marker1 = new ymaps.Placemark(point1, {
        balloonContent: 'Точка 1: Москва'
    }, {
        preset: 'islands#blueDotIcon'
    });
    
    const marker2 = new ymaps.Placemark(point2, {
        balloonContent: 'Точка 2: Санкт-Петербург'
    }, {
        preset: 'islands#redDotIcon'
    });
    
    // Создаем линию между точками
    const polyline = new ymaps.Polyline([point1, point2], {
        balloonContent: 'Маршрут'
    }, {
        strokeColor: '#ff0000',
        strokeWidth: 3,
        strokeStyle: 'dash'
    });
    
    // Добавляем объекты на карту
    window.mapKit.map.geoObjects.add(marker1);
    window.mapKit.map.geoObjects.add(marker2);
    window.mapKit.map.geoObjects.add(polyline);
    
    // Обновляем списки
    window.mapKit.markers.push(marker1, marker2);
    window.mapKit.polylines.push(polyline);
    window.mapKit.objects.push(marker1, marker2, polyline);
    
    // Рассчитываем расстояние
    const distance = window.mapKit.calculateDistance(point1, point2);
    const distanceKm = (distance / 1000).toFixed(1);
    
    // Центрируем карту на обеих точках
    window.mapKit.map.setBounds([point1, point2], {
        checkZoomRange: true,
        duration: 1000
    });
    
    window.mapKit.updateInfo();
    
    alert(`Расстояние между Москвой и Санкт-Петербургом: ${distanceKm} км (${distance.toLocaleString()} м)`);
}

/**
 * Пример 3: Создание маршрута с промежуточными точками
 */
function routeExample() {
    // Очищаем предыдущие объекты
    window.mapKit.clearAll();
    
    // Создаем маршрут с несколькими точками
    const routePoints = [
        [55.7558, 37.6176], // Москва
        [55.7522, 37.6156], // Красная площадь
        [55.7494, 37.6204], // Театральная площадь
        [55.7461, 37.6172]  // Лубянская площадь
    ];
    
    // Добавляем маркеры для каждой точки
    const placeNames = ['Москва', 'Красная площадь', 'Театральная площадь', 'Лубянская площадь'];
    
    routePoints.forEach((point, index) => {
        const marker = new ymaps.Placemark(point, {
            balloonContent: `${index + 1}. ${placeNames[index]}`
        }, {
            preset: 'islands#dotIcon',
            iconColor: index === 0 ? '#00ff00' : index === routePoints.length - 1 ? '#ff0000' : '#ffff00'
        });
        
        window.mapKit.map.geoObjects.add(marker);
        window.mapKit.markers.push(marker);
        window.mapKit.objects.push(marker);
    });
    
    // Создаем линию маршрута
    const routeLine = new ymaps.Polyline([routePoints], {
        balloonContent: 'Маршрут по центру Москвы'
    }, {
        strokeColor: '#0000ff',
        strokeWidth: 4,
        strokeStyle: 'solid'
    });
    
    window.mapKit.map.geoObjects.add(routeLine);
    window.mapKit.polylines.push(routeLine);
    window.mapKit.objects.push(routeLine);
    
    // Центрируем карту на маршруте
    window.mapKit.map.setBounds(routePoints, {
        checkZoomRange: true,
        duration: 1000
    });
    
    window.mapKit.updateInfo();
    
    alert(`Создан маршрут с ${routePoints.length} точками через центр Москвы!`);
}

/**
 * Пример 4: Кластеризация маркеров
 */
function clusteringExample() {
    // Очищаем предыдущие объекты
    window.mapKit.clearAll();
    
    // Создаем множество случайных маркеров в области Москвы
    const markers = [];
    const center = [55.7558, 37.6176];
    
    for (let i = 0; i < 50; i++) {
        const lat = center[0] + (Math.random() - 0.5) * 0.1;
        const lon = center[1] + (Math.random() - 0.5) * 0.1;
        
        const marker = new ymaps.Placemark([lat, lon], {
            balloonContent: `Маркер ${i + 1}`
        }, {
            preset: 'islands#dotIcon',
            iconColor: getRandomColor()
        });
        
        markers.push(marker);
    }
    
    // Создаем кластеризатор
    const clusterer = new ymaps.Clusterer({
        preset: 'islands#blueClusterIcons',
        groupByCoordinates: false,
        clusterDisableClickZoom: false,
        clusterHideIconOnBalloonOpen: false,
        geoObjectHideIconOnBalloonOpen: false
    });
    
    // Добавляем маркеры в кластеризатор
    clusterer.add(markers);
    
    // Добавляем кластеризатор на карту
    window.mapKit.map.geoObjects.add(clusterer);
    window.mapKit.objects.push(clusterer);
    
    // Обновляем информацию
    window.mapKit.updateInfo();
    
    alert(`Создано 50 маркеров с кластеризацией! Приближайте карту, чтобы увидеть кластеры.`);
}

/**
 * Пример 5: Создание круговой области
 */
function createCircleExample() {
    // Очищаем предыдущие объекты
    window.mapKit.clearAll();
    
    const center = [55.7558, 37.6176]; // Москва
    const radius = 5000; // 5 км в метрах
    
    // Создаем круг (полигон с множеством точек)
    const points = [];
    const segments = 64; // Количество сегментов для плавности
    
    for (let i = 0; i <= segments; i++) {
        const angle = (i / segments) * 2 * Math.PI;
        const lat = center[0] + (radius / 111320) * Math.cos(angle);
        const lon = center[1] + (radius / (111320 * Math.cos(center[0] * Math.PI / 180))) * Math.sin(angle);
        points.push([lat, lon]);
    }
    
    // Создаем полигон в виде круга
    const circle = new ymaps.Polygon([points], {
        balloonContent: `Круг радиусом ${radius / 1000} км вокруг центра Москвы`
    }, {
        fillColor: '#00ff00',
        strokeColor: '#008000',
        strokeWidth: 2,
        fillOpacity: 0.3
    });
    
    // Добавляем центр круга
    const centerMarker = new ymaps.Placemark(center, {
        balloonContent: 'Центр круга'
    }, {
        preset: 'islands#redDotIcon'
    });
    
    window.mapKit.map.geoObjects.add(circle);
    window.mapKit.map.geoObjects.add(centerMarker);
    window.mapKit.polygons.push(circle);
    window.mapKit.markers.push(centerMarker);
    window.mapKit.objects.push(circle, centerMarker);
    
    // Центрируем карту на круге
    window.mapKit.map.setBounds(points, {
        checkZoomRange: true,
        duration: 1000
    });
    
    window.mapKit.updateInfo();
    
    alert(`Создан круг радиусом ${radius / 1000} км вокруг центра Москвы!`);
}

/**
 * Вспомогательная функция для получения случайного цвета
 */
function getRandomColor() {
    const colors = [
        '#FF6B6B', '#4ECDC4', '#45B7D1', '#96CEB4', '#FFEAA7',
        '#DDA0DD', '#98D8C8', '#F7DC6F', '#BB8FCE', '#85C1E9'
    ];
    return colors[Math.floor(Math.random() * colors.length)];
}

/**
 * Демонстрация базовых функций
 */
function demonstrateBasicFunctions() {
    console.log('Доступные примеры:');
    console.log('- searchAddressExample() - поиск по адресу');
    console.log('- distanceExample() - измерение расстояния');
    console.log('- routeExample() - создание маршрута');
    console.log('- clusteringExample() - кластеризация маркеров');
    console.log('- createCircleExample() - создание круговой области');
    
    // Добавляем глобальные функции для консоли
    window.searchAddressExample = searchAddressExample;
    window.distanceExample = distanceExample;
    window.routeExample = routeExample;
    window.clusteringExample = clusteringExample;
    window.createCircleExample = createCircleExample;
}

// Экспортируем функции для использования в других файлах
if (typeof module !== 'undefined' && module.exports) {
    module.exports = {
        searchAddressExample,
        distanceExample,
        routeExample,
        clusteringExample,
        createCircleExample
    };
}
