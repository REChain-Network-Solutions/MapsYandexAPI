@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp

import com.yandex.runtime.kmp.internal.toDouble
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * A value respecting the device locale.
 */
actual typealias LocalizedValue = platform.YandexMapsMobile.YMKLocalizedValue

/**
 * Value in SI units for distance, speed and duration.
 */
actual val LocalizedValue.mpValue: Double
    get() = value()
/**
 * Localized text. For example: "15 ft" or "42 km".
 */
actual val LocalizedValue.mpText: String
    get() = text()

actual object LocalizedValueFactory {
    actual fun create(
        value: Double,
        text: String,
    ): LocalizedValue {
        return LocalizedValue.localizedValueWithValue(
            value,
            text,
        )
    }
}

