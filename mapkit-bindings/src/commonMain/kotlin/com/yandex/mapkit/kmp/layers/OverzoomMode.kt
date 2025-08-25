@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.layers

expect enum class OverzoomMode {
    /**
     * Overzoom is fully disabled.
     */
    DISABLED,
    /**
     * If a tile is absent or translucent, tiles from lower or higher zooms
     * will be shown.
     */
    ENABLED,
    /**
     * Same as Enabled, plus it fetches tiles well below absent or
     * translucent tiles that don't have any overzoom tiles to replace them
     * with.
     */
    WITH_PREFETCH,
}

