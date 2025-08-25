@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

import kotlinx.cinterop.objcPtr

/**
 * Listener for user interactions with the map.
 */
actual interface InputListener {
    /**
     * Called when a tap occurred unless a tap was handled by geo objects or
     * map objects. param@ position absolute screen coordinates
     */
    actual fun onMapTap(
        map: com.yandex.mapkit.kmp.map.Map,
        point: com.yandex.mapkit.kmp.geometry.Point,
    ): Unit

    /**
     * Called when a long tap occurred.
     */
    actual fun onMapLongTap(
        map: com.yandex.mapkit.kmp.map.Map,
        point: com.yandex.mapkit.kmp.geometry.Point,
    ): Unit
}

class InputListenerWrapper internal constructor(impl: InputListener, @Suppress("UNUSED_PARAMETER") tag: Unit) : platform.darwin.NSObject(), platform.YandexMapsMobile.YMKMapInputListenerProtocol {
    private val impl = kotlin.native.ref.WeakReference(impl)

    override fun onMapTapWithMap(
        map: platform.YandexMapsMobile.YMKMap,
        point: platform.YandexMapsMobile.YMKPoint,
    ): Unit {
        return impl.get()?.onMapTap(
            map.let { com.yandex.mapkit.kmp.map.YMKMapWrapper(it) },
            point,
        ) ?: Unit
    }

    override fun onMapLongTapWithMap(
        map: platform.YandexMapsMobile.YMKMap,
        point: platform.YandexMapsMobile.YMKPoint,
    ): Unit {
        return impl.get()?.onMapLongTap(
            map.let { com.yandex.mapkit.kmp.map.YMKMapWrapper(it) },
            point,
        ) ?: Unit
    }

    internal companion object
}

fun InputListenerWrapper(impl: InputListener): InputListenerWrapper {
    val pointerToCompanionObject = kotlinx.cinterop.interpretCPointer<kotlinx.cinterop.COpaque>(InputListenerWrapper.Companion.objcPtr())
    val value = kotlinx.cinterop.autoreleasepool {
        platform.objc.objc_getAssociatedObject(impl, pointerToCompanionObject)
    }

    if (value != null) {
        return value as InputListenerWrapper
    }

    val result = InputListenerWrapper(impl, Unit)
    kotlinx.cinterop.autoreleasepool {
        platform.objc.objc_setAssociatedObject(impl, pointerToCompanionObject, result, platform.objc.OBJC_ASSOCIATION_RETAIN)
    }
    return result
}

open class YMKMapInputListenerProtocolWrapper(val impl: platform.YandexMapsMobile.YMKMapInputListenerProtocol) : InputListener {
    override fun onMapTap(
        map: com.yandex.mapkit.kmp.map.Map,
        point: com.yandex.mapkit.kmp.geometry.Point,
    ): Unit {
        return impl.onMapTapWithMap(
            map.impl,
            point,
        )
    }

    override fun onMapLongTap(
        map: com.yandex.mapkit.kmp.map.Map,
        point: com.yandex.mapkit.kmp.geometry.Point,
    ): Unit {
        return impl.onMapLongTapWithMap(
            map.impl,
            point,
        )
    }
}

