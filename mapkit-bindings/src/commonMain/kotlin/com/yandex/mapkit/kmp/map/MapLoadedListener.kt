@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * Listener interface for when the map has finished loading. This occurs
 * after all tiles required to render the map have been fetched, and are
 * ready to be rendered. This event will not fire if the map never loads
 * due to connectivity issues, or if the map is continuously changing
 * and never completes loading due to the user constantly interacting
 * with the map.
 */
expect interface MapLoadedListener {
    /**
     * Called after the map has finished loading all visible tiles. This
     * will only be called once. You must set another listener if you want
     * to be notified again.
     */
    fun onMapLoaded(
        statistics: com.yandex.mapkit.kmp.map.MapLoadStatistics,
    ): Unit
}

