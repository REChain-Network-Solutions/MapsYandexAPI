@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

import com.yandex.runtime.kmp.internal.toInt
import com.yandex.runtime.kmp.internal.toNSNumber

actual interface Cluster {
    val impl: platform.YandexMapsMobile.YMKCluster

    /**
     * Adds a tap listener for cluster.
     *
     * The class does not retain the object in the 'clusterTapListener' parameter.
     * It is your responsibility to maintain a strong reference to
     * the target object while it is attached to a class.
     */
    actual fun addClusterTapListener(
        clusterTapListener: com.yandex.mapkit.kmp.map.ClusterTapListener,
    ): Unit

    /**
     * Removes tap listener.
     */
    actual fun removeClusterTapListener(
        clusterTapListener: com.yandex.mapkit.kmp.map.ClusterTapListener,
    ): Unit

    /**
     * Tells if this object is valid or not. Any method called on an invalid
     * object will throw an exception. The object becomes invalid only on UI
     * thread, and only when its implementation depends on objects already
     * destroyed by now. Please refer to general docs about the interface for
     * details on its invalidation.
     */
    actual fun isValid(): Boolean
}
/**
 * A list of placemarks the cluster contains.
 */
actual val Cluster.placemarks: kotlin.collections.List<com.yandex.mapkit.kmp.map.PlacemarkMapObject>
    get() = impl.placemarks.let { it as kotlin.collections.List<platform.YandexMapsMobile.YMKPlacemarkMapObject> }.map { it.let { com.yandex.mapkit.kmp.map.YMKPlacemarkMapObjectWrapper(it) } }
/**
 * Cluster size.
 */
actual val Cluster.size: Int
    get() = impl.size.toInt()
/**
 * Cluster representation displayed on the map.
 */
actual val Cluster.appearance: com.yandex.mapkit.kmp.map.PlacemarkMapObject
    get() = impl.appearance.let { com.yandex.mapkit.kmp.map.YMKPlacemarkMapObjectWrapper(it) }

open class YMKClusterWrapper(override val impl: platform.YandexMapsMobile.YMKCluster) : Cluster {
    override fun addClusterTapListener(
        clusterTapListener: com.yandex.mapkit.kmp.map.ClusterTapListener,
    ): Unit {
        return impl.addClusterTapListenerWithClusterTapListener(
            clusterTapListener.let { com.yandex.mapkit.kmp.map.ClusterTapListenerWrapper(it) },
        )
    }

    override fun removeClusterTapListener(
        clusterTapListener: com.yandex.mapkit.kmp.map.ClusterTapListener,
    ): Unit {
        return impl.removeClusterTapListenerWithClusterTapListener(
            clusterTapListener.let { com.yandex.mapkit.kmp.map.ClusterTapListenerWrapper(it) },
        )
    }

    override fun isValid(): Boolean = impl.isValid()
}

