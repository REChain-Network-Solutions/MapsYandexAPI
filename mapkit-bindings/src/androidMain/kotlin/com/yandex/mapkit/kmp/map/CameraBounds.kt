@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * The object that is used to interact with the map bounds.
 */
actual typealias CameraBounds = com.yandex.mapkit.map.CameraBounds

/**
 * Latitudes should be in range [-89.3, 89.3]. Longitudes should be in
 * range [-180, 180).
 *
 */
actual var CameraBounds.latLngBounds: com.yandex.mapkit.kmp.geometry.BoundingBox?
    get() = latLngBounds
    set(value) {
        latLngBounds = value
    }

