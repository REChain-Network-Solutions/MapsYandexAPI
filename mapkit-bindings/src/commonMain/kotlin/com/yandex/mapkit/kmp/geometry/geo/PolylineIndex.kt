@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.geometry.geo

/**
 * Provides methods for binding locations to a polyline
 */
expect interface PolylineIndex {
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
    fun closestPolylinePosition(
        point: com.yandex.mapkit.kmp.geometry.Point,
        priority: com.yandex.mapkit.kmp.geometry.geo.PolylineIndexPriority,
        maxLocationBias: Double,
    ): com.yandex.mapkit.kmp.geometry.PolylinePosition?

    /**
     * Finds the closest polyline position between the two positions.
     * Returns null if there is no position that satisfies the condition
     * distance(point, position) < maxLocationBias.
     */
    fun closestPolylinePosition(
        point: com.yandex.mapkit.kmp.geometry.Point,
        positionFrom: com.yandex.mapkit.kmp.geometry.PolylinePosition,
        positionTo: com.yandex.mapkit.kmp.geometry.PolylinePosition,
        maxLocationBias: Double,
    ): com.yandex.mapkit.kmp.geometry.PolylinePosition?
}

expect enum class PolylineIndexPriority {
    CLOSEST_TO_RAW_POINT,
    CLOSEST_TO_START,
}

