@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Single currency exchange prices.
 */
actual typealias CurrencyExchangeType = com.yandex.mapkit.search.CurrencyExchangeType

/**
 * ISO-4217 currency name, for example "USD" or "RUB" or "EUR".
 *
 */
actual val CurrencyExchangeType.mpName: String?
    get() = name
/**
 * Buy rate.
 *
 */
actual val CurrencyExchangeType.mpBuy: com.yandex.mapkit.kmp.Money?
    get() = buy
/**
 * Sell rate.
 *
 */
actual val CurrencyExchangeType.mpSell: com.yandex.mapkit.kmp.Money?
    get() = sell

actual object CurrencyExchangeTypeFactory {
    actual fun create(
        name: String?,
        buy: com.yandex.mapkit.kmp.Money?,
        sell: com.yandex.mapkit.kmp.Money?,
    ): CurrencyExchangeType {
        return CurrencyExchangeType(
            name,
            buy,
            sell,
        )
    }
}

/**
 * Currency exchange snippet.
 */
actual typealias CurrencyExchangeMetadata = com.yandex.mapkit.search.CurrencyExchangeMetadata

/**
 * Available currency exchange rates.
 */
actual val CurrencyExchangeMetadata.mpCurrencies: kotlin.collections.List<com.yandex.mapkit.search.kmp.CurrencyExchangeType>
    get() = currencies

actual object CurrencyExchangeMetadataFactory {
    actual fun create(
        currencies: kotlin.collections.List<com.yandex.mapkit.search.kmp.CurrencyExchangeType>,
    ): CurrencyExchangeMetadata {
        return CurrencyExchangeMetadata(
            currencies,
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.currencyExchangeMetadata: CurrencyExchangeMetadata?
    get() = getItem(CurrencyExchangeMetadata::class.java)

