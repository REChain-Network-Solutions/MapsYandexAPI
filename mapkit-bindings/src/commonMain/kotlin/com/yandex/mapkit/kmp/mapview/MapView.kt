package com.yandex.mapkit.kmp.mapview

import com.yandex.mapkit.kmp.map.MapWindow

expect interface MapView {

    val mapWindow: MapWindow

    /**
     * Should be called from from corresponding method of activity or fragment containing this view
     */
    fun onStart()

    /**
     * Should be called from from corresponding method of activity or fragment containing this view
     */
    fun onStop()

    fun setNonInteractive(value: Boolean)
}
