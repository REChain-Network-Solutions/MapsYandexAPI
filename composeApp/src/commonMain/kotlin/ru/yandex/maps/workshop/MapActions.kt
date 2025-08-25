package ru.yandex.maps.workshop

import com.yandex.mapkit.kmp.geometry.Point
import com.yandex.mapkit.kmp.map.CameraPosition
import com.yandex.mapkit.kmp.map.InputListener
import com.yandex.mapkit.kmp.map.Map
import com.yandex.mapkit.kmp.map.MapObject
import com.yandex.mapkit.kmp.map.MapObjectTapListener

typealias MapObjectTapAction = (MapObject) -> Unit
typealias MapTapAction = (Point) -> Unit
typealias MapMoveAction = (cameraPosition: CameraPosition, finished: Boolean) -> Unit

class CameraListenerWrapper(private val onCameraMoved: MapMoveAction) {
    // TODO
}

class TapListenerWrapper(
    private val longTapAction: MapTapAction,
    private val tapAction: MapTapAction,
): InputListener {
    override fun onMapTap(
        map: Map,
        point: Point
    ) {
        tapAction(point)
    }

    override fun onMapLongTap(
        map: Map,
        point: Point
    ) {
        println("--> onMapLongTap")
        longTapAction(point)
    }
}

class ObjectTapListenerWrapper(private val tapAction: MapObjectTapAction): MapObjectTapListener {
    override fun onMapObjectTap(
        mapObject: MapObject,
        point: Point
    ): Boolean {
        tapAction(mapObject)
        return true
    }
}
