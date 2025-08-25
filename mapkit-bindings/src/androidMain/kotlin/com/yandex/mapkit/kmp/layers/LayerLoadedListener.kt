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
actual typealias LayerLoadedListener = com.yandex.mapkit.layers.LayerLoadedListener

