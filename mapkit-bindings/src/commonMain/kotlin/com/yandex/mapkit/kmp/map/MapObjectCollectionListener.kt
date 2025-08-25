@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * The map object collection can't be modified in callbacks. A
 * runtime::RuntimeError exception is thrown if this happens.
 */
expect interface MapObjectCollectionListener {
    /**
     * Called every time a new map object is added to the collection.
     */
    fun onMapObjectAdded(
        mapObject: com.yandex.mapkit.kmp.map.MapObject,
    ): Unit

    /**
     * Called every time a map object is removed from the collection.
     */
    fun onMapObjectRemoved(
        mapObject: com.yandex.mapkit.kmp.map.MapObject,
    ): Unit
}

