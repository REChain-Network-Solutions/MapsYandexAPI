@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.geometry.geo

/**
 * Makes two conversions: world->XY and XY->world, where XY are tile
 * indexes. There are two main derived classes: spherical mercator
 * (google, osm) and wgs84 mercator (yandex).
 */
expect interface Projection {
    /**
     * Converts the world coordinates to a flat world position.
     */
    fun worldToXY(
        geoPoint: com.yandex.mapkit.kmp.geometry.Point,
        zoom: Int,
    ): com.yandex.mapkit.kmp.geometry.geo.XYPoint

    /**
     * Converts the flat world position to world coordinates.
     */
    fun xyToWorld(
        xyPoint: com.yandex.mapkit.kmp.geometry.geo.XYPoint,
        zoom: Int,
    ): com.yandex.mapkit.kmp.geometry.Point

    /**
     * Tells if this object is valid or not. Any method called on an invalid
     * object will throw an exception. The object becomes invalid only on UI
     * thread, and only when its implementation depends on objects already
     * destroyed by now. Please refer to general docs about the interface for
     * details on its invalidation.
     */
    fun isValid(): Boolean
}

