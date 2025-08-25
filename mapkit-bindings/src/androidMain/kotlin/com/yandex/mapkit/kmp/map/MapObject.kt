@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * An object displayed on the map.
 */
actual typealias MapObject = com.yandex.mapkit.map.MapObject

/**
 * Returns the collection of map objects that the current map object
 * belongs to.
 */
actual val MapObject.parent: com.yandex.mapkit.kmp.map.BaseMapObjectCollection
    get() = parent
/**
 * Manages visibility of the object on the map. Default: true.
 */
actual var MapObject.visible: Boolean
    get() = isVisible
    set(value) {
        isVisible = value
    }
/**
 * Gets the z-index, which affects: <ul><li>Rendering order.</li>
 * <li>Dispatching of UI events (taps and drags are dispatched to
 * objects with higher z-indexes first).</li></ul> Z-index is relative
 * to the parent.
 */
actual var MapObject.zIndex: Float
    get() = zIndex
    set(value) {
        zIndex = value
    }
/**
 * If true, the map object can be dragged by the user. Default: false.
 */
actual var MapObject.draggable: Boolean
    get() = isDraggable
    set(value) {
        isDraggable = value
    }
/**
 * Use this property to attach any object-related metadata.
 *
 */
actual var MapObject.userData: Any?
    get() = userData
    set(value) {
        userData = value
    }

