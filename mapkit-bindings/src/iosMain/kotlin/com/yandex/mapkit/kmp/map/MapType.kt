@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST", "REDUNDANT_ELSE_IN_WHEN")

package com.yandex.mapkit.kmp.map

/**
 * The type of map displayed.
 */
actual enum class MapType {
    /**
     * Do not use any of the predefined maps.
     */
    NONE,
    /**
     * Raster map.
     */
    MAP,
    /**
     * Allowed only for Yandex apps Default satellite map.
     */
    SATELLITE,
    /**
     * Allowed only for Yandex apps Satellite map with roads, placemarks and
     * labels.
     */
    HYBRID,
    /**
     * Vector map.
     */
    VECTOR_MAP,;

    companion object {
        fun toKmp(v: platform.Foundation.NSNumber): MapType {
            return toKmp(platform.YandexMapsMobile.YMKMapType.byValue(v.unsignedLongValue))
        }

        fun toKmp(v: platform.YandexMapsMobile.YMKMapType): MapType {
            return when (v) {
                platform.YandexMapsMobile.YMKMapType.YMKMapTypeNone -> MapType.NONE
                platform.YandexMapsMobile.YMKMapType.YMKMapTypeMap -> MapType.MAP
                platform.YandexMapsMobile.YMKMapType.YMKMapTypeSatellite -> MapType.SATELLITE
                platform.YandexMapsMobile.YMKMapType.YMKMapTypeHybrid -> MapType.HYBRID
                platform.YandexMapsMobile.YMKMapType.YMKMapTypeVectorMap -> MapType.VECTOR_MAP
                else -> error("unknown YMKMapType")
            }
        }
    }
}

fun MapType.fromKmp(): platform.YandexMapsMobile.YMKMapType {
    return when (this) {
        MapType.NONE -> platform.YandexMapsMobile.YMKMapType.YMKMapTypeNone
        MapType.MAP -> platform.YandexMapsMobile.YMKMapType.YMKMapTypeMap
        MapType.SATELLITE -> platform.YandexMapsMobile.YMKMapType.YMKMapTypeSatellite
        MapType.HYBRID -> platform.YandexMapsMobile.YMKMapType.YMKMapTypeHybrid
        MapType.VECTOR_MAP -> platform.YandexMapsMobile.YMKMapType.YMKMapTypeVectorMap
    }
}

