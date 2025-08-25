@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

import com.yandex.runtime.kmp.internal.toBoolean
import com.yandex.runtime.kmp.internal.toInt
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * The time interval used to compose availability info.
 *
 * Can describe two kinds of intervals: 1. 24-hour interval
 * (`isTwentyFourHours` is true, `from` and `to` are not used). 2.
 * Smaller time interval (`isTwentyFourHours` is false, `from` and `to`
 * are set to the begin and end of the interval)
 */
actual typealias TimeRange = platform.YandexMapsMobile.YMKSearchTimeRange

/**
 * All day (24 hours) time range marker.
 *
 */
actual val TimeRange.mpIsTwentyFourHours: Boolean?
    get() = isTwentyFourHours()?.toBoolean()
/**
 * Interval start (seconds from midnight).
 *
 */
actual val TimeRange.mpFrom: Int?
    get() = from()?.toInt()
/**
 * Interval end (seconds from midnight).
 *
 */
actual val TimeRange.mpTo: Int?
    get() = to()?.toInt()

actual object TimeRangeFactory {
    actual fun create(
        isTwentyFourHours: Boolean?,
        from: Int?,
        to: Int?,
    ): TimeRange {
        return TimeRange.timeRangeWithIsTwentyFourHours(
            isTwentyFourHours?.toNSNumber(),
            from?.toNSNumber(),
            to?.toNSNumber(),
        )
    }
}

/**
 * A group of week days.
 */
actual enum class DayGroup(val value: Int) {
    /**
     * Nothing.
     */
    NONE(0),
    /**
     * Sunday.
     */
    SUNDAY(1),
    /**
     * Monday.
     */
    MONDAY(1 shl 1),
    /**
     * Tuesday.
     */
    TUESDAY(1 shl 2),
    /**
     * Wednesday.
     */
    WEDNESDAY(1 shl 3),
    /**
     * Thursday.
     */
    THURSDAY(1 shl 4),
    /**
     * Friday.
     */
    FRIDAY(1 shl 5),
    /**
     * Saturday.
     */
    SATURDAY(1 shl 6),
    /**
     * Weekdays from Monday to Friday.
     */
    WEEKDAYS(MONDAY.value or TUESDAY.value or WEDNESDAY.value or THURSDAY.value or FRIDAY.value),
    /**
     * Weekend days (Saturday and Sunday)
     */
    WEEKEND(SATURDAY.value or SUNDAY.value),
    /**
     * Every day of the week
     */
    EVERYDAY(WEEKDAYS.value or WEEKEND.value),
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
actual typealias Availability = platform.YandexMapsMobile.YMKSearchAvailability

/**
 * Days where time ranges are applicable
 */
actual val Availability.mpDays: Int
    get() = days().toInt()
/**
 * Ranges for open hours
 */
actual val Availability.mpTimeRanges: kotlin.collections.List<com.yandex.mapkit.search.kmp.TimeRange>
    get() = timeRanges().let { it as kotlin.collections.List<platform.YandexMapsMobile.YMKSearchTimeRange> }

actual object AvailabilityFactory {
    actual fun create(
        days: Int,
        timeRanges: kotlin.collections.List<com.yandex.mapkit.search.kmp.TimeRange>,
    ): Availability {
        return Availability.availabilityWithDays(
            days.toULong(),
            timeRanges.let { it as kotlin.collections.List<*> },
        )
    }
}

