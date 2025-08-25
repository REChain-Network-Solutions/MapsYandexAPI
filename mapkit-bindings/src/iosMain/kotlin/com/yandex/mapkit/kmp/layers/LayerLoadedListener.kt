@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.layers

import kotlinx.cinterop.objcPtr

/**
 * Allows user to be notified when the layer has finished loading. This
 * occurs after all tiles required to render the layer have been
 * fetched, and are ready to be rendered. This event will not fire if
 * the layer never loads due to connectivity issues, or if the layer is
 * continuously changing and never completes loading due to the user
 * constantly interacting with the map.
 */
actual interface LayerLoadedListener {
    /**
     * Called after the layer has finished loading all visible tiles. This
     * will only be called once. You must set another listener if you want
     * to be notified again.
     */
    actual fun onLayerLoaded(): Unit
}

class LayerLoadedListenerWrapper internal constructor(impl: LayerLoadedListener, @Suppress("UNUSED_PARAMETER") tag: Unit) : platform.darwin.NSObject(), platform.YandexMapsMobile.YMKLayerLoadedListenerProtocol {
    private val impl = kotlin.native.ref.WeakReference(impl)

    override fun onLayerLoaded(): Unit {
        return impl.get()?.onLayerLoaded() ?: Unit
    }

    internal companion object
}

fun LayerLoadedListenerWrapper(impl: LayerLoadedListener): LayerLoadedListenerWrapper {
    val pointerToCompanionObject = kotlinx.cinterop.interpretCPointer<kotlinx.cinterop.COpaque>(LayerLoadedListenerWrapper.Companion.objcPtr())
    val value = kotlinx.cinterop.autoreleasepool {
        platform.objc.objc_getAssociatedObject(impl, pointerToCompanionObject)
    }

    if (value != null) {
        return value as LayerLoadedListenerWrapper
    }

    val result = LayerLoadedListenerWrapper(impl, Unit)
    kotlinx.cinterop.autoreleasepool {
        platform.objc.objc_setAssociatedObject(impl, pointerToCompanionObject, result, platform.objc.OBJC_ASSOCIATION_RETAIN)
    }
    return result
}

open class YMKLayerLoadedListenerProtocolWrapper(val impl: platform.YandexMapsMobile.YMKLayerLoadedListenerProtocol) : LayerLoadedListener {
    override fun onLayerLoaded(): Unit {
        return impl.onLayerLoaded()
    }
}

