@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST", "REDUNDANT_ELSE_IN_WHEN")

package com.yandex.mapkit.kmp.map

/**
 * Supported map style modes
 */
actual enum class MapMode {
    /**
     * Basic map
     */
    MAP,
    /**
     * Public transport map
     */
    TRANSIT,
    /**
     * Automobile navigation map
     */
    DRIVING,
    /**
     * Administrative map
     */
    ADMIN,
    /**
     * Legacy basic map design, can be used to preserve compatibility with
     * app design/legacy map customizaitons
     */
    LEGACY_MAP,
    /**
     * Upcoming basic map design
     */
    FUTURE_MAP,;

    companion object {
        fun toKmp(v: platform.Foundation.NSNumber): MapMode {
            return toKmp(platform.YandexMapsMobile.YMKMapMode.byValue(v.unsignedLongValue))
        }

        fun toKmp(v: platform.YandexMapsMobile.YMKMapMode): MapMode {
            return when (v) {
                platform.YandexMapsMobile.YMKMapMode.YMKMapModeMap -> MapMode.MAP
                platform.YandexMapsMobile.YMKMapMode.YMKMapModeTransit -> MapMode.TRANSIT
                platform.YandexMapsMobile.YMKMapMode.YMKMapModeDriving -> MapMode.DRIVING
                platform.YandexMapsMobile.YMKMapMode.YMKMapModeAdmin -> MapMode.ADMIN
                platform.YandexMapsMobile.YMKMapMode.YMKMapModeLegacyMap -> MapMode.LEGACY_MAP
                platform.YandexMapsMobile.YMKMapMode.YMKMapModeFutureMap -> MapMode.FUTURE_MAP
                else -> error("unknown YMKMapMode")
            }
        }
    }
}

fun MapMode.fromKmp(): platform.YandexMapsMobile.YMKMapMode {
    return when (this) {
        MapMode.MAP -> platform.YandexMapsMobile.YMKMapMode.YMKMapModeMap
        MapMode.TRANSIT -> platform.YandexMapsMobile.YMKMapMode.YMKMapModeTransit
        MapMode.DRIVING -> platform.YandexMapsMobile.YMKMapMode.YMKMapModeDriving
        MapMode.ADMIN -> platform.YandexMapsMobile.YMKMapMode.YMKMapModeAdmin
        MapMode.LEGACY_MAP -> platform.YandexMapsMobile.YMKMapMode.YMKMapModeLegacyMap
        MapMode.FUTURE_MAP -> platform.YandexMapsMobile.YMKMapMode.YMKMapModeFutureMap
    }
}

