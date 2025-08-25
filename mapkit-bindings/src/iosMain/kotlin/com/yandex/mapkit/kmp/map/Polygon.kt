@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

import com.yandex.runtime.kmp.internal.toBoolean
import com.yandex.runtime.kmp.internal.toFloat
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * A polygon displayed on the map.
 */
actual interface PolygonMapObject : com.yandex.mapkit.kmp.map.MapObject {
    override val impl: platform.YandexMapsMobile.YMKPolygonMapObject

    /**
     * Sets pattern to fill polygon. @attention Original linear sizes of
     * pattern should be equal to power of 2. @attention Fill color is
     * ignored if a pattern is set.
     */
    actual fun setPattern(
        animatedImage: com.yandex.runtime.kmp.image.AnimatedImageProvider,
        scale: Float,
    ): Unit

    /**
     * Sets pattern to fill polygon. @attention Original linear sizes of
     * pattern should be equal to power of 2. @attention Fill color is
     * ignored if pattern is set.
     */
    actual fun setPattern(
        image: com.yandex.runtime.kmp.image.ImageProvider,
        scale: Float,
    ): Unit

    /**
     * Removes pattern.
     */
    actual fun resetPattern(): Unit
}
actual var PolygonMapObject.geometry: com.yandex.mapkit.kmp.geometry.Polygon
    get() = impl.geometry
    set(value) {
        impl.geometry = value
    }
/**
 * Sets the stroke color. Default: hexademical RGBA code 0x0066FFFF.
 * Setting the stroke color to any transparent color (for example, RGBA
 * code 0x00000000) effectively disables the stroke.
 */
actual var PolygonMapObject.strokeColor: com.yandex.runtime.kmp.Color
    get() = impl.strokeColor
    set(value) {
        impl.strokeColor = value
    }
/**
 * Sets the stroke width in units. Default: 5. The size of a unit is
 * equal to the size of a pixel at the current zoom when the camera
 * position's tilt is equal to 0 and the scale factor is equal to 1.
 */
actual var PolygonMapObject.strokeWidth: Float
    get() = impl.strokeWidth
    set(value) {
        impl.strokeWidth = value
    }
/**
 * Sets the fill color. Default: hexademical RGBA code 0x0066FF99.
 * @attention Fill color is ignored if a pattern is set.
 */
actual var PolygonMapObject.fillColor: com.yandex.runtime.kmp.Color
    get() = impl.fillColor
    set(value) {
        impl.fillColor = value
    }
/**
 * The object geometry can be interpreted in two different ways:
 * <ul><li>If the object mode is 'geodesic', the object geometry is
 * defined on a sphere.</li> <li>Otherwise, the object geometry is
 * defined in projected space.</li></ul> Default: false.
 */
actual var PolygonMapObject.geodesic: Boolean
    get() = impl.geodesic
    set(value) {
        impl.geodesic = value
    }

open class YMKPolygonMapObjectWrapper(override val impl: platform.YandexMapsMobile.YMKPolygonMapObject) : PolygonMapObject, com.yandex.mapkit.kmp.map.YMKMapObjectWrapper(impl) {
    override fun setPattern(
        animatedImage: com.yandex.runtime.kmp.image.AnimatedImageProvider,
        scale: Float,
    ): Unit {
        return impl.setPatternWithAnimatedImage(
            animatedImage.impl,
            scale,
        )
    }

    override fun setPattern(
        image: com.yandex.runtime.kmp.image.ImageProvider,
        scale: Float,
    ): Unit {
        return impl.setPatternWithImage(
            image.impl,
            scale,
        )
    }

    override fun resetPattern(): Unit {
        return impl.resetPattern()
    }
}

