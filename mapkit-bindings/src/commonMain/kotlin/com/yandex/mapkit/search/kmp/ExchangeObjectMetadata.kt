@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Single currency exchange prices.
 */
expect class CurrencyExchangeType

/**
 * ISO-4217 currency name, for example "USD" or "RUB" or "EUR".
 *
 */
expect val CurrencyExchangeType.mpName: String?
/**
 * Buy rate.
 *
 */
expect val CurrencyExchangeType.mpBuy: com.yandex.mapkit.kmp.Money?
/**
 * Sell rate.
 *
 */
expect val CurrencyExchangeType.mpSell: com.yandex.mapkit.kmp.Money?

expect object CurrencyExchangeTypeFactory {
    fun create(
        name: String?,
        buy: com.yandex.mapkit.kmp.Money?,
        sell: com.yandex.mapkit.kmp.Money?,
    ): CurrencyExchangeType
}

/**
 * Currency exchange snippet.
 */
expect class CurrencyExchangeMetadata

/**
 * Available currency exchange rates.
 */
expect val CurrencyExchangeMetadata.mpCurrencies: kotlin.collections.List<com.yandex.mapkit.search.kmp.CurrencyExchangeType>

expect object CurrencyExchangeMetadataFactory {
    fun create(
        currencies: kotlin.collections.List<com.yandex.mapkit.search.kmp.CurrencyExchangeType>,
    ): CurrencyExchangeMetadata
}

expect val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.currencyExchangeMetadata: CurrencyExchangeMetadata?

