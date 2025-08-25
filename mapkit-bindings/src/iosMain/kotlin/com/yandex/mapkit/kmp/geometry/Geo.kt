@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.geometry

import com.yandex.runtime.kmp.internal.toDouble
import com.yandex.runtime.kmp.internal.toNSNumber

actual object Geo {
    /**
     * Calculate the great-circle distance between two points on a sphere
     * with a radius equal to the Earth's radius using the haversine formula
     * described here: http://en.wikipedia.org/wiki/Haversine_formula
     *
     * This formula is numerically better-conditioned for small distances,
     * according to http://en.wikipedia.org/wiki/Great-circle_distance
     */
    actual fun distance(
        firstPoint: com.yandex.mapkit.kmp.geometry.Point,
        secondPoint: com.yandex.mapkit.kmp.geometry.Point,
    ): Double {
        return platform.YandexMapsMobile.YMKGeo.distanceWithFirstPoint(
            firstPoint,
            secondPoint,
        )
    }

    /**
     * Find the point on a given segment (great-circle arc or shorter arc)
     * that is closest to a given point.
     */
    actual fun closestPoint(
        point: com.yandex.mapkit.kmp.geometry.Point,
        segment: com.yandex.mapkit.kmp.geometry.Segment,
    ): com.yandex.mapkit.kmp.geometry.Point {
        return platform.YandexMapsMobile.YMKGeo.closestPointWithPoint(
            point,
            segment,
        )
    }

    /**
     * Find a point X on a given segment AB such that d(AX)/d(AB) = factor,
     * where factor is a given number in [0, 1].
     */
    actual fun pointOnSegmentByFactor(
        segment: com.yandex.mapkit.kmp.geometry.Segment,
        factor: Double,
    ): com.yandex.mapkit.kmp.geometry.Point {
        return platform.YandexMapsMobile.YMKGeo.pointOnSegmentByFactorWithSegment(
            segment,
            factor,
        )
    }

    /**
     * Calculate the course (bearing) between two points in degrees in the
     * range [0, 360].
     */
    actual fun course(
        firstPoint: com.yandex.mapkit.kmp.geometry.Point,
        secondPoint: com.yandex.mapkit.kmp.geometry.Point,
    ): Double {
        return platform.YandexMapsMobile.YMKGeo.courseWithFirstPoint(
            firstPoint,
            secondPoint,
        )
    }
}

