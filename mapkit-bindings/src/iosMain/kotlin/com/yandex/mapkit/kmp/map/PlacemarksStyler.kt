@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

import com.yandex.runtime.kmp.fromKmp
import com.yandex.runtime.kmp.fromKmpOptional
import com.yandex.runtime.kmp.toKmp

/**
 * Represents a styler for all placemarks in the map object collection,
 * including placemarks in child collections. When a new placemark is
 * added to a collection, the placemark tries to get style properties
 * from the collection where it has been added. If this collection
 * doesn't have a style property, the placemark tries to get it from the
 * closest parent collection. If none of these collections have a
 * specific style property, the placemark sets the default value for
 * this property.
 */
actual interface PlacemarksStyler {
    val impl: platform.YandexMapsMobile.YMKPlacemarksStyler

    /**
     * Sets piecewise linear scale, depending on the zoom. The 'points' must
     * be sorted by x; x coordinates must be unique. If zoom <
     * minZoom(points) or zoom > maxZoom(points), it is set within the
     * defined bounds before applying the function. By default, the scale
     * function is defined by a single point (1, 1). If points is null or
     * points.empty(), it resets the function to the default. If
     * points.size() == 1, the scale is constant and equals point.y.
     */
    actual fun setScaleFunction(
        points: kotlin.collections.List<com.yandex.runtime.kmp.NativePoint>,
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

open class YMKPlacemarksStylerWrapper(override val impl: platform.YandexMapsMobile.YMKPlacemarksStyler) : PlacemarksStyler {
    override fun setScaleFunction(
        points: kotlin.collections.List<com.yandex.runtime.kmp.NativePoint>,
    ): Unit {
        return impl.setScaleFunctionWithPoints(
            points.map { it.fromKmpOptional() }.let { it as kotlin.collections.List<*> },
        )
    }

    override fun isValid(): Boolean = impl.isValid()
}

