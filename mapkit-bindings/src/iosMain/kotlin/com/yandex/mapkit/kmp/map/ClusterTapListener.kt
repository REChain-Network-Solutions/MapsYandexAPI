@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

import com.yandex.runtime.kmp.internal.toBoolean
import com.yandex.runtime.kmp.internal.toNSNumber
import kotlinx.cinterop.objcPtr

actual interface ClusterTapListener {
    /**
     * Called when cluster is tapped. Return true if the event was handled.
     * Otherwise it will be passed to underlying objects.
     */
    actual fun onClusterTap(
        cluster: com.yandex.mapkit.kmp.map.Cluster,
    ): Boolean
}

class ClusterTapListenerWrapper internal constructor(impl: ClusterTapListener, @Suppress("UNUSED_PARAMETER") tag: Unit) : platform.darwin.NSObject(), platform.YandexMapsMobile.YMKClusterTapListenerProtocol {
    private val impl = kotlin.native.ref.WeakReference(impl)

    override fun onClusterTapWithCluster(
        cluster: platform.YandexMapsMobile.YMKCluster,
    ): Boolean {
        return impl.get()?.onClusterTap(
            cluster.let { com.yandex.mapkit.kmp.map.YMKClusterWrapper(it) },
        ) ?: false
    }

    internal companion object
}

fun ClusterTapListenerWrapper(impl: ClusterTapListener): ClusterTapListenerWrapper {
    val pointerToCompanionObject = kotlinx.cinterop.interpretCPointer<kotlinx.cinterop.COpaque>(ClusterTapListenerWrapper.Companion.objcPtr())
    val value = kotlinx.cinterop.autoreleasepool {
        platform.objc.objc_getAssociatedObject(impl, pointerToCompanionObject)
    }

    if (value != null) {
        return value as ClusterTapListenerWrapper
    }

    val result = ClusterTapListenerWrapper(impl, Unit)
    kotlinx.cinterop.autoreleasepool {
        platform.objc.objc_setAssociatedObject(impl, pointerToCompanionObject, result, platform.objc.OBJC_ASSOCIATION_RETAIN)
    }
    return result
}

open class YMKClusterTapListenerProtocolWrapper(val impl: platform.YandexMapsMobile.YMKClusterTapListenerProtocol) : ClusterTapListener {
    override fun onClusterTap(
        cluster: com.yandex.mapkit.kmp.map.Cluster,
    ): Boolean {
        return impl.onClusterTapWithCluster(
            cluster.impl,
        )
    }
}

