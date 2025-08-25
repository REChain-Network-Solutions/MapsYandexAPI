@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

import com.yandex.runtime.kmp.internal.toDouble
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * Builder for filter collection.
 */
actual interface FilterCollectionBuilder {
    val impl: platform.YandexMapsMobile.YMKSearchFilterCollectionBuilder

    /**
     * Adds boolean filter with given id to collection.
     *
     * @param filterId filter id to add.
     */
    actual fun addBooleanFilter(
        filterId: String,
    ): Unit

    /**
     * Adds enum filter with given id and values to the collection.
     *
     * @param filterId filter id to add.
     * @param valueIds list of value ids for the enum filter.
     */
    actual fun addEnumFilter(
        filterId: String,
        valueIds: kotlin.collections.List<String>,
    ): Unit

    /**
     * Adds range filter with given id and limits to the collection.
     *
     * @param filterId filter id to add.
     * @param from lower range limit.
     * @param to upper range limit.
     */
    actual fun addRangeFilter(
        filterId: String,
        from: Double,
        to: Double,
    ): Unit

    /**
     * Adds date filter with given id and limits to the collection. Limits
     * are encoded as strings in YYYYMMDD format.
     *
     * @param filterId filter id to add.
     * @param from lower range limit.
     * @param to upper range limit.
     */
    actual fun addDateFilter(
        filterId: String,
        from: String,
        to: String,
    ): Unit

    /**
     * Builds resulting collection.
     * @return collection built from previously given filters.
     */
    actual fun build(): com.yandex.mapkit.search.kmp.FilterCollection
}

open class YMKSearchFilterCollectionBuilderWrapper(override val impl: platform.YandexMapsMobile.YMKSearchFilterCollectionBuilder) : FilterCollectionBuilder {
    override fun addBooleanFilter(
        filterId: String,
    ): Unit {
        return impl.addBooleanFilterWithFilterId(
            filterId,
        )
    }

    override fun addEnumFilter(
        filterId: String,
        valueIds: kotlin.collections.List<String>,
    ): Unit {
        return impl.addEnumFilterWithFilterId(
            filterId,
            valueIds.let { it as kotlin.collections.List<*> },
        )
    }

    override fun addRangeFilter(
        filterId: String,
        from: Double,
        to: Double,
    ): Unit {
        return impl.addRangeFilterWithFilterId(
            filterId,
            from,
            to,
        )
    }

    override fun addDateFilter(
        filterId: String,
        from: String,
        to: String,
    ): Unit {
        return impl.addDateFilterWithFilterId(
            filterId,
            from,
            to,
        )
    }

    override fun build(): com.yandex.mapkit.search.kmp.FilterCollection {
        return impl.build()
    }
}

actual object FilterCollectionUtils {
    /**
     * Creates new [FilterCollectionBuilder].
     * @return builder for [FilterCollection].
     */
    actual fun createFilterCollectionBuilder(): com.yandex.mapkit.search.kmp.FilterCollectionBuilder {
        return platform.YandexMapsMobile.YMKSearchFilterCollectionUtils.createFilterCollectionBuilder().let { com.yandex.mapkit.search.kmp.YMKSearchFilterCollectionBuilderWrapper(it) }
    }
}

