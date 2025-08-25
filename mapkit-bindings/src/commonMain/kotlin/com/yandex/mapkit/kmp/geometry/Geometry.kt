@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.geometry

/**
 * A rectangular box around the object.
 */
expect class BoundingBox

/**
 * The coordinates of the southwest corner of the box.
 */
expect val BoundingBox.mpSouthWest: com.yandex.mapkit.kmp.geometry.Point
/**
 * The coordinates of the northeast corner of the box.
 */
expect val BoundingBox.mpNorthEast: com.yandex.mapkit.kmp.geometry.Point

expect object BoundingBoxFactory {
    fun create(
        southWest: com.yandex.mapkit.kmp.geometry.Point,
        northEast: com.yandex.mapkit.kmp.geometry.Point,
    ): BoundingBox
}

/**
 * A circle around the specified point.
 */
expect class Circle

/**
 * The coordinates of the center of the circle.
 */
expect val Circle.mpCenter: com.yandex.mapkit.kmp.geometry.Point
/**
 * The radius of the circle in meters.
 */
expect val Circle.mpRadius: Float

expect object CircleFactory {
    fun create(
        center: com.yandex.mapkit.kmp.geometry.Point,
        radius: Float,
    ): Circle
}

/**
 * A line between two points.
 */
expect class Segment

/**
 * Starting point of the segment.
 */
expect val Segment.mpStartPoint: com.yandex.mapkit.kmp.geometry.Point
/**
 * End point of the segment.
 */
expect val Segment.mpEndPoint: com.yandex.mapkit.kmp.geometry.Point

expect object SegmentFactory {
    fun create(
        startPoint: com.yandex.mapkit.kmp.geometry.Point,
        endPoint: com.yandex.mapkit.kmp.geometry.Point,
    ): Segment
}

/**
 * A polyline between a number of points. A polyline is drawn between
 * consecutive points.
 */
expect class Polyline

/**
 * The list of points to connect.
 */
expect val Polyline.mpPoints: kotlin.collections.List<com.yandex.mapkit.kmp.geometry.Point>

expect object PolylineFactory {
    fun create(
        points: kotlin.collections.List<com.yandex.mapkit.kmp.geometry.Point>,
    ): Polyline
}

/**
 * The position on a polyline.
 */
expect class PolylinePosition

/**
 * Zero-based index of the polyline segment.
 */
expect val PolylinePosition.mpSegmentIndex: Int
/**
 * Position in the specified segment. Possible values: from 0 to 1,
 * where 0 is the start of the segment and 1 is the end of it.
 */
expect val PolylinePosition.mpSegmentPosition: Double

expect object PolylinePositionFactory {
    fun create(
        segmentIndex: Int,
        segmentPosition: Double,
    ): PolylinePosition
}

/**
 * A part of a polyline.
 */
expect class Subpolyline

/**
 * The start of the selected part of the polyline.
 */
expect val Subpolyline.mpBegin: com.yandex.mapkit.kmp.geometry.PolylinePosition
/**
 * The end of the selected part of the polyline.
 */
expect val Subpolyline.mpEnd: com.yandex.mapkit.kmp.geometry.PolylinePosition

expect object SubpolylineFactory {
    fun create(
        begin: com.yandex.mapkit.kmp.geometry.PolylinePosition,
        end: com.yandex.mapkit.kmp.geometry.PolylinePosition,
    ): Subpolyline
}

/**
 * A sequence of four or more vertices, with all points along the
 * linearly-interpolated curves (line segments) between each pair of
 * consecutive vertices. A ring must have either 0, 4 or more points.
 * The first and last points of the ring must be in the same position.
 * The ring must not intersect with itself.
 */
expect class LinearRing

/**
 * The list of points to connect.
 */
expect val LinearRing.mpPoints: kotlin.collections.List<com.yandex.mapkit.kmp.geometry.Point>

expect object LinearRingFactory {
    fun create(
        points: kotlin.collections.List<com.yandex.mapkit.kmp.geometry.Point>,
    ): LinearRing
}

/**
 * A polygon with one or more polygons in it. The exterior and interior
 * areas are specified using LinearRing.
 */
expect class Polygon

/**
 * The ring specifying the area.
 */
expect val Polygon.mpOuterRing: com.yandex.mapkit.kmp.geometry.LinearRing
/**
 * The list of rings in the specified area.
 */
expect val Polygon.mpInnerRings: kotlin.collections.List<com.yandex.mapkit.kmp.geometry.LinearRing>

expect object PolygonFactory {
    fun create(
        outerRing: com.yandex.mapkit.kmp.geometry.LinearRing,
        innerRings: kotlin.collections.List<com.yandex.mapkit.kmp.geometry.LinearRing>,
    ): Polygon
}

/**
 * An area consisting of multiple external polygons.
 */
expect class MultiPolygon

expect val MultiPolygon.mpPolygons: kotlin.collections.List<com.yandex.mapkit.kmp.geometry.Polygon>

expect object MultiPolygonFactory {
    fun create(
        polygons: kotlin.collections.List<com.yandex.mapkit.kmp.geometry.Polygon>,
    ): MultiPolygon
}

/**
 * A container of other geometry objects. Point - A point on the map.
 * Polyline - A polyline between a number of points. Polygon - A polygon
 * with one or more polygons in it. BoundingBox - A rectangular box
 * around the object. Circle - A circle around the specified point.
 */
expect class Geometry

expect val Geometry.point: com.yandex.mapkit.kmp.geometry.Point?
expect val Geometry.polyline: com.yandex.mapkit.kmp.geometry.Polyline?
expect val Geometry.polygon: com.yandex.mapkit.kmp.geometry.Polygon?
expect val Geometry.multiPolygon: com.yandex.mapkit.kmp.geometry.MultiPolygon?
expect val Geometry.boundingBox: com.yandex.mapkit.kmp.geometry.BoundingBox?
expect val Geometry.circle: com.yandex.mapkit.kmp.geometry.Circle?

expect fun GeometryFromPoint(point: com.yandex.mapkit.kmp.geometry.Point): Geometry
expect fun GeometryFromPolyline(polyline: com.yandex.mapkit.kmp.geometry.Polyline): Geometry
expect fun GeometryFromPolygon(polygon: com.yandex.mapkit.kmp.geometry.Polygon): Geometry
expect fun GeometryFromMultiPolygon(multiPolygon: com.yandex.mapkit.kmp.geometry.MultiPolygon): Geometry
expect fun GeometryFromBoundingBox(boundingBox: com.yandex.mapkit.kmp.geometry.BoundingBox): Geometry
expect fun GeometryFromCircle(circle: com.yandex.mapkit.kmp.geometry.Circle): Geometry

