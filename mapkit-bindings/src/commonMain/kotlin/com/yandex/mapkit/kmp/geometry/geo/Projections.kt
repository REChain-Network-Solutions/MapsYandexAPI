@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.geometry.geo

expect object Projections {
    val wgs84Mercator: com.yandex.mapkit.kmp.geometry.geo.Projection
    val sphericalMercator: com.yandex.mapkit.kmp.geometry.geo.Projection
}

