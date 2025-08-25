package ru.yandex.maps.workshop.internal.map

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import com.yandex.mapkit.kmp.map.MapWindow
import com.yandex.mapkit.kmp.mapview.toCommon
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGRectMake
import platform.YandexMapsMobile.YMKMapView

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun NativeMap(
    state: MapState,
    onCreate: (MapWindow) -> Unit,
    onRelease: (MapWindow?) -> Unit
) {
    UIKitView(
        factory = {
            val mapView = YMKMapView(CGRectMake(0.0, 0.0, 0.0, 0.0))
            val mapWindow = mapView.toCommon().mapWindow
            onCreate(mapWindow)
            mapWindow.also { state.map = it }
            mapView
        },
        update = {},
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        onRelease = {
            onRelease(state.map)
            state.map = null
        }
    )
}
