@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

import com.yandex.runtime.kmp.internal.toBoolean
import com.yandex.runtime.kmp.internal.toDouble
import com.yandex.runtime.kmp.internal.toInt
import com.yandex.runtime.kmp.internal.toNSNumber

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
actual typealias BusinessFilter = platform.YandexMapsMobile.YMKSearchBusinessFilter

/**
 * Filter id.
 */
actual val BusinessFilter.mpId: String
    get() = id()
/**
 * Human-readable filter name.
 *
 */
actual val BusinessFilter.mpName: String?
    get() = name()
/**
 * The filter should not be used by the client, because filter is either
 * used already (selected:true, disabled:true) or nothing would be found
 * (selected:false, disabled:true).
 *
 */
actual val BusinessFilter.mpDisabled: Boolean?
    get() = disabled()?.toBoolean()
actual val BusinessFilter.mpIconLight: com.yandex.mapkit.kmp.Image?
    get() = iconLight()
actual val BusinessFilter.mpIconDark: com.yandex.mapkit.kmp.Image?
    get() = iconDark()
actual val BusinessFilter.mpIconAfterLight: com.yandex.mapkit.kmp.Image?
    get() = iconAfterLight()
actual val BusinessFilter.mpIconAfterDark: com.yandex.mapkit.kmp.Image?
    get() = iconAfterDark()
/**
 * Only one of multiple available values should be selected.
 *
 */
actual val BusinessFilter.mpSingleSelect: Boolean?
    get() = singleSelect()?.toBoolean()
/**
 * Filter values.
 */
actual val BusinessFilter.mpValues: com.yandex.mapkit.search.kmp.BusinessFilterValues
    get() = values()

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
        return BusinessFilter.businessFilterWithId(
            id,
            name,
            disabled?.toNSNumber(),
            iconLight,
            iconDark,
            iconAfterLight,
            iconAfterDark,
            singleSelect?.toNSNumber(),
            values,
        )
    }
}

/**
 * Value for boolean filters.
 */
actual typealias BusinessFilterBooleanValue = platform.YandexMapsMobile.YMKSearchBusinessFilterBooleanValue

/**
 * Filter value. Set in server reponse for selected filters.
 */
actual val BusinessFilterBooleanValue.mpValue: Boolean
    get() = value()
/**
 * Selected marker. Set in server response for selected filters.
 *
 */
actual val BusinessFilterBooleanValue.mpSelected: Boolean?
    get() = selected()?.toBoolean()

actual object BusinessFilterBooleanValueFactory {
    actual fun create(
        value: Boolean,
        selected: Boolean?,
    ): BusinessFilterBooleanValue {
        return BusinessFilterBooleanValue.booleanValueWithValue(
            value,
            selected?.toNSNumber(),
        )
    }
}

/**
 * Value for enum filters.
 */
actual typealias BusinessFilterEnumValue = platform.YandexMapsMobile.YMKSearchBusinessFilterEnumValue

/**
 * Filter value. Set in server response for selected filters.
 */
actual val BusinessFilterEnumValue.mpValue: com.yandex.mapkit.search.kmp.FeatureEnumValue
    get() = value()
/**
 * Selected marker. Set in server response for selected filters.
 *
 */
actual val BusinessFilterEnumValue.mpSelected: Boolean?
    get() = selected()?.toBoolean()
/**
 * Same as [BusinessFilter.disabled], but for this specific enum value.
 *
 */
actual val BusinessFilterEnumValue.mpDisabled: Boolean?
    get() = disabled()?.toBoolean()

actual object BusinessFilterEnumValueFactory {
    actual fun create(
        value: com.yandex.mapkit.search.kmp.FeatureEnumValue,
        selected: Boolean?,
        disabled: Boolean?,
    ): BusinessFilterEnumValue {
        return BusinessFilterEnumValue.enumValueWithValue(
            value,
            selected?.toNSNumber(),
            disabled?.toNSNumber(),
        )
    }
}

/**
 * Value for range filters.
 */
actual typealias BusinessFilterRangeValue = platform.YandexMapsMobile.YMKSearchBusinessFilterRangeValue

/**
 * Minimum allowed filter value.
 */
actual val BusinessFilterRangeValue.mpFrom: Double
    get() = from()
/**
 * Maximum allowed filter value.
 */
actual val BusinessFilterRangeValue.mpTo: Double
    get() = to()

actual object BusinessFilterRangeValueFactory {
    actual fun create(
        from: Double,
        to: Double,
    ): BusinessFilterRangeValue {
        return BusinessFilterRangeValue.rangeValueWithFrom(
            from,
            to,
        )
    }
}

/**
 * Value for date filters.
 */
actual typealias BusinessFilterDateValue = platform.YandexMapsMobile.YMKSearchBusinessFilterDateValue

/**
 * @hidden
 * Dummy field to make code generation work.
 */
actual val BusinessFilterDateValue.mpReserved: Int
    get() = reserved().toInt()

actual object BusinessFilterDateValueFactory {
    actual fun create(
        reserved: Int,
    ): BusinessFilterDateValue {
        return BusinessFilterDateValue.dateValueWithReserved(
            reserved.toLong(),
        )
    }
}

/**
 * Possible filter values.
 */
actual typealias BusinessFilterValues = platform.YandexMapsMobile.YMKSearchBusinessFilterValues

actual val BusinessFilterValues.booleans: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessFilterBooleanValue>?
    get() = booleans.let { it as kotlin.collections.List<platform.YandexMapsMobile.YMKSearchBusinessFilterBooleanValue> }
actual val BusinessFilterValues.enums: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessFilterEnumValue>?
    get() = enums.let { it as kotlin.collections.List<platform.YandexMapsMobile.YMKSearchBusinessFilterEnumValue> }
actual val BusinessFilterValues.range: com.yandex.mapkit.search.kmp.BusinessFilterRangeValue?
    get() = range
actual val BusinessFilterValues.date: com.yandex.mapkit.search.kmp.BusinessFilterDateValue?
    get() = date

actual fun BusinessFilterValuesFromBooleans(booleans: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessFilterBooleanValue>): BusinessFilterValues = platform.YandexMapsMobile.YMKSearchBusinessFilterValues.valuesWithBooleans(booleans)
actual fun BusinessFilterValuesFromEnums(enums: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessFilterEnumValue>): BusinessFilterValues = platform.YandexMapsMobile.YMKSearchBusinessFilterValues.valuesWithEnums(enums)
actual fun BusinessFilterValuesFromRange(range: com.yandex.mapkit.search.kmp.BusinessFilterRangeValue): BusinessFilterValues = platform.YandexMapsMobile.YMKSearchBusinessFilterValues.valuesWithRange(range)
actual fun BusinessFilterValuesFromDate(date: com.yandex.mapkit.search.kmp.BusinessFilterDateValue): BusinessFilterValues = platform.YandexMapsMobile.YMKSearchBusinessFilterValues.valuesWithDate(date)

/**
 * Collection of filters.
 */
actual typealias FilterSet = platform.YandexMapsMobile.YMKSearchFilterSet

/**
 * IDs for filters in the collection.
 */
actual val FilterSet.mpIds: kotlin.collections.List<String>
    get() = ids().let { it as kotlin.collections.List<String> }

actual object FilterSetFactory {
    actual fun create(
        ids: kotlin.collections.List<String>,
    ): FilterSet {
        return FilterSet.filterSetWithIds(
            ids.let { it as kotlin.collections.List<*> },
        )
    }
}

