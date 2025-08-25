@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.layers

/**
 * Information about the tapped object.
 */
expect interface GeoObjectTapEvent {
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
 * @return GeoObject The object that was tapped.
 */
expect val GeoObjectTapEvent.geoObject: com.yandex.mapkit.kmp.GeoObject

