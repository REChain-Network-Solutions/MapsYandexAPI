@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * A filter that could be applied to search results.
 *
 * Filters can be either boolean (that is Wi-Fi availability in a cafe)
 * or enumerated (that is cuisine type in a restaurant). Enumerated
 * filters support multiple selected values (OR-combined), to search,
 * for example, for restaurants with Armenian or Georgian cuisine at
 * once.
 *
 * This class is used in two separate ways: server response contains all
 * filters applicable to current search request and client can use some
 * of these filters to get more specific results in the following search
 * requests
 */
expect class BusinessFilter

/**
 * Filter id.
 */
expect val BusinessFilter.mpId: String
/**
 * Human-readable filter name.
 *
 */
expect val BusinessFilter.mpName: String?
/**
 * The filter should not be used by the client, because filter is either
 * used already (selected:true, disabled:true) or nothing would be found
 * (selected:false, disabled:true).
 *
 */
expect val BusinessFilter.mpDisabled: Boolean?
expect val BusinessFilter.mpIconLight: com.yandex.mapkit.kmp.Image?
expect val BusinessFilter.mpIconDark: com.yandex.mapkit.kmp.Image?
expect val BusinessFilter.mpIconAfterLight: com.yandex.mapkit.kmp.Image?
expect val BusinessFilter.mpIconAfterDark: com.yandex.mapkit.kmp.Image?
/**
 * Only one of multiple available values should be selected.
 *
 */
expect val BusinessFilter.mpSingleSelect: Boolean?
/**
 * Filter values.
 */
expect val BusinessFilter.mpValues: com.yandex.mapkit.search.kmp.BusinessFilterValues

expect object BusinessFilterFactory {
    fun create(
        id: String,
        name: String?,
        disabled: Boolean?,
        iconLight: com.yandex.mapkit.kmp.Image?,
        iconDark: com.yandex.mapkit.kmp.Image?,
        iconAfterLight: com.yandex.mapkit.kmp.Image?,
        iconAfterDark: com.yandex.mapkit.kmp.Image?,
        singleSelect: Boolean?,
        values: com.yandex.mapkit.search.kmp.BusinessFilterValues,
    ): BusinessFilter
}

/**
 * Value for boolean filters.
 */
expect class BusinessFilterBooleanValue

/**
 * Filter value. Set in server reponse for selected filters.
 */
expect val BusinessFilterBooleanValue.mpValue: Boolean
/**
 * Selected marker. Set in server response for selected filters.
 *
 */
expect val BusinessFilterBooleanValue.mpSelected: Boolean?

expect object BusinessFilterBooleanValueFactory {
    fun create(
        value: Boolean,
        selected: Boolean?,
    ): BusinessFilterBooleanValue
}

/**
 * Value for enum filters.
 */
expect class BusinessFilterEnumValue

/**
 * Filter value. Set in server response for selected filters.
 */
expect val BusinessFilterEnumValue.mpValue: com.yandex.mapkit.search.kmp.FeatureEnumValue
/**
 * Selected marker. Set in server response for selected filters.
 *
 */
expect val BusinessFilterEnumValue.mpSelected: Boolean?
/**
 * Same as [BusinessFilter.disabled], but for this specific enum value.
 *
 */
expect val BusinessFilterEnumValue.mpDisabled: Boolean?

expect object BusinessFilterEnumValueFactory {
    fun create(
        value: com.yandex.mapkit.search.kmp.FeatureEnumValue,
        selected: Boolean?,
        disabled: Boolean?,
    ): BusinessFilterEnumValue
}

/**
 * Value for range filters.
 */
expect class BusinessFilterRangeValue

/**
 * Minimum allowed filter value.
 */
expect val BusinessFilterRangeValue.mpFrom: Double
/**
 * Maximum allowed filter value.
 */
expect val BusinessFilterRangeValue.mpTo: Double

expect object BusinessFilterRangeValueFactory {
    fun create(
        from: Double,
        to: Double,
    ): BusinessFilterRangeValue
}

/**
 * Value for date filters.
 */
expect class BusinessFilterDateValue

/**
 * @hidden
 * Dummy field to make code generation work.
 */
expect val BusinessFilterDateValue.mpReserved: Int

expect object BusinessFilterDateValueFactory {
    fun create(
        reserved: Int,
    ): BusinessFilterDateValue
}

/**
 * Possible filter values.
 */
expect class BusinessFilterValues

expect val BusinessFilterValues.booleans: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessFilterBooleanValue>?
expect val BusinessFilterValues.enums: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessFilterEnumValue>?
expect val BusinessFilterValues.range: com.yandex.mapkit.search.kmp.BusinessFilterRangeValue?
expect val BusinessFilterValues.date: com.yandex.mapkit.search.kmp.BusinessFilterDateValue?

expect fun BusinessFilterValuesFromBooleans(booleans: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessFilterBooleanValue>): BusinessFilterValues
expect fun BusinessFilterValuesFromEnums(enums: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessFilterEnumValue>): BusinessFilterValues
expect fun BusinessFilterValuesFromRange(range: com.yandex.mapkit.search.kmp.BusinessFilterRangeValue): BusinessFilterValues
expect fun BusinessFilterValuesFromDate(date: com.yandex.mapkit.search.kmp.BusinessFilterDateValue): BusinessFilterValues

/**
 * Collection of filters.
 */
expect class FilterSet

/**
 * IDs for filters in the collection.
 */
expect val FilterSet.mpIds: kotlin.collections.List<String>

expect object FilterSetFactory {
    fun create(
        ids: kotlin.collections.List<String>,
    ): FilterSet
}

