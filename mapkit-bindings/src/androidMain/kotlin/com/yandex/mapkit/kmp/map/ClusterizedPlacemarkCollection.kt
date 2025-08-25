@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * A collection that displays large groups of placemarks efficiently.
 * Placemarks that are too close to each other with current zoom are
 * rendered as a single cluster. Placemarks and clusters are not shown
 * until clusterPlacemarks method is called explicitly.
 */
actual typealias ClusterizedPlacemarkCollection = com.yandex.mapkit.map.ClusterizedPlacemarkCollection

