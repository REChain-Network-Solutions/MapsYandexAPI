@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

import kotlinx.cinterop.objcPtr

/**
 * Listener interface for when the map has finished loading. This occurs
 * after all tiles required to render the map have been fetched, and are
 * ready to be rendered. This event will not fire if the map never loads
 * due to connectivity issues, or if the map is continuously changing
 * and never completes loading due to the user constantly interacting
 * with the map.
 */
actual interface MapLoadedListener {
    /**
     * Called after the map has finished loading all visible tiles. This
     * will only be called once. You must set another listener if you want
     * to be notified again.
     */
    actual fun onMapLoaded(
        statistics: com.yandex.mapkit.kmp.map.MapLoadStatistics,
    ): Unit
}

class MapLoadedListenerWrapper internal constructor(impl: MapLoadedListener, @Suppress("UNUSED_PARAMETER") tag: Unit) : platform.darwin.NSObject(), platform.YandexMapsMobile.YMKMapLoadedListenerProtocol {
    private val impl = kotlin.native.ref.WeakReference(impl)

    override fun onMapLoadedWithStatistics(
        statistics: platform.YandexMapsMobile.YMKMapLoadStatistics,
    ): Unit {
        return impl.get()?.onMapLoaded(
            statistics,
        ) ?: Unit
    }

    internal companion object
}

fun MapLoadedListenerWrapper(impl: MapLoadedListener): MapLoadedListenerWrapper {
    val pointerToCompanionObject = kotlinx.cinterop.interpretCPointer<kotlinx.cinterop.COpaque>(MapLoadedListenerWrapper.Companion.objcPtr())
    val value = kotlinx.cinterop.autoreleasepool {
        platform.objc.objc_getAssociatedObject(impl, pointerToCompanionObject)
    }

    if (value != null) {
        return value as MapLoadedListenerWrapper
    }

    val result = MapLoadedListenerWrapper(impl, Unit)
    kotlinx.cinterop.autoreleasepool {
        platform.objc.objc_setAssociatedObject(impl, pointerToCompanionObject, result, platform.objc.OBJC_ASSOCIATION_RETAIN)
    }
    return result
}

open class YMKMapLoadedListenerProtocolWrapper(val impl: platform.YandexMapsMobile.YMKMapLoadedListenerProtocol) : MapLoadedListener {
    override fun onMapLoaded(
        statistics: com.yandex.mapkit.kmp.map.MapLoadStatistics,
    ): Unit {
        return impl.onMapLoadedWithStatistics(
            statistics,
        )
    }
}

