@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Fuel name and price.
 */
actual typealias FuelType = com.yandex.mapkit.search.FuelType

/**
 * Fuel name.
 *
 */
actual val FuelType.mpName: String?
    get() = name
/**
 * Fuel price.
 *
 */
actual val FuelType.mpPrice: com.yandex.mapkit.kmp.Money?
    get() = price

actual object FuelTypeFactory {
    actual fun create(
        name: String?,
        price: com.yandex.mapkit.kmp.Money?,
    ): FuelType {
        return FuelType(
            name,
            price,
        )
    }
}

/**
 * Fuel snippet.
 */
actual typealias FuelMetadata = com.yandex.mapkit.search.FuelMetadata

/**
 * Snippet update time as UNIX timestamp.
 *
 */
actual val FuelMetadata.mpTimestamp: Long?
    get() = timestamp
/**
 * Fuel list.
 */
actual val FuelMetadata.mpFuels: kotlin.collections.List<com.yandex.mapkit.search.kmp.FuelType>
    get() = fuels
/**
 * Attribution information.
 *
 */
actual val FuelMetadata.mpAttribution: com.yandex.mapkit.kmp.Attribution?
    get() = attribution

actual object FuelMetadataFactory {
    actual fun create(
        timestamp: Long?,
        fuels: kotlin.collections.List<com.yandex.mapkit.search.kmp.FuelType>,
        attribution: com.yandex.mapkit.kmp.Attribution?,
    ): FuelMetadata {
        return FuelMetadata(
            timestamp,
            fuels,
            attribution,
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.fuelMetadata: FuelMetadata?
    get() = getItem(FuelMetadata::class.java)

