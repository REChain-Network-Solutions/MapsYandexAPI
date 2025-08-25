@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * Specifies the way provided zoom focus point affects gestures.
 */
expect enum class GestureFocusPointMode {
    /**
     * Only "double tap" (zoom in) and "tap with two fingers" (zoom out) use
     * provided gesture focus point. Other gestures ignore it and continue
     * to use their source point for transformation.
     */
    AFFECTS_TAP_GESTURES,
    /**
     * All gestures: pinch and stretch, tap and swipe, double tap, tap with
     * two fingers, rotation use provided gesture focus point and ignore
     * their source point. @attention Scroll is suppressed when this mode is
     * on and "pinch and stretch" or "rotation" gesture performed
     */
    AFFECTS_ALL_GESTURES,
}

