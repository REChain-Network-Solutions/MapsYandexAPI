@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * The type of map displayed.
 */
expect enum class MapType {
    /**
     * Do not use any of the predefined maps.
     */
    NONE,
    /**
     * Raster map.
     */
    MAP,
    /**
     * Allowed only for Yandex apps Default satellite map.
     */
    SATELLITE,
    /**
     * Allowed only for Yandex apps Satellite map with roads, placemarks and
     * labels.
     */
    HYBRID,
    /**
     * Vector map.
     */
    VECTOR_MAP,
}

