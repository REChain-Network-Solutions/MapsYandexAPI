@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.geometry

/**
 * A point at the specified coordinates.
 */
expect class Point

/**
 * The point's latitude.
 */
expect val Point.mpLatitude: Double
/**
 * The point's longitude.
 */
expect val Point.mpLongitude: Double

expect object PointFactory {
    fun create(
        latitude: Double,
        longitude: Double,
    ): Point
}

