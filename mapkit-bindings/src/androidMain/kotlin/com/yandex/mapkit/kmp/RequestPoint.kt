@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

/**
 * The waypoint and a point the path must go through.
 */
actual typealias RequestPointType = com.yandex.mapkit.RequestPointType

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
actual typealias RequestPoint = com.yandex.mapkit.RequestPoint

/**
 * The request point.
 */
actual val RequestPoint.mpPoint: com.yandex.mapkit.kmp.geometry.Point
    get() = point
/**
 * The type of request point specified.
 */
actual val RequestPoint.mpType: com.yandex.mapkit.kmp.RequestPointType
    get() = type
/**
 * Opaque string that describe entrances, driving arrival points and so
 * on.
 *
 */
actual val RequestPoint.mpPointContext: String?
    get() = pointContext
/**
 * Specifies what driving arrival point to use. If point is not
 * specified then server will select one.
 *
 */
actual val RequestPoint.mpDrivingArrivalPointId: String?
    get() = drivingArrivalPointId
/**
 * Indoor level (floor) id
 *
 */
actual val RequestPoint.mpIndoorLevelId: String?
    get() = indoorLevelId

actual object RequestPointFactory {
    actual fun create(
        point: com.yandex.mapkit.kmp.geometry.Point,
        type: com.yandex.mapkit.kmp.RequestPointType,
        pointContext: String?,
        drivingArrivalPointId: String?,
        indoorLevelId: String?,
    ): RequestPoint {
        return RequestPoint(
            point,
            type,
            pointContext,
            drivingArrivalPointId,
            indoorLevelId,
        )
    }
}

