@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Struct to fine-tune search request.
 */
actual typealias SearchOptions = com.yandex.mapkit.search.SearchOptions

/**
 * The search type can be one of the [SearchType] values or their
 * bitwise 'OR' combination. If searchType is not initialized, it means
 * to search in all the sources.
 */
actual var SearchOptions.mpSearchTypes: Int
    get() = searchTypes
    set(value) {
        searchTypes = value
    }
/**
 * Maximum number of search results per page.
 *
 */
actual var SearchOptions.mpResultPageSize: Int?
    get() = resultPageSize
    set(value) {
        resultPageSize = value
    }
/**
 * Snippets that will be requested. The value should be one of
 * [Snippet], or their bitwise 'OR' combination.
 */
actual var SearchOptions.mpSnippets: Int
    get() = snippets
    set(value) {
        snippets = value
    }
/**
 * The server uses the user position to calculate the distance from the
 * user to search results.
 *
 */
actual var SearchOptions.mpUserPosition: com.yandex.mapkit.kmp.geometry.Point?
    get() = userPosition
    set(value) {
        userPosition = value
    }
/**
 * String that sets an identifier for the request source.
 *
 */
actual var SearchOptions.mpOrigin: String?
    get() = origin
    set(value) {
        origin = value
    }
/**
 * Adds the geometry to the server response.
 */
actual var SearchOptions.mpGeometry: Boolean
    get() = geometry
    set(value) {
        geometry = value
    }
/**
 * Force disable correction of spelling mistakes.
 */
actual var SearchOptions.mpDisableSpellingCorrection: Boolean
    get() = disableSpellingCorrection
    set(value) {
        disableSpellingCorrection = value
    }
/**
 * Filter set that will be requested. Please note that the full set of
 * filters that can be applied can only be obtained after the primary
 * request. If you pass an invalid filter to the primary request (for
 * example, "pharmacy with swimming pool"), the behavior is undefined.
 * That is the search can either ignore an invalid filter or return an
 * empty response.
 *
 */
actual var SearchOptions.mpFilters: FilterCollection?
    get() = filters
    set(value) {
        filters = value
    }

actual object SearchOptionsFactory {
    actual val Default: SearchOptions = SearchOptions()

    actual fun create(
        searchTypes: Int,
        resultPageSize: Int?,
        snippets: Int,
        userPosition: com.yandex.mapkit.kmp.geometry.Point?,
        origin: String?,
        geometry: Boolean,
        disableSpellingCorrection: Boolean,
        filters: FilterCollection?,
    ): SearchOptions {
        return SearchOptions(
            searchTypes,
            resultPageSize,
            snippets,
            userPosition,
            origin,
            geometry,
            disableSpellingCorrection,
            filters,
        )
    }
}

