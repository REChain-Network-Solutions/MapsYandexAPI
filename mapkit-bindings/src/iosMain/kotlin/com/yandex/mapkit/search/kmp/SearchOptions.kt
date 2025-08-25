@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

import com.yandex.runtime.kmp.internal.toInt
import com.yandex.runtime.kmp.internal.toNSNumber
import platform.YandexMapsMobile.YMKSearchOptions

/**
 * Struct to fine-tune search request.
 */
actual typealias SearchOptions = platform.YandexMapsMobile.YMKSearchOptions

/**
 * The search type can be one of the [SearchType] values or their
 * bitwise 'OR' combination. If searchType is not initialized, it means
 * to search in all the sources.
 */
actual var SearchOptions.mpSearchTypes: Int
    get() = searchTypes().toInt()
    set(value) {
        searchTypes = value.toULong()
    }
/**
 * Maximum number of search results per page.
 *
 */
actual var SearchOptions.mpResultPageSize: Int?
    get() = resultPageSize()?.toInt()
    set(value) {
        resultPageSize = value?.toNSNumber()
    }
/**
 * Snippets that will be requested. The value should be one of
 * [Snippet], or their bitwise 'OR' combination.
 */
actual var SearchOptions.mpSnippets: Int
    get() = snippets().toInt()
    set(value) {
        snippets = value.toULong()
    }

/**
 * The server uses the user position to calculate the distance from the
 * user to search results.
 *
 */
actual var SearchOptions.mpUserPosition: com.yandex.mapkit.kmp.geometry.Point?
    get() = userPosition()
    set(value) {
        userPosition = value
    }
/**
 * String that sets an identifier for the request source.
 *
 */
actual var SearchOptions.mpOrigin: String?
    get() = origin()
    set(value) {
        origin = value
    }
/**
 * Adds the geometry to the server response.
 */
actual var SearchOptions.mpGeometry: Boolean
    get() = geometry()
    set(value) {
        geometry = value
    }
/**
 * Force disable correction of spelling mistakes.
 */
actual var SearchOptions.mpDisableSpellingCorrection: Boolean
    get() = disableSpellingCorrection()
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
actual var SearchOptions.mpFilters: com.yandex.mapkit.search.kmp.FilterCollection?
    get() = filters()
    set(value) {
        filters = value
    }

actual object SearchOptionsFactory {

    actual val Default: SearchOptions get() = YMKSearchOptions()

    actual fun create(
        searchTypes: Int,
        resultPageSize: Int?,
        snippets: Int,
        userPosition: com.yandex.mapkit.kmp.geometry.Point?,
        origin: String?,
        geometry: Boolean,
        disableSpellingCorrection: Boolean,
        filters: com.yandex.mapkit.search.kmp.FilterCollection?,
    ): SearchOptions {
        return SearchOptions.searchOptionsWithSearchTypes(
            searchTypes.toULong(),
            resultPageSize?.toNSNumber(),
            snippets.toULong(),
            userPosition,
            origin,
            geometry,
            disableSpellingCorrection,
            filters
        )
    }
}

