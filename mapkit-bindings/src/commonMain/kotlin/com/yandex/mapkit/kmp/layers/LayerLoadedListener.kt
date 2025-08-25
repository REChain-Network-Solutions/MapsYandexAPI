@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.layers

/**
 * Allows user to be notified when the layer has finished loading. This
 * occurs after all tiles required to render the layer have been
 * fetched, and are ready to be rendered. This event will not fire if
 * the layer never loads due to connectivity issues, or if the layer is
 * continuously changing and never completes loading due to the user
 * constantly interacting with the map.
 */
expect interface LayerLoadedListener {
    /**
     * Called after the layer has finished loading all visible tiles. This
     * will only be called once. You must set another listener if you want
     * to be notified again.
     */
    fun onLayerLoaded(): Unit
}

