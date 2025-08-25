@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.geometry.geo

import com.yandex.runtime.kmp.internal.toDouble
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * World coordinates.
 */
actual typealias XYPoint = platform.YandexMapsMobile.YMKXYPoint

/**
 * The horizontal position of the point.
 */
actual val XYPoint.mpX: Double
    get() = x()
/**
 * The vertical position of the point.
 */
actual val XYPoint.mpY: Double
    get() = y()

actual object XYPointFactory {
    actual fun create(
        x: Double,
        y: Double,
    ): XYPoint {
        return XYPoint.xYPointWithX(
            x,
            y,
        )
    }
}

