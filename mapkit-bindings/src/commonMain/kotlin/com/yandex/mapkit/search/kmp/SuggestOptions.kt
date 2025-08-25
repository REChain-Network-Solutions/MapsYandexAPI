@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Bitmask for requested suggest types.
 */
expect enum class SuggestType {
    /**
     * Default value: server-defined types are returned.
     */
    UNSPECIFIED,
    /**
     * Toponyms.
     */
    GEO,
    /**
     * Companies.
     */
    BIZ,
    /**
     * Mass transit routes.
     */
    TRANSIT,
}

/**
 * Struct to fine-tune suggest request.
 */
expect class SuggestOptions

/**
 * The suggest type can be one of [SuggestType] values or their bitwise
 * 'OR' combination. If suggestType is not initialized, it means to use
 * server-defined types.
 */
expect var SuggestOptions.mpSuggestTypes: Int
/**
 * The server uses the user position to calculate the distance from the
 * user to suggest results.
 *
 */
expect var SuggestOptions.mpUserPosition: com.yandex.mapkit.kmp.geometry.Point?
/**
 * Enable word-by-word suggestion items.
 */
expect var SuggestOptions.mpSuggestWords: Boolean
/**
 * Strictly limit the output and keep only objects that fall within the
 * window. The window is advisory in nature and doesn't impose strict
 * restrictions on search results, helping to select the most relevant
 * hints.
 */
expect var SuggestOptions.mpStrictBounds: Boolean

expect object SuggestOptionsFactory {
    fun create(
        suggestTypes: Int,
        userPosition: com.yandex.mapkit.kmp.geometry.Point?,
        suggestWords: Boolean,
        strictBounds: Boolean,
    ): SuggestOptions
}

