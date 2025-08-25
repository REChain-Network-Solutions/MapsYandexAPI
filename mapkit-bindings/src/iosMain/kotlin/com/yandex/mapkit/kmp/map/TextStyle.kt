@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST", "REDUNDANT_ELSE_IN_WHEN")

package com.yandex.mapkit.kmp.map

import com.yandex.runtime.kmp.internal.toBoolean
import com.yandex.runtime.kmp.internal.toFloat
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * The style of placemarks's text.
 */
actual typealias TextStyle = platform.YandexMapsMobile.YMKTextStyle

/**
 * Text font size in units. default: 8
 */
actual var TextStyle.mpSize: Float
    get() = size()
    set(value) {
        size = value
    }
/**
 * Text color. default: black
 */
actual var TextStyle.mpColor: com.yandex.runtime.kmp.Color
    get() = color()
    set(value) {
        color = value
    }
/**
 * Outline width in units. default: 1
 */
actual var TextStyle.mpOutlineWidth: Float
    get() = outlineWidth()
    set(value) {
        outlineWidth = value
    }
/**
 * Outline color. default: white
 */
actual var TextStyle.mpOutlineColor: com.yandex.runtime.kmp.Color
    get() = outlineColor()
    set(value) {
        outlineColor = value
    }
/**
 * Text placement position. default: Center
 */
actual var TextStyle.mpPlacement: com.yandex.mapkit.kmp.map.TextStylePlacement
    get() = placement().let { com.yandex.mapkit.kmp.map.TextStylePlacement.toKmp(it) }
    set(value) {
        placement = value.fromKmp()
    }
/**
 * Text offset in units. Measured either from point or form icon edges,
 * depending on [TextStyle.offsetFromIcon] value Direction of the offset
 * specified with [TextStyle.placement] property Ignored when
 * [placement] is 'Center' default: 0
 */
actual var TextStyle.mpOffset: Float
    get() = offset()
    set(value) {
        offset = value
    }
/**
 * When set, [TextStyle.offset] is a padding between the text and icon
 * edges. default: true
 */
actual var TextStyle.mpOffsetFromIcon: Boolean
    get() = offsetFromIcon()
    set(value) {
        offsetFromIcon = value
    }
/**
 * Allow dropping text but keeping icon during conflict resolution
 * default: false
 */
actual var TextStyle.mpTextOptional: Boolean
    get() = textOptional()
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
        return TextStyle.textStyleWithSize(
            size,
            color,
            outlineWidth,
            outlineColor,
            placement.fromKmp(),
            offset,
            offsetFromIcon,
            textOptional,
        )
    }
}

/**
 * Text placement positions
 */
actual enum class TextStylePlacement {
    CENTER,
    LEFT,
    RIGHT,
    TOP,
    BOTTOM,
    TOP_LEFT,
    TOP_RIGHT,
    BOTTOM_LEFT,
    BOTTOM_RIGHT,;

    companion object {
        fun toKmp(v: platform.Foundation.NSNumber): TextStylePlacement {
            return toKmp(platform.YandexMapsMobile.YMKTextStylePlacement.byValue(v.unsignedLongValue))
        }

        fun toKmp(v: platform.YandexMapsMobile.YMKTextStylePlacement): TextStylePlacement {
            return when (v) {
                platform.YandexMapsMobile.YMKTextStylePlacement.YMKTextStylePlacementCenter -> TextStylePlacement.CENTER
                platform.YandexMapsMobile.YMKTextStylePlacement.YMKTextStylePlacementLeft -> TextStylePlacement.LEFT
                platform.YandexMapsMobile.YMKTextStylePlacement.YMKTextStylePlacementRight -> TextStylePlacement.RIGHT
                platform.YandexMapsMobile.YMKTextStylePlacement.YMKTextStylePlacementTop -> TextStylePlacement.TOP
                platform.YandexMapsMobile.YMKTextStylePlacement.YMKTextStylePlacementBottom -> TextStylePlacement.BOTTOM
                platform.YandexMapsMobile.YMKTextStylePlacement.YMKTextStylePlacementTopLeft -> TextStylePlacement.TOP_LEFT
                platform.YandexMapsMobile.YMKTextStylePlacement.YMKTextStylePlacementTopRight -> TextStylePlacement.TOP_RIGHT
                platform.YandexMapsMobile.YMKTextStylePlacement.YMKTextStylePlacementBottomLeft -> TextStylePlacement.BOTTOM_LEFT
                platform.YandexMapsMobile.YMKTextStylePlacement.YMKTextStylePlacementBottomRight -> TextStylePlacement.BOTTOM_RIGHT
                else -> error("unknown YMKTextStylePlacement")
            }
        }
    }
}

fun TextStylePlacement.fromKmp(): platform.YandexMapsMobile.YMKTextStylePlacement {
    return when (this) {
        TextStylePlacement.CENTER -> platform.YandexMapsMobile.YMKTextStylePlacement.YMKTextStylePlacementCenter
        TextStylePlacement.LEFT -> platform.YandexMapsMobile.YMKTextStylePlacement.YMKTextStylePlacementLeft
        TextStylePlacement.RIGHT -> platform.YandexMapsMobile.YMKTextStylePlacement.YMKTextStylePlacementRight
        TextStylePlacement.TOP -> platform.YandexMapsMobile.YMKTextStylePlacement.YMKTextStylePlacementTop
        TextStylePlacement.BOTTOM -> platform.YandexMapsMobile.YMKTextStylePlacement.YMKTextStylePlacementBottom
        TextStylePlacement.TOP_LEFT -> platform.YandexMapsMobile.YMKTextStylePlacement.YMKTextStylePlacementTopLeft
        TextStylePlacement.TOP_RIGHT -> platform.YandexMapsMobile.YMKTextStylePlacement.YMKTextStylePlacementTopRight
        TextStylePlacement.BOTTOM_LEFT -> platform.YandexMapsMobile.YMKTextStylePlacement.YMKTextStylePlacementBottomLeft
        TextStylePlacement.BOTTOM_RIGHT -> platform.YandexMapsMobile.YMKTextStylePlacement.YMKTextStylePlacementBottomRight
    }
}

