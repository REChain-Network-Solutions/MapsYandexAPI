@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp

import com.yandex.runtime.kmp.internal.toInt
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * Describes the range of zoom levels. Zooming is allowed between zMin
 * and zMax values.
 */
actual typealias ZoomRangeIdl = platform.YandexMapsMobile.YMKZoomRange

/**
 * Lower limit of zoom range, inclusive.
 */
actual val ZoomRangeIdl.mpZMin: Int
    get() = zMin().toInt()
/**
 * Upper limit of zoom range, exclusive.
 */
actual val ZoomRangeIdl.mpZMax: Int
    get() = zMax().toInt()

actual object ZoomRangeIdlFactory {
    actual fun create(
        zMin: Int,
        zMax: Int,
    ): ZoomRangeIdl {
        return ZoomRangeIdl.zoomRangeWithZMin(
            zMin.toULong(),
            zMax.toULong(),
        )
    }
}

