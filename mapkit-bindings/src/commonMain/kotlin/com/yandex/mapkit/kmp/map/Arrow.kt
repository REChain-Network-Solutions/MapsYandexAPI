@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * The arrow element.
 */
expect interface Arrow {
    /**
     * Tells if this object is valid or not. Any method called on an invalid
     * object will throw an exception. The object becomes invalid only on UI
     * thread, and only when its implementation depends on objects already
     * destroyed by now. Please refer to general docs about the interface for
     * details on its invalidation.
     */
    fun isValid(): Boolean
}

/**
 * Arrow center.
 */
expect val Arrow.position: com.yandex.mapkit.kmp.geometry.PolylinePosition
/**
 * Arrow fill color.
 */
expect var Arrow.fillColor: com.yandex.runtime.kmp.Color
/**
 * Color of the arrow's outline. Default: white.
 */
expect var Arrow.outlineColor: com.yandex.runtime.kmp.Color
/**
 * The width of the outline in units. Default: 2.
 */
expect var Arrow.outlineWidth: Float
/**
 * The overall length of the arrow (including the tip) in units. The
 * size of a unit is equal to the size of a pixel at the current zoom
 * level when the camera tilt is equal to 0 and the scale factor is
 * equal to 1.
 */
expect var Arrow.length: Float
/**
 * Arrow visibility. Default: true.
 */
expect var Arrow.visible: Boolean
/**
 * Describes height of the arrowhead in units. Default: 0.2 * length.
 */
expect var Arrow.triangleHeight: Float

