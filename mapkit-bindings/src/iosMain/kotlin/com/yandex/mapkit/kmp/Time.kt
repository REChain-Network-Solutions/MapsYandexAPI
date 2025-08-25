@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp

import com.yandex.runtime.kmp.internal.toInt
import com.yandex.runtime.kmp.internal.toLong
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * Time in I18nTime format.
 */
actual typealias Time = platform.YandexMapsMobile.YMKTime

/**
 * Time value.
 */
actual val Time.mpValue: Long
    get() = value()
/**
 * Time offset to account for time zones.
 */
actual val Time.mpTzOffset: Int
    get() = tzOffset().toInt()
/**
 * The description of the timer.
 */
actual val Time.mpText: String
    get() = text()

actual object TimeFactory {
    actual fun create(
        value: Long,
        tzOffset: Int,
        text: String,
    ): Time {
        return Time.timeWithValue(
            value,
            tzOffset.toLong(),
            text,
        )
    }
}

