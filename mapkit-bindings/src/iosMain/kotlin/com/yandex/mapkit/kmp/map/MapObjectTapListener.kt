@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

import com.yandex.runtime.kmp.internal.toBoolean
import com.yandex.runtime.kmp.internal.toNSNumber
import kotlinx.cinterop.objcPtr

/**
 * If an event is not handled by the source object then it's propagated
 * to its parent. This listener can be attached to any MapObject
 * including MapObjectCollection.
 */
actual interface MapObjectTapListener {
    /**
     * Returns true if the event was handled. The event will not be
     * propagated to the parent. Returns false if the event wasn't handled.
     * The event will be propagated to the parent.
     */
    actual fun onMapObjectTap(
        mapObject: com.yandex.mapkit.kmp.map.MapObject,
        point: com.yandex.mapkit.kmp.geometry.Point,
    ): Boolean
}

class MapObjectTapListenerWrapper internal constructor(impl: MapObjectTapListener, @Suppress("UNUSED_PARAMETER") tag: Unit) : platform.darwin.NSObject(), platform.YandexMapsMobile.YMKMapObjectTapListenerProtocol {
    private val impl = kotlin.native.ref.WeakReference(impl)

    override fun onMapObjectTapWithMapObject(
        mapObject: platform.YandexMapsMobile.YMKMapObject,
        point: platform.YandexMapsMobile.YMKPoint,
    ): Boolean {
        return impl.get()?.onMapObjectTap(
            mapObject.let { com.yandex.mapkit.kmp.map.YMKMapObjectWrapper(it) },
            point,
        ) ?: false
    }

    internal companion object
}

fun MapObjectTapListenerWrapper(impl: MapObjectTapListener): MapObjectTapListenerWrapper {
    val pointerToCompanionObject = kotlinx.cinterop.interpretCPointer<kotlinx.cinterop.COpaque>(MapObjectTapListenerWrapper.Companion.objcPtr())
    val value = kotlinx.cinterop.autoreleasepool {
        platform.objc.objc_getAssociatedObject(impl, pointerToCompanionObject)
    }

    if (value != null) {
        return value as MapObjectTapListenerWrapper
    }

    val result = MapObjectTapListenerWrapper(impl, Unit)
    kotlinx.cinterop.autoreleasepool {
        platform.objc.objc_setAssociatedObject(impl, pointerToCompanionObject, result, platform.objc.OBJC_ASSOCIATION_RETAIN)
    }
    return result
}

open class YMKMapObjectTapListenerProtocolWrapper(val impl: platform.YandexMapsMobile.YMKMapObjectTapListenerProtocol) : MapObjectTapListener {
    override fun onMapObjectTap(
        mapObject: com.yandex.mapkit.kmp.map.MapObject,
        point: com.yandex.mapkit.kmp.geometry.Point,
    ): Boolean {
        return impl.onMapObjectTapWithMapObject(
            mapObject.impl,
            point,
        )
    }
}

