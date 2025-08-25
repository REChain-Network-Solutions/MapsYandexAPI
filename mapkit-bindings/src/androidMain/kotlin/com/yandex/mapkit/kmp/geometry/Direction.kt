@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.geometry

/**
 * Represents the panorama direction.
 */
actual typealias Direction = com.yandex.mapkit.geometry.Direction

/**
 * Panorama azimuth.
 */
actual val Direction.mpAzimuth: Double
    get() = azimuth
/**
 * Panorama tilt.
 */
actual val Direction.mpTilt: Double
    get() = tilt

actual object DirectionFactory {
    actual fun create(
        azimuth: Double,
        tilt: Double,
    ): Direction {
        return Direction(
            azimuth,
            tilt,
        )
    }
}

