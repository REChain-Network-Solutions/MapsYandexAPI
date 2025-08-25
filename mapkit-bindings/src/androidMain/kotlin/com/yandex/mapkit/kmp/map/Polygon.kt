@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * A polygon displayed on the map.
 */
actual typealias PolygonMapObject = com.yandex.mapkit.map.PolygonMapObject

actual var PolygonMapObject.geometry: com.yandex.mapkit.kmp.geometry.Polygon
    get() = geometry
    set(value) {
        geometry = value
    }
/**
 * Sets the stroke color. Default: hexademical RGBA code 0x0066FFFF.
 * Setting the stroke color to any transparent color (for example, RGBA
 * code 0x00000000) effectively disables the stroke.
 */
actual var PolygonMapObject.strokeColor: com.yandex.runtime.kmp.Color
    get() = strokeColor
    set(value) {
        strokeColor = value
    }
/**
 * Sets the stroke width in units. Default: 5. The size of a unit is
 * equal to the size of a pixel at the current zoom when the camera
 * position's tilt is equal to 0 and the scale factor is equal to 1.
 */
actual var PolygonMapObject.strokeWidth: Float
    get() = strokeWidth
    set(value) {
        strokeWidth = value
    }
/**
 * Sets the fill color. Default: hexademical RGBA code 0x0066FF99.
 * @attention Fill color is ignored if a pattern is set.
 */
actual var PolygonMapObject.fillColor: com.yandex.runtime.kmp.Color
    get() = fillColor
    set(value) {
        fillColor = value
    }
/**
 * The object geometry can be interpreted in two different ways:
 * <ul><li>If the object mode is 'geodesic', the object geometry is
 * defined on a sphere.</li> <li>Otherwise, the object geometry is
 * defined in projected space.</li></ul> Default: false.
 */
actual var PolygonMapObject.geodesic: Boolean
    get() = isGeodesic
    set(value) {
        isGeodesic = value
    }

