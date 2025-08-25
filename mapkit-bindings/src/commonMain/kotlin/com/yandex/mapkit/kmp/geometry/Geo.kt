@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.geometry

expect object Geo {
    /**
     * Calculate the great-circle distance between two points on a sphere
     * with a radius equal to the Earth's radius using the haversine formula
     * described here: http://en.wikipedia.org/wiki/Haversine_formula
     *
     * This formula is numerically better-conditioned for small distances,
     * according to http://en.wikipedia.org/wiki/Great-circle_distance
     */
    fun distance(
        firstPoint: com.yandex.mapkit.kmp.geometry.Point,
        secondPoint: com.yandex.mapkit.kmp.geometry.Point,
    ): Double

    /**
     * Find the point on a given segment (great-circle arc or shorter arc)
     * that is closest to a given point.
     */
    fun closestPoint(
        point: com.yandex.mapkit.kmp.geometry.Point,
        segment: com.yandex.mapkit.kmp.geometry.Segment,
    ): com.yandex.mapkit.kmp.geometry.Point

    /**
     * Find a point X on a given segment AB such that d(AX)/d(AB) = factor,
     * where factor is a given number in [0, 1].
     */
    fun pointOnSegmentByFactor(
        segment: com.yandex.mapkit.kmp.geometry.Segment,
        factor: Double,
    ): com.yandex.mapkit.kmp.geometry.Point

    /**
     * Calculate the course (bearing) between two points in degrees in the
     * range [0, 360].
     */
    fun course(
        firstPoint: com.yandex.mapkit.kmp.geometry.Point,
        secondPoint: com.yandex.mapkit.kmp.geometry.Point,
    ): Double
}

