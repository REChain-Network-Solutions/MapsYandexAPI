@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

/**
 * A value respecting the device locale.
 */
actual typealias LocalizedValue = com.yandex.mapkit.LocalizedValue

/**
 * Value in SI units for distance, speed and duration.
 */
actual val LocalizedValue.mpValue: Double
    get() = value
/**
 * Localized text. For example: "15 ft" or "42 km".
 */
actual val LocalizedValue.mpText: String
    get() = text

actual object LocalizedValueFactory {
    actual fun create(
        value: Double,
        text: String,
    ): LocalizedValue {
        return LocalizedValue(
            value,
            text,
        )
    }
}

