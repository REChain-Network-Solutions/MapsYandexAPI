package ru.yandex.maps.workshop.internal.map

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.yandex.mapkit.kmp.map.MapWindow
import com.yandex.mapkit.kmp.mapview.toCommon
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.kmp.map.CameraPositionFactory
import com.yandex.mapkit.kmp.geometry.PointFactory
import com.yandex.mapkit.kmp.AnimationFactory
import com.yandex.mapkit.kmp.AnimationType

@Composable
actual fun NativeMap(
    state: MapState,
    onCreate: (MapWindow) -> Unit,
    onRelease: (MapWindow?) -> Unit
) {
    AndroidView(
        factory = { context ->
            MapView(context).also { mapView ->
                val mapWindow = mapView.toCommon().mapWindow.also { state.map = it }
                
                // Настраиваем карту для красивого старта
                val isaacCathedralPoint = PointFactory.create(59.9343, 30.3061)
                
                // Сначала показываем мир
                val worldPosition = CameraPositionFactory.create(
                    target = PointFactory.create(55.0, 37.0), // Центр России
                    zoom = 3.0f,
                    azimuth = 0.0f,
                    tilt = 0.0f
                )
                
                // Устанавливаем начальную позицию без анимации
                mapWindow.map.move(worldPosition)
                
                // Через небольшую задержку делаем красивый полет к Исаакиевскому собору
                android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                    val animation = AnimationFactory.create(
                        type = AnimationType.SMOOTH,
                        duration = 3.0f
                    )
                    
                    val targetPosition = CameraPositionFactory.create(
                        target = isaacCathedralPoint,
                        zoom = 16.0f,
                        azimuth = 0.0f,
                        tilt = 0.0f
                    )
                    
                    mapWindow.map.move(targetPosition, animation, null)
                }, 1000) // Задержка 1 секунда
                
                // Настраиваем InputListener для обработки нажатий
                android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                    state.setupInputListener()
                }, 1500) // Настраиваем после завершения анимации
                
                onCreate(mapWindow)
            }
        },
        update = {},
        onRelease = {
            onRelease(state.map)
            state.map = null
        }
    )
}
