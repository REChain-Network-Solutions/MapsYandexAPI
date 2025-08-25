@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

expect interface Cluster {
    /**
     * Adds a tap listener for cluster.
     *
     * The class does not retain the object in the 'clusterTapListener' parameter.
     * It is your responsibility to maintain a strong reference to
     * the target object while it is attached to a class.
     */
    fun addClusterTapListener(
        clusterTapListener: com.yandex.mapkit.kmp.map.ClusterTapListener,
    ): Unit

    /**
     * Removes tap listener.
     */
    fun removeClusterTapListener(
        clusterTapListener: com.yandex.mapkit.kmp.map.ClusterTapListener,
    ): Unit

    /**
     * Tells if this object is valid or not. Any method called on an invalid
     * object will throw an exception. The object becomes invalid only on UI
     * thread, and only when its implementation depends on objects already
     * destroyed by now. Please refer to general docs about the interface for
     * details on its invalidation.
     */
    fun isValid(): Boolean
}

/**
 * A list of placemarks the cluster contains.
 */
expect val Cluster.placemarks: kotlin.collections.List<com.yandex.mapkit.kmp.map.PlacemarkMapObject>
/**
 * Cluster size.
 */
expect val Cluster.size: Int
/**
 * Cluster representation displayed on the map.
 */
expect val Cluster.appearance: com.yandex.mapkit.kmp.map.PlacemarkMapObject

