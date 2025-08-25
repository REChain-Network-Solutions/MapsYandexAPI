@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * The time interval used to compose availability info.
 *
 * Can describe two kinds of intervals: 1. 24-hour interval
 * (`isTwentyFourHours` is true, `from` and `to` are not used). 2.
 * Smaller time interval (`isTwentyFourHours` is false, `from` and `to`
 * are set to the begin and end of the interval)
 */
actual typealias TimeRange = com.yandex.mapkit.search.TimeRange

/**
 * All day (24 hours) time range marker.
 *
 */
actual val TimeRange.mpIsTwentyFourHours: Boolean?
    get() = isTwentyFourHours
/**
 * Interval start (seconds from midnight).
 *
 */
actual val TimeRange.mpFrom: Int?
    get() = from
/**
 * Interval end (seconds from midnight).
 *
 */
actual val TimeRange.mpTo: Int?
    get() = to

actual object TimeRangeFactory {
    actual fun create(
        isTwentyFourHours: Boolean?,
        from: Int?,
        to: Int?,
    ): TimeRange {
        return TimeRange(
            isTwentyFourHours,
            from,
            to,
        )
    }
}

/**
 * A group of week days.
 */
actual typealias DayGroup = com.yandex.mapkit.search.DayGroup

/**
 * A single value of availability information.
 *
 * Allows to describe business schedule for a group of days. For
 * example, an organization open on weekdays from 9 AM to 6 PM with a
 * lunch break from 1 PM to 2 PM can be described as a single
 * `Availability` with `days` equal to `DayGroup::Weekdays` and two time
 * ranges (9:00-13:00, 14:00-18:00). More complicated schedules will
 * require multiple `Availabilities`.
 */
actual typealias Availability = com.yandex.mapkit.search.Availability

/**
 * Days where time ranges are applicable
 */
actual val Availability.mpDays: Int
    get() = days
/**
 * Ranges for open hours
 */
actual val Availability.mpTimeRanges: kotlin.collections.List<com.yandex.mapkit.search.kmp.TimeRange>
    get() = timeRanges

actual object AvailabilityFactory {
    actual fun create(
        days: Int,
        timeRanges: kotlin.collections.List<com.yandex.mapkit.search.kmp.TimeRange>,
    ): Availability {
        return Availability(
            days,
            timeRanges,
        )
    }
}

