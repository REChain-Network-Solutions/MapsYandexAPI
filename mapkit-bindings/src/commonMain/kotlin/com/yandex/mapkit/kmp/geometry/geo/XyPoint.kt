@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.geometry.geo

/**
 * World coordinates.
 */
expect class XYPoint

/**
 * The horizontal position of the point.
 */
expect val XYPoint.mpX: Double
/**
 * The vertical position of the point.
 */
expect val XYPoint.mpY: Double

expect object XYPointFactory {
    fun create(
        x: Double,
        y: Double,
    ): XYPoint
}

