@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * The object that is used to interact with the map bounds.
 */
expect interface CameraBounds {

    /**
     * Minimum available zoom level considering zoom level hint provided via
     * #setMinZoomPreference.
     */
    fun getMinZoom(): Float

    /**
     * Maximum available zoom level considering zoom level hint provided via
     * #setMaxZoomPreference
     */
    fun getMaxZoom(): Float

    /**
     * Set minimum available zoom level hint.
     */
    fun setMinZoomPreference(
        zoom: Float,
    ): Unit

    /**
     * Set maximum available zoom level hint.
     */
    fun setMaxZoomPreference(
        zoom: Float,
    ): Unit

    /**
     * Reset minimum and maximum available zoom level hints.
     */
    fun resetMinMaxZoomPreference(): Unit

    /**
     * Tells if this object is valid or not. Any method called on an invalid
     * object will throw an exception. The object becomes invalid only on UI
     * thread, and only when its implementation depends on objects already
     * destroyed by now. Please refer to general docs about the interface for
     * details on its invalidation.
     */
    fun isValid(): Boolean
}

/**
 * Latitudes should be in range [-89.3, 89.3]. Longitudes should be in
 * range [-180, 180).
 *
 */
expect var CameraBounds.latLngBounds: com.yandex.mapkit.kmp.geometry.BoundingBox?

