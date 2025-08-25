@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * Defines the visible region.
 */
actual typealias VisibleRegion = com.yandex.mapkit.map.VisibleRegion

/**
 * Top-left of the visible region.
 */
actual val VisibleRegion.mpTopLeft: com.yandex.mapkit.kmp.geometry.Point
    get() = topLeft
/**
 * Top-right of the visible region.
 */
actual val VisibleRegion.mpTopRight: com.yandex.mapkit.kmp.geometry.Point
    get() = topRight
/**
 * Bottom-left of the visible region.
 */
actual val VisibleRegion.mpBottomLeft: com.yandex.mapkit.kmp.geometry.Point
    get() = bottomLeft
/**
 * Bottom-right of the visible region.
 */
actual val VisibleRegion.mpBottomRight: com.yandex.mapkit.kmp.geometry.Point
    get() = bottomRight

actual object VisibleRegionFactory {
    actual fun create(
        topLeft: com.yandex.mapkit.kmp.geometry.Point,
        topRight: com.yandex.mapkit.kmp.geometry.Point,
        bottomLeft: com.yandex.mapkit.kmp.geometry.Point,
        bottomRight: com.yandex.mapkit.kmp.geometry.Point,
    ): VisibleRegion {
        return VisibleRegion(
            topLeft,
            topRight,
            bottomLeft,
            bottomRight,
        )
    }
}

actual object VisibleRegionUtils {
    /**
     * Converts visible region to polygon geometry
     */
    actual fun toPolygon(
        visibleRegion: com.yandex.mapkit.kmp.map.VisibleRegion,
    ): com.yandex.mapkit.kmp.geometry.Geometry {
        return com.yandex.mapkit.map.VisibleRegionUtils.toPolygon(
            visibleRegion,
        )
    }

    /**
     * @return BoundingBox for provided [VisibleRegion]
     */
    actual fun getBounds(
        visibleRegion: com.yandex.mapkit.kmp.map.VisibleRegion,
    ): com.yandex.mapkit.kmp.geometry.BoundingBox {
        return com.yandex.mapkit.map.VisibleRegionUtils.getBounds(
            visibleRegion,
        )
    }
}

