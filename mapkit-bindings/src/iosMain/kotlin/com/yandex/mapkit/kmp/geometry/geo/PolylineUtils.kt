@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.geometry.geo

import com.yandex.runtime.kmp.internal.toDouble
import com.yandex.runtime.kmp.internal.toNSNumber

actual object PolylineUtils {
    /**
     * The position of the fork on the road.
     *
     * @param firstPolyline The first path of the fork.
     * @param firstPolylinePosition The position of the first path.
     * @param secondPolyline The second path of the fork.
     * @param secondPolylinePosition The position of the second path.
     */
    actual fun positionsOfFork(
        firstPolyline: com.yandex.mapkit.kmp.geometry.Polyline,
        firstPolylinePosition: com.yandex.mapkit.kmp.geometry.PolylinePosition,
        secondPolyline: com.yandex.mapkit.kmp.geometry.Polyline,
        secondPolylinePosition: com.yandex.mapkit.kmp.geometry.PolylinePosition,
    ): kotlin.collections.List<com.yandex.mapkit.kmp.geometry.PolylinePosition> {
        return platform.YandexMapsMobile.YMKPolylineUtils.positionsOfForkWithFirstPolyline(
            firstPolyline,
            firstPolylinePosition,
            secondPolyline,
            secondPolylinePosition,
        ).let { it as kotlin.collections.List<platform.YandexMapsMobile.YMKPolylinePosition> }
    }

    /**
     * Advance the polyline position by a specified distance in meters.
     *
     * @param polyline The polyline.
     * @param position The polyline position.
     * @param distance Distance.
     */
    actual fun advancePolylinePosition(
        polyline: com.yandex.mapkit.kmp.geometry.Polyline,
        position: com.yandex.mapkit.kmp.geometry.PolylinePosition,
        distance: Double,
    ): com.yandex.mapkit.kmp.geometry.PolylinePosition {
        return platform.YandexMapsMobile.YMKPolylineUtils.advancePolylinePositionWithPolyline(
            polyline,
            position,
            distance,
        )
    }

    /**
     * The point in the polyline.
     *
     * @param geometry The polyline.
     * @param position The polyline position.
     */
    actual fun pointByPolylinePosition(
        geometry: com.yandex.mapkit.kmp.geometry.Polyline,
        position: com.yandex.mapkit.kmp.geometry.PolylinePosition,
    ): com.yandex.mapkit.kmp.geometry.Point {
        return platform.YandexMapsMobile.YMKPolylineUtils.pointByPolylinePositionWithGeometry(
            geometry,
            position,
        )
    }

    actual fun distanceBetweenPolylinePositions(
        polyline: com.yandex.mapkit.kmp.geometry.Polyline,
        from: com.yandex.mapkit.kmp.geometry.PolylinePosition,
        to: com.yandex.mapkit.kmp.geometry.PolylinePosition,
    ): Double {
        return platform.YandexMapsMobile.YMKPolylineUtils.distanceBetweenPolylinePositionsWithPolyline(
            polyline,
            from,
            to,
        )
    }

    /**
     * Creates PolylineIndex for polyline. See [PolylineIndex] for details.
     */
    actual fun createPolylineIndex(
        polyline: com.yandex.mapkit.kmp.geometry.Polyline,
    ): com.yandex.mapkit.kmp.geometry.geo.PolylineIndex {
        return platform.YandexMapsMobile.YMKPolylineUtils.createPolylineIndexWithPolyline(
            polyline,
        ).let { com.yandex.mapkit.kmp.geometry.geo.YMKPolylineIndexWrapper(it) }
    }
}

