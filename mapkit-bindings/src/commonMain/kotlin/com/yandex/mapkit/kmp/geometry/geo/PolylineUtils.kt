@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.geometry.geo

expect object PolylineUtils {
    /**
     * The position of the fork on the road.
     *
     * @param firstPolyline The first path of the fork.
     * @param firstPolylinePosition The position of the first path.
     * @param secondPolyline The second path of the fork.
     * @param secondPolylinePosition The position of the second path.
     */
    fun positionsOfFork(
        firstPolyline: com.yandex.mapkit.kmp.geometry.Polyline,
        firstPolylinePosition: com.yandex.mapkit.kmp.geometry.PolylinePosition,
        secondPolyline: com.yandex.mapkit.kmp.geometry.Polyline,
        secondPolylinePosition: com.yandex.mapkit.kmp.geometry.PolylinePosition,
    ): kotlin.collections.List<com.yandex.mapkit.kmp.geometry.PolylinePosition>

    /**
     * Advance the polyline position by a specified distance in meters.
     *
     * @param polyline The polyline.
     * @param position The polyline position.
     * @param distance Distance.
     */
    fun advancePolylinePosition(
        polyline: com.yandex.mapkit.kmp.geometry.Polyline,
        position: com.yandex.mapkit.kmp.geometry.PolylinePosition,
        distance: Double,
    ): com.yandex.mapkit.kmp.geometry.PolylinePosition

    /**
     * The point in the polyline.
     *
     * @param geometry The polyline.
     * @param position The polyline position.
     */
    fun pointByPolylinePosition(
        geometry: com.yandex.mapkit.kmp.geometry.Polyline,
        position: com.yandex.mapkit.kmp.geometry.PolylinePosition,
    ): com.yandex.mapkit.kmp.geometry.Point

    fun distanceBetweenPolylinePositions(
        polyline: com.yandex.mapkit.kmp.geometry.Polyline,
        from: com.yandex.mapkit.kmp.geometry.PolylinePosition,
        to: com.yandex.mapkit.kmp.geometry.PolylinePosition,
    ): Double

    /**
     * Creates PolylineIndex for polyline. See [PolylineIndex] for details.
     */
    fun createPolylineIndex(
        polyline: com.yandex.mapkit.kmp.geometry.Polyline,
    ): com.yandex.mapkit.kmp.geometry.geo.PolylineIndex
}

