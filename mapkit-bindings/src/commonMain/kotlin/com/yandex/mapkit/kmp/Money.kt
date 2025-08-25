@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

expect class Money

expect val Money.mpValue: Double
expect val Money.mpText: String
expect val Money.mpCurrency: String

expect object MoneyFactory {
    fun create(
        value: Double,
        text: String,
        currency: String,
    ): Money
}

