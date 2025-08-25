@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

import com.yandex.runtime.kmp.internal.toBoolean
import com.yandex.runtime.kmp.internal.toFloat
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * The circle element.
 */
actual interface CircleMapObject : com.yandex.mapkit.kmp.map.MapObject {
    override val impl: platform.YandexMapsMobile.YMKCircleMapObject
}
actual var CircleMapObject.geometry: com.yandex.mapkit.kmp.geometry.Circle
    get() = impl.geometry
    set(value) {
        impl.geometry = value
    }
/**
 * Sets the stroke color. Setting the stroke color to any transparent
 * color (for example, RGBA code 0x00000000) effectively disables the
 * stroke. default: 0x0066FFFF
 */
actual var CircleMapObject.strokeColor: com.yandex.runtime.kmp.Color
    get() = impl.strokeColor
    set(value) {
        impl.strokeColor = value
    }
/**
 * Sets the stroke width in units. The size of a unit is equal to the
 * size of a pixel at the current zoom level when the camera position's
 * tilt is equal to 0 and the scale factor is equal to 1. default: 5
 */
actual var CircleMapObject.strokeWidth: Float
    get() = impl.strokeWidth
    set(value) {
        impl.strokeWidth = value
    }
/**
 * Sets the fill color. default: 0x0066FF99
 */
actual var CircleMapObject.fillColor: com.yandex.runtime.kmp.Color
    get() = impl.fillColor
    set(value) {
        impl.fillColor = value
    }
/**
 * The object's geometry can be interpreted in two different ways:
 * <ul><li>If the object mode is 'geodesic', the object's geometry is
 * defined on a sphere.</li> <li>Otherwise, the object's geometry is
 * defined in projected space.</li></ul> Default: false.
 */
actual var CircleMapObject.geodesic: Boolean
    get() = impl.geodesic
    set(value) {
        impl.geodesic = value
    }

open class YMKCircleMapObjectWrapper(override val impl: platform.YandexMapsMobile.YMKCircleMapObject) : CircleMapObject, com.yandex.mapkit.kmp.map.YMKMapObjectWrapper(impl)

