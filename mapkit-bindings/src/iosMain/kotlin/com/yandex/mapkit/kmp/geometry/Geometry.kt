@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.geometry

import com.yandex.runtime.kmp.internal.toDouble
import com.yandex.runtime.kmp.internal.toFloat
import com.yandex.runtime.kmp.internal.toInt
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * A rectangular box around the object.
 */
actual typealias BoundingBox = platform.YandexMapsMobile.YMKBoundingBox

/**
 * The coordinates of the southwest corner of the box.
 */
actual val BoundingBox.mpSouthWest: com.yandex.mapkit.kmp.geometry.Point
    get() = southWest()
/**
 * The coordinates of the northeast corner of the box.
 */
actual val BoundingBox.mpNorthEast: com.yandex.mapkit.kmp.geometry.Point
    get() = northEast()

actual object BoundingBoxFactory {
    actual fun create(
        southWest: com.yandex.mapkit.kmp.geometry.Point,
        northEast: com.yandex.mapkit.kmp.geometry.Point,
    ): BoundingBox {
        return BoundingBox.boundingBoxWithSouthWest(
            southWest,
            northEast,
        )
    }
}

/**
 * A circle around the specified point.
 */
actual typealias Circle = platform.YandexMapsMobile.YMKCircle

/**
 * The coordinates of the center of the circle.
 */
actual val Circle.mpCenter: com.yandex.mapkit.kmp.geometry.Point
    get() = center()
/**
 * The radius of the circle in meters.
 */
actual val Circle.mpRadius: Float
    get() = radius()

actual object CircleFactory {
    actual fun create(
        center: com.yandex.mapkit.kmp.geometry.Point,
        radius: Float,
    ): Circle {
        return Circle.circleWithCenter(
            center,
            radius,
        )
    }
}

/**
 * A line between two points.
 */
actual typealias Segment = platform.YandexMapsMobile.YMKSegment

/**
 * Starting point of the segment.
 */
actual val Segment.mpStartPoint: com.yandex.mapkit.kmp.geometry.Point
    get() = startPoint()
/**
 * End point of the segment.
 */
actual val Segment.mpEndPoint: com.yandex.mapkit.kmp.geometry.Point
    get() = endPoint()

actual object SegmentFactory {
    actual fun create(
        startPoint: com.yandex.mapkit.kmp.geometry.Point,
        endPoint: com.yandex.mapkit.kmp.geometry.Point,
    ): Segment {
        return Segment.segmentWithStartPoint(
            startPoint,
            endPoint,
        )
    }
}

/**
 * A polyline between a number of points. A polyline is drawn between
 * consecutive points.
 */
actual typealias Polyline = platform.YandexMapsMobile.YMKPolyline

/**
 * The list of points to connect.
 */
actual val Polyline.mpPoints: kotlin.collections.List<com.yandex.mapkit.kmp.geometry.Point>
    get() = points().let { it as kotlin.collections.List<platform.YandexMapsMobile.YMKPoint> }

actual object PolylineFactory {
    actual fun create(
        points: kotlin.collections.List<com.yandex.mapkit.kmp.geometry.Point>,
    ): Polyline {
        return Polyline.polylineWithPoints(
            points.let { it as kotlin.collections.List<*> },
        )
    }
}

/**
 * The position on a polyline.
 */
actual typealias PolylinePosition = platform.YandexMapsMobile.YMKPolylinePosition

/**
 * Zero-based index of the polyline segment.
 */
actual val PolylinePosition.mpSegmentIndex: Int
    get() = segmentIndex().toInt()
/**
 * Position in the specified segment. Possible values: from 0 to 1,
 * where 0 is the start of the segment and 1 is the end of it.
 */
actual val PolylinePosition.mpSegmentPosition: Double
    get() = segmentPosition()

actual object PolylinePositionFactory {
    actual fun create(
        segmentIndex: Int,
        segmentPosition: Double,
    ): PolylinePosition {
        return PolylinePosition.polylinePositionWithSegmentIndex(
            segmentIndex.toULong(),
            segmentPosition,
        )
    }
}

/**
 * A part of a polyline.
 */
actual typealias Subpolyline = platform.YandexMapsMobile.YMKSubpolyline

/**
 * The start of the selected part of the polyline.
 */
actual val Subpolyline.mpBegin: com.yandex.mapkit.kmp.geometry.PolylinePosition
    get() = begin()
/**
 * The end of the selected part of the polyline.
 */
actual val Subpolyline.mpEnd: com.yandex.mapkit.kmp.geometry.PolylinePosition
    get() = end()

actual object SubpolylineFactory {
    actual fun create(
        begin: com.yandex.mapkit.kmp.geometry.PolylinePosition,
        end: com.yandex.mapkit.kmp.geometry.PolylinePosition,
    ): Subpolyline {
        return Subpolyline.subpolylineWithBegin(
            begin,
            end,
        )
    }
}

/**
 * A sequence of four or more vertices, with all points along the
 * linearly-interpolated curves (line segments) between each pair of
 * consecutive vertices. A ring must have either 0, 4 or more points.
 * The first and last points of the ring must be in the same position.
 * The ring must not intersect with itself.
 */
actual typealias LinearRing = platform.YandexMapsMobile.YMKLinearRing

/**
 * The list of points to connect.
 */
actual val LinearRing.mpPoints: kotlin.collections.List<com.yandex.mapkit.kmp.geometry.Point>
    get() = points().let { it as kotlin.collections.List<platform.YandexMapsMobile.YMKPoint> }

actual object LinearRingFactory {
    actual fun create(
        points: kotlin.collections.List<com.yandex.mapkit.kmp.geometry.Point>,
    ): LinearRing {
        return LinearRing.linearRingWithPoints(
            points.let { it as kotlin.collections.List<*> },
        )
    }
}

/**
 * A polygon with one or more polygons in it. The exterior and interior
 * areas are specified using LinearRing.
 */
actual typealias Polygon = platform.YandexMapsMobile.YMKPolygon

/**
 * The ring specifying the area.
 */
actual val Polygon.mpOuterRing: com.yandex.mapkit.kmp.geometry.LinearRing
    get() = outerRing()
/**
 * The list of rings in the specified area.
 */
actual val Polygon.mpInnerRings: kotlin.collections.List<com.yandex.mapkit.kmp.geometry.LinearRing>
    get() = innerRings().let { it as kotlin.collections.List<platform.YandexMapsMobile.YMKLinearRing> }

actual object PolygonFactory {
    actual fun create(
        outerRing: com.yandex.mapkit.kmp.geometry.LinearRing,
        innerRings: kotlin.collections.List<com.yandex.mapkit.kmp.geometry.LinearRing>,
    ): Polygon {
        return Polygon.polygonWithOuterRing(
            outerRing,
            innerRings.let { it as kotlin.collections.List<*> },
        )
    }
}

/**
 * An area consisting of multiple external polygons.
 */
actual typealias MultiPolygon = platform.YandexMapsMobile.YMKMultiPolygon

actual val MultiPolygon.mpPolygons: kotlin.collections.List<com.yandex.mapkit.kmp.geometry.Polygon>
    get() = polygons().let { it as kotlin.collections.List<platform.YandexMapsMobile.YMKPolygon> }

actual object MultiPolygonFactory {
    actual fun create(
        polygons: kotlin.collections.List<com.yandex.mapkit.kmp.geometry.Polygon>,
    ): MultiPolygon {
        return MultiPolygon.multiPolygonWithPolygons(
            polygons.let { it as kotlin.collections.List<*> },
        )
    }
}

/**
 * A container of other geometry objects. Point - A point on the map.
 * Polyline - A polyline between a number of points. Polygon - A polygon
 * with one or more polygons in it. BoundingBox - A rectangular box
 * around the object. Circle - A circle around the specified point.
 */
actual typealias Geometry = platform.YandexMapsMobile.YMKGeometry

actual val Geometry.point: com.yandex.mapkit.kmp.geometry.Point?
    get() = point
actual val Geometry.polyline: com.yandex.mapkit.kmp.geometry.Polyline?
    get() = polyline
actual val Geometry.polygon: com.yandex.mapkit.kmp.geometry.Polygon?
    get() = polygon
actual val Geometry.multiPolygon: com.yandex.mapkit.kmp.geometry.MultiPolygon?
    get() = multiPolygon
actual val Geometry.boundingBox: com.yandex.mapkit.kmp.geometry.BoundingBox?
    get() = boundingBox
actual val Geometry.circle: com.yandex.mapkit.kmp.geometry.Circle?
    get() = circle

actual fun GeometryFromPoint(point: com.yandex.mapkit.kmp.geometry.Point): Geometry = platform.YandexMapsMobile.YMKGeometry.geometryWithPoint(point)
actual fun GeometryFromPolyline(polyline: com.yandex.mapkit.kmp.geometry.Polyline): Geometry = platform.YandexMapsMobile.YMKGeometry.geometryWithPolyline(polyline)
actual fun GeometryFromPolygon(polygon: com.yandex.mapkit.kmp.geometry.Polygon): Geometry = platform.YandexMapsMobile.YMKGeometry.geometryWithPolygon(polygon)
actual fun GeometryFromMultiPolygon(multiPolygon: com.yandex.mapkit.kmp.geometry.MultiPolygon): Geometry = platform.YandexMapsMobile.YMKGeometry.geometryWithMultiPolygon(multiPolygon)
actual fun GeometryFromBoundingBox(boundingBox: com.yandex.mapkit.kmp.geometry.BoundingBox): Geometry = platform.YandexMapsMobile.YMKGeometry.geometryWithBoundingBox(boundingBox)
actual fun GeometryFromCircle(circle: com.yandex.mapkit.kmp.geometry.Circle): Geometry = platform.YandexMapsMobile.YMKGeometry.geometryWithCircle(circle)

