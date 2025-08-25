@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST", "REDUNDANT_ELSE_IN_WHEN")

package com.yandex.mapkit.search.kmp

/**
 * Result display type.
 */
actual enum class DisplayType {
    /**
     * Each result makes sense without the others (that is toponyms), they
     * probably shouldn'be displayed as a group, and probably there's no
     * need to make additional requests after user moves the map or zooms in
     * or out.
     */
    SINGLE,
    /**
     * Results are meaningful as a group (that is category query), they
     * should be displayed all together, and it makes sense to send
     * additional requests after user changes visible map region.
     */
    MULTIPLE,;

    companion object {
        fun toKmp(v: platform.Foundation.NSNumber): DisplayType {
            return toKmp(platform.YandexMapsMobile.YMKSearchDisplayType.byValue(v.unsignedLongValue))
        }

        fun toKmp(v: platform.YandexMapsMobile.YMKSearchDisplayType): DisplayType {
            return when (v) {
                platform.YandexMapsMobile.YMKSearchDisplayType.YMKSearchDisplayTypeSingle -> DisplayType.SINGLE
                platform.YandexMapsMobile.YMKSearchDisplayType.YMKSearchDisplayTypeMultiple -> DisplayType.MULTIPLE
                else -> error("unknown YMKSearchDisplayType")
            }
        }
    }
}

fun DisplayType.fromKmp(): platform.YandexMapsMobile.YMKSearchDisplayType {
    return when (this) {
        DisplayType.SINGLE -> platform.YandexMapsMobile.YMKSearchDisplayType.YMKSearchDisplayTypeSingle
        DisplayType.MULTIPLE -> platform.YandexMapsMobile.YMKSearchDisplayType.YMKSearchDisplayTypeMultiple
    }
}

