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
expect class TimeRange

/**
 * All day (24 hours) time range marker.
 *
 */
expect val TimeRange.mpIsTwentyFourHours: Boolean?
/**
 * Interval start (seconds from midnight).
 *
 */
expect val TimeRange.mpFrom: Int?
/**
 * Interval end (seconds from midnight).
 *
 */
expect val TimeRange.mpTo: Int?

expect object TimeRangeFactory {
    fun create(
        isTwentyFourHours: Boolean?,
        from: Int?,
        to: Int?,
    ): TimeRange
}

/**
 * A group of week days.
 */
expect enum class DayGroup {
    /**
     * Nothing.
     */
    NONE,
    /**
     * Sunday.
     */
    SUNDAY,
    /**
     * Monday.
     */
    MONDAY,
    /**
     * Tuesday.
     */
    TUESDAY,
    /**
     * Wednesday.
     */
    WEDNESDAY,
    /**
     * Thursday.
     */
    THURSDAY,
    /**
     * Friday.
     */
    FRIDAY,
    /**
     * Saturday.
     */
    SATURDAY,
    /**
     * Weekdays from Monday to Friday.
     */
    WEEKDAYS,
    /**
     * Weekend days (Saturday and Sunday)
     */
    WEEKEND,
    /**
     * Every day of the week
     */
    EVERYDAY,
}

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
expect class Availability

/**
 * Days where time ranges are applicable
 */
expect val Availability.mpDays: Int
/**
 * Ranges for open hours
 */
expect val Availability.mpTimeRanges: kotlin.collections.List<com.yandex.mapkit.search.kmp.TimeRange>

expect object AvailabilityFactory {
    fun create(
        days: Int,
        timeRanges: kotlin.collections.List<com.yandex.mapkit.search.kmp.TimeRange>,
    ): Availability
}

