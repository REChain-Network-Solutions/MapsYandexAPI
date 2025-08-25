@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * This listener is notified when a map object is being dragged. Note
 * that the map object's "draggable" property needs to be set to True in
 * order to activate dragging. A long tap on a map object activates
 * dragging mode.
 */
expect interface MapObjectDragListener {
    /**
     * Raised when dragging mode is active for the given map object.
     */
    fun onMapObjectDragStart(
        mapObject: com.yandex.mapkit.kmp.map.MapObject,
    ): Unit

    /**
     * Raised when the user is moving a finger and the map object follows
     * it.
     */
    fun onMapObjectDrag(
        mapObject: com.yandex.mapkit.kmp.map.MapObject,
        point: com.yandex.mapkit.kmp.geometry.Point,
    ): Unit

    /**
     * Raised when the user released the tap.
     */
    fun onMapObjectDragEnd(
        mapObject: com.yandex.mapkit.kmp.map.MapObject,
    ): Unit
}

