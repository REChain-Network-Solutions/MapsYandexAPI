@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

import com.yandex.runtime.kmp.internal.toBoolean
import com.yandex.runtime.kmp.internal.toFloat
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * An object displayed on the map.
 */
actual interface MapObject {
    val impl: platform.YandexMapsMobile.YMKMapObject

    /**
     * Manages visibility of the object.
     *
     * @param animation Describes the transition between visible and not
     * visible states.
     * @param onFinished Called when the transition is finished.
     */
    actual fun setVisible(
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
    actual fun addTapListener(
        tapListener: com.yandex.mapkit.kmp.map.MapObjectTapListener,
    ): Unit

    /**
     * Removes the tap listener from the object.
     */
    actual fun removeTapListener(
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
    actual fun setDragListener(
        dragListener: com.yandex.mapkit.kmp.map.MapObjectDragListener?,
    ): Unit

    /**
     * Tells if this object is valid or not. Any method called on an invalid
     * object will throw an exception. The object becomes invalid only on UI
     * thread, and only when its implementation depends on objects already
     * destroyed by now. Please refer to general docs about the interface for
     * details on its invalidation.
     */
    actual fun isValid(): Boolean
}
/**
 * Returns the collection of map objects that the current map object
 * belongs to.
 */
actual val MapObject.parent: com.yandex.mapkit.kmp.map.BaseMapObjectCollection
    get() = impl.parent.let { com.yandex.mapkit.kmp.map.YMKBaseMapObjectCollectionWrapper(it) }
/**
 * Manages visibility of the object on the map. Default: true.
 */
actual var MapObject.visible: Boolean
    get() = impl.visible
    set(value) {
        impl.visible = value
    }
/**
 * Gets the z-index, which affects: <ul><li>Rendering order.</li>
 * <li>Dispatching of UI events (taps and drags are dispatched to
 * objects with higher z-indexes first).</li></ul> Z-index is relative
 * to the parent.
 */
actual var MapObject.zIndex: Float
    get() = impl.zIndex
    set(value) {
        impl.zIndex = value
    }
/**
 * If true, the map object can be dragged by the user. Default: false.
 */
actual var MapObject.draggable: Boolean
    get() = impl.draggable
    set(value) {
        impl.draggable = value
    }
/**
 * Use this property to attach any object-related metadata.
 *
 */
actual var MapObject.userData: Any?
    get() = impl.userData
    set(value) {
        impl.userData = value
    }

open class YMKMapObjectWrapper(override val impl: platform.YandexMapsMobile.YMKMapObject) : MapObject {
    override fun setVisible(
        visible: Boolean,
        animation: com.yandex.mapkit.kmp.Animation,
        onFinished: com.yandex.mapkit.kmp.map.Callback?,
    ): Unit {
        return impl.setVisibleWithVisible(
            visible,
            animation,
            onFinished?.let { com.yandex.mapkit.kmp.map.CallbackWrapper(it) },
        )
    }

    override fun addTapListener(
        tapListener: com.yandex.mapkit.kmp.map.MapObjectTapListener,
    ): Unit {
        return impl.addTapListenerWithTapListener(
            tapListener.let { com.yandex.mapkit.kmp.map.MapObjectTapListenerWrapper(it) },
        )
    }

    override fun removeTapListener(
        tapListener: com.yandex.mapkit.kmp.map.MapObjectTapListener,
    ): Unit {
        return impl.removeTapListenerWithTapListener(
            tapListener.let { com.yandex.mapkit.kmp.map.MapObjectTapListenerWrapper(it) },
        )
    }

    override fun setDragListener(
        dragListener: com.yandex.mapkit.kmp.map.MapObjectDragListener?,
    ): Unit {
        return impl.setDragListenerWithDragListener(
            dragListener?.let { com.yandex.mapkit.kmp.map.MapObjectDragListenerWrapper(it) },
        )
    }

    override fun isValid(): Boolean = impl.isValid()
}

