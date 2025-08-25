@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Filters for search request. Use builder to setup this class.
 */
expect class FilterCollection

/**
 * List of boolean filter ids to be set.
 */
expect val FilterCollection.mpBooleanFilters: kotlin.collections.List<String>
/**
 * Mapping of enum filter ids to list of enum value ids.
 */
expect val FilterCollection.mpEnumFilters: kotlin.collections.Map<String, kotlin.collections.List<String>>
/**
 * Mapping of range filter ids to number range.
 */
expect val FilterCollection.mpRangeFilters: kotlin.collections.Map<String, FilterCollectionNumberRange>
/**
 * Mapping of date filter ids to date range.
 */
expect val FilterCollection.mpDateFilters: kotlin.collections.Map<String, FilterCollectionDateRange>

expect object FilterCollectionFactory {
    fun create(
        booleanFilters: kotlin.collections.List<String>,
        enumFilters: kotlin.collections.Map<String, kotlin.collections.List<String>>,
        rangeFilters: kotlin.collections.Map<String, FilterCollectionNumberRange>,
        dateFilters: kotlin.collections.Map<String, FilterCollectionDateRange>,
    ): FilterCollection
}

/**
 * Number range. Used for range filter.
 */
expect class FilterCollectionNumberRange

/**
 * Lower range limit.
 */
expect val FilterCollectionNumberRange.mpFrom: Double
/**
 * Upper range limit.
 */
expect val FilterCollectionNumberRange.mpTo: Double

expect object FilterCollectionNumberRangeFactory {
    fun create(
        from: Double,
        to: Double,
    ): FilterCollectionNumberRange
}

/**
 * Date range. Dates are encoded as strings in YYYYMMDD format.
 */
expect class FilterCollectionDateRange

/**
 * Lower range limit.
 */
expect val FilterCollectionDateRange.mpFrom: String
/**
 * Upper range limit.
 */
expect val FilterCollectionDateRange.mpTo: String

expect object FilterCollectionDateRangeFactory {
    fun create(
        from: String,
        to: String,
    ): FilterCollectionDateRange
}

