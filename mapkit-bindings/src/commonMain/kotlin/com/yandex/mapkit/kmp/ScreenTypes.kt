@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

/**
 * A point on the device screen.
 */
expect class ScreenPoint

/**
 * The horizontal position of the point in pixels from the left screen
 * border.
 */
expect val ScreenPoint.mpX: Float
/**
 * The vertical position of the point in pixels from the top screen
 * border.
 */
expect val ScreenPoint.mpY: Float

expect object ScreenPointFactory {
    fun create(
        x: Float,
        y: Float,
    ): ScreenPoint
}

/**
 * A rectangle on the device screen.
 */
expect class ScreenRect

/**
 * The position of the top left corner of the rectangle.
 */
expect val ScreenRect.mpTopLeft: com.yandex.mapkit.kmp.ScreenPoint
/**
 * The position of the bottom right corner of the rectangle.
 */
expect val ScreenRect.mpBottomRight: com.yandex.mapkit.kmp.ScreenPoint

expect object ScreenRectFactory {
    fun create(
        topLeft: com.yandex.mapkit.kmp.ScreenPoint,
        bottomRight: com.yandex.mapkit.kmp.ScreenPoint,
    ): ScreenRect
}

