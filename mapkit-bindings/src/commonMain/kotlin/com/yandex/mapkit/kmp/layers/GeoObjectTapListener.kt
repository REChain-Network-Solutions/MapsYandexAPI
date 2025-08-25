@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.layers

/**
 * Retrieves the brief geoObject info when an object is tapped.
 */
expect interface GeoObjectTapListener {
    /**
     * Listener that retrieves brief geoObject info for the tapped object.
     * Returns false if the event wasn't handled. The event will be
     * propagated to the map.
     */
    fun onObjectTap(
        event: com.yandex.mapkit.kmp.layers.GeoObjectTapEvent,
    ): Boolean
}

