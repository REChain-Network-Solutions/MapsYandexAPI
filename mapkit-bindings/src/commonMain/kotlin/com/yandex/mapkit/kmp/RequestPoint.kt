@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

/**
 * The waypoint and a point the path must go through.
 */
expect enum class RequestPointType {
    /**
     * The target waypoint.
     */
    WAYPOINT,
    /**
     * A point the route must pass through.
     */
    VIAPOINT,
}

/**
 * There are two types of request points. A waypoint is a destination.
 * Use it when you plan to stop there. Via points (throughpoints)
 * correct the route to make it pass through all the via points.
 * Waypoints are guaranteed to be between sections in the resulting
 * route. Via points are embedded into sections.
 *
 * For each request point, you can provide a point context. It's an
 * opaque string that describe entrances, driving arrival points and so
 * on. If such context is provided then a client will get routes to
 * those additional points.
 */
expect class RequestPoint

/**
 * The request point.
 */
expect val RequestPoint.mpPoint: com.yandex.mapkit.kmp.geometry.Point
/**
 * The type of request point specified.
 */
expect val RequestPoint.mpType: com.yandex.mapkit.kmp.RequestPointType
/**
 * Opaque string that describe entrances, driving arrival points and so
 * on.
 *
 */
expect val RequestPoint.mpPointContext: String?
/**
 * Specifies what driving arrival point to use. If point is not
 * specified then server will select one.
 *
 */
expect val RequestPoint.mpDrivingArrivalPointId: String?
/**
 * Indoor level (floor) id
 *
 */
expect val RequestPoint.mpIndoorLevelId: String?

expect object RequestPointFactory {
    fun create(
        point: com.yandex.mapkit.kmp.geometry.Point,
        type: com.yandex.mapkit.kmp.RequestPointType,
        pointContext: String?,
        drivingArrivalPointId: String?,
        indoorLevelId: String?,
    ): RequestPoint
}

