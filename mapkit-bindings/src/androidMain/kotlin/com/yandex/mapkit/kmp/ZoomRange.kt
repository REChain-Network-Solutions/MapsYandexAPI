@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

/**
 * Describes the range of zoom levels. Zooming is allowed between zMin
 * and zMax values.
 */
actual typealias ZoomRangeIdl = com.yandex.mapkit.ZoomRange

/**
 * Lower limit of zoom range, inclusive.
 */
actual val ZoomRangeIdl.mpZMin: Int
    get() = zMin
/**
 * Upper limit of zoom range, exclusive.
 */
actual val ZoomRangeIdl.mpZMax: Int
    get() = zMax

actual object ZoomRangeIdlFactory {
    actual fun create(
        zMin: Int,
        zMax: Int,
    ): ZoomRangeIdl {
        return ZoomRangeIdl(
            zMin,
            zMax,
        )
    }
}

