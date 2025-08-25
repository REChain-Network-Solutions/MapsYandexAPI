@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

import com.yandex.runtime.kmp.internal.toBoolean
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * Bitmask for requested suggest types.
 */
actual enum class SuggestType(val value: Int) {
    /**
     * Default value: server-defined types are returned.
     */
    UNSPECIFIED(0),
    /**
     * Toponyms.
     */
    GEO(1),
    /**
     * Companies.
     */
    BIZ(1 shl 1),
    /**
     * Mass transit routes.
     */
    TRANSIT(1 shl 2),
    /**
     * Web link or deep link
     */
    LINK(1 shl 3),
}

/**
 * Struct to fine-tune suggest request.
 */
actual typealias SuggestOptions = platform.YandexMapsMobile.YMKSuggestOptions

/**
 * The suggest type can be one of [SuggestType] values or their bitwise
 * 'OR' combination. If suggestType is not initialized, it means to use
 * server-defined types.
 */
actual var SuggestOptions.mpSuggestTypes: Int
    get() = suggestTypes().toInt()
    set(value) {
        suggestTypes = value.toULong()
    }
/**
 * The server uses the user position to calculate the distance from the
 * user to suggest results.
 *
 */
actual var SuggestOptions.mpUserPosition: com.yandex.mapkit.kmp.geometry.Point?
    get() = userPosition()
    set(value) {
        userPosition = value
    }
/**
 * Enable word-by-word suggestion items.
 */
actual var SuggestOptions.mpSuggestWords: Boolean
    get() = suggestWords()
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
    get() = strictBounds()
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
        return SuggestOptions.suggestOptionsWithSuggestTypes(
            suggestTypes.toULong(),
            userPosition,
            suggestWords,
            strictBounds,
        )
    }
}

