@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * Listener for user interactions with the map.
 */
expect interface InputListener {
    /**
     * Called when a tap occurred unless a tap was handled by geo objects or
     * map objects. param@ position absolute screen coordinates
     */
    fun onMapTap(
        map: com.yandex.mapkit.kmp.map.Map,
        point: com.yandex.mapkit.kmp.geometry.Point,
    ): Unit

    /**
     * Called when a long tap occurred.
     */
    fun onMapLongTap(
        map: com.yandex.mapkit.kmp.map.Map,
        point: com.yandex.mapkit.kmp.geometry.Point,
    ): Unit
}

