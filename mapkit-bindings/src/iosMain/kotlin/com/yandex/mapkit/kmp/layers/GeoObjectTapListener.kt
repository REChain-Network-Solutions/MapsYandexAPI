@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.layers

import com.yandex.runtime.kmp.internal.toBoolean
import com.yandex.runtime.kmp.internal.toNSNumber
import kotlinx.cinterop.objcPtr

/**
 * Retrieves the brief geoObject info when an object is tapped.
 */
actual interface GeoObjectTapListener {
    /**
     * Listener that retrieves brief geoObject info for the tapped object.
     * Returns false if the event wasn't handled. The event will be
     * propagated to the map.
     */
    actual fun onObjectTap(
        event: com.yandex.mapkit.kmp.layers.GeoObjectTapEvent,
    ): Boolean
}

class GeoObjectTapListenerWrapper internal constructor(impl: GeoObjectTapListener, @Suppress("UNUSED_PARAMETER") tag: Unit) : platform.darwin.NSObject(), platform.YandexMapsMobile.YMKLayersGeoObjectTapListenerProtocol {
    private val impl = kotlin.native.ref.WeakReference(impl)

    override fun onObjectTapWithEvent(
        event: platform.YandexMapsMobile.YMKGeoObjectTapEvent,
    ): Boolean {
        return impl.get()?.onObjectTap(
            event.let { com.yandex.mapkit.kmp.layers.YMKGeoObjectTapEventWrapper(it) },
        ) ?: false
    }

    internal companion object
}

fun GeoObjectTapListenerWrapper(impl: GeoObjectTapListener): GeoObjectTapListenerWrapper {
    val pointerToCompanionObject = kotlinx.cinterop.interpretCPointer<kotlinx.cinterop.COpaque>(GeoObjectTapListenerWrapper.Companion.objcPtr())
    val value = kotlinx.cinterop.autoreleasepool {
        platform.objc.objc_getAssociatedObject(impl, pointerToCompanionObject)
    }

    if (value != null) {
        return value as GeoObjectTapListenerWrapper
    }

    val result = GeoObjectTapListenerWrapper(impl, Unit)
    kotlinx.cinterop.autoreleasepool {
        platform.objc.objc_setAssociatedObject(impl, pointerToCompanionObject, result, platform.objc.OBJC_ASSOCIATION_RETAIN)
    }
    return result
}

open class YMKLayersGeoObjectTapListenerProtocolWrapper(val impl: platform.YandexMapsMobile.YMKLayersGeoObjectTapListenerProtocol) : GeoObjectTapListener {
    override fun onObjectTap(
        event: com.yandex.mapkit.kmp.layers.GeoObjectTapEvent,
    ): Boolean {
        return impl.onObjectTapWithEvent(
            event.impl,
        )
    }
}

