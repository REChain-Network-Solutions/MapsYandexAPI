@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST", "REDUNDANT_ELSE_IN_WHEN")

package com.yandex.mapkit.search.kmp

import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * Search manager type. Describes difference in online/offline search
 * handling.
 */
actual enum class SearchManagerType {
    /**
     * Online search manager. Always tries to use online search even if
     * network is not available.
     */
    ONLINE,
    /**
     * Offline search manager. Always tries to use offline search even if
     * network is available.
     * @attention This feature is not available in the free MapKit version.
     */
    OFFLINE,
    /**
     * Combined search manager. Decision to use online or offline search is
     * based on internal timeout. If server manages to respond within given
     * time, then online search result is returned. Otherwise uses offline
     * search.  Will combine online and offline search result in single
     * session (hence the name). Timeout logic is applied on each resubmit
     * until first response from offline search is returned to the listener.
     * After that timeout is reduced to zero for all following resubmits.
     * @attention This feature is not available in the free MapKit version.
     */
    COMBINED,;

    companion object {
        fun toKmp(v: platform.Foundation.NSNumber): SearchManagerType {
            return toKmp(platform.YandexMapsMobile.YMKSearchManagerType.byValue(v.unsignedLongValue))
        }

        fun toKmp(v: platform.YandexMapsMobile.YMKSearchManagerType): SearchManagerType {
            return when (v) {
                platform.YandexMapsMobile.YMKSearchManagerType.YMKSearchManagerTypeOnline -> SearchManagerType.ONLINE
                platform.YandexMapsMobile.YMKSearchManagerType.YMKSearchManagerTypeOffline -> SearchManagerType.OFFLINE
                platform.YandexMapsMobile.YMKSearchManagerType.YMKSearchManagerTypeCombined -> SearchManagerType.COMBINED
                else -> error("unknown SearchManagerType")
            }
        }
    }
}

fun SearchManagerType.fromKmp(): platform.YandexMapsMobile.YMKSearchManagerType {
    return when (this) {
        SearchManagerType.ONLINE -> platform.YandexMapsMobile.YMKSearchManagerType.YMKSearchManagerTypeOnline
        SearchManagerType.OFFLINE -> platform.YandexMapsMobile.YMKSearchManagerType.YMKSearchManagerTypeOffline
        SearchManagerType.COMBINED -> platform.YandexMapsMobile.YMKSearchManagerType.YMKSearchManagerTypeCombined
    }
}

/**
 * Main interface to start search.
 */
actual interface SearchManager {
    val impl: platform.YandexMapsMobile.YMKSearchManager

    /**
     * Search request for searching a user query near given geometry.
     *
     * @param text User query.
     * @param geometry Geometry to search near. Supported types: point,
     * bounding box, polyline and polygon. If the polyline is provided,
     * setSortByDistance(polyline) is assumed on the first request. Polygon
     * is expected to be a search window: 4 points in outer ring (or 5 if
     * the last point is equal to the first) and no inner rings.
     * @param searchOptions Various additional search parameters, see
     * [SearchOptions] definition for details.
     * @param searchListener Listener to handle search result.
     *
     * @return [Session] which allows further searches, cancel and retry.
     */
    actual fun submit(
        text: String,
        geometry: com.yandex.mapkit.kmp.geometry.Geometry,
        searchOptions: com.yandex.mapkit.search.kmp.SearchOptions,
        searchListener: com.yandex.mapkit.search.kmp.SessionSearchListener,
    ): com.yandex.mapkit.search.kmp.Session

    /**
     * Search request that is used to search for a user query along the
     * given polyline inside the given window.
     *
     * @param text User query.
     * @param polyline Polyline to search near; {@link
     * search.Session#setSortByDistance(const mapkit.geometry.Geometry)} is
     * assumed on the first request.
     * @param geometry Geometry to search near; supported types: point,
     * bounding box, polyline and polygon. Polygon is expected to be a
     * search window: 4 points in outer ring (or 5 if the last point is
     * equal to first) and no inner rings.
     * @param searchOptions Various additional search parameters, see
     * [SearchOptions] definition for details.
     * @param searchListener Listener to handle search result.
     *
     * @return [Session] which allows further searches, cancel and retry.
     * Session should be stored by user or search is automatically
     * cancelled.
     */
    actual fun submit(
        text: String,
        polyline: com.yandex.mapkit.kmp.geometry.Polyline,
        geometry: com.yandex.mapkit.kmp.geometry.Geometry,
        searchOptions: com.yandex.mapkit.search.kmp.SearchOptions,
        searchListener: com.yandex.mapkit.search.kmp.SessionSearchListener,
    ): com.yandex.mapkit.search.kmp.Session

    /**
     * Reverse search request (to search objects at the given coordinates)
     *
     * @param point Coordinates to search at.
     * @param zoom Current zoom level. Skips objects that are too small for
     * a given zoom level.
     * @param searchOptions Additional search parameters, see
     * [SearchOptions] definition for details. Currently the only supported
     * options are [SearchOptions.origin], [SearchOptions.searchTypes] and
     * [SearchOptions.snippets]. Only 'geo' and 'biz' types are supported
     * and not at the same time.
     * @param searchListener Listener to handle search result.
     *
     * @return [Session] which allows further searches, cancel and retry.
     * Session should be stored by user or search is automatically
     * cancelled.
     */
    actual fun submit(
        point: com.yandex.mapkit.kmp.geometry.Point,
        zoom: Int?,
        searchOptions: com.yandex.mapkit.search.kmp.SearchOptions,
        searchListener: com.yandex.mapkit.search.kmp.SessionSearchListener,
    ): com.yandex.mapkit.search.kmp.Session

    /**
     * Search request for URI resolution.
     *
     * @param uri Object uri.
     * @param searchOptions Additional search parameters, see
     * [SearchOptions] definition for details. Currently the only supported
     * options are [SearchOptions.origin] and [SearchOptions.snippets].
     * @param searchListener Listener to handle search result.
     *
     * @return [Session] which allows search cancel and retry. Should be
     * stored by user or search is automatically cancelled.
     */
    actual fun resolveURI(
        uri: String,
        searchOptions: com.yandex.mapkit.search.kmp.SearchOptions,
        searchListener: com.yandex.mapkit.search.kmp.SessionSearchListener,
    ): com.yandex.mapkit.search.kmp.Session

    /**
     * Search request with URI. Allows multiple results in response.
     *
     * @param uri Object uri.
     * @param searchOptions Additional search parameters, see
     * [SearchOptions] definition for details. Currently the only supported
     * options are [SearchOptions.origin], [SearchOptions.snippets] and.
     * [SearchOptions.resultPageSize].
     * @param searchListener Listener to handle search result.
     *
     * @return [Session] which allows search cancel and retry. Should be
     * stored by user or search is automatically cancelled.
     */
    actual fun searchByURI(
        uri: String,
        searchOptions: com.yandex.mapkit.search.kmp.SearchOptions,
        searchListener: com.yandex.mapkit.search.kmp.SessionSearchListener,
    ): com.yandex.mapkit.search.kmp.Session
}

open class YMKSearchManagerWrapper(override val impl: platform.YandexMapsMobile.YMKSearchManager) : SearchManager {
    override fun submit(
        text: String,
        geometry: com.yandex.mapkit.kmp.geometry.Geometry,
        searchOptions: com.yandex.mapkit.search.kmp.SearchOptions,
        searchListener: com.yandex.mapkit.search.kmp.SessionSearchListener,
    ): com.yandex.mapkit.search.kmp.Session {
        return impl.submitWithText(
            text,
            geometry,
            searchOptions,
            searchListener.let { com.yandex.mapkit.search.kmp.SessionSearchListenerWrapper(it) },
        ).let { com.yandex.mapkit.search.kmp.YMKSearchSessionWrapper(it) }
    }

    override fun submit(
        text: String,
        polyline: com.yandex.mapkit.kmp.geometry.Polyline,
        geometry: com.yandex.mapkit.kmp.geometry.Geometry,
        searchOptions: com.yandex.mapkit.search.kmp.SearchOptions,
        searchListener: com.yandex.mapkit.search.kmp.SessionSearchListener,
    ): com.yandex.mapkit.search.kmp.Session {
        return impl.submitWithText(
            text,
            polyline,
            geometry,
            searchOptions,
            searchListener.let { com.yandex.mapkit.search.kmp.SessionSearchListenerWrapper(it) },
        ).let { com.yandex.mapkit.search.kmp.YMKSearchSessionWrapper(it) }
    }

    override fun submit(
        point: com.yandex.mapkit.kmp.geometry.Point,
        zoom: Int?,
        searchOptions: com.yandex.mapkit.search.kmp.SearchOptions,
        searchListener: com.yandex.mapkit.search.kmp.SessionSearchListener,
    ): com.yandex.mapkit.search.kmp.Session {
        return impl.submitWithPoint(
            point,
            zoom?.toNSNumber(),
            searchOptions,
            searchListener.let { com.yandex.mapkit.search.kmp.SessionSearchListenerWrapper(it) },
        ).let { com.yandex.mapkit.search.kmp.YMKSearchSessionWrapper(it) }
    }

    override fun resolveURI(
        uri: String,
        searchOptions: com.yandex.mapkit.search.kmp.SearchOptions,
        searchListener: com.yandex.mapkit.search.kmp.SessionSearchListener,
    ): com.yandex.mapkit.search.kmp.Session {
        return impl.resolveURIWithUri(
            uri,
            searchOptions,
            searchListener.let { com.yandex.mapkit.search.kmp.SessionSearchListenerWrapper(it) },
        ).let { com.yandex.mapkit.search.kmp.YMKSearchSessionWrapper(it) }
    }

    override fun searchByURI(
        uri: String,
        searchOptions: com.yandex.mapkit.search.kmp.SearchOptions,
        searchListener: com.yandex.mapkit.search.kmp.SessionSearchListener,
    ): com.yandex.mapkit.search.kmp.Session {
        return impl.searchByURIWithUri(
            uri,
            searchOptions,
            searchListener.let { com.yandex.mapkit.search.kmp.SessionSearchListenerWrapper(it) },
        ).let { com.yandex.mapkit.search.kmp.YMKSearchSessionWrapper(it) }
    }
}

