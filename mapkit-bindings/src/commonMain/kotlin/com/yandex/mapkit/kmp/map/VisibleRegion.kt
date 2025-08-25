@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * Defines the visible region.
 */
expect class VisibleRegion

/**
 * Top-left of the visible region.
 */
expect val VisibleRegion.mpTopLeft: com.yandex.mapkit.kmp.geometry.Point
/**
 * Top-right of the visible region.
 */
expect val VisibleRegion.mpTopRight: com.yandex.mapkit.kmp.geometry.Point
/**
 * Bottom-left of the visible region.
 */
expect val VisibleRegion.mpBottomLeft: com.yandex.mapkit.kmp.geometry.Point
/**
 * Bottom-right of the visible region.
 */
expect val VisibleRegion.mpBottomRight: com.yandex.mapkit.kmp.geometry.Point

expect object VisibleRegionFactory {
    fun create(
        topLeft: com.yandex.mapkit.kmp.geometry.Point,
        topRight: com.yandex.mapkit.kmp.geometry.Point,
        bottomLeft: com.yandex.mapkit.kmp.geometry.Point,
        bottomRight: com.yandex.mapkit.kmp.geometry.Point,
    ): VisibleRegion
}

expect object VisibleRegionUtils {
    /**
     * Converts visible region to polygon geometry
     */
    fun toPolygon(
        visibleRegion: com.yandex.mapkit.kmp.map.VisibleRegion,
    ): com.yandex.mapkit.kmp.geometry.Geometry

    /**
     * @return BoundingBox for provided [VisibleRegion]
     */
    fun getBounds(
        visibleRegion: com.yandex.mapkit.kmp.map.VisibleRegion,
    ): com.yandex.mapkit.kmp.geometry.BoundingBox
}

