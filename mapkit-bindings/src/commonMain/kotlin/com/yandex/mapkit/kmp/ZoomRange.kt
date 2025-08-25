@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

/**
 * Describes the range of zoom levels. Zooming is allowed between zMin
 * and zMax values.
 */
expect class ZoomRangeIdl

/**
 * Lower limit of zoom range, inclusive.
 */
expect val ZoomRangeIdl.mpZMin: Int
/**
 * Upper limit of zoom range, exclusive.
 */
expect val ZoomRangeIdl.mpZMax: Int

expect object ZoomRangeIdlFactory {
    fun create(
        zMin: Int,
        zMax: Int,
    ): ZoomRangeIdl
}

