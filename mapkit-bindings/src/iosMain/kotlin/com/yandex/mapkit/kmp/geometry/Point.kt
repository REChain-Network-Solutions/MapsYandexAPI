@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.geometry

import com.yandex.runtime.kmp.internal.toDouble
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * A point at the specified coordinates.
 */
actual typealias Point = platform.YandexMapsMobile.YMKPoint

/**
 * The point's latitude.
 */
actual val Point.mpLatitude: Double
    get() = latitude()
/**
 * The point's longitude.
 */
actual val Point.mpLongitude: Double
    get() = longitude()

actual object PointFactory {
    actual fun create(
        latitude: Double,
        longitude: Double,
    ): Point {
        return Point.pointWithLatitude(
            latitude,
            longitude,
        )
    }
}

