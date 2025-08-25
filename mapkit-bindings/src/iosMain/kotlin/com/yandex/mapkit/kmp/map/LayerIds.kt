@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

actual object LayerIds {
    actual val mapLayerId: String
        get() = platform.YandexMapsMobile.YMKLayerIds.mapLayerId()
    /**
     * Deprecated. There is no separate jams layer anymore.
     */
    actual val jamsLayerId: String
        get() = platform.YandexMapsMobile.YMKLayerIds.jamsLayerId()
    /**
     * Deprecated. There is no separate ppoi layer anymore.
     */
    actual val personalizedPoiLayerId: String
        get() = platform.YandexMapsMobile.YMKLayerIds.personalizedPoiLayerId()
    actual val transportLayerId: String
        get() = platform.YandexMapsMobile.YMKLayerIds.transportLayerId()
    actual val searchPinsLayerId: String
        get() = platform.YandexMapsMobile.YMKLayerIds.searchPinsLayerId()
    actual val advertPinsLayerId: String
        get() = platform.YandexMapsMobile.YMKLayerIds.advertPinsLayerId()
    actual val buildingsLayerId: String
        get() = platform.YandexMapsMobile.YMKLayerIds.buildingsLayerId()
    actual val mapObjectsLayerId: String
        get() = platform.YandexMapsMobile.YMKLayerIds.mapObjectsLayerId()
    actual val routeMapObjectsLayerId: String
        get() = platform.YandexMapsMobile.YMKLayerIds.routeMapObjectsLayerId()
    actual val userLocationLayerId: String
        get() = platform.YandexMapsMobile.YMKLayerIds.userLocationLayerId()
    actual val drivingNavigationBaseLayerId: String
        get() = platform.YandexMapsMobile.YMKLayerIds.drivingNavigationBaseLayerId()
    actual val drivingNavigationRoutePinsLayerId: String
        get() = platform.YandexMapsMobile.YMKLayerIds.drivingNavigationRoutePinsLayerId()
    actual val drivingNavigationBalloonsLayerId: String
        get() = platform.YandexMapsMobile.YMKLayerIds.drivingNavigationBalloonsLayerId()
    actual val drivingNavigationUserPlacemarkLayerId: String
        get() = platform.YandexMapsMobile.YMKLayerIds.drivingNavigationUserPlacemarkLayerId()
    actual val roadEventsLayerId: String
        get() = platform.YandexMapsMobile.YMKLayerIds.roadEventsLayerId()
}

