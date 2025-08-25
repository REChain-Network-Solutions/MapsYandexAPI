@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Filters for search request. Use builder to setup this class.
 */
actual typealias FilterCollection = com.yandex.mapkit.search.FilterCollection

/**
 * List of boolean filter ids to be set.
 */
actual val FilterCollection.mpBooleanFilters: kotlin.collections.List<String>
    get() = booleanFilters
/**
 * Mapping of enum filter ids to list of enum value ids.
 */
actual val FilterCollection.mpEnumFilters: kotlin.collections.Map<String, kotlin.collections.List<String>>
    get() = enumFilters
/**
 * Mapping of range filter ids to number range.
 */
actual val FilterCollection.mpRangeFilters: kotlin.collections.Map<String, FilterCollectionNumberRange>
    get() = rangeFilters
/**
 * Mapping of date filter ids to date range.
 */
actual val FilterCollection.mpDateFilters: kotlin.collections.Map<String, FilterCollectionDateRange>
    get() = dateFilters

actual object FilterCollectionFactory {
    actual fun create(
        booleanFilters: kotlin.collections.List<String>,
        enumFilters: kotlin.collections.Map<String, kotlin.collections.List<String>>,
        rangeFilters: kotlin.collections.Map<String, FilterCollectionNumberRange>,
        dateFilters: kotlin.collections.Map<String, FilterCollectionDateRange>,
    ): FilterCollection {
        return FilterCollection(
            booleanFilters,
            enumFilters,
            rangeFilters,
            dateFilters,
        )
    }
}

/**
 * Number range. Used for range filter.
 */
actual typealias FilterCollectionNumberRange = com.yandex.mapkit.search.FilterCollection.NumberRange

/**
 * Lower range limit.
 */
actual val FilterCollectionNumberRange.mpFrom: Double
    get() = from
/**
 * Upper range limit.
 */
actual val FilterCollectionNumberRange.mpTo: Double
    get() = to

actual object FilterCollectionNumberRangeFactory {
    actual fun create(
        from: Double,
        to: Double,
    ): FilterCollectionNumberRange {
        return FilterCollectionNumberRange(
            from,
            to,
        )
    }
}

/**
 * Date range. Dates are encoded as strings in YYYYMMDD format.
 */
actual typealias FilterCollectionDateRange = com.yandex.mapkit.search.FilterCollection.DateRange

/**
 * Lower range limit.
 */
actual val FilterCollectionDateRange.mpFrom: String
    get() = from
/**
 * Upper range limit.
 */
actual val FilterCollectionDateRange.mpTo: String
    get() = to

actual object FilterCollectionDateRangeFactory {
    actual fun create(
        from: String,
        to: String,
    ): FilterCollectionDateRange {
        return FilterCollectionDateRange(
            from,
            to,
        )
    }
}

