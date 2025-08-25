@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

actual object LayerIds {
    actual val mapLayerId: String
        get() = com.yandex.mapkit.map.LayerIds.getMapLayerId()
    /**
     * Deprecated. There is no separate jams layer anymore.
     */
    actual val jamsLayerId: String
        get() = com.yandex.mapkit.map.LayerIds.getJamsLayerId()
    /**
     * Deprecated. There is no separate ppoi layer anymore.
     */
    actual val personalizedPoiLayerId: String
        get() = com.yandex.mapkit.map.LayerIds.getPersonalizedPoiLayerId()
    actual val transportLayerId: String
        get() = com.yandex.mapkit.map.LayerIds.getTransportLayerId()
    actual val searchPinsLayerId: String
        get() = com.yandex.mapkit.map.LayerIds.getSearchPinsLayerId()
    actual val advertPinsLayerId: String
        get() = com.yandex.mapkit.map.LayerIds.getAdvertPinsLayerId()
    actual val buildingsLayerId: String
        get() = com.yandex.mapkit.map.LayerIds.getBuildingsLayerId()
    actual val mapObjectsLayerId: String
        get() = com.yandex.mapkit.map.LayerIds.getMapObjectsLayerId()
    actual val routeMapObjectsLayerId: String
        get() = com.yandex.mapkit.map.LayerIds.getRouteMapObjectsLayerId()
    actual val userLocationLayerId: String
        get() = com.yandex.mapkit.map.LayerIds.getUserLocationLayerId()
    actual val drivingNavigationBaseLayerId: String
        get() = com.yandex.mapkit.map.LayerIds.getDrivingNavigationBaseLayerId()
    actual val drivingNavigationRoutePinsLayerId: String
        get() = com.yandex.mapkit.map.LayerIds.getDrivingNavigationRoutePinsLayerId()
    actual val drivingNavigationBalloonsLayerId: String
        get() = com.yandex.mapkit.map.LayerIds.getDrivingNavigationBalloonsLayerId()
    actual val drivingNavigationUserPlacemarkLayerId: String
        get() = com.yandex.mapkit.map.LayerIds.getDrivingNavigationUserPlacemarkLayerId()
    actual val roadEventsLayerId: String
        get() = com.yandex.mapkit.map.LayerIds.getRoadEventsLayerId()
}

