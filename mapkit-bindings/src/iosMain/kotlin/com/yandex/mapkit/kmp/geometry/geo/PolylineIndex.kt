@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST", "REDUNDANT_ELSE_IN_WHEN")

package com.yandex.mapkit.kmp.geometry.geo

import com.yandex.runtime.kmp.internal.toDouble
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * Provides methods for binding locations to a polyline
 */
actual interface PolylineIndex {
    val impl: platform.YandexMapsMobile.YMKPolylineIndex

    /**
     * Finds closest polyline position. Returns null if there is no position
     * that satisfies the condition distance(point, position) <
     * maxLocationBias.
     *
     * Two modes are available: 1. Find the closest polyline position to a
     * raw point [PolylineIndexPriority.CLOSEST_TO_RAW_POINT] 2. Find the
     * closest polyline position to the polyline start
     * [PolylineIndexPriority.CLOSEST_TO_START].
     */
    actual fun closestPolylinePosition(
        point: com.yandex.mapkit.kmp.geometry.Point,
        priority: com.yandex.mapkit.kmp.geometry.geo.PolylineIndexPriority,
        maxLocationBias: Double,
    ): com.yandex.mapkit.kmp.geometry.PolylinePosition?

    /**
     * Finds the closest polyline position between the two positions.
     * Returns null if there is no position that satisfies the condition
     * distance(point, position) < maxLocationBias.
     */
    actual fun closestPolylinePosition(
        point: com.yandex.mapkit.kmp.geometry.Point,
        positionFrom: com.yandex.mapkit.kmp.geometry.PolylinePosition,
        positionTo: com.yandex.mapkit.kmp.geometry.PolylinePosition,
        maxLocationBias: Double,
    ): com.yandex.mapkit.kmp.geometry.PolylinePosition?
}

open class YMKPolylineIndexWrapper(override val impl: platform.YandexMapsMobile.YMKPolylineIndex) : PolylineIndex {
    override fun closestPolylinePosition(
        point: com.yandex.mapkit.kmp.geometry.Point,
        priority: com.yandex.mapkit.kmp.geometry.geo.PolylineIndexPriority,
        maxLocationBias: Double,
    ): com.yandex.mapkit.kmp.geometry.PolylinePosition? {
        return impl.closestPolylinePositionWithPoint(
            point,
            priority.fromKmp(),
            maxLocationBias,
        )
    }

    override fun closestPolylinePosition(
        point: com.yandex.mapkit.kmp.geometry.Point,
        positionFrom: com.yandex.mapkit.kmp.geometry.PolylinePosition,
        positionTo: com.yandex.mapkit.kmp.geometry.PolylinePosition,
        maxLocationBias: Double,
    ): com.yandex.mapkit.kmp.geometry.PolylinePosition? {
        return impl.closestPolylinePositionWithPoint(
            point,
            positionFrom,
            positionTo,
            maxLocationBias,
        )
    }
}

actual enum class PolylineIndexPriority {
    CLOSEST_TO_RAW_POINT,
    CLOSEST_TO_START,;

    companion object {
        fun toKmp(v: platform.Foundation.NSNumber): PolylineIndexPriority {
            return toKmp(platform.YandexMapsMobile.YMKPolylineIndexPriority.byValue(v.unsignedLongValue))
        }

        fun toKmp(v: platform.YandexMapsMobile.YMKPolylineIndexPriority): PolylineIndexPriority {
            return when (v) {
                platform.YandexMapsMobile.YMKPolylineIndexPriority.YMKPolylineIndexPriorityClosestToRawPoint -> PolylineIndexPriority.CLOSEST_TO_RAW_POINT
                platform.YandexMapsMobile.YMKPolylineIndexPriority.YMKPolylineIndexPriorityClosestToStart -> PolylineIndexPriority.CLOSEST_TO_START
                else -> error("unknown YMKPolylineIndexPriority")
            }
        }
    }
}

fun PolylineIndexPriority.fromKmp(): platform.YandexMapsMobile.YMKPolylineIndexPriority {
    return when (this) {
        PolylineIndexPriority.CLOSEST_TO_RAW_POINT -> platform.YandexMapsMobile.YMKPolylineIndexPriority.YMKPolylineIndexPriorityClosestToRawPoint
        PolylineIndexPriority.CLOSEST_TO_START -> platform.YandexMapsMobile.YMKPolylineIndexPriority.YMKPolylineIndexPriorityClosestToStart
    }
}

