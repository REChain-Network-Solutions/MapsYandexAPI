package com.yandex.mapkit.kmp.mapview

import com.yandex.mapkit.kmp.map.MapWindow
import com.yandex.mapkit.kmp.map.YMKMapWindowWrapper
import platform.YandexMapsMobile.YMKMapView as NativeMapView

actual interface MapView {
    actual val mapWindow: MapWindow
    actual fun onStart()
    actual fun onStop()
    actual fun setNonInteractive(value: Boolean)
}

class MapViewWrapper internal constructor(private val nativeMapView: NativeMapView) : MapView {

    fun toNative(): NativeMapView {
        return nativeMapView
    }

    override fun onStart() {

    }

    override fun onStop() {
    }

    override val mapWindow: MapWindow = nativeMapView.mapWindow!!.let(::YMKMapWindowWrapper)

    override fun setNonInteractive(value: Boolean) {
        nativeMapView.setNoninteractive(value)
    }

}

fun NativeMapView.toCommon(): MapView {
    return MapViewWrapper(this)
}
