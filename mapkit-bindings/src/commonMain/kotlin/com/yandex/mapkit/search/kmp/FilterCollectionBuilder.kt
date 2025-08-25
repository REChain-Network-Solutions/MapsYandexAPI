@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Builder for filter collection.
 */
expect interface FilterCollectionBuilder {
    /**
     * Adds boolean filter with given id to collection.
     *
     * @param filterId filter id to add.
     */
    fun addBooleanFilter(
        filterId: String,
    ): Unit

    /**
     * Adds enum filter with given id and values to the collection.
     *
     * @param filterId filter id to add.
     * @param valueIds list of value ids for the enum filter.
     */
    fun addEnumFilter(
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
    fun addRangeFilter(
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
    fun addDateFilter(
        filterId: String,
        from: String,
        to: String,
    ): Unit

    /**
     * Builds resulting collection.
     * @return collection built from previously given filters.
     */
    fun build(): com.yandex.mapkit.search.kmp.FilterCollection
}

expect object FilterCollectionUtils {
    /**
     * Creates new [FilterCollectionBuilder].
     * @return builder for [FilterCollection].
     */
    fun createFilterCollectionBuilder(): com.yandex.mapkit.search.kmp.FilterCollectionBuilder
}

