@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * Provides an interface to load and control animation of placemark.
 */
actual typealias PlacemarkAnimation = com.yandex.mapkit.map.PlacemarkAnimation

/**
 * If true, animation will be played in the reverse direction. Default
 * value is false.
 */
actual var PlacemarkAnimation.reversed: Boolean
    get() = isReversed
    set(value) {
        isReversed = value
    }

