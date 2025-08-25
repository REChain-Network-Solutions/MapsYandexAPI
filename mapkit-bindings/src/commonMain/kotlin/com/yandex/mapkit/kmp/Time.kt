@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

/**
 * Time in I18nTime format.
 */
expect class Time

/**
 * Time value.
 */
expect val Time.mpValue: Long
/**
 * Time offset to account for time zones.
 */
expect val Time.mpTzOffset: Int
/**
 * The description of the timer.
 */
expect val Time.mpText: String

expect object TimeFactory {
    fun create(
        value: Long,
        tzOffset: Int,
        text: String,
    ): Time
}

