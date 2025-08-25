@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

import com.yandex.runtime.kmp.internal.toBoolean
import com.yandex.runtime.kmp.internal.toFloat
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * The arrow element.
 */
actual interface Arrow {
    val impl: platform.YandexMapsMobile.YMKArrow

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
 * Arrow center.
 */
actual val Arrow.position: com.yandex.mapkit.kmp.geometry.PolylinePosition
    get() = impl.position
/**
 * Arrow fill color.
 */
actual var Arrow.fillColor: com.yandex.runtime.kmp.Color
    get() = impl.fillColor
    set(value) {
        impl.fillColor = value
    }
/**
 * Color of the arrow's outline. Default: white.
 */
actual var Arrow.outlineColor: com.yandex.runtime.kmp.Color
    get() = impl.outlineColor
    set(value) {
        impl.outlineColor = value
    }
/**
 * The width of the outline in units. Default: 2.
 */
actual var Arrow.outlineWidth: Float
    get() = impl.outlineWidth
    set(value) {
        impl.outlineWidth = value
    }
/**
 * The overall length of the arrow (including the tip) in units. The
 * size of a unit is equal to the size of a pixel at the current zoom
 * level when the camera tilt is equal to 0 and the scale factor is
 * equal to 1.
 */
actual var Arrow.length: Float
    get() = impl.length
    set(value) {
        impl.length = value
    }
/**
 * Arrow visibility. Default: true.
 */
actual var Arrow.visible: Boolean
    get() = impl.visible
    set(value) {
        impl.visible = value
    }
/**
 * Describes height of the arrowhead in units. Default: 0.2 * length.
 */
actual var Arrow.triangleHeight: Float
    get() = impl.triangleHeight
    set(value) {
        impl.triangleHeight = value
    }

open class YMKArrowWrapper(override val impl: platform.YandexMapsMobile.YMKArrow) : Arrow {
    override fun isValid(): Boolean = impl.isValid()
}

