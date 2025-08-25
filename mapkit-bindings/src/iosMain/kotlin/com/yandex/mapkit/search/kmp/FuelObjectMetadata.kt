@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

import com.yandex.runtime.kmp.internal.toLong
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * Fuel name and price.
 */
actual typealias FuelType = platform.YandexMapsMobile.YMKSearchFuelType

/**
 * Fuel name.
 *
 */
actual val FuelType.mpName: String?
    get() = name()
/**
 * Fuel price.
 *
 */
actual val FuelType.mpPrice: com.yandex.mapkit.kmp.Money?
    get() = price()

actual object FuelTypeFactory {
    actual fun create(
        name: String?,
        price: com.yandex.mapkit.kmp.Money?,
    ): FuelType {
        return FuelType.fuelTypeWithName(
            name,
            price,
        )
    }
}

/**
 * Fuel snippet.
 */
actual typealias FuelMetadata = platform.YandexMapsMobile.YMKSearchFuelMetadata

/**
 * Snippet update time as UNIX timestamp.
 *
 */
actual val FuelMetadata.mpTimestamp: Long?
    get() = timestamp()?.toLong()
/**
 * Fuel list.
 */
actual val FuelMetadata.mpFuels: kotlin.collections.List<com.yandex.mapkit.search.kmp.FuelType>
    get() = fuels().let { it as kotlin.collections.List<platform.YandexMapsMobile.YMKSearchFuelType> }
/**
 * Attribution information.
 *
 */
actual val FuelMetadata.mpAttribution: com.yandex.mapkit.kmp.Attribution?
    get() = attribution()

actual object FuelMetadataFactory {
    actual fun create(
        timestamp: Long?,
        fuels: kotlin.collections.List<com.yandex.mapkit.search.kmp.FuelType>,
        attribution: com.yandex.mapkit.kmp.Attribution?,
    ): FuelMetadata {
        return FuelMetadata.fuelMetadataWithTimestamp(
            timestamp?.toNSNumber(),
            fuels.let { it as kotlin.collections.List<*> },
            attribution,
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.fuelMetadata: FuelMetadata?
    get() = impl.getItemOfClass(FuelMetadata) as? FuelMetadata

