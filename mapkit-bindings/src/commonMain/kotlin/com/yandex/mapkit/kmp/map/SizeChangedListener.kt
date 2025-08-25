@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * Listener for MapWindow size changes.
 */
expect interface SizeChangedListener {
    /**
     * Called when MapWindow handles the platform SizeChanged event. param@
     * absolute Screen coordinates.
     */
    fun onMapWindowSizeChanged(
        mapWindow: com.yandex.mapkit.kmp.map.MapWindow,
        newWidth: Int,
        newHeight: Int,
    ): Unit
}

