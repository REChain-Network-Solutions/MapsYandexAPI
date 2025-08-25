@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.geometry.geo

/**
 * World coordinates.
 */
actual typealias XYPoint = com.yandex.mapkit.geometry.geo.XYPoint

/**
 * The horizontal position of the point.
 */
actual val XYPoint.mpX: Double
    get() = x
/**
 * The vertical position of the point.
 */
actual val XYPoint.mpY: Double
    get() = y

actual object XYPointFactory {
    actual fun create(
        x: Double,
        y: Double,
    ): XYPoint {
        return XYPoint(
            x,
            y,
        )
    }
}

