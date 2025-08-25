@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * If an event is not handled by the source object then it's propagated
 * to its parent. This listener can be attached to any MapObject
 * including MapObjectCollection.
 */
expect interface MapObjectTapListener {
    /**
     * Returns true if the event was handled. The event will not be
     * propagated to the parent. Returns false if the event wasn't handled.
     * The event will be propagated to the parent.
     */
    fun onMapObjectTap(
        mapObject: com.yandex.mapkit.kmp.map.MapObject,
        point: com.yandex.mapkit.kmp.geometry.Point,
    ): Boolean
}

