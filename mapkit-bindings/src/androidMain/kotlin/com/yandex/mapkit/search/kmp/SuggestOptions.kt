@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Bitmask for requested suggest types.
 */
actual typealias SuggestType = com.yandex.mapkit.search.SuggestType


/**
 * Struct to fine-tune suggest request.
 */
actual typealias SuggestOptions = com.yandex.mapkit.search.SuggestOptions

/**
 * The suggest type can be one of [SuggestType] values or their bitwise
 * 'OR' combination. If suggestType is not initialized, it means to use
 * server-defined types.
 */
actual var SuggestOptions.mpSuggestTypes: Int
    get() = suggestTypes
    set(value) {
        suggestTypes = value
    }
/**
 * The server uses the user position to calculate the distance from the
 * user to suggest results.
 *
 */
actual var SuggestOptions.mpUserPosition: com.yandex.mapkit.kmp.geometry.Point?
    get() = userPosition
    set(value) {
        userPosition = value
    }
/**
 * Enable word-by-word suggestion items.
 */
actual var SuggestOptions.mpSuggestWords: Boolean
    get() = suggestWords
    set(value) {
        suggestWords = value
    }
/**
 * Strictly limit the output and keep only objects that fall within the
 * window. The window is advisory in nature and doesn't impose strict
 * restrictions on search results, helping to select the most relevant
 * hints.
 */
actual var SuggestOptions.mpStrictBounds: Boolean
    get() = strictBounds
    set(value) {
        strictBounds = value
    }

actual object SuggestOptionsFactory {
    actual fun create(
        suggestTypes: Int,
        userPosition: com.yandex.mapkit.kmp.geometry.Point?,
        suggestWords: Boolean,
        strictBounds: Boolean,
    ): SuggestOptions {
        return SuggestOptions(
            suggestTypes,
            userPosition,
            suggestWords,
            strictBounds,
        )
    }
}

