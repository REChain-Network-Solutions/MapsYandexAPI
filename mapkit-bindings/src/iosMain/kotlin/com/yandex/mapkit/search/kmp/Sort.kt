@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST", "REDUNDANT_ELSE_IN_WHEN")

package com.yandex.mapkit.search.kmp

/**
 * Sort type as returned in response.
 */
actual enum class SortType {
    /**
     * Results are ordered by rank (or "goodness").
     */
    RANK,
    /**
     * Results are ordered by distance from some origin.
     */
    DISTANCE,;

    companion object {
        fun toKmp(v: platform.Foundation.NSNumber): SortType {
            return toKmp(platform.YandexMapsMobile.YMKSearchSortType.byValue(v.unsignedLongValue))
        }

        fun toKmp(v: platform.YandexMapsMobile.YMKSearchSortType): SortType {
            return when (v) {
                platform.YandexMapsMobile.YMKSearchSortType.YMKSearchSortTypeRank -> SortType.RANK
                platform.YandexMapsMobile.YMKSearchSortType.YMKSearchSortTypeDistance -> SortType.DISTANCE
                else -> error("unknown YMKSearchSortType")
            }
        }
    }
}

fun SortType.fromKmp(): platform.YandexMapsMobile.YMKSearchSortType {
    return when (this) {
        SortType.RANK -> platform.YandexMapsMobile.YMKSearchSortType.YMKSearchSortTypeRank
        SortType.DISTANCE -> platform.YandexMapsMobile.YMKSearchSortType.YMKSearchSortTypeDistance
    }
}

/**
 * Sort origin types when results are ordered by distance (see
 * [SortType]).
 */
actual enum class SortOrigin {
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
    REQUEST,;

    companion object {
        fun toKmp(v: platform.Foundation.NSNumber): SortOrigin {
            return toKmp(platform.YandexMapsMobile.YMKSearchSortOrigin.byValue(v.unsignedLongValue))
        }

        fun toKmp(v: platform.YandexMapsMobile.YMKSearchSortOrigin): SortOrigin {
            return when (v) {
                platform.YandexMapsMobile.YMKSearchSortOrigin.YMKSearchSortOriginUser -> SortOrigin.USER
                platform.YandexMapsMobile.YMKSearchSortOrigin.YMKSearchSortOriginQuery -> SortOrigin.QUERY
                platform.YandexMapsMobile.YMKSearchSortOrigin.YMKSearchSortOriginRequest -> SortOrigin.REQUEST
                else -> error("unknown YMKSearchSortOrigin")
            }
        }
    }
}

fun SortOrigin.fromKmp(): platform.YandexMapsMobile.YMKSearchSortOrigin {
    return when (this) {
        SortOrigin.USER -> platform.YandexMapsMobile.YMKSearchSortOrigin.YMKSearchSortOriginUser
        SortOrigin.QUERY -> platform.YandexMapsMobile.YMKSearchSortOrigin.YMKSearchSortOriginQuery
        SortOrigin.REQUEST -> platform.YandexMapsMobile.YMKSearchSortOrigin.YMKSearchSortOriginRequest
    }
}

/**
 * Describes response sort.
 */
actual typealias Sort = platform.YandexMapsMobile.YMKSearchSort

/**
 * Sorting type.
 */
actual val Sort.mpType: com.yandex.mapkit.search.kmp.SortType
    get() = type().let { com.yandex.mapkit.search.kmp.SortType.toKmp(it) }
/**
 * Sort origin (if results are ordered by distance).
 *
 */
actual val Sort.mpOrigin: com.yandex.mapkit.search.kmp.SortOrigin?
    get() = origin()?.let { com.yandex.mapkit.search.kmp.SortOrigin.toKmp(it) }

actual object SortFactory {
    actual fun create(
        type: com.yandex.mapkit.search.kmp.SortType,
        origin: com.yandex.mapkit.search.kmp.SortOrigin?,
    ): Sort {
        return Sort.sortWithType(
            type.fromKmp(),
            origin?.let { platform.Foundation.NSNumber(unsignedLong = it.fromKmp().value) },
        )
    }
}

