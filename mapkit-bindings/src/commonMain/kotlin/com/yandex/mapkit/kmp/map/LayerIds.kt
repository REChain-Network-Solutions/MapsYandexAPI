@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

expect object LayerIds {
    val mapLayerId: String
    /**
     * Deprecated. There is no separate jams layer anymore.
     */
    val jamsLayerId: String
    /**
     * Deprecated. There is no separate ppoi layer anymore.
     */
    val personalizedPoiLayerId: String
    val transportLayerId: String
    val searchPinsLayerId: String
    val advertPinsLayerId: String
    val buildingsLayerId: String
    val mapObjectsLayerId: String
    val routeMapObjectsLayerId: String
    val userLocationLayerId: String
    val drivingNavigationBaseLayerId: String
    val drivingNavigationRoutePinsLayerId: String
    val drivingNavigationBalloonsLayerId: String
    val drivingNavigationUserPlacemarkLayerId: String
    val roadEventsLayerId: String
}

