@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Struct to fine-tune search request.
 */
expect class SearchOptions

/**
 * The search type can be one of the [SearchType] values or their
 * bitwise 'OR' combination. If searchType is not initialized, it means
 * to search in all the sources.
 */
expect var SearchOptions.mpSearchTypes: Int
/**
 * Maximum number of search results per page.
 *
 */
expect var SearchOptions.mpResultPageSize: Int?
/**
 * Snippets that will be requested. The value should be one of
 * [Snippet], or their bitwise 'OR' combination.
 */
expect var SearchOptions.mpSnippets: Int
/**
 * The server uses the user position to calculate the distance from the
 * user to search results.
 *
 */
expect var SearchOptions.mpUserPosition: com.yandex.mapkit.kmp.geometry.Point?
/**
 * String that sets an identifier for the request source.
 *
 */
expect var SearchOptions.mpOrigin: String?
/**
 * Adds the geometry to the server response.
 */
expect var SearchOptions.mpGeometry: Boolean
/**
 * Force disable correction of spelling mistakes.
 */
expect var SearchOptions.mpDisableSpellingCorrection: Boolean
/**
 * Filter set that will be requested. Please note that the full set of
 * filters that can be applied can only be obtained after the primary
 * request. If you pass an invalid filter to the primary request (for
 * example, "pharmacy with swimming pool"), the behavior is undefined.
 * That is the search can either ignore an invalid filter or return an
 * empty response.
 *
 */
expect var SearchOptions.mpFilters: com.yandex.mapkit.search.kmp.FilterCollection?

expect object SearchOptionsFactory {
    val Default: SearchOptions

    fun create(
        searchTypes: Int,
        resultPageSize: Int?,
        snippets: Int,
        userPosition: com.yandex.mapkit.kmp.geometry.Point?,
        origin: String?,
        geometry: Boolean,
        disableSpellingCorrection: Boolean,
        filters: com.yandex.mapkit.search.kmp.FilterCollection?,
    ): SearchOptions
}

