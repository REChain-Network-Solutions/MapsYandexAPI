@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Sort type as returned in response.
 */
actual typealias SortType = com.yandex.mapkit.search.SortType

/**
 * Sort origin types when results are ordered by distance (see
 * [SortType]).
 */
actual typealias SortOrigin = com.yandex.mapkit.search.SortOrigin

/**
 * Describes response sort.
 */
actual typealias Sort = com.yandex.mapkit.search.Sort

/**
 * Sorting type.
 */
actual val Sort.mpType: com.yandex.mapkit.search.kmp.SortType
    get() = type
/**
 * Sort origin (if results are ordered by distance).
 *
 */
actual val Sort.mpOrigin: com.yandex.mapkit.search.kmp.SortOrigin?
    get() = origin

actual object SortFactory {
    actual fun create(
        type: com.yandex.mapkit.search.kmp.SortType,
        origin: com.yandex.mapkit.search.kmp.SortOrigin?,
    ): Sort {
        return Sort(
            type,
            origin,
        )
    }
}

