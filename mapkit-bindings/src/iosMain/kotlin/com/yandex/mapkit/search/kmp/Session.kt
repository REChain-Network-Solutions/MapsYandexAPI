@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

import com.yandex.runtime.kmp.internal.fromKmp
import com.yandex.runtime.kmp.internal.toBoolean
import com.yandex.runtime.kmp.internal.toKmp
import com.yandex.runtime.kmp.internal.toNSNumber
import kotlinx.cinterop.objcPtr

/**
 * Interface denoting ongoing search session. Allows search cancellation
 * and retry.  For many request types allows further searches.
 */
actual interface Session {
    val impl: platform.YandexMapsMobile.YMKSearchSession

    /**
     * Cancels the current request.
     */
    actual fun cancel(): Unit

    /**
     * Retries the last request. If there is an active request, it is
     * cancelled.
     *
     * @param searchListener Listener to handle search result.
     */
    actual fun retry(
        searchListener: com.yandex.mapkit.search.kmp.SessionSearchListener,
    ): Unit

    /**
     * Check the availability of the next result page.
     * @return True if there are more search results and one can call
     * [Session.fetchNextPage], false otherwise.
     */
    actual fun hasNextPage(): Boolean

    /**
     * Request the next page of search results. Ignored if the current
     * request isn't ready. Will throw if called when {@link
     * search.Session#hasNextPage()} is false.
     *
     * @param searchListener Listener to handle search result.
     */
    actual fun fetchNextPage(
        searchListener: com.yandex.mapkit.search.kmp.SessionSearchListener,
    ): Unit

    @Deprecated("Use {@link SearchOptions#filters} instead.")
    actual fun setFilters(
        filters: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessFilter>,
    ): Unit

    /**
     * Requests sorting by distance for future resubmits. Supported geometry
     * types: point, polyline.
     *
     * @param origin Origin to sort by distance from.
     */
    actual fun setSortByDistance(
        origin: com.yandex.mapkit.kmp.geometry.Geometry,
    ): Unit

    /**
     * Resets the sort if it was previously set (for example by
     * [Session.setSortByDistance]) for future resubmits.
     */
    actual fun resetSort(): Unit

    /**
     * Sets the search area for future resubmits. Supported geometry types:
     * bounding box, polyline, polygon. Polygon is expected to be a search
     * window: 4 points in outer ring (or 5 if last point is equal to first)
     * and no inner rings.
     *
     * @param area Search area for future resubmits.
     */
    actual fun setSearchArea(
        area: com.yandex.mapkit.kmp.geometry.Geometry,
    ): Unit

    /**
     * Set searchOptions for future resubmits.
     *
     * @param searchOptions Additional search parameters, see
     * [SearchOptions]. Supported options: [SearchOptions.origin],
     * [SearchOptions.userPosition].
     */
    actual fun setSearchOptions(
        searchOptions: com.yandex.mapkit.search.kmp.SearchOptions,
    ): Unit

    /**
     * Redo the last search with currently set values of search area, search
     * options, filters, sort type and sort origin. Isn't applicable to
     * reverse geosearch and URI resolving. Ignored it the current request
     * is the first one; cancels current request otherwise.
     *
     * @param searchListener Listener to handle search result.
     */
    actual fun resubmit(
        searchListener: com.yandex.mapkit.search.kmp.SessionSearchListener,
    ): Unit
}

open class YMKSearchSessionWrapper(override val impl: platform.YandexMapsMobile.YMKSearchSession) : Session {
    override fun cancel(): Unit {
        return impl.cancel()
    }

    override fun retry(
        searchListener: com.yandex.mapkit.search.kmp.SessionSearchListener,
    ): Unit {
        return impl.retryWithResponseHandler(
            searchListener.let { com.yandex.mapkit.search.kmp.SessionSearchListenerWrapper(it) },
        )
    }

    override fun hasNextPage(): Boolean {
        return impl.hasNextPage()
    }

    override fun fetchNextPage(
        searchListener: com.yandex.mapkit.search.kmp.SessionSearchListener,
    ): Unit {
        return impl.fetchNextPageWithResponseHandler(
            searchListener.let { com.yandex.mapkit.search.kmp.SessionSearchListenerWrapper(it) },
        )
    }

    override fun setFilters(
        filters: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessFilter>,
    ): Unit {
        return impl.setFiltersWithFilters(
            filters.let { it as kotlin.collections.List<*> },
        )
    }

    override fun setSortByDistance(
        origin: com.yandex.mapkit.kmp.geometry.Geometry,
    ): Unit {
        return impl.setSortByDistanceWithOrigin(
            origin,
        )
    }

    override fun resetSort(): Unit {
        return impl.resetSort()
    }

    override fun setSearchArea(
        area: com.yandex.mapkit.kmp.geometry.Geometry,
    ): Unit {
        return impl.setSearchAreaWithArea(
            area,
        )
    }

    override fun setSearchOptions(
        searchOptions: com.yandex.mapkit.search.kmp.SearchOptions,
    ): Unit {
        return impl.setSearchOptionsWithSearchOptions(
            searchOptions,
        )
    }

    override fun resubmit(
        searchListener: com.yandex.mapkit.search.kmp.SessionSearchListener,
    ): Unit {
        return impl.resubmitWithResponseHandler(
            searchListener.let { com.yandex.mapkit.search.kmp.SessionSearchListenerWrapper(it) },
        )
    }
}

actual interface SessionSearchListener {
    actual fun onSearchResponse(
        response: com.yandex.mapkit.search.kmp.Response,
    ): Unit
    actual fun onSearchError(
        error: com.yandex.runtime.kmp.Error,
    ): Unit
}

internal class SessionSearchListenerWrapper(val impl: SessionSearchListener): platform.YandexMapsMobile.YMKSearchSessionResponseHandler {
    override fun invoke(
        response: platform.YandexMapsMobile.YMKSearchResponse?,
        error: platform.Foundation.NSError?,
    ) {
        if (error == null) {
            impl.onSearchResponse(
                response!!,
            )
        } else {
            impl.onSearchError(error.toKmp())
        }
    }
}

