@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST", "REDUNDANT_ELSE_IN_WHEN")

package com.yandex.mapkit.kmp

/**
 * The waypoint and a point the path must go through.
 */
actual enum class RequestPointType {
    /**
     * The target waypoint.
     */
    WAYPOINT,
    /**
     * A point the route must pass through.
     */
    VIAPOINT,;

    companion object {
        fun toKmp(v: platform.Foundation.NSNumber): RequestPointType {
            return toKmp(platform.YandexMapsMobile.YMKRequestPointType.byValue(v.unsignedLongValue))
        }

        fun toKmp(v: platform.YandexMapsMobile.YMKRequestPointType): RequestPointType {
            return when (v) {
                platform.YandexMapsMobile.YMKRequestPointType.YMKRequestPointTypeWaypoint -> RequestPointType.WAYPOINT
                platform.YandexMapsMobile.YMKRequestPointType.YMKRequestPointTypeViapoint -> RequestPointType.VIAPOINT
                else -> error("unknown YMKRequestPointType")
            }
        }
    }
}

fun RequestPointType.fromKmp(): platform.YandexMapsMobile.YMKRequestPointType {
    return when (this) {
        RequestPointType.WAYPOINT -> platform.YandexMapsMobile.YMKRequestPointType.YMKRequestPointTypeWaypoint
        RequestPointType.VIAPOINT -> platform.YandexMapsMobile.YMKRequestPointType.YMKRequestPointTypeViapoint
    }
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
actual typealias RequestPoint = platform.YandexMapsMobile.YMKRequestPoint

/**
 * The request point.
 */
actual val RequestPoint.mpPoint: com.yandex.mapkit.kmp.geometry.Point
    get() = point()
/**
 * The type of request point specified.
 */
actual val RequestPoint.mpType: com.yandex.mapkit.kmp.RequestPointType
    get() = type().let { com.yandex.mapkit.kmp.RequestPointType.toKmp(it) }
/**
 * Opaque string that describe entrances, driving arrival points and so
 * on.
 *
 */
actual val RequestPoint.mpPointContext: String?
    get() = pointContext()
/**
 * Specifies what driving arrival point to use. If point is not
 * specified then server will select one.
 *
 */
actual val RequestPoint.mpDrivingArrivalPointId: String?
    get() = drivingArrivalPointId()
/**
 * Indoor level (floor) id
 *
 */
actual val RequestPoint.mpIndoorLevelId: String?
    get() = indoorLevelId()

actual object RequestPointFactory {
    actual fun create(
        point: com.yandex.mapkit.kmp.geometry.Point,
        type: com.yandex.mapkit.kmp.RequestPointType,
        pointContext: String?,
        drivingArrivalPointId: String?,
        indoorLevelId: String?,
    ): RequestPoint {
        return RequestPoint.requestPointWithPoint(
            point,
            type.fromKmp(),
            pointContext,
            drivingArrivalPointId,
            indoorLevelId,
        )
    }
}

