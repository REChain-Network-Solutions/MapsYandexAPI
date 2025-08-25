@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

import com.yandex.runtime.kmp.internal.toInt
import com.yandex.runtime.kmp.internal.toNSNumber
import kotlinx.cinterop.objcPtr

/**
 * Listener for MapWindow size changes.
 */
actual interface SizeChangedListener {
    /**
     * Called when MapWindow handles the platform SizeChanged event. param@
     * absolute Screen coordinates.
     */
    actual fun onMapWindowSizeChanged(
        mapWindow: com.yandex.mapkit.kmp.map.MapWindow,
        newWidth: Int,
        newHeight: Int,
    ): Unit
}

class SizeChangedListenerWrapper internal constructor(impl: SizeChangedListener, @Suppress("UNUSED_PARAMETER") tag: Unit) : platform.darwin.NSObject(), platform.YandexMapsMobile.YMKMapSizeChangedListenerProtocol {
    private val impl = kotlin.native.ref.WeakReference(impl)

    override fun onMapWindowSizeChangedWithMapWindow(
        mapWindow: platform.YandexMapsMobile.YMKMapWindow,
        newWidth: Long,
        newHeight: Long,
    ): Unit {
        return impl.get()?.onMapWindowSizeChanged(
            mapWindow.let { com.yandex.mapkit.kmp.map.YMKMapWindowWrapper(it) },
            newWidth.toInt(),
            newHeight.toInt(),
        ) ?: Unit
    }

    internal companion object
}

fun SizeChangedListenerWrapper(impl: SizeChangedListener): SizeChangedListenerWrapper {
    val pointerToCompanionObject = kotlinx.cinterop.interpretCPointer<kotlinx.cinterop.COpaque>(SizeChangedListenerWrapper.Companion.objcPtr())
    val value = kotlinx.cinterop.autoreleasepool {
        platform.objc.objc_getAssociatedObject(impl, pointerToCompanionObject)
    }

    if (value != null) {
        return value as SizeChangedListenerWrapper
    }

    val result = SizeChangedListenerWrapper(impl, Unit)
    kotlinx.cinterop.autoreleasepool {
        platform.objc.objc_setAssociatedObject(impl, pointerToCompanionObject, result, platform.objc.OBJC_ASSOCIATION_RETAIN)
    }
    return result
}

open class YMKMapSizeChangedListenerProtocolWrapper(val impl: platform.YandexMapsMobile.YMKMapSizeChangedListenerProtocol) : SizeChangedListener {
    override fun onMapWindowSizeChanged(
        mapWindow: com.yandex.mapkit.kmp.map.MapWindow,
        newWidth: Int,
        newHeight: Int,
    ): Unit {
        return impl.onMapWindowSizeChangedWithMapWindow(
            mapWindow.impl,
            newWidth.toLong(),
            newHeight.toLong(),
        )
    }
}

