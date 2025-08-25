@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

actual typealias Cluster = com.yandex.mapkit.map.Cluster

/**
 * A list of placemarks the cluster contains.
 */
actual val Cluster.placemarks: kotlin.collections.List<com.yandex.mapkit.kmp.map.PlacemarkMapObject>
    get() = placemarks
/**
 * Cluster size.
 */
actual val Cluster.size: Int
    get() = size
/**
 * Cluster representation displayed on the map.
 */
actual val Cluster.appearance: com.yandex.mapkit.kmp.map.PlacemarkMapObject
    get() = appearance

