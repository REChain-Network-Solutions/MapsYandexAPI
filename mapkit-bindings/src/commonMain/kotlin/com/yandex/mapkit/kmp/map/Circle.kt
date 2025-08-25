@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * The circle element.
 */
expect interface CircleMapObject : com.yandex.mapkit.kmp.map.MapObject

expect var CircleMapObject.geometry: com.yandex.mapkit.kmp.geometry.Circle
/**
 * Sets the stroke color. Setting the stroke color to any transparent
 * color (for example, RGBA code 0x00000000) effectively disables the
 * stroke. default: 0x0066FFFF
 */
expect var CircleMapObject.strokeColor: com.yandex.runtime.kmp.Color
/**
 * Sets the stroke width in units. The size of a unit is equal to the
 * size of a pixel at the current zoom level when the camera position's
 * tilt is equal to 0 and the scale factor is equal to 1. default: 5
 */
expect var CircleMapObject.strokeWidth: Float
/**
 * Sets the fill color. default: 0x0066FF99
 */
expect var CircleMapObject.fillColor: com.yandex.runtime.kmp.Color
/**
 * The object's geometry can be interpreted in two different ways:
 * <ul><li>If the object mode is 'geodesic', the object's geometry is
 * defined on a sphere.</li> <li>Otherwise, the object's geometry is
 * defined in projected space.</li></ul> Default: false.
 */
expect var CircleMapObject.geodesic: Boolean
