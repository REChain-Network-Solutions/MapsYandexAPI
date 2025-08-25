@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * The circle element.
 */
actual typealias CircleMapObject = com.yandex.mapkit.map.CircleMapObject

actual var CircleMapObject.geometry: com.yandex.mapkit.kmp.geometry.Circle
    get() = geometry
    set(value) {
        geometry = value
    }
/**
 * Sets the stroke color. Setting the stroke color to any transparent
 * color (for example, RGBA code 0x00000000) effectively disables the
 * stroke. default: 0x0066FFFF
 */
actual var CircleMapObject.strokeColor: com.yandex.runtime.kmp.Color
    get() = strokeColor
    set(value) {
        strokeColor = value
    }
/**
 * Sets the stroke width in units. The size of a unit is equal to the
 * size of a pixel at the current zoom level when the camera position's
 * tilt is equal to 0 and the scale factor is equal to 1. default: 5
 */
actual var CircleMapObject.strokeWidth: Float
    get() = strokeWidth
    set(value) {
        strokeWidth = value
    }
/**
 * Sets the fill color. default: 0x0066FF99
 */
actual var CircleMapObject.fillColor: com.yandex.runtime.kmp.Color
    get() = fillColor
    set(value) {
        fillColor = value
    }
/**
 * The object's geometry can be interpreted in two different ways:
 * <ul><li>If the object mode is 'geodesic', the object's geometry is
 * defined on a sphere.</li> <li>Otherwise, the object's geometry is
 * defined in projected space.</li></ul> Default: false.
 */
actual var CircleMapObject.geodesic: Boolean
    get() = isGeodesic
    set(value) {
        isGeodesic = value
    }
