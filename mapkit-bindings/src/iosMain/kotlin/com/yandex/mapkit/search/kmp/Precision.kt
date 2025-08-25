@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST", "REDUNDANT_ELSE_IN_WHEN")

package com.yandex.mapkit.search.kmp

/**
 * Precision for matching house numbers (response vs. request).
 */
actual enum class Precision {
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
    NEARBY,;

    companion object {
        fun toKmp(v: platform.Foundation.NSNumber): Precision {
            return toKmp(platform.YandexMapsMobile.YMKSearchPrecision.byValue(v.unsignedLongValue))
        }

        fun toKmp(v: platform.YandexMapsMobile.YMKSearchPrecision): Precision {
            return when (v) {
                platform.YandexMapsMobile.YMKSearchPrecision.YMKSearchPrecisionExact -> Precision.EXACT
                platform.YandexMapsMobile.YMKSearchPrecision.YMKSearchPrecisionNumber -> Precision.NUMBER
                platform.YandexMapsMobile.YMKSearchPrecision.YMKSearchPrecisionRange -> Precision.RANGE
                platform.YandexMapsMobile.YMKSearchPrecision.YMKSearchPrecisionNearby -> Precision.NEARBY
                else -> error("unknown YMKSearchPrecision")
            }
        }
    }
}

fun Precision.fromKmp(): platform.YandexMapsMobile.YMKSearchPrecision {
    return when (this) {
        Precision.EXACT -> platform.YandexMapsMobile.YMKSearchPrecision.YMKSearchPrecisionExact
        Precision.NUMBER -> platform.YandexMapsMobile.YMKSearchPrecision.YMKSearchPrecisionNumber
        Precision.RANGE -> platform.YandexMapsMobile.YMKSearchPrecision.YMKSearchPrecisionRange
        Precision.NEARBY -> platform.YandexMapsMobile.YMKSearchPrecision.YMKSearchPrecisionNearby
    }
}

