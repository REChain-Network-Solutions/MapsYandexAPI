@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

/**
 * Single currency exchange prices.
 */
actual typealias CurrencyExchangeType = platform.YandexMapsMobile.YMKSearchCurrencyExchangeType

/**
 * ISO-4217 currency name, for example "USD" or "RUB" or "EUR".
 *
 */
actual val CurrencyExchangeType.mpName: String?
    get() = name()
/**
 * Buy rate.
 *
 */
actual val CurrencyExchangeType.mpBuy: com.yandex.mapkit.kmp.Money?
    get() = buy()
/**
 * Sell rate.
 *
 */
actual val CurrencyExchangeType.mpSell: com.yandex.mapkit.kmp.Money?
    get() = sell()

actual object CurrencyExchangeTypeFactory {
    actual fun create(
        name: String?,
        buy: com.yandex.mapkit.kmp.Money?,
        sell: com.yandex.mapkit.kmp.Money?,
    ): CurrencyExchangeType {
        return CurrencyExchangeType.currencyExchangeTypeWithName(
            name,
            buy,
            sell,
        )
    }
}

/**
 * Currency exchange snippet.
 */
actual typealias CurrencyExchangeMetadata = platform.YandexMapsMobile.YMKSearchCurrencyExchangeMetadata

/**
 * Available currency exchange rates.
 */
actual val CurrencyExchangeMetadata.mpCurrencies: kotlin.collections.List<com.yandex.mapkit.search.kmp.CurrencyExchangeType>
    get() = currencies().let { it as kotlin.collections.List<platform.YandexMapsMobile.YMKSearchCurrencyExchangeType> }

actual object CurrencyExchangeMetadataFactory {
    actual fun create(
        currencies: kotlin.collections.List<com.yandex.mapkit.search.kmp.CurrencyExchangeType>,
    ): CurrencyExchangeMetadata {
        return CurrencyExchangeMetadata.currencyExchangeMetadataWithCurrencies(
            currencies.let { it as kotlin.collections.List<*> },
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.currencyExchangeMetadata: CurrencyExchangeMetadata?
    get() = impl.getItemOfClass(CurrencyExchangeMetadata) as? CurrencyExchangeMetadata

