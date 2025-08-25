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
actual typealias BusinessFilter = com.yandex.mapkit.search.BusinessFilter

/**
 * Filter id.
 */
actual val BusinessFilter.mpId: String
    get() = id
/**
 * Human-readable filter name.
 *
 */
actual val BusinessFilter.mpName: String?
    get() = name
/**
 * The filter should not be used by the client, because filter is either
 * used already (selected:true, disabled:true) or nothing would be found
 * (selected:false, disabled:true).
 *
 */
actual val BusinessFilter.mpDisabled: Boolean?
    get() = disabled
actual val BusinessFilter.mpIconLight: com.yandex.mapkit.kmp.Image?
    get() = iconLight
actual val BusinessFilter.mpIconDark: com.yandex.mapkit.kmp.Image?
    get() = iconDark
actual val BusinessFilter.mpIconAfterLight: com.yandex.mapkit.kmp.Image?
    get() = iconAfterLight
actual val BusinessFilter.mpIconAfterDark: com.yandex.mapkit.kmp.Image?
    get() = iconAfterDark
/**
 * Only one of multiple available values should be selected.
 *
 */
actual val BusinessFilter.mpSingleSelect: Boolean?
    get() = singleSelect
/**
 * Filter values.
 */
actual val BusinessFilter.mpValues: com.yandex.mapkit.search.kmp.BusinessFilterValues
    get() = values

actual object BusinessFilterFactory {
    actual fun create(
        id: String,
        name: String?,
        disabled: Boolean?,
        iconLight: com.yandex.mapkit.kmp.Image?,
        iconDark: com.yandex.mapkit.kmp.Image?,
        iconAfterLight: com.yandex.mapkit.kmp.Image?,
        iconAfterDark: com.yandex.mapkit.kmp.Image?,
        singleSelect: Boolean?,
        values: com.yandex.mapkit.search.kmp.BusinessFilterValues,
    ): BusinessFilter {
        return BusinessFilter(
            id,
            name,
            disabled,
            iconLight,
            iconDark,
            iconAfterLight,
            iconAfterDark,
            singleSelect,
            values,
        )
    }
}

/**
 * Value for boolean filters.
 */
actual typealias BusinessFilterBooleanValue = com.yandex.mapkit.search.BusinessFilter.BooleanValue

/**
 * Filter value. Set in server reponse for selected filters.
 */
actual val BusinessFilterBooleanValue.mpValue: Boolean
    get() = value
/**
 * Selected marker. Set in server response for selected filters.
 *
 */
actual val BusinessFilterBooleanValue.mpSelected: Boolean?
    get() = selected

actual object BusinessFilterBooleanValueFactory {
    actual fun create(
        value: Boolean,
        selected: Boolean?,
    ): BusinessFilterBooleanValue {
        return BusinessFilterBooleanValue(
            value,
            selected,
        )
    }
}

/**
 * Value for enum filters.
 */
actual typealias BusinessFilterEnumValue = com.yandex.mapkit.search.BusinessFilter.EnumValue

/**
 * Filter value. Set in server response for selected filters.
 */
actual val BusinessFilterEnumValue.mpValue: com.yandex.mapkit.search.kmp.FeatureEnumValue
    get() = value
/**
 * Selected marker. Set in server response for selected filters.
 *
 */
actual val BusinessFilterEnumValue.mpSelected: Boolean?
    get() = selected
/**
 * Same as [BusinessFilter.disabled], but for this specific enum value.
 *
 */
actual val BusinessFilterEnumValue.mpDisabled: Boolean?
    get() = disabled

actual object BusinessFilterEnumValueFactory {
    actual fun create(
        value: com.yandex.mapkit.search.kmp.FeatureEnumValue,
        selected: Boolean?,
        disabled: Boolean?,
    ): BusinessFilterEnumValue {
        return BusinessFilterEnumValue(
            value,
            selected,
            disabled,
        )
    }
}

/**
 * Value for range filters.
 */
actual typealias BusinessFilterRangeValue = com.yandex.mapkit.search.BusinessFilter.RangeValue

/**
 * Minimum allowed filter value.
 */
actual val BusinessFilterRangeValue.mpFrom: Double
    get() = from
/**
 * Maximum allowed filter value.
 */
actual val BusinessFilterRangeValue.mpTo: Double
    get() = to

actual object BusinessFilterRangeValueFactory {
    actual fun create(
        from: Double,
        to: Double,
    ): BusinessFilterRangeValue {
        return BusinessFilterRangeValue(
            from,
            to,
        )
    }
}

/**
 * Value for date filters.
 */
actual typealias BusinessFilterDateValue = com.yandex.mapkit.search.BusinessFilter.DateValue

/**
 * @hidden
 * Dummy field to make code generation work.
 */
actual val BusinessFilterDateValue.mpReserved: Int
    get() = reserved

actual object BusinessFilterDateValueFactory {
    actual fun create(
        reserved: Int,
    ): BusinessFilterDateValue {
        return BusinessFilterDateValue(
            reserved,
        )
    }
}

/**
 * Possible filter values.
 */
actual typealias BusinessFilterValues = com.yandex.mapkit.search.BusinessFilter.Values

actual val BusinessFilterValues.booleans: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessFilterBooleanValue>?
    get() = booleans
actual val BusinessFilterValues.enums: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessFilterEnumValue>?
    get() = enums
actual val BusinessFilterValues.range: com.yandex.mapkit.search.kmp.BusinessFilterRangeValue?
    get() = range
actual val BusinessFilterValues.date: com.yandex.mapkit.search.kmp.BusinessFilterDateValue?
    get() = date

actual fun BusinessFilterValuesFromBooleans(booleans: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessFilterBooleanValue>): BusinessFilterValues = com.yandex.mapkit.search.BusinessFilter.Values.fromBooleans(booleans)
actual fun BusinessFilterValuesFromEnums(enums: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessFilterEnumValue>): BusinessFilterValues = com.yandex.mapkit.search.BusinessFilter.Values.fromEnums(enums)
actual fun BusinessFilterValuesFromRange(range: com.yandex.mapkit.search.kmp.BusinessFilterRangeValue): BusinessFilterValues = com.yandex.mapkit.search.BusinessFilter.Values.fromRange(range)
actual fun BusinessFilterValuesFromDate(date: com.yandex.mapkit.search.kmp.BusinessFilterDateValue): BusinessFilterValues = com.yandex.mapkit.search.BusinessFilter.Values.fromDate(date)

/**
 * Collection of filters.
 */
actual typealias FilterSet = com.yandex.mapkit.search.FilterSet

/**
 * IDs for filters in the collection.
 */
actual val FilterSet.mpIds: kotlin.collections.List<String>
    get() = ids

actual object FilterSetFactory {
    actual fun create(
        ids: kotlin.collections.List<String>,
    ): FilterSet {
        return FilterSet(
            ids,
        )
    }
}

