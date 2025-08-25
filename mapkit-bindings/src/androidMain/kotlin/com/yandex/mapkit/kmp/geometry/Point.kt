@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.geometry

/**
 * A point at the specified coordinates.
 */
actual typealias Point = com.yandex.mapkit.geometry.Point

/**
 * The point's latitude.
 */
actual val Point.mpLatitude: Double
    get() = latitude
/**
 * The point's longitude.
 */
actual val Point.mpLongitude: Double
    get() = longitude

actual object PointFactory {
    actual fun create(
        latitude: Double,
        longitude: Double,
    ): Point {
        return Point(
            latitude,
            longitude,
        )
    }
}

