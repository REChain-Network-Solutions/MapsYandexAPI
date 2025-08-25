@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Common info for business search response.
 */
expect class BusinessResultMetadata

/**
 * List of categories (also known as rubrics) present in response.
 * Non-empty list means that the request was treated as a 'category
 * request' by the server (for example requests like "where to eat",
 * "cinema", and other).
 */
expect val BusinessResultMetadata.mpCategories: kotlin.collections.List<com.yandex.mapkit.search.kmp.Category>
/**
 * List of chains present in response. Non-empty list means that the
 * request was treaded as 'chain request' (for example "mcdonalds",
 * "starbucks", and other).
 */
expect val BusinessResultMetadata.mpChains: kotlin.collections.List<com.yandex.mapkit.search.kmp.Chain>
/**
 * List of applicable filters.
 */
expect val BusinessResultMetadata.mpBusinessFilters: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessFilter>
/**
 * Collection of filters that probably are of utmost interest to the
 * user.
 *
 */
expect val BusinessResultMetadata.mpImportantFilters: com.yandex.mapkit.search.kmp.FilterSet?
/**
 * Relevant currency for the prices
 */
expect val BusinessResultMetadata.mpPricesCurrencies: kotlin.collections.List<String>

expect object BusinessResultMetadataFactory {
    fun create(
        categories: kotlin.collections.List<com.yandex.mapkit.search.kmp.Category>,
        chains: kotlin.collections.List<com.yandex.mapkit.search.kmp.Chain>,
        businessFilters: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessFilter>,
        importantFilters: com.yandex.mapkit.search.kmp.FilterSet?,
        pricesCurrencies: kotlin.collections.List<String>,
    ): BusinessResultMetadata
}

