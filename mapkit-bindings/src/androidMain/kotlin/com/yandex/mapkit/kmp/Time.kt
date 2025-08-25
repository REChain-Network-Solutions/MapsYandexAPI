@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

/**
 * Time in I18nTime format.
 */
actual typealias Time = com.yandex.mapkit.Time

/**
 * Time value.
 */
actual val Time.mpValue: Long
    get() = value
/**
 * Time offset to account for time zones.
 */
actual val Time.mpTzOffset: Int
    get() = tzOffset
/**
 * The description of the timer.
 */
actual val Time.mpText: String
    get() = text

actual object TimeFactory {
    actual fun create(
        value: Long,
        tzOffset: Int,
        text: String,
    ): Time {
        return Time(
            value,
            tzOffset,
            text,
        )
    }
}

