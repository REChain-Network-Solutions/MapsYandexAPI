@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Fuel name and price.
 */
expect class FuelType

/**
 * Fuel name.
 *
 */
expect val FuelType.mpName: String?
/**
 * Fuel price.
 *
 */
expect val FuelType.mpPrice: com.yandex.mapkit.kmp.Money?

expect object FuelTypeFactory {
    fun create(
        name: String?,
        price: com.yandex.mapkit.kmp.Money?,
    ): FuelType
}

/**
 * Fuel snippet.
 */
expect class FuelMetadata

/**
 * Snippet update time as UNIX timestamp.
 *
 */
expect val FuelMetadata.mpTimestamp: Long?
/**
 * Fuel list.
 */
expect val FuelMetadata.mpFuels: kotlin.collections.List<com.yandex.mapkit.search.kmp.FuelType>
/**
 * Attribution information.
 *
 */
expect val FuelMetadata.mpAttribution: com.yandex.mapkit.kmp.Attribution?

expect object FuelMetadataFactory {
    fun create(
        timestamp: Long?,
        fuels: kotlin.collections.List<com.yandex.mapkit.search.kmp.FuelType>,
        attribution: com.yandex.mapkit.kmp.Attribution?,
    ): FuelMetadata
}

expect val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.fuelMetadata: FuelMetadata?

