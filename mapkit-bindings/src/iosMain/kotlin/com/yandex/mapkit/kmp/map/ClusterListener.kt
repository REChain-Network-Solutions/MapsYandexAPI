@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

import kotlinx.cinterop.objcPtr

actual interface ClusterListener {
    /**
     * This method is called when a new cluster is added to the collection
     * and should customize cluster appearance based on its content.
     */
    actual fun onClusterAdded(
        cluster: com.yandex.mapkit.kmp.map.Cluster,
    ): Unit
}

class ClusterListenerWrapper internal constructor(impl: ClusterListener, @Suppress("UNUSED_PARAMETER") tag: Unit) : platform.darwin.NSObject(), platform.YandexMapsMobile.YMKClusterListenerProtocol {
    private val impl = kotlin.native.ref.WeakReference(impl)

    override fun onClusterAddedWithCluster(
        cluster: platform.YandexMapsMobile.YMKCluster,
    ): Unit {
        return impl.get()?.onClusterAdded(
            cluster.let { com.yandex.mapkit.kmp.map.YMKClusterWrapper(it) },
        ) ?: Unit
    }

    internal companion object
}

fun ClusterListenerWrapper(impl: ClusterListener): ClusterListenerWrapper {
    val pointerToCompanionObject = kotlinx.cinterop.interpretCPointer<kotlinx.cinterop.COpaque>(ClusterListenerWrapper.Companion.objcPtr())
    val value = kotlinx.cinterop.autoreleasepool {
        platform.objc.objc_getAssociatedObject(impl, pointerToCompanionObject)
    }

    if (value != null) {
        return value as ClusterListenerWrapper
    }

    val result = ClusterListenerWrapper(impl, Unit)
    kotlinx.cinterop.autoreleasepool {
        platform.objc.objc_setAssociatedObject(impl, pointerToCompanionObject, result, platform.objc.OBJC_ASSOCIATION_RETAIN)
    }
    return result
}

open class YMKClusterListenerProtocolWrapper(val impl: platform.YandexMapsMobile.YMKClusterListenerProtocol) : ClusterListener {
    override fun onClusterAdded(
        cluster: com.yandex.mapkit.kmp.map.Cluster,
    ): Unit {
        return impl.onClusterAddedWithCluster(
            cluster.impl,
        )
    }
}

