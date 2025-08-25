@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.geometry.geo

/**
 * Makes two conversions: world->XY and XY->world, where XY are tile
 * indexes. There are two main derived classes: spherical mercator
 * (google, osm) and wgs84 mercator (yandex).
 */
actual typealias Projection = com.yandex.mapkit.geometry.geo.Projection

