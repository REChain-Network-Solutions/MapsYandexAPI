@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * The time it took to load map elements.
 */
actual typealias MapLoadStatistics = com.yandex.mapkit.map.MapLoadStatistics

actual val MapLoadStatistics.mpCurZoomGeometryLoaded: Long
    get() = curZoomGeometryLoaded
actual val MapLoadStatistics.mpCurZoomPlacemarksLoaded: Long
    get() = curZoomPlacemarksLoaded
actual val MapLoadStatistics.mpCurZoomLabelsLoaded: Long
    get() = curZoomLabelsLoaded
/**
 * The time it took to load delayed geometry.
 */
actual val MapLoadStatistics.mpDelayedGeometryLoaded: Long
    get() = delayedGeometryLoaded
/**
 * The time it took to load models.
 */
actual val MapLoadStatistics.mpCurZoomModelsLoaded: Long
    get() = curZoomModelsLoaded
/**
 * The time it took to load all map objects.
 */
actual val MapLoadStatistics.mpFullyLoaded: Long
    get() = fullyLoaded
/**
 * The time it took for all map objects to appear.
 */
actual val MapLoadStatistics.mpFullyAppeared: Long
    get() = fullyAppeared
actual val MapLoadStatistics.mpRenderObjectCount: Int
    get() = renderObjectCount
/**
 * Tile memory usage in bytes
 */
actual val MapLoadStatistics.mpTileMemoryUsage: Int
    get() = tileMemoryUsage

actual object MapLoadStatisticsFactory {
    actual fun create(
        curZoomGeometryLoaded: Long,
        curZoomPlacemarksLoaded: Long,
        curZoomLabelsLoaded: Long,
        delayedGeometryLoaded: Long,
        curZoomModelsLoaded: Long,
        fullyLoaded: Long,
        fullyAppeared: Long,
        renderObjectCount: Int,
        tileMemoryUsage: Int,
    ): MapLoadStatistics {
        return MapLoadStatistics(
            curZoomGeometryLoaded,
            curZoomPlacemarksLoaded,
            curZoomLabelsLoaded,
            delayedGeometryLoaded,
            curZoomModelsLoaded,
            fullyLoaded,
            fullyAppeared,
            renderObjectCount,
            tileMemoryUsage,
        )
    }
}

