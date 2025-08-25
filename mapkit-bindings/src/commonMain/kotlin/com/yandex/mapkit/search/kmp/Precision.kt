@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Precision for matching house numbers (response vs. request).
 */
expect enum class Precision {
    /**
     * The house number in the response is exactly the same as requested
     * (3/2 vs. 3/2)
     */
    EXACT,
    /**
     * The house number in the response has the same number part as the
     * requested one (5 vs. 5a).
     */
    NUMBER,
    /**
     * The house number and coordinates are restored from the house range.
     * This means that there is no information about this specific house,
     * but there is information about a range of houses to infer house
     * position from.
     */
    RANGE,
    /**
     * The house number in the response is close to the requested one (13
     * vs. 11).
     */
    NEARBY,
}

