package ru.yandex.maps.workshop.internal.map

import com.yandex.mapkit.kmp.geometry.Point
import com.yandex.mapkit.kmp.geometry.PointFactory

/**
 * Конфигурация анимаций для карты
 */
object MapAnimationConfig {
    
    // Координаты Исаакиевского собора
    val ISAAC_CATHEDRAL = PointFactory.create(59.9343, 30.3061)
    
    // Координаты промежуточных точек для эффекта полета
    val FLIGHT_PATH = listOf(
        PointFactory.create(55.0, 37.0),    // Москва
        PointFactory.create(57.0, 35.0),    // Тверь
        PointFactory.create(59.0, 32.0),    // Новгород
        ISAAC_CATHEDRAL                     // Исаакиевский собор
    )
    
    // Настройки зума для каждой точки полета
    val FLIGHT_ZOOM_LEVELS = listOf(4.0f, 6.0f, 8.0f, 16.0f)
    
    // Настройки анимации
    object Animation {
        const val INITIAL_DELAY = 1000L        // Задержка перед началом анимации (мс)
        const val FLIGHT_POINT_DELAY = 2500L   // Задержка между точками полета (мс)
        const val SMOOTH_DURATION = 4.0f       // Длительность плавной анимации (сек)
        const val QUICK_DURATION = 2.0f        // Длительность быстрой анимации (сек)
        const val INITIAL_ZOOM = 3.0f          // Начальный зум (вид на весь мир)
        const val FINAL_ZOOM = 16.0f           // Финальный зум (Исаакиевский собор)
    }
    
    // Настройки камеры
    object Camera {
        const val DEFAULT_AZIMUTH = 0.0f       // Азимут камеры
        const val DEFAULT_TILT = 0.0f          // Наклон камеры
    }
}
