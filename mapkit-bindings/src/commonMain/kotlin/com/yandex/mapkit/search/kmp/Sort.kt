@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Sort type as returned in response.
 */
expect enum class SortType {
    /**
     * Results are ordered by rank (or "goodness").
     */
    RANK,
    /**
     * Results are ordered by distance from some origin.
     */
    DISTANCE,
}

/**
 * Sort origin types when results are ordered by distance (see
 * [SortType]).
 */
expect enum class SortOrigin {
    /**
     * Sort origin is a user position. User position can be set via {link
     * SearchOptions}.
     */
    USER,
    /**
     * Sort origin is a toponym extracted from the user query. For example
     * this type can be set for queries like "cafe near Central Park".
     */
    QUERY,
    /**
     * Sort origin is directly specified by client. This type can be set for
     * sessions with {link search.Session#setSortByDistance(const
     * mapkit.geometry.Geometry)} called.
     */
    REQUEST,
}

/**
 * Describes response sort.
 */
expect class Sort

/**
 * Sorting type.
 */
expect val Sort.mpType: com.yandex.mapkit.search.kmp.SortType
/**
 * Sort origin (if results are ordered by distance).
 *
 */
expect val Sort.mpOrigin: com.yandex.mapkit.search.kmp.SortOrigin?

expect object SortFactory {
    fun create(
        type: com.yandex.mapkit.search.kmp.SortType,
        origin: com.yandex.mapkit.search.kmp.SortOrigin?,
    ): Sort
}

