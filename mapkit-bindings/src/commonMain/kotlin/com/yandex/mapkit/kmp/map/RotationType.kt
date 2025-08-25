@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * The way rotation is handled.
 */
expect enum class RotationType {
    /**
     * Ignores the placemark direction; stable in screen space.
     */
    NO_ROTATION,
    /**
     * Follows the placemark direction. For non-flat placemarks, the
     * direction vector is projected onto the screen plane.
     */
    ROTATE,
}

