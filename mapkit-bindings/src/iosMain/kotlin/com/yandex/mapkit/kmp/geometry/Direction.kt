@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.geometry

import com.yandex.runtime.kmp.internal.toDouble
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * Represents the panorama direction.
 */
actual typealias Direction = platform.YandexMapsMobile.YMKDirection

/**
 * Panorama azimuth.
 */
actual val Direction.mpAzimuth: Double
    get() = azimuth()
/**
 * Panorama tilt.
 */
actual val Direction.mpTilt: Double
    get() = tilt()

actual object DirectionFactory {
    actual fun create(
        azimuth: Double,
        tilt: Double,
    ): Direction {
        return Direction.directionWithAzimuth(
            azimuth,
            tilt,
        )
    }
}

