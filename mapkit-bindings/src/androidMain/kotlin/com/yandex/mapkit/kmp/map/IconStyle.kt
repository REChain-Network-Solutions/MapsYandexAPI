@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * The style of created icons.
 */
actual typealias IconStyle = com.yandex.mapkit.map.IconStyle

/**
 * An anchor is used to alter image placement. Normalized: (0.0f, 0.0f)
 * denotes the top left image corner; (1.0f, 1.0f) denotes bottom right.
 * Default is (0.5f, 0.5f).
 *
 */
actual var IconStyle.mpAnchor: com.yandex.runtime.kmp.NativePoint?
    get() = anchor
    set(value) {
        anchor = value
    }
/**
 * Icon rotation type. Default: NoRotation.
 *
 */
actual var IconStyle.mpRotationType: com.yandex.mapkit.kmp.map.RotationType?
    get() = rotationType
    set(value) {
        rotationType = value
    }
/**
 * Z-index of the icon, relative to the placemark's z-index.
 *
 */
actual var IconStyle.mpZIndex: Float?
    get() = zIndex
    set(value) {
        zIndex = value
    }
/**
 * If true, the icon is displayed on the map surface. If false, the icon
 * is displayed on the screen surface. Default: false.
 *
 */
actual var IconStyle.mpFlat: Boolean?
    get() = flat
    set(value) {
        flat = value
    }
/**
 * Sets icon visibility. Default: true.
 *
 */
actual var IconStyle.mpVisible: Boolean?
    get() = visible
    set(value) {
        visible = value
    }
/**
 * Scale of the icon. Default: 1.0f.
 *
 */
actual var IconStyle.mpScale: Float?
    get() = scale
    set(value) {
        scale = value
    }
/**
 * Tappable area on the icon. Coordinates are measured the same way as
 * anchor coordinates. If rect is empty or invalid, the icon will not
 * process taps. By default, icons process all taps.
 *
 */
actual var IconStyle.mpTappableArea: com.yandex.mapkit.kmp.map.Rect?
    get() = tappableArea
    set(value) {
        tappableArea = value
    }

actual object IconStyleFactory {
    actual fun create(
        anchor: com.yandex.runtime.kmp.NativePoint?,
        rotationType: com.yandex.mapkit.kmp.map.RotationType?,
        zIndex: Float?,
        flat: Boolean?,
        visible: Boolean?,
        scale: Float?,
        tappableArea: com.yandex.mapkit.kmp.map.Rect?,
    ): IconStyle {
        return IconStyle(
            anchor,
            rotationType,
            zIndex,
            flat,
            visible,
            scale,
            tappableArea,
        )
    }
}

