@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * The style of placemarks's text.
 */
actual typealias TextStyle = com.yandex.mapkit.map.TextStyle

/**
 * Text font size in units. default: 8
 */
actual var TextStyle.mpSize: Float
    get() = size
    set(value) {
        size = value
    }
/**
 * Text color. default: black
 */
actual var TextStyle.mpColor: com.yandex.runtime.kmp.Color
    get() = color
    set(value) {
        color = value
    }
/**
 * Outline width in units. default: 1
 */
actual var TextStyle.mpOutlineWidth: Float
    get() = outlineWidth
    set(value) {
        outlineWidth = value
    }
/**
 * Outline color. default: white
 */
actual var TextStyle.mpOutlineColor: com.yandex.runtime.kmp.Color
    get() = outlineColor
    set(value) {
        outlineColor = value
    }
/**
 * Text placement position. default: Center
 */
actual var TextStyle.mpPlacement: com.yandex.mapkit.kmp.map.TextStylePlacement
    get() = placement
    set(value) {
        placement = value
    }
/**
 * Text offset in units. Measured either from point or form icon edges,
 * depending on [TextStyle.offsetFromIcon] value Direction of the offset
 * specified with [TextStyle.placement] property Ignored when
 * [placement] is 'Center' default: 0
 */
actual var TextStyle.mpOffset: Float
    get() = offset
    set(value) {
        offset = value
    }
/**
 * When set, [TextStyle.offset] is a padding between the text and icon
 * edges. default: true
 */
actual var TextStyle.mpOffsetFromIcon: Boolean
    get() = offsetFromIcon
    set(value) {
        offsetFromIcon = value
    }
/**
 * Allow dropping text but keeping icon during conflict resolution
 * default: false
 */
actual var TextStyle.mpTextOptional: Boolean
    get() = textOptional
    set(value) {
        textOptional = value
    }

actual object TextStyleFactory {
    actual fun create(
        size: Float,
        color: com.yandex.runtime.kmp.Color,
        outlineWidth: Float,
        outlineColor: com.yandex.runtime.kmp.Color,
        placement: com.yandex.mapkit.kmp.map.TextStylePlacement,
        offset: Float,
        offsetFromIcon: Boolean,
        textOptional: Boolean,
    ): TextStyle {
        return TextStyle(
            size,
            color,
            outlineWidth,
            outlineColor,
            placement,
            offset,
            offsetFromIcon,
            textOptional,
        )
    }
}

/**
 * Text placement positions
 */
actual typealias TextStylePlacement = com.yandex.mapkit.map.TextStyle.Placement

