@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.layers

/**
 * Options that are used when adding a layer to the map.
 */
expect class LayerOptions

/**
 * Inactive layers are not displayed on the map and do not request any
 * tiles from TileProvider.
 */
expect var LayerOptions.mpActive: Boolean
/**
 * Indicates the availability of night mode for this layer (for example,
 * night mode is disabled for the satellite layer). Default: true.
 */
expect var LayerOptions.mpNightModeAvailable: Boolean
/**
 * Determines whether tiles are cached on persistent storage or not.
 */
expect var LayerOptions.mpCacheable: Boolean
/**
 * Indicates whether layer activation/deactivation should be animated.
 */
expect var LayerOptions.mpAnimateOnActivation: Boolean
/**
 * Specifies duration of tile appearing animation. Can be set to 0 to
 * disable animation. Default: 400 ms
 */
expect var LayerOptions.mpTileAppearingAnimationDuration: Long
/**
 * Whether to render tiles from adjacent zoom levels in place of absent
 * or translucent tiles.
 */
expect var LayerOptions.mpOverzoomMode: com.yandex.mapkit.kmp.layers.OverzoomMode
/**
 * Set this flag if layer is transparent, that is parts of underlying
 * layers can be seen through it. This will disable some rendering
 * optimizations that take advantage of layer opacity.
 */
expect var LayerOptions.mpTransparent: Boolean
/**
 * Determines if layer supports versioning
 */
expect var LayerOptions.mpVersionSupport: Boolean

expect object LayerOptionsFactory {
    fun create(
        active: Boolean,
        nightModeAvailable: Boolean,
        cacheable: Boolean,
        animateOnActivation: Boolean,
        tileAppearingAnimationDuration: Long,
        overzoomMode: com.yandex.mapkit.kmp.layers.OverzoomMode,
        transparent: Boolean,
        versionSupport: Boolean,
    ): LayerOptions
}

