@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * The style of placemarks's text.
 */
expect class TextStyle

/**
 * Text font size in units. default: 8
 */
expect var TextStyle.mpSize: Float
/**
 * Text color. default: black
 */
expect var TextStyle.mpColor: com.yandex.runtime.kmp.Color
/**
 * Outline width in units. default: 1
 */
expect var TextStyle.mpOutlineWidth: Float
/**
 * Outline color. default: white
 */
expect var TextStyle.mpOutlineColor: com.yandex.runtime.kmp.Color
/**
 * Text placement position. default: Center
 */
expect var TextStyle.mpPlacement: com.yandex.mapkit.kmp.map.TextStylePlacement
/**
 * Text offset in units. Measured either from point or form icon edges,
 * depending on [TextStyle.offsetFromIcon] value Direction of the offset
 * specified with [TextStyle.placement] property Ignored when
 * [placement] is 'Center' default: 0
 */
expect var TextStyle.mpOffset: Float
/**
 * When set, [TextStyle.offset] is a padding between the text and icon
 * edges. default: true
 */
expect var TextStyle.mpOffsetFromIcon: Boolean
/**
 * Allow dropping text but keeping icon during conflict resolution
 * default: false
 */
expect var TextStyle.mpTextOptional: Boolean

expect object TextStyleFactory {
    fun create(
        size: Float,
        color: com.yandex.runtime.kmp.Color,
        outlineWidth: Float,
        outlineColor: com.yandex.runtime.kmp.Color,
        placement: com.yandex.mapkit.kmp.map.TextStylePlacement,
        offset: Float,
        offsetFromIcon: Boolean,
        textOptional: Boolean,
    ): TextStyle
}

/**
 * Text placement positions
 */
expect enum class TextStylePlacement {
    CENTER,
    LEFT,
    RIGHT,
    TOP,
    BOTTOM,
    TOP_LEFT,
    TOP_RIGHT,
    BOTTOM_LEFT,
    BOTTOM_RIGHT,
}

