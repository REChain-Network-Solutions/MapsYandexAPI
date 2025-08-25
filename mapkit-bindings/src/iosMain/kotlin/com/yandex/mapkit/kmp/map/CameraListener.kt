@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

import com.yandex.runtime.kmp.internal.toBoolean
import com.yandex.runtime.kmp.internal.toNSNumber
import kotlinx.cinterop.objcPtr

/**
 * Listens for updates to the camera position.
 */
actual interface CameraListener {
    /**
     * Triggered when the camera position changed.
     *
     * @param map Event source.
     * @param cameraPosition Current camera position.
     * @param cameraUpdateReason The reason of camera update.
     * @param finished True if the camera finished moving, false otherwise.
     * If a movement is cancelled then cameraUpdateReason represents
     * initiator of cancellation.
     */
    actual fun onCameraPositionChanged(
        map: com.yandex.mapkit.kmp.map.Map,
        cameraPosition: com.yandex.mapkit.kmp.map.CameraPosition,
        cameraUpdateReason: com.yandex.mapkit.kmp.map.CameraUpdateReason,
        finished: Boolean,
    ): Unit
}

class CameraListenerWrapper internal constructor(impl: CameraListener, @Suppress("UNUSED_PARAMETER") tag: Unit) : platform.darwin.NSObject(), platform.YandexMapsMobile.YMKMapCameraListenerProtocol {
    private val impl = kotlin.native.ref.WeakReference(impl)

    override fun onCameraPositionChangedWithMap(
        map: platform.YandexMapsMobile.YMKMap,
        cameraPosition: platform.YandexMapsMobile.YMKCameraPosition,
        cameraUpdateReason: platform.YandexMapsMobile.YMKCameraUpdateReason,
        finished: Boolean,
    ): Unit {
        return impl.get()?.onCameraPositionChanged(
            map.let { com.yandex.mapkit.kmp.map.YMKMapWrapper(it) },
            cameraPosition,
            cameraUpdateReason.let { com.yandex.mapkit.kmp.map.CameraUpdateReason.toKmp(it) },
            finished,
        ) ?: Unit
    }

    internal companion object
}

fun CameraListenerWrapper(impl: CameraListener): CameraListenerWrapper {
    val pointerToCompanionObject = kotlinx.cinterop.interpretCPointer<kotlinx.cinterop.COpaque>(CameraListenerWrapper.Companion.objcPtr())
    val value = kotlinx.cinterop.autoreleasepool {
        platform.objc.objc_getAssociatedObject(impl, pointerToCompanionObject)
    }

    if (value != null) {
        return value as CameraListenerWrapper
    }

    val result = CameraListenerWrapper(impl, Unit)
    kotlinx.cinterop.autoreleasepool {
        platform.objc.objc_setAssociatedObject(impl, pointerToCompanionObject, result, platform.objc.OBJC_ASSOCIATION_RETAIN)
    }
    return result
}

open class YMKMapCameraListenerProtocolWrapper(val impl: platform.YandexMapsMobile.YMKMapCameraListenerProtocol) : CameraListener {
    override fun onCameraPositionChanged(
        map: com.yandex.mapkit.kmp.map.Map,
        cameraPosition: com.yandex.mapkit.kmp.map.CameraPosition,
        cameraUpdateReason: com.yandex.mapkit.kmp.map.CameraUpdateReason,
        finished: Boolean,
    ): Unit {
        return impl.onCameraPositionChangedWithMap(
            map.impl,
            cameraPosition,
            cameraUpdateReason.fromKmp(),
            finished,
        )
    }
}

