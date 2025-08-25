@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST", "REDUNDANT_ELSE_IN_WHEN")

package com.yandex.mapkit.kmp.map

/**
 * Specifies the way provided zoom focus point affects gestures.
 */
actual enum class GestureFocusPointMode {
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
    AFFECTS_ALL_GESTURES,;

    companion object {
        fun toKmp(v: platform.Foundation.NSNumber): GestureFocusPointMode {
            return toKmp(platform.YandexMapsMobile.YMKGestureFocusPointMode.byValue(v.unsignedLongValue))
        }

        fun toKmp(v: platform.YandexMapsMobile.YMKGestureFocusPointMode): GestureFocusPointMode {
            return when (v) {
                platform.YandexMapsMobile.YMKGestureFocusPointMode.YMKGestureFocusPointModeAffectsTapGestures -> GestureFocusPointMode.AFFECTS_TAP_GESTURES
                platform.YandexMapsMobile.YMKGestureFocusPointMode.YMKGestureFocusPointModeAffectsAllGestures -> GestureFocusPointMode.AFFECTS_ALL_GESTURES
                else -> error("unknown YMKGestureFocusPointMode")
            }
        }
    }
}

fun GestureFocusPointMode.fromKmp(): platform.YandexMapsMobile.YMKGestureFocusPointMode {
    return when (this) {
        GestureFocusPointMode.AFFECTS_TAP_GESTURES -> platform.YandexMapsMobile.YMKGestureFocusPointMode.YMKGestureFocusPointModeAffectsTapGestures
        GestureFocusPointMode.AFFECTS_ALL_GESTURES -> platform.YandexMapsMobile.YMKGestureFocusPointMode.YMKGestureFocusPointModeAffectsAllGestures
    }
}

