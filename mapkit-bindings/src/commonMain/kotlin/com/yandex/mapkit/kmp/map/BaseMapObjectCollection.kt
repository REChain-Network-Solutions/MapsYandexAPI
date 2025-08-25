@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

expect interface PlacemarkCreatedCallback {
    /**
     * Can be used to setup placemark style and position Will be called
     * before MapObjectCollectionListener#onMapObjectAdded
     */
    fun onPlacemarkCreated(
        placemark: com.yandex.mapkit.kmp.map.PlacemarkMapObject,
    ): Unit
}

expect interface BaseMapObjectCollection : com.yandex.mapkit.kmp.map.MapObject {
    /**
     * Traverses through the collection with a visitor object. Used for
     * iteration over map objects in the collection.
     *
     * The class does not retain the object in the 'mapObjectVisitor' parameter.
     * It is your responsibility to maintain a strong reference to
     * the target object while it is attached to a class.
     */
    fun traverse(
        mapObjectVisitor: com.yandex.mapkit.kmp.map.MapObjectVisitor,
    ): Unit

    /**
     * Removes the given map object from the collection.
     */
    fun remove(
        mapObject: com.yandex.mapkit.kmp.map.MapObject,
    ): Unit

    /**
     * Removes all map objects from the collection.
     */
    fun clear(): Unit

    /**
     * Adds a listener to track notifications of changes to the collection.
     *
     * The class does not retain the object in the 'collectionListener' parameter.
     * It is your responsibility to maintain a strong reference to
     * the target object while it is attached to a class.
     */
    fun addListener(
        collectionListener: com.yandex.mapkit.kmp.map.MapObjectCollectionListener,
    ): Unit

    /**
     * Removes a listener.
     */
    fun removeListener(
        collectionListener: com.yandex.mapkit.kmp.map.MapObjectCollectionListener,
    ): Unit
}

