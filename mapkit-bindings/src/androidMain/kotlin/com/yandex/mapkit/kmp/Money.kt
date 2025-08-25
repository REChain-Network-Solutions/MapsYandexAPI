@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

actual typealias Money = com.yandex.mapkit.Money

actual val Money.mpValue: Double
    get() = value
actual val Money.mpText: String
    get() = text
actual val Money.mpCurrency: String
    get() = currency

actual object MoneyFactory {
    actual fun create(
        value: Double,
        text: String,
        currency: String,
    ): Money {
        return Money(
            value,
            text,
            currency,
        )
    }
}

