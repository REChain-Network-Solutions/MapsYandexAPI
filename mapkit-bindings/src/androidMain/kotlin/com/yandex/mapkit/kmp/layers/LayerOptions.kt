@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.layers

/**
 * Options that are used when adding a layer to the map.
 */
actual typealias LayerOptions = com.yandex.mapkit.layers.LayerOptions

/**
 * Inactive layers are not displayed on the map and do not request any
 * tiles from TileProvider.
 */
actual var LayerOptions.mpActive: Boolean
    get() = active
    set(value) {
        active = value
    }
/**
 * Indicates the availability of night mode for this layer (for example,
 * night mode is disabled for the satellite layer). Default: true.
 */
actual var LayerOptions.mpNightModeAvailable: Boolean
    get() = nightModeAvailable
    set(value) {
        nightModeAvailable = value
    }
/**
 * Determines whether tiles are cached on persistent storage or not.
 */
actual var LayerOptions.mpCacheable: Boolean
    get() = cacheable
    set(value) {
        cacheable = value
    }
/**
 * Indicates whether layer activation/deactivation should be animated.
 */
actual var LayerOptions.mpAnimateOnActivation: Boolean
    get() = animateOnActivation
    set(value) {
        animateOnActivation = value
    }
/**
 * Specifies duration of tile appearing animation. Can be set to 0 to
 * disable animation. Default: 400 ms
 */
actual var LayerOptions.mpTileAppearingAnimationDuration: Long
    get() = tileAppearingAnimationDuration
    set(value) {
        tileAppearingAnimationDuration = value
    }
/**
 * Whether to render tiles from adjacent zoom levels in place of absent
 * or translucent tiles.
 */
actual var LayerOptions.mpOverzoomMode: com.yandex.mapkit.kmp.layers.OverzoomMode
    get() = overzoomMode
    set(value) {
        overzoomMode = value
    }
/**
 * Set this flag if layer is transparent, that is parts of underlying
 * layers can be seen through it. This will disable some rendering
 * optimizations that take advantage of layer opacity.
 */
actual var LayerOptions.mpTransparent: Boolean
    get() = transparent
    set(value) {
        transparent = value
    }
/**
 * Determines if layer supports versioning
 */
actual var LayerOptions.mpVersionSupport: Boolean
    get() = versionSupport
    set(value) {
        versionSupport = value
    }

actual object LayerOptionsFactory {
    actual fun create(
        active: Boolean,
        nightModeAvailable: Boolean,
        cacheable: Boolean,
        animateOnActivation: Boolean,
        tileAppearingAnimationDuration: Long,
        overzoomMode: com.yandex.mapkit.kmp.layers.OverzoomMode,
        transparent: Boolean,
        versionSupport: Boolean,
    ): LayerOptions {
        return LayerOptions(
            active,
            nightModeAvailable,
            cacheable,
            animateOnActivation,
            tileAppearingAnimationDuration,
            overzoomMode,
            transparent,
            versionSupport,
        )
    }
}

