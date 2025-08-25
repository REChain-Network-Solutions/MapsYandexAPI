@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * The position of the camera.
 */
actual typealias CameraPosition = com.yandex.mapkit.map.CameraPosition

/**
 * The point the camera is looking at.
 */
actual val CameraPosition.mpTarget: com.yandex.mapkit.kmp.geometry.Point
    get() = target
/**
 * Zoom level. 0 corresponds to the whole world displayed in a single
 * tile.
 */
actual val CameraPosition.mpZoom: Float
    get() = zoom
/**
 * Angle between north and the direction of interest on the map plane,
 * in degrees in the range [0, 360).
 */
actual val CameraPosition.mpAzimuth: Float
    get() = azimuth
/**
 * Camera tilt in degrees. 0 means vertical downward.
 */
actual val CameraPosition.mpTilt: Float
    get() = tilt

actual object CameraPositionFactory {
    actual fun create(
        target: com.yandex.mapkit.kmp.geometry.Point,
        zoom: Float,
        azimuth: Float,
        tilt: Float,
    ): CameraPosition {
        return CameraPosition(
            target,
            zoom,
            azimuth,
            tilt,
        )
    }
}

