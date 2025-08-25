package ru.yandex.maps.workshop.internal.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.yandex.mapkit.kmp.ScreenPointFactory
import com.yandex.mapkit.kmp.geometry.Point
import com.yandex.mapkit.kmp.geometry.PointFactory
import com.yandex.mapkit.kmp.map.MapWindow
import com.yandex.mapkit.kmp.map.CameraPositionFactory
import com.yandex.mapkit.kmp.AnimationFactory
import com.yandex.mapkit.kmp.AnimationType
import com.yandex.mapkit.kmp.map.InputListener

class MapState(val coordinates: Point? = null) {
    var map by mutableStateOf<MapWindow?>(null)
    
    // Callbacks для обработки событий
    var onMapTap: ((Point) -> Unit)? = null
    var onMapLongTap: ((Point) -> Unit)? = null

    companion object {
        val Saver: Saver<MapState, Point> = Saver(
            save = { it.coordinates },
            restore = { MapState(it) },
        )
    }

    fun screenToWorld(x: Float, y: Float): Point? {
        return map?.screenToWorld(ScreenPointFactory.create(x, y))
    }
    
    /**
     * Плавно перемещается к указанной точке с анимацией
     */
    fun moveToPoint(point: Point, zoom: Float = 16.0f, duration: Float = 2.0f) {
        map?.let { mapWindow ->
            val cameraPosition = CameraPositionFactory.create(
                target = point,
                zoom = zoom,
                azimuth = 0.0f,
                tilt = 0.0f
            )
            
            val animation = AnimationFactory.create(
                type = AnimationType.SMOOTH,
                duration = duration
            )
            
            mapWindow.map.move(cameraPosition, animation, null)
        }
    }
    
    /**
     * Красивый эффект полета к Исаакиевскому собору
     */
    fun flyToIsaacCathedral() {
        val isaacPoint = PointFactory.create(59.9343, 30.3061)
        
        // Сначала отдаляемся
        val startPosition = CameraPositionFactory.create(
            target = PointFactory.create(55.0, 37.0), // Москва
            zoom = 4.0f,
            azimuth = 0.0f,
            tilt = 0.0f
        )
        
        val startAnimation = AnimationFactory.create(
            type = AnimationType.SMOOTH,
            duration = 1.0f
        )
        
        map?.let { mapWindow ->
            mapWindow.map.move(startPosition, startAnimation, null)
            // Затем приближаемся к Исаакиевскому собору
            moveToPoint(isaacPoint, 16.0f, 3.0f)
        }
    }
    
    /**
     * Быстрое перемещение к Исаакиевскому собору
     */
    fun moveToIsaacCathedral() {
        val isaacPoint = PointFactory.create(59.9343, 30.3061)
        moveToPoint(isaacPoint, 16.0f, 2.0f)
    }
    
    /**
     * Перемещение к центру Санкт-Петербурга
     */
    fun moveToStPetersburg() {
        val spbPoint = PointFactory.create(59.9311, 30.3609) // Дворцовая площадь
        moveToPoint(spbPoint, 12.0f, 2.0f)
    }
    
    /**
     * Перемещение к центру Москвы
     */
    fun moveToMoscow() {
        val moscowPoint = PointFactory.create(55.7558, 37.6173) // Красная площадь
        moveToPoint(moscowPoint, 12.0f, 2.5f)
    }
    
    /**
     * Настраивает InputListener для обработки нажатий на карту
     */
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
}

@Composable
fun Map(
    state: MapState = rememberMapState(),
    onCreate: (MapWindow) -> Unit = {},
    onRelease: (MapWindow?) -> Unit = {}
) {
    NativeMap(
        state = state,
        onCreate = onCreate,
        onRelease = onRelease,
    )
}

@Composable
expect fun NativeMap(
    state: MapState = rememberMapState(),
    onCreate: (MapWindow) -> Unit,
    onRelease: (MapWindow?) -> Unit
)

@Composable
inline fun rememberMapState(
    key: String? = null,
    crossinline init: MapState.() -> Unit = {}
): MapState = rememberSaveable(key = key, saver = MapState.Saver) {
    MapState().apply(init)
}
