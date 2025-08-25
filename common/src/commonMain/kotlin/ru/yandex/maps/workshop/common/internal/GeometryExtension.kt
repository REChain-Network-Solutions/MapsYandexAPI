package ru.yandex.maps.workshop.common.internal

import com.yandex.mapkit.kmp.geometry.BoundingBoxFactory
import com.yandex.mapkit.kmp.geometry.Geometry
import com.yandex.mapkit.kmp.geometry.GeometryFromBoundingBox
import com.yandex.mapkit.kmp.geometry.Point
import com.yandex.mapkit.kmp.geometry.PointFactory
import com.yandex.mapkit.kmp.geometry.mpLatitude
import com.yandex.mapkit.kmp.geometry.mpLongitude
import com.yandex.mapkit.utils.degreesToRadians
import kotlin.math.cos

internal fun createBoundingBox(center: Point, radius: Double): Geometry {
    // Переводим радиус из метров в градусы (упрощенно)
    val delta = radius / 111320.0 // ~1 градус = 111.32 км


    val minLat = center.mpLatitude - delta
    val maxLat = center.mpLatitude + delta
    val minLon = center.mpLongitude - delta / cos(center.mpLatitude.degreesToRadians())
    val maxLon = center.mpLongitude + delta / cos(center.mpLatitude.degreesToRadians())

    return GeometryFromBoundingBox(
        BoundingBoxFactory.create(
            PointFactory.create(minLat, minLon),
            PointFactory.create(maxLat, maxLon)
        )
    )
}
