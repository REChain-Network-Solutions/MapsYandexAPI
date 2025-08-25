@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Common info for business search response.
 */
actual typealias BusinessResultMetadata = com.yandex.mapkit.search.BusinessResultMetadata

/**
 * List of categories (also known as rubrics) present in response.
 * Non-empty list means that the request was treated as a 'category
 * request' by the server (for example requests like "where to eat",
 * "cinema", and other).
 */
actual val BusinessResultMetadata.mpCategories: kotlin.collections.List<com.yandex.mapkit.search.kmp.Category>
    get() = categories
/**
 * List of chains present in response. Non-empty list means that the
 * request was treaded as 'chain request' (for example "mcdonalds",
 * "starbucks", and other).
 */
actual val BusinessResultMetadata.mpChains: kotlin.collections.List<com.yandex.mapkit.search.kmp.Chain>
    get() = chains
/**
 * List of applicable filters.
 */
actual val BusinessResultMetadata.mpBusinessFilters: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessFilter>
    get() = businessFilters
/**
 * Collection of filters that probably are of utmost interest to the
 * user.
 *
 */
actual val BusinessResultMetadata.mpImportantFilters: com.yandex.mapkit.search.kmp.FilterSet?
    get() = importantFilters
/**
 * Relevant currency for the prices
 */
actual val BusinessResultMetadata.mpPricesCurrencies: kotlin.collections.List<String>
    get() = pricesCurrencies

actual object BusinessResultMetadataFactory {
    actual fun create(
        categories: kotlin.collections.List<com.yandex.mapkit.search.kmp.Category>,
        chains: kotlin.collections.List<com.yandex.mapkit.search.kmp.Chain>,
        businessFilters: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessFilter>,
        importantFilters: com.yandex.mapkit.search.kmp.FilterSet?,
        pricesCurrencies: kotlin.collections.List<String>,
    ): BusinessResultMetadata {
        return BusinessResultMetadata(
            categories,
            chains,
            businessFilters,
            importantFilters,
            pricesCurrencies,
        )
    }
}

