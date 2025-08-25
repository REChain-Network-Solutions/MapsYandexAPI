@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * The point of view.
 */
expect enum class PointOfView {
    /**
     * Point of View is centered on the screen.
     */
    SCREEN_CENTER,
    /**
     * Point of View has an x-coordinate at focusPoint and a y-coordinate at
     * the center of the screen.
     */
    ADAPT_TO_FOCUS_POINT_HORIZONTALLY,
}

