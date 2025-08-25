@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * The time it took to load map elements.
 */
expect class MapLoadStatistics

expect val MapLoadStatistics.mpCurZoomGeometryLoaded: Long
expect val MapLoadStatistics.mpCurZoomPlacemarksLoaded: Long
expect val MapLoadStatistics.mpCurZoomLabelsLoaded: Long
/**
 * The time it took to load delayed geometry.
 */
expect val MapLoadStatistics.mpDelayedGeometryLoaded: Long
/**
 * The time it took to load models.
 */
expect val MapLoadStatistics.mpCurZoomModelsLoaded: Long
/**
 * The time it took to load all map objects.
 */
expect val MapLoadStatistics.mpFullyLoaded: Long
/**
 * The time it took for all map objects to appear.
 */
expect val MapLoadStatistics.mpFullyAppeared: Long
expect val MapLoadStatistics.mpRenderObjectCount: Int
/**
 * Tile memory usage in bytes
 */
expect val MapLoadStatistics.mpTileMemoryUsage: Int

expect object MapLoadStatisticsFactory {
    fun create(
        curZoomGeometryLoaded: Long,
        curZoomPlacemarksLoaded: Long,
        curZoomLabelsLoaded: Long,
        delayedGeometryLoaded: Long,
        curZoomModelsLoaded: Long,
        fullyLoaded: Long,
        fullyAppeared: Long,
        renderObjectCount: Int,
        tileMemoryUsage: Int,
    ): MapLoadStatistics
}

