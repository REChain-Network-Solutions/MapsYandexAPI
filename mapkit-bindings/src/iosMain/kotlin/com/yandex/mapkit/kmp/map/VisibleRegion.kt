@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

/**
 * Defines the visible region.
 */
actual typealias VisibleRegion = platform.YandexMapsMobile.YMKVisibleRegion

/**
 * Top-left of the visible region.
 */
actual val VisibleRegion.mpTopLeft: com.yandex.mapkit.kmp.geometry.Point
    get() = topLeft()
/**
 * Top-right of the visible region.
 */
actual val VisibleRegion.mpTopRight: com.yandex.mapkit.kmp.geometry.Point
    get() = topRight()
/**
 * Bottom-left of the visible region.
 */
actual val VisibleRegion.mpBottomLeft: com.yandex.mapkit.kmp.geometry.Point
    get() = bottomLeft()
/**
 * Bottom-right of the visible region.
 */
actual val VisibleRegion.mpBottomRight: com.yandex.mapkit.kmp.geometry.Point
    get() = bottomRight()

actual object VisibleRegionFactory {
    actual fun create(
        topLeft: com.yandex.mapkit.kmp.geometry.Point,
        topRight: com.yandex.mapkit.kmp.geometry.Point,
        bottomLeft: com.yandex.mapkit.kmp.geometry.Point,
        bottomRight: com.yandex.mapkit.kmp.geometry.Point,
    ): VisibleRegion {
        return VisibleRegion.visibleRegionWithTopLeft(
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
        return platform.YandexMapsMobile.YMKVisibleRegionUtils.toPolygonWithVisibleRegion(
            visibleRegion,
        )
    }

    /**
     * @return BoundingBox for provided [VisibleRegion]
     */
    actual fun getBounds(
        visibleRegion: com.yandex.mapkit.kmp.map.VisibleRegion,
    ): com.yandex.mapkit.kmp.geometry.BoundingBox {
        return platform.YandexMapsMobile.YMKVisibleRegionUtils.getBoundsWithVisibleRegion(
            visibleRegion,
        )
    }
}

