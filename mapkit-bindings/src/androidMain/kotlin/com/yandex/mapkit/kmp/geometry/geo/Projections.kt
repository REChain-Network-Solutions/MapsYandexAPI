@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.geometry.geo

actual object Projections {
    actual val wgs84Mercator: com.yandex.mapkit.kmp.geometry.geo.Projection
        get() = com.yandex.mapkit.geometry.geo.Projections.getWgs84Mercator()
    actual val sphericalMercator: com.yandex.mapkit.kmp.geometry.geo.Projection
        get() = com.yandex.mapkit.geometry.geo.Projections.getSphericalMercator()
}

