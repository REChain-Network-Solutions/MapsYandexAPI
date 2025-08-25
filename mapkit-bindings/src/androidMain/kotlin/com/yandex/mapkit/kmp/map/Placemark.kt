@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * Represents a geo-positioned object on the map.
 */
actual typealias PlacemarkMapObject = com.yandex.mapkit.map.PlacemarkMapObject

actual var PlacemarkMapObject.geometry: com.yandex.mapkit.kmp.geometry.Point
    get() = geometry
    set(value) {
        geometry = value
    }
/**
 * Angle between the direction of an object and the direction to north.
 * Measured in degrees. Default: 0.f.
 */
actual var PlacemarkMapObject.direction: Float
    get() = direction
    set(value) {
        direction = value
    }
/**
 * Opacity multiplicator for the placemark content. Values below 0 will
 * be set to 0. Default: 1.
 */
actual var PlacemarkMapObject.opacity: Float
    get() = opacity
    set(value) {
        opacity = value
    }
