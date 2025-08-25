@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

/**
 * A point on the device screen.
 */
actual typealias ScreenPoint = com.yandex.mapkit.ScreenPoint

/**
 * The horizontal position of the point in pixels from the left screen
 * border.
 */
actual val ScreenPoint.mpX: Float
    get() = x
/**
 * The vertical position of the point in pixels from the top screen
 * border.
 */
actual val ScreenPoint.mpY: Float
    get() = y

actual object ScreenPointFactory {
    actual fun create(
        x: Float,
        y: Float,
    ): ScreenPoint {
        return ScreenPoint(
            x,
            y,
        )
    }
}

/**
 * A rectangle on the device screen.
 */
actual typealias ScreenRect = com.yandex.mapkit.ScreenRect

/**
 * The position of the top left corner of the rectangle.
 */
actual val ScreenRect.mpTopLeft: com.yandex.mapkit.kmp.ScreenPoint
    get() = topLeft
/**
 * The position of the bottom right corner of the rectangle.
 */
actual val ScreenRect.mpBottomRight: com.yandex.mapkit.kmp.ScreenPoint
    get() = bottomRight

actual object ScreenRectFactory {
    actual fun create(
        topLeft: com.yandex.mapkit.kmp.ScreenPoint,
        bottomRight: com.yandex.mapkit.kmp.ScreenPoint,
    ): ScreenRect {
        return ScreenRect(
            topLeft,
            bottomRight,
        )
    }
}

