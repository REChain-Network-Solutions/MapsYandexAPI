@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * Supported map style modes
 */
expect enum class MapMode {
    /**
     * Basic map
     */
    MAP,
    /**
     * Public transport map
     */
    TRANSIT,
    /**
     * Automobile navigation map
     */
    DRIVING,
    /**
     * Administrative map
     */
    ADMIN,
    /**
     * Legacy basic map design, can be used to preserve compatibility with
     * app design/legacy map customizaitons
     */
    LEGACY_MAP,
    /**
     * Upcoming basic map design
     */
    FUTURE_MAP,
}

