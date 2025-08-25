@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * A polygon displayed on the map.
 */
expect interface PolygonMapObject : com.yandex.mapkit.kmp.map.MapObject {
    /**
     * Sets pattern to fill polygon. @attention Original linear sizes of
     * pattern should be equal to power of 2. @attention Fill color is
     * ignored if a pattern is set.
     */
    fun setPattern(
        animatedImage: com.yandex.runtime.kmp.image.AnimatedImageProvider,
        scale: Float,
    ): Unit

    /**
     * Sets pattern to fill polygon. @attention Original linear sizes of
     * pattern should be equal to power of 2. @attention Fill color is
     * ignored if pattern is set.
     */
    fun setPattern(
        image: com.yandex.runtime.kmp.image.ImageProvider,
        scale: Float,
    ): Unit

    /**
     * Removes pattern.
     */
    fun resetPattern(): Unit
}

expect var PolygonMapObject.geometry: com.yandex.mapkit.kmp.geometry.Polygon
/**
 * Sets the stroke color. Default: hexademical RGBA code 0x0066FFFF.
 * Setting the stroke color to any transparent color (for example, RGBA
 * code 0x00000000) effectively disables the stroke.
 */
expect var PolygonMapObject.strokeColor: com.yandex.runtime.kmp.Color
/**
 * Sets the stroke width in units. Default: 5. The size of a unit is
 * equal to the size of a pixel at the current zoom when the camera
 * position's tilt is equal to 0 and the scale factor is equal to 1.
 */
expect var PolygonMapObject.strokeWidth: Float
/**
 * Sets the fill color. Default: hexademical RGBA code 0x0066FF99.
 * @attention Fill color is ignored if a pattern is set.
 */
expect var PolygonMapObject.fillColor: com.yandex.runtime.kmp.Color
/**
 * The object geometry can be interpreted in two different ways:
 * <ul><li>If the object mode is 'geodesic', the object geometry is
 * defined on a sphere.</li> <li>Otherwise, the object geometry is
 * defined in projected space.</li></ul> Default: false.
 */
expect var PolygonMapObject.geodesic: Boolean

