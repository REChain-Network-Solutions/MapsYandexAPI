@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.layers

/**
 * Information about the tapped object.
 */
actual typealias GeoObjectTapEvent = com.yandex.mapkit.layers.GeoObjectTapEvent

/**
 * @return GeoObject The object that was tapped.
 */
actual val GeoObjectTapEvent.geoObject: com.yandex.mapkit.kmp.GeoObject
    get() = geoObject

