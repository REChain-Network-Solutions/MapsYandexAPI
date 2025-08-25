@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

import kotlinx.cinterop.objcPtr

/**
 * The map object collection can't be modified in callbacks. A
 * runtime::RuntimeError exception is thrown if this happens.
 */
actual interface MapObjectCollectionListener {
    /**
     * Called every time a new map object is added to the collection.
     */
    actual fun onMapObjectAdded(
        mapObject: com.yandex.mapkit.kmp.map.MapObject,
    ): Unit

    /**
     * Called every time a map object is removed from the collection.
     */
    actual fun onMapObjectRemoved(
        mapObject: com.yandex.mapkit.kmp.map.MapObject,
    ): Unit
}

class MapObjectCollectionListenerWrapper internal constructor(impl: MapObjectCollectionListener, @Suppress("UNUSED_PARAMETER") tag: Unit) : platform.darwin.NSObject(), platform.YandexMapsMobile.YMKMapObjectCollectionListenerProtocol {
    private val impl = kotlin.native.ref.WeakReference(impl)

    override fun onMapObjectAddedWithMapObject(
        mapObject: platform.YandexMapsMobile.YMKMapObject,
    ): Unit {
        return impl.get()?.onMapObjectAdded(
            mapObject.let { com.yandex.mapkit.kmp.map.YMKMapObjectWrapper(it) },
        ) ?: Unit
    }

    override fun onMapObjectRemovedWithMapObject(
        mapObject: platform.YandexMapsMobile.YMKMapObject,
    ): Unit {
        return impl.get()?.onMapObjectRemoved(
            mapObject.let { com.yandex.mapkit.kmp.map.YMKMapObjectWrapper(it) },
        ) ?: Unit
    }

    internal companion object
}

fun MapObjectCollectionListenerWrapper(impl: MapObjectCollectionListener): MapObjectCollectionListenerWrapper {
    val pointerToCompanionObject = kotlinx.cinterop.interpretCPointer<kotlinx.cinterop.COpaque>(MapObjectCollectionListenerWrapper.Companion.objcPtr())
    val value = kotlinx.cinterop.autoreleasepool {
        platform.objc.objc_getAssociatedObject(impl, pointerToCompanionObject)
    }

    if (value != null) {
        return value as MapObjectCollectionListenerWrapper
    }

    val result = MapObjectCollectionListenerWrapper(impl, Unit)
    kotlinx.cinterop.autoreleasepool {
        platform.objc.objc_setAssociatedObject(impl, pointerToCompanionObject, result, platform.objc.OBJC_ASSOCIATION_RETAIN)
    }
    return result
}

open class YMKMapObjectCollectionListenerProtocolWrapper(val impl: platform.YandexMapsMobile.YMKMapObjectCollectionListenerProtocol) : MapObjectCollectionListener {
    override fun onMapObjectAdded(
        mapObject: com.yandex.mapkit.kmp.map.MapObject,
    ): Unit {
        return impl.onMapObjectAddedWithMapObject(
            mapObject.impl,
        )
    }

    override fun onMapObjectRemoved(
        mapObject: com.yandex.mapkit.kmp.map.MapObject,
    ): Unit {
        return impl.onMapObjectRemovedWithMapObject(
            mapObject.impl,
        )
    }
}

