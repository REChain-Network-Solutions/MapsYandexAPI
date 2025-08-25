/**
 * Утилиты для работы с Яндекс Картами
 * Вспомогательные функции для геопространственных вычислений
 */

class MapUtils {
    /**
     * Константы для геодезических вычислений
     */
    static EARTH_RADIUS = 6371000; // Радиус Земли в метрах
    static DEG_TO_RAD = Math.PI / 180;
    static RAD_TO_DEG = 180 / Math.PI;

    /**
     * Вычисление расстояния между двумя точками (формула гаверсинуса)
     * @param {Array} point1 - [широта, долгота] первой точки
     * @param {Array} point2 - [широта, долгота] второй точки
     * @returns {number} Расстояние в метрах
     */
    static calculateDistance(point1, point2) {
        const [lat1, lon1] = point1;
        const [lat2, lon2] = point2;
        
        const dLat = (lat2 - lat1) * this.DEG_TO_RAD;
        const dLon = (lon2 - lon1) * this.DEG_TO_RAD;
        
        const a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                  Math.cos(lat1 * this.DEG_TO_RAD) * Math.cos(lat2 * this.DEG_TO_RAD) *
                  Math.sin(dLon / 2) * Math.sin(dLon / 2);
        
        const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        
        return this.EARTH_RADIUS * c;
    }

    /**
     * Вычисление азимута между двумя точками
     * @param {Array} point1 - [широта, долгота] первой точки
     * @param {Array} point2 - [широта, долгота] второй точки
     * @returns {number} Азимут в градусах (0-360)
     */
    static calculateBearing(point1, point2) {
        const [lat1, lon1] = point1;
        const [lat2, lon2] = point2;
        
        const dLon = (lon2 - lon1) * this.DEG_TO_RAD;
        const lat1Rad = lat1 * this.DEG_TO_RAD;
        const lat2Rad = lat2 * this.DEG_TO_RAD;
        
        const y = Math.sin(dLon) * Math.cos(lat2Rad);
        const x = Math.cos(lat1Rad) * Math.sin(lat2Rad) -
                  Math.sin(lat1Rad) * Math.cos(lat2Rad) * Math.cos(dLon);
        
        let bearing = Math.atan2(y, x) * this.RAD_TO_DEG;
        bearing = (bearing + 360) % 360;
        
        return bearing;
    }

    /**
     * Вычисление точки на заданном расстоянии и азимуте
     * @param {Array} startPoint - [широта, долгота] начальной точки
     * @param {number} distance - Расстояние в метрах
     * @param {number} bearing - Азимут в градусах
     * @returns {Array} [широта, долгота] конечной точки
     */
    static calculateDestinationPoint(startPoint, distance, bearing) {
        const [lat1, lon1] = startPoint;
        const bearingRad = bearing * this.DEG_TO_RAD;
        const lat1Rad = lat1 * this.DEG_TO_RAD;
        const lon1Rad = lon1 * this.DEG_TO_RAD;
        
        const angularDistance = distance / this.EARTH_RADIUS;
        
        const lat2Rad = Math.asin(
            Math.sin(lat1Rad) * Math.cos(angularDistance) +
            Math.cos(lat1Rad) * Math.sin(angularDistance) * Math.cos(bearingRad)
        );
        
        const lon2Rad = lon1Rad + Math.atan2(
            Math.sin(bearingRad) * Math.sin(angularDistance) * Math.cos(lat1Rad),
            Math.cos(angularDistance) - Math.sin(lat1Rad) * Math.sin(lat2Rad)
        );
        
        return [lat2Rad * this.RAD_TO_DEG, lon2Rad * this.RAD_TO_DEG];
    }

    /**
     * Создание круга вокруг точки
     * @param {Array} center - [широта, долгота] центра
     * @param {number} radius - Радиус в метрах
     * @param {number} segments - Количество сегментов (по умолчанию 64)
     * @returns {Array} Массив точек круга
     */
    static createCircle(center, radius, segments = 64) {
        const points = [];
        const [centerLat, centerLon] = center;
        
        for (let i = 0; i <= segments; i++) {
            const angle = (i / segments) * 2 * Math.PI;
            const point = this.calculateDestinationPoint(center, radius, angle * this.RAD_TO_DEG);
            points.push(point);
        }
        
        return points;
    }

    /**
     * Создание прямоугольника вокруг точки
     * @param {Array} center - [широта, долгота] центра
     * @param {number} width - Ширина в метрах
     * @param {number} height - Высота в метрах
     * @param {number} rotation - Поворот в градусах (по умолчанию 0)
     * @returns {Array} Массив точек прямоугольника
     */
    static createRectangle(center, width, height, rotation = 0) {
        const [centerLat, centerLon] = center;
        const halfWidth = width / 2;
        const halfHeight = height / 2;
        
        // Создаем углы прямоугольника
        const corners = [
            [-halfHeight, -halfWidth],
            [-halfHeight, halfWidth],
            [halfHeight, halfWidth],
            [halfHeight, -halfWidth]
        ];
        
        const points = [];
        
        corners.forEach(([dy, dx]) => {
            // Поворачиваем точку
            const cosRot = Math.cos(rotation * this.DEG_TO_RAD);
            const sinRot = Math.sin(rotation * this.DEG_TO_RAD);
            
            const rotatedX = dx * cosRot - dy * sinRot;
            const rotatedY = dx * sinRot + dy * cosRot;
            
            // Конвертируем в координаты
            const lat = centerLat + (rotatedY / 111320);
            const lon = centerLon + (rotatedX / (111320 * Math.cos(centerLat * this.DEG_TO_RAD)));
            
            points.push([lat, lon]);
        });
        
        return points;
    }

    /**
     * Проверка, находится ли точка внутри полигона
     * @param {Array} point - [широта, долгота] проверяемой точки
     * @param {Array} polygon - Массив точек полигона
     * @returns {boolean} true, если точка внутри полигона
     */
    static isPointInPolygon(point, polygon) {
        const [lat, lon] = point;
        let inside = false;
        
        for (let i = 0, j = polygon.length - 1; i < polygon.length; j = i++) {
            const [latI, lonI] = polygon[i];
            const [latJ, lonJ] = polygon[j];
            
            if (((latI > lat) !== (latJ > lat)) &&
                (lon < (lonJ - lonI) * (lat - latI) / (latJ - latI) + lonI)) {
                inside = !inside;
            }
        }
        
        return inside;
    }

    /**
     * Вычисление площади полигона
     * @param {Array} polygon - Массив точек полигона
     * @returns {number} Площадь в квадратных метрах
     */
    static calculatePolygonArea(polygon) {
        if (polygon.length < 3) return 0;
        
        let area = 0;
        const n = polygon.length;
        
        for (let i = 0; i < n; i++) {
            const [latI, lonI] = polygon[i];
            const [latJ, lonJ] = polygon[(i + 1) % n];
            
            area += (lonJ - lonI) * (2 + Math.sin(latI * this.DEG_TO_RAD) + Math.sin(latJ * this.DEG_TO_RAD));
        }
        
        area = Math.abs(area) * this.EARTH_RADIUS * this.EARTH_RADIUS / 2;
        return area;
    }

    /**
     * Вычисление центра масс полигона
     * @param {Array} polygon - Массив точек полигона
     * @returns {Array} [широта, долгота] центра масс
     */
    static calculatePolygonCentroid(polygon) {
        if (polygon.length === 0) return null;
        if (polygon.length === 1) return polygon[0];
        
        let centerLat = 0;
        let centerLon = 0;
        
        polygon.forEach(([lat, lon]) => {
            centerLat += lat;
            centerLon += lon;
        });
        
        return [centerLat / polygon.length, centerLon / polygon.length];
    }

    /**
     * Сглаживание полигона (алгоритм Рамера-Дугласа-Пекера)
     * @param {Array} polygon - Массив точек полигона
     * @param {number} tolerance - Допуск в метрах
     * @returns {Array} Упрощенный полигон
     */
    static simplifyPolygon(polygon, tolerance) {
        if (polygon.length <= 2) return polygon;
        
        const toleranceRad = tolerance / this.EARTH_RADIUS;
        
        function perpendicularDistance(point, lineStart, lineEnd) {
            const [lat, lon] = point;
            const [lat1, lon1] = lineStart;
            const [lat2, lon2] = lineEnd;
            
            const lat1Rad = lat1 * this.DEG_TO_RAD;
            const lon1Rad = lon1 * this.DEG_TO_RAD;
            const lat2Rad = lat2 * this.DEG_TO_RAD;
            const lon2Rad = lon2 * this.DEG_TO_RAD;
            const latRad = lat * this.DEG_TO_RAD;
            const lonRad = lon * this.DEG_TO_RAD;
            
            const a = Math.sin(latRad - lat1Rad);
            const b = Math.sin(lat2Rad - lat1Rad);
            const c = Math.sin(lonRad - lon1Rad);
            const d = Math.sin(lon2Rad - lon1Rad);
            
            return Math.abs(a * d - b * c);
        }
        
        function douglasPeucker(points, start, end) {
            if (end - start <= 1) return;
            
            let maxDistance = 0;
            let maxIndex = start;
            
            for (let i = start + 1; i < end; i++) {
                const distance = perpendicularDistance(points[i], points[start], points[end]);
                if (distance > maxDistance) {
                    maxDistance = distance;
                    maxIndex = i;
                }
            }
            
            if (maxDistance > toleranceRad) {
                douglasPeucker(points, start, maxIndex);
                douglasPeucker(points, maxIndex, end);
            } else {
                for (let i = start + 1; i < end; i++) {
                    points[i] = null;
                }
            }
        }
        
        const points = [...polygon];
        douglasPeucker(points, 0, points.length - 1);
        
        return points.filter(point => point !== null);
    }

    /**
     * Конвертация координат в различные форматы
     */
    static convertCoordinates(lat, lon, format = 'decimal') {
        switch (format) {
            case 'decimal':
                return [lat, lon];
            
            case 'dms': // градусы, минуты, секунды
                const latDeg = Math.floor(Math.abs(lat));
                const latMin = Math.floor((Math.abs(lat) - latDeg) * 60);
                const latSec = ((Math.abs(lat) - latDeg - latMin / 60) * 3600).toFixed(2);
                
                const lonDeg = Math.floor(Math.abs(lon));
                const lonMin = Math.floor((Math.abs(lon) - lonDeg) * 60);
                const lonSec = ((Math.abs(lon) - lonDeg - lonMin / 60) * 3600).toFixed(2);
                
                return {
                    latitude: `${latDeg}° ${latMin}' ${latSec}" ${lat >= 0 ? 'N' : 'S'}`,
                    longitude: `${lonDeg}° ${lonMin}' ${lonSec}" ${lon >= 0 ? 'E' : 'W'}`
                };
            
            case 'dm': // градусы, минуты
                const latDeg2 = Math.floor(Math.abs(lat));
                const latMin2 = ((Math.abs(lat) - latDeg2) * 60).toFixed(4);
                
                const lonDeg2 = Math.floor(Math.abs(lon));
                const lonMin2 = ((Math.abs(lon) - lonDeg2) * 60).toFixed(4);
                
                return {
                    latitude: `${latDeg2}° ${latMin2}' ${lat >= 0 ? 'N' : 'S'}`,
                    longitude: `${lonDeg2}° ${lonMin2}' ${lon >= 0 ? 'E' : 'W'}`
                };
            
            default:
                return [lat, lon];
        }
    }

    /**
     * Форматирование расстояния в читаемом виде
     * @param {number} distance - Расстояние в метрах
     * @returns {string} Отформатированное расстояние
     */
    static formatDistance(distance) {
        if (distance < 1000) {
            return `${Math.round(distance)} м`;
        } else if (distance < 100000) {
            return `${(distance / 1000).toFixed(1)} км`;
        } else {
            return `${Math.round(distance / 1000)} км`;
        }
    }

    /**
     * Форматирование площади в читаемом виде
     * @param {number} area - Площадь в квадратных метрах
     * @returns {string} Отформатированная площадь
     */
    static formatArea(area) {
        if (area < 10000) {
            return `${Math.round(area)} м²`;
        } else if (area < 1000000) {
            return `${(area / 10000).toFixed(2)} га`;
        } else {
            return `${(area / 1000000).toFixed(2)} км²`;
        }
    }
}

// Экспорт утилит
if (typeof module !== 'undefined' && module.exports) {
    module.exports = MapUtils;
} else {
    window.MapUtils = MapUtils;
}

// Глобальные функции для удобства использования
window.calculateDistance = MapUtils.calculateDistance.bind(MapUtils);
window.calculateBearing = MapUtils.calculateBearing.bind(MapUtils);
window.createCircle = MapUtils.createCircle.bind(MapUtils);
window.createRectangle = MapUtils.createRectangle.bind(MapUtils);
window.isPointInPolygon = MapUtils.isPointInPolygon.bind(MapUtils);
window.calculatePolygonArea = MapUtils.calculatePolygonArea.bind(MapUtils);
window.formatDistance = MapUtils.formatDistance.bind(MapUtils);
window.formatArea = MapUtils.formatArea.bind(MapUtils);
