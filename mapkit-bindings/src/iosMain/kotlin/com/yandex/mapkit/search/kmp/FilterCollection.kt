@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

import com.yandex.runtime.kmp.internal.toDouble
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * Filters for search request. Use builder to setup this class.
 */
actual typealias FilterCollection = platform.YandexMapsMobile.YMKSearchFilterCollection

/**
 * List of boolean filter ids to be set.
 */
actual val FilterCollection.mpBooleanFilters: kotlin.collections.List<String>
    get() = booleanFilters().let { it as kotlin.collections.List<String> }
/**
 * Mapping of enum filter ids to list of enum value ids.
 */
actual val FilterCollection.mpEnumFilters: kotlin.collections.Map<String, kotlin.collections.List<String>>
    get() = enumFilters().let { it as kotlin.collections.Map<String, kotlin.collections.List<String>> }.mapValues { it.value.let { it as kotlin.collections.List<String> } }
/**
 * Mapping of range filter ids to number range.
 */
actual val FilterCollection.mpRangeFilters: kotlin.collections.Map<String, FilterCollectionNumberRange>
    get() = rangeFilters().let { it as kotlin.collections.Map<String, platform.YandexMapsMobile.YMKSearchFilterCollectionNumberRange> }
/**
 * Mapping of date filter ids to date range.
 */
actual val FilterCollection.mpDateFilters: kotlin.collections.Map<String, FilterCollectionDateRange>
    get() = dateFilters().let { it as kotlin.collections.Map<String, platform.YandexMapsMobile.YMKSearchFilterCollectionDateRange> }

actual object FilterCollectionFactory {
    actual fun create(
        booleanFilters: kotlin.collections.List<String>,
        enumFilters: kotlin.collections.Map<String, kotlin.collections.List<String>>,
        rangeFilters: kotlin.collections.Map<String, FilterCollectionNumberRange>,
        dateFilters: kotlin.collections.Map<String, FilterCollectionDateRange>,
    ): FilterCollection {
        return FilterCollection.filterCollectionWithBooleanFilters(
            booleanFilters.let { it as kotlin.collections.List<*> },
            enumFilters.mapValues { it.value.let { it as kotlin.collections.List<*> } }.let { it as kotlin.collections.Map<Any?, *> },
            rangeFilters.let { it as kotlin.collections.Map<Any?, *> },
            dateFilters.let { it as kotlin.collections.Map<Any?, *> },
        )
    }
}

/**
 * Number range. Used for range filter.
 */
actual typealias FilterCollectionNumberRange = platform.YandexMapsMobile.YMKSearchFilterCollectionNumberRange

/**
 * Lower range limit.
 */
actual val FilterCollectionNumberRange.mpFrom: Double
    get() = from()
/**
 * Upper range limit.
 */
actual val FilterCollectionNumberRange.mpTo: Double
    get() = to()

actual object FilterCollectionNumberRangeFactory {
    actual fun create(
        from: Double,
        to: Double,
    ): FilterCollectionNumberRange {
        return FilterCollectionNumberRange.numberRangeWithFrom(
            from,
            to,
        )
    }
}

/**
 * Date range. Dates are encoded as strings in YYYYMMDD format.
 */
actual typealias FilterCollectionDateRange = platform.YandexMapsMobile.YMKSearchFilterCollectionDateRange

/**
 * Lower range limit.
 */
actual val FilterCollectionDateRange.mpFrom: String
    get() = from()
/**
 * Upper range limit.
 */
actual val FilterCollectionDateRange.mpTo: String
    get() = to()

actual object FilterCollectionDateRangeFactory {
    actual fun create(
        from: String,
        to: String,
    ): FilterCollectionDateRange {
        return FilterCollectionDateRange.dateRangeWithFrom(
            from,
            to,
        )
    }
}

