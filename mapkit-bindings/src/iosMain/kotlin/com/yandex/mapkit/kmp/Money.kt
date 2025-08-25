@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp

import com.yandex.runtime.kmp.internal.toDouble
import com.yandex.runtime.kmp.internal.toNSNumber

actual typealias Money = platform.YandexMapsMobile.YMKTaxiMoney

actual val Money.mpValue: Double
    get() = value()
actual val Money.mpText: String
    get() = text()
actual val Money.mpCurrency: String
    get() = currency()

actual object MoneyFactory {
    actual fun create(
        value: Double,
        text: String,
        currency: String,
    ): Money {
        return Money.moneyWithValue(
            value,
            text,
            currency,
        )
    }
}

