@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

import kotlinx.cinterop.objcPtr

/**
 * This listener is notified when a map object is being dragged. Note
 * that the map object's "draggable" property needs to be set to True in
 * order to activate dragging. A long tap on a map object activates
 * dragging mode.
 */
actual interface MapObjectDragListener {
    /**
     * Raised when dragging mode is active for the given map object.
     */
    actual fun onMapObjectDragStart(
        mapObject: com.yandex.mapkit.kmp.map.MapObject,
    ): Unit

    /**
     * Raised when the user is moving a finger and the map object follows
     * it.
     */
    actual fun onMapObjectDrag(
        mapObject: com.yandex.mapkit.kmp.map.MapObject,
        point: com.yandex.mapkit.kmp.geometry.Point,
    ): Unit

    /**
     * Raised when the user released the tap.
     */
    actual fun onMapObjectDragEnd(
        mapObject: com.yandex.mapkit.kmp.map.MapObject,
    ): Unit
}

class MapObjectDragListenerWrapper internal constructor(impl: MapObjectDragListener, @Suppress("UNUSED_PARAMETER") tag: Unit) : platform.darwin.NSObject(), platform.YandexMapsMobile.YMKMapObjectDragListenerProtocol {
    private val impl = kotlin.native.ref.WeakReference(impl)

    override fun onMapObjectDragStartWithMapObject(
        mapObject: platform.YandexMapsMobile.YMKMapObject,
    ): Unit {
        return impl.get()?.onMapObjectDragStart(
            mapObject.let { com.yandex.mapkit.kmp.map.YMKMapObjectWrapper(it) },
        ) ?: Unit
    }

    override fun onMapObjectDragWithMapObject(
        mapObject: platform.YandexMapsMobile.YMKMapObject,
        point: platform.YandexMapsMobile.YMKPoint,
    ): Unit {
        return impl.get()?.onMapObjectDrag(
            mapObject.let { com.yandex.mapkit.kmp.map.YMKMapObjectWrapper(it) },
            point,
        ) ?: Unit
    }

    override fun onMapObjectDragEndWithMapObject(
        mapObject: platform.YandexMapsMobile.YMKMapObject,
    ): Unit {
        return impl.get()?.onMapObjectDragEnd(
            mapObject.let { com.yandex.mapkit.kmp.map.YMKMapObjectWrapper(it) },
        ) ?: Unit
    }

    internal companion object
}

fun MapObjectDragListenerWrapper(impl: MapObjectDragListener): MapObjectDragListenerWrapper {
    val pointerToCompanionObject = kotlinx.cinterop.interpretCPointer<kotlinx.cinterop.COpaque>(MapObjectDragListenerWrapper.Companion.objcPtr())
    val value = kotlinx.cinterop.autoreleasepool {
        platform.objc.objc_getAssociatedObject(impl, pointerToCompanionObject)
    }

    if (value != null) {
        return value as MapObjectDragListenerWrapper
    }

    val result = MapObjectDragListenerWrapper(impl, Unit)
    kotlinx.cinterop.autoreleasepool {
        platform.objc.objc_setAssociatedObject(impl, pointerToCompanionObject, result, platform.objc.OBJC_ASSOCIATION_RETAIN)
    }
    return result
}

open class YMKMapObjectDragListenerProtocolWrapper(val impl: platform.YandexMapsMobile.YMKMapObjectDragListenerProtocol) : MapObjectDragListener {
    override fun onMapObjectDragStart(
        mapObject: com.yandex.mapkit.kmp.map.MapObject,
    ): Unit {
        return impl.onMapObjectDragStartWithMapObject(
            mapObject.impl,
        )
    }

    override fun onMapObjectDrag(
        mapObject: com.yandex.mapkit.kmp.map.MapObject,
        point: com.yandex.mapkit.kmp.geometry.Point,
    ): Unit {
        return impl.onMapObjectDragWithMapObject(
            mapObject.impl,
            point,
        )
    }

    override fun onMapObjectDragEnd(
        mapObject: com.yandex.mapkit.kmp.map.MapObject,
    ): Unit {
        return impl.onMapObjectDragEndWithMapObject(
            mapObject.impl,
        )
    }
}

