@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

import com.yandex.runtime.kmp.internal.toDouble
import com.yandex.runtime.kmp.internal.toInt
import com.yandex.runtime.kmp.internal.toLong
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * The time it took to load map elements.
 */
actual typealias MapLoadStatistics = platform.YandexMapsMobile.YMKMapLoadStatistics

actual val MapLoadStatistics.mpCurZoomGeometryLoaded: Long
    get() = curZoomGeometryLoaded().toLong()
actual val MapLoadStatistics.mpCurZoomPlacemarksLoaded: Long
    get() = curZoomPlacemarksLoaded().toLong()
actual val MapLoadStatistics.mpCurZoomLabelsLoaded: Long
    get() = curZoomLabelsLoaded().toLong()
/**
 * The time it took to load delayed geometry.
 */
actual val MapLoadStatistics.mpDelayedGeometryLoaded: Long
    get() = delayedGeometryLoaded().toLong()
/**
 * The time it took to load models.
 */
actual val MapLoadStatistics.mpCurZoomModelsLoaded: Long
    get() = curZoomModelsLoaded().toLong()
/**
 * The time it took to load all map objects.
 */
actual val MapLoadStatistics.mpFullyLoaded: Long
    get() = fullyLoaded().toLong()
/**
 * The time it took for all map objects to appear.
 */
actual val MapLoadStatistics.mpFullyAppeared: Long
    get() = fullyAppeared().toLong()
actual val MapLoadStatistics.mpRenderObjectCount: Int
    get() = renderObjectCount().toInt()
/**
 * Tile memory usage in bytes
 */
actual val MapLoadStatistics.mpTileMemoryUsage: Int
    get() = tileMemoryUsage().toInt()

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
        return MapLoadStatistics.mapLoadStatisticsWithCurZoomGeometryLoaded(
            curZoomGeometryLoaded.toDouble(),
            curZoomPlacemarksLoaded.toDouble(),
            curZoomLabelsLoaded.toDouble(),
            delayedGeometryLoaded.toDouble(),
            curZoomModelsLoaded.toDouble(),
            fullyLoaded.toDouble(),
            fullyAppeared.toDouble(),
            renderObjectCount.toLong(),
            tileMemoryUsage.toULong(),
        )
    }
}

