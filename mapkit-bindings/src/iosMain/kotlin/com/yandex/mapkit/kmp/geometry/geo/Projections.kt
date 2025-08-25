@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.geometry.geo

actual object Projections {
    actual val wgs84Mercator: com.yandex.mapkit.kmp.geometry.geo.Projection
        get() = platform.YandexMapsMobile.YMKProjections.wgs84Mercator().let { com.yandex.mapkit.kmp.geometry.geo.YMKProjectionWrapper(it) }
    actual val sphericalMercator: com.yandex.mapkit.kmp.geometry.geo.Projection
        get() = platform.YandexMapsMobile.YMKProjections.sphericalMercator().let { com.yandex.mapkit.kmp.geometry.geo.YMKProjectionWrapper(it) }
}

