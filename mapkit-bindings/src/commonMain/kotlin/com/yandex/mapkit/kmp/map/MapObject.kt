@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * An object displayed on the map.
 */
expect interface MapObject {
    /**
     * Manages visibility of the object.
     *
     * @param animation Describes the transition between visible and not
     * visible states.
     * @param onFinished Called when the transition is finished.
     */
    fun setVisible(
        visible: Boolean,
        animation: com.yandex.mapkit.kmp.Animation,
        onFinished: com.yandex.mapkit.kmp.map.Callback?,
    ): Unit

    /**
     * Adds a tap listener to the object.
     *
     * The class does not retain the object in the 'tapListener' parameter.
     * It is your responsibility to maintain a strong reference to
     * the target object while it is attached to a class.
     */
    fun addTapListener(
        tapListener: com.yandex.mapkit.kmp.map.MapObjectTapListener,
    ): Unit

    /**
     * Removes the tap listener from the object.
     */
    fun removeTapListener(
        tapListener: com.yandex.mapkit.kmp.map.MapObjectTapListener,
    ): Unit

    /**
     * Sets a drag listener for the object. Each object can only have one
     * drag listener.
     *
     * The class does not retain the object in the 'dragListener' parameter.
     * It is your responsibility to maintain a strong reference to
     * the target object while it is attached to a class.
     */
    fun setDragListener(
        dragListener: com.yandex.mapkit.kmp.map.MapObjectDragListener?,
    ): Unit

    /**
     * Tells if this object is valid or not. Any method called on an invalid
     * object will throw an exception. The object becomes invalid only on UI
     * thread, and only when its implementation depends on objects already
     * destroyed by now. Please refer to general docs about the interface for
     * details on its invalidation.
     */
    fun isValid(): Boolean
}

/**
 * Returns the collection of map objects that the current map object
 * belongs to.
 */
expect val MapObject.parent: com.yandex.mapkit.kmp.map.BaseMapObjectCollection
/**
 * Manages visibility of the object on the map. Default: true.
 */
expect var MapObject.visible: Boolean
/**
 * Gets the z-index, which affects: <ul><li>Rendering order.</li>
 * <li>Dispatching of UI events (taps and drags are dispatched to
 * objects with higher z-indexes first).</li></ul> Z-index is relative
 * to the parent.
 */
expect var MapObject.zIndex: Float
/**
 * If true, the map object can be dragged by the user. Default: false.
 */
expect var MapObject.draggable: Boolean
/**
 * Use this property to attach any object-related metadata.
 *
 */
expect var MapObject.userData: Any?

