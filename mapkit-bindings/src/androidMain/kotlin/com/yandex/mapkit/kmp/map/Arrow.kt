@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * The arrow element.
 */
actual typealias Arrow = com.yandex.mapkit.map.Arrow

/**
 * Arrow center.
 */
actual val Arrow.position: com.yandex.mapkit.kmp.geometry.PolylinePosition
    get() = position
/**
 * Arrow fill color.
 */
actual var Arrow.fillColor: com.yandex.runtime.kmp.Color
    get() = fillColor
    set(value) {
        fillColor = value
    }
/**
 * Color of the arrow's outline. Default: white.
 */
actual var Arrow.outlineColor: com.yandex.runtime.kmp.Color
    get() = outlineColor
    set(value) {
        outlineColor = value
    }
/**
 * The width of the outline in units. Default: 2.
 */
actual var Arrow.outlineWidth: Float
    get() = outlineWidth
    set(value) {
        outlineWidth = value
    }
/**
 * The overall length of the arrow (including the tip) in units. The
 * size of a unit is equal to the size of a pixel at the current zoom
 * level when the camera tilt is equal to 0 and the scale factor is
 * equal to 1.
 */
actual var Arrow.length: Float
    get() = length
    set(value) {
        length = value
    }
/**
 * Arrow visibility. Default: true.
 */
actual var Arrow.visible: Boolean
    get() = isVisible
    set(value) {
        isVisible = value
    }
/**
 * Describes height of the arrowhead in units. Default: 0.2 * length.
 */
actual var Arrow.triangleHeight: Float
    get() = triangleHeight
    set(value) {
        triangleHeight = value
    }

