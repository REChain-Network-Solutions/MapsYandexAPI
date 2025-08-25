@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.geometry.geo

import com.yandex.runtime.kmp.internal.toInt
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * Makes two conversions: world->XY and XY->world, where XY are tile
 * indexes. There are two main derived classes: spherical mercator
 * (google, osm) and wgs84 mercator (yandex).
 */
actual interface Projection {
    val impl: platform.YandexMapsMobile.YMKProjection

    /**
     * Converts the world coordinates to a flat world position.
     */
    actual fun worldToXY(
        geoPoint: com.yandex.mapkit.kmp.geometry.Point,
        zoom: Int,
    ): com.yandex.mapkit.kmp.geometry.geo.XYPoint

    /**
     * Converts the flat world position to world coordinates.
     */
    actual fun xyToWorld(
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
    actual fun isValid(): Boolean
}

open class YMKProjectionWrapper(override val impl: platform.YandexMapsMobile.YMKProjection) : Projection {
    override fun worldToXY(
        geoPoint: com.yandex.mapkit.kmp.geometry.Point,
        zoom: Int,
    ): com.yandex.mapkit.kmp.geometry.geo.XYPoint {
        return impl.worldToXYWithGeoPoint(
            geoPoint,
            zoom.toLong(),
        )
    }

    override fun xyToWorld(
        xyPoint: com.yandex.mapkit.kmp.geometry.geo.XYPoint,
        zoom: Int,
    ): com.yandex.mapkit.kmp.geometry.Point {
        return impl.xyToWorldWithXyPoint(
            xyPoint,
            zoom.toLong(),
        )
    }

    override fun isValid(): Boolean = impl.isValid()
}

