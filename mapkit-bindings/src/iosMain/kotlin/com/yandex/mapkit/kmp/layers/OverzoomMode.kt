@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST", "REDUNDANT_ELSE_IN_WHEN")

package com.yandex.mapkit.kmp.layers

actual enum class OverzoomMode {
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
    WITH_PREFETCH,;

    companion object {
        fun toKmp(v: platform.Foundation.NSNumber): OverzoomMode {
            return toKmp(platform.YandexMapsMobile.YMKOverzoomMode.byValue(v.unsignedLongValue))
        }

        fun toKmp(v: platform.YandexMapsMobile.YMKOverzoomMode): OverzoomMode {
            return when (v) {
                platform.YandexMapsMobile.YMKOverzoomMode.YMKOverzoomModeDisabled -> OverzoomMode.DISABLED
                platform.YandexMapsMobile.YMKOverzoomMode.YMKOverzoomModeEnabled -> OverzoomMode.ENABLED
                platform.YandexMapsMobile.YMKOverzoomMode.YMKOverzoomModeWithPrefetch -> OverzoomMode.WITH_PREFETCH
                else -> error("unknown YMKOverzoomMode")
            }
        }
    }
}

fun OverzoomMode.fromKmp(): platform.YandexMapsMobile.YMKOverzoomMode {
    return when (this) {
        OverzoomMode.DISABLED -> platform.YandexMapsMobile.YMKOverzoomMode.YMKOverzoomModeDisabled
        OverzoomMode.ENABLED -> platform.YandexMapsMobile.YMKOverzoomMode.YMKOverzoomModeEnabled
        OverzoomMode.WITH_PREFETCH -> platform.YandexMapsMobile.YMKOverzoomMode.YMKOverzoomModeWithPrefetch
    }
}

