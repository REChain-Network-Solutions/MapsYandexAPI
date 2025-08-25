@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST", "REDUNDANT_ELSE_IN_WHEN")

package com.yandex.mapkit.kmp.map

import kotlinx.cinterop.objcPtr

actual interface PlacemarkCreatedCallback {
    actual fun onPlacemarkCreated(
        placemark: com.yandex.mapkit.kmp.map.PlacemarkMapObject,
    ): Unit
}

internal class PlacemarkCreatedCallbackWrapper(val impl: PlacemarkCreatedCallback): platform.YandexMapsMobile.YMKPlacemarkCreatedCallback {
    override fun invoke(
        placemark: platform.YandexMapsMobile.YMKPlacemarkMapObject?,
    ) {
            impl.onPlacemarkCreated(
                placemark!!.let { com.yandex.mapkit.kmp.map.YMKPlacemarkMapObjectWrapper(it) },
            )
    }
}

actual interface BaseMapObjectCollection : com.yandex.mapkit.kmp.map.MapObject {
    override val impl: platform.YandexMapsMobile.YMKBaseMapObjectCollection

    /**
     * Traverses through the collection with a visitor object. Used for
     * iteration over map objects in the collection.
     *
     * The class does not retain the object in the 'mapObjectVisitor' parameter.
     * It is your responsibility to maintain a strong reference to
     * the target object while it is attached to a class.
     */
    actual fun traverse(
        mapObjectVisitor: com.yandex.mapkit.kmp.map.MapObjectVisitor,
    ): Unit

    /**
     * Removes the given map object from the collection.
     */
    actual fun remove(
        mapObject: com.yandex.mapkit.kmp.map.MapObject,
    ): Unit

    /**
     * Removes all map objects from the collection.
     */
    actual fun clear(): Unit

    /**
     * Adds a listener to track notifications of changes to the collection.
     *
     * The class does not retain the object in the 'collectionListener' parameter.
     * It is your responsibility to maintain a strong reference to
     * the target object while it is attached to a class.
     */
    actual fun addListener(
        collectionListener: com.yandex.mapkit.kmp.map.MapObjectCollectionListener,
    ): Unit

    /**
     * Removes a listener.
     */
    actual fun removeListener(
        collectionListener: com.yandex.mapkit.kmp.map.MapObjectCollectionListener,
    ): Unit
}

open class YMKBaseMapObjectCollectionWrapper(override val impl: platform.YandexMapsMobile.YMKBaseMapObjectCollection) : BaseMapObjectCollection, com.yandex.mapkit.kmp.map.YMKMapObjectWrapper(impl) {
    override fun traverse(
        mapObjectVisitor: com.yandex.mapkit.kmp.map.MapObjectVisitor,
    ): Unit {
        return impl.traverseWithMapObjectVisitor(
            mapObjectVisitor.let { com.yandex.mapkit.kmp.map.MapObjectVisitorWrapper(it) },
        )
    }

    override fun remove(
        mapObject: com.yandex.mapkit.kmp.map.MapObject,
    ): Unit {
        return impl.removeWithMapObject(
            mapObject.impl,
        )
    }

    override fun clear(): Unit {
        return impl.clear()
    }

    override fun addListener(
        collectionListener: com.yandex.mapkit.kmp.map.MapObjectCollectionListener,
    ): Unit {
        return impl.addListenerWithCollectionListener(
            collectionListener.let { com.yandex.mapkit.kmp.map.MapObjectCollectionListenerWrapper(it) },
        )
    }

    override fun removeListener(
        collectionListener: com.yandex.mapkit.kmp.map.MapObjectCollectionListener,
    ): Unit {
        return impl.removeListenerWithCollectionListener(
            collectionListener.let { com.yandex.mapkit.kmp.map.MapObjectCollectionListenerWrapper(it) },
        )
    }
}

