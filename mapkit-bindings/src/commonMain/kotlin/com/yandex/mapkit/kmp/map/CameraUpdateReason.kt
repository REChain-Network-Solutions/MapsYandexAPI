@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * The reason of the camera update.
 */
expect enum class CameraUpdateReason {
    /**
     * User manipulation, for example: zoom, scroll, rotate, fling.
     */
    GESTURES,
    /**
     * Application, by calling the map::move method.
     */
    APPLICATION,
}

