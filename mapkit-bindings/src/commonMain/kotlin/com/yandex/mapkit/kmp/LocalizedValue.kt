@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

/**
 * A value respecting the device locale.
 */
expect class LocalizedValue

/**
 * Value in SI units for distance, speed and duration.
 */
expect val LocalizedValue.mpValue: Double
/**
 * Localized text. For example: "15 ft" or "42 km".
 */
expect val LocalizedValue.mpText: String

expect object LocalizedValueFactory {
    fun create(
        value: Double,
        text: String,
    ): LocalizedValue
}

