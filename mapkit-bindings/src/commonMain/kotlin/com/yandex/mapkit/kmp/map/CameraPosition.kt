@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * The position of the camera.
 */
expect class CameraPosition

/**
 * The point the camera is looking at.
 */
expect val CameraPosition.mpTarget: com.yandex.mapkit.kmp.geometry.Point
/**
 * Zoom level. 0 corresponds to the whole world displayed in a single
 * tile.
 */
expect val CameraPosition.mpZoom: Float
/**
 * Angle between north and the direction of interest on the map plane,
 * in degrees in the range [0, 360).
 */
expect val CameraPosition.mpAzimuth: Float
/**
 * Camera tilt in degrees. 0 means vertical downward.
 */
expect val CameraPosition.mpTilt: Float

expect object CameraPositionFactory {
    fun create(
        target: com.yandex.mapkit.kmp.geometry.Point,
        zoom: Float,
        azimuth: Float,
        tilt: Float,
    ): CameraPosition
}

