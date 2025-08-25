@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * The style of created icons.
 */
expect class IconStyle

/**
 * An anchor is used to alter image placement. Normalized: (0.0f, 0.0f)
 * denotes the top left image corner; (1.0f, 1.0f) denotes bottom right.
 * Default is (0.5f, 0.5f).
 *
 */
expect var IconStyle.mpAnchor: com.yandex.runtime.kmp.NativePoint?
/**
 * Icon rotation type. Default: NoRotation.
 *
 */
expect var IconStyle.mpRotationType: com.yandex.mapkit.kmp.map.RotationType?
/**
 * Z-index of the icon, relative to the placemark's z-index.
 *
 */
expect var IconStyle.mpZIndex: Float?
/**
 * If true, the icon is displayed on the map surface. If false, the icon
 * is displayed on the screen surface. Default: false.
 *
 */
expect var IconStyle.mpFlat: Boolean?
/**
 * Sets icon visibility. Default: true.
 *
 */
expect var IconStyle.mpVisible: Boolean?
/**
 * Scale of the icon. Default: 1.0f.
 *
 */
expect var IconStyle.mpScale: Float?
/**
 * Tappable area on the icon. Coordinates are measured the same way as
 * anchor coordinates. If rect is empty or invalid, the icon will not
 * process taps. By default, icons process all taps.
 *
 */
expect var IconStyle.mpTappableArea: com.yandex.mapkit.kmp.map.Rect?

expect object IconStyleFactory {
    fun create(
        anchor: com.yandex.runtime.kmp.NativePoint?,
        rotationType: com.yandex.mapkit.kmp.map.RotationType?,
        zIndex: Float?,
        flat: Boolean?,
        visible: Boolean?,
        scale: Float?,
        tappableArea: com.yandex.mapkit.kmp.map.Rect?,
    ): IconStyle
}

