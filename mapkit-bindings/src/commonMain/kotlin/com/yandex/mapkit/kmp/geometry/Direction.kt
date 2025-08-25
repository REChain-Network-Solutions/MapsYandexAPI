@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.geometry

/**
 * Represents the panorama direction.
 */
expect class Direction

/**
 * Panorama azimuth.
 */
expect val Direction.mpAzimuth: Double
/**
 * Panorama tilt.
 */
expect val Direction.mpTilt: Double

expect object DirectionFactory {
    fun create(
        azimuth: Double,
        tilt: Double,
    ): Direction
}

