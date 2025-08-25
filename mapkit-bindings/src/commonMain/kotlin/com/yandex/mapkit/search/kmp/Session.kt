@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Interface denoting ongoing search session. Allows search cancellation
 * and retry.  For many request types allows further searches.
 */
expect interface Session {
    /**
     * Cancels the current request.
     */
    fun cancel(): Unit

    /**
     * Retries the last request. If there is an active request, it is
     * cancelled.
     *
     * @param searchListener Listener to handle search result.
     */
    fun retry(
        searchListener: com.yandex.mapkit.search.kmp.SessionSearchListener,
    ): Unit

    /**
     * Check the availability of the next result page.
     * @return True if there are more search results and one can call
     * [Session.fetchNextPage], false otherwise.
     */
    fun hasNextPage(): Boolean

    /**
     * Request the next page of search results. Ignored if the current
     * request isn't ready. Will throw if called when {@link
     * search.Session#hasNextPage()} is false.
     *
     * @param searchListener Listener to handle search result.
     */
    fun fetchNextPage(
        searchListener: com.yandex.mapkit.search.kmp.SessionSearchListener,
    ): Unit

    @Deprecated("Use {@link SearchOptions#filters} instead.")
    fun setFilters(
        filters: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessFilter>,
    ): Unit

    /**
     * Requests sorting by distance for future resubmits. Supported geometry
     * types: point, polyline.
     *
     * @param origin Origin to sort by distance from.
     */
    fun setSortByDistance(
        origin: com.yandex.mapkit.kmp.geometry.Geometry,
    ): Unit

    /**
     * Resets the sort if it was previously set (for example by
     * [Session.setSortByDistance]) for future resubmits.
     */
    fun resetSort(): Unit

    /**
     * Sets the search area for future resubmits. Supported geometry types:
     * bounding box, polyline, polygon. Polygon is expected to be a search
     * window: 4 points in outer ring (or 5 if last point is equal to first)
     * and no inner rings.
     *
     * @param area Search area for future resubmits.
     */
    fun setSearchArea(
        area: com.yandex.mapkit.kmp.geometry.Geometry,
    ): Unit

    /**
     * Set searchOptions for future resubmits.
     *
     * @param searchOptions Additional search parameters, see
     * [SearchOptions]. Supported options: [SearchOptions.origin],
     * [SearchOptions.userPosition].
     */
    fun setSearchOptions(
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
    fun resubmit(
        searchListener: com.yandex.mapkit.search.kmp.SessionSearchListener,
    ): Unit
}

expect interface SessionSearchListener {
    /**
     * Callback for results processing.
     *
     * @param response Response with search result.
     */
    fun onSearchResponse(
        response: com.yandex.mapkit.search.kmp.Response,
    ): Unit

    /**
     * Callback for error processing.
     *
     * @param error Error information.
     */
    fun onSearchError(
        error: com.yandex.runtime.kmp.Error,
    ): Unit
}

