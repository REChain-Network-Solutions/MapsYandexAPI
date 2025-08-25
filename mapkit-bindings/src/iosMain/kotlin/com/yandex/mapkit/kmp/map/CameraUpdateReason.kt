@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST", "REDUNDANT_ELSE_IN_WHEN")

package com.yandex.mapkit.kmp.map

/**
 * The reason of the camera update.
 */
actual enum class CameraUpdateReason {
    /**
     * User manipulation, for example: zoom, scroll, rotate, fling.
     */
    GESTURES,
    /**
     * Application, by calling the map::move method.
     */
    APPLICATION,;

    companion object {
        fun toKmp(v: platform.Foundation.NSNumber): CameraUpdateReason {
            return toKmp(platform.YandexMapsMobile.YMKCameraUpdateReason.byValue(v.unsignedLongValue))
        }

        fun toKmp(v: platform.YandexMapsMobile.YMKCameraUpdateReason): CameraUpdateReason {
            return when (v) {
                platform.YandexMapsMobile.YMKCameraUpdateReason.YMKCameraUpdateReasonGestures -> CameraUpdateReason.GESTURES
                platform.YandexMapsMobile.YMKCameraUpdateReason.YMKCameraUpdateReasonApplication -> CameraUpdateReason.APPLICATION
                else -> error("unknown YMKCameraUpdateReason")
            }
        }
    }
}

fun CameraUpdateReason.fromKmp(): platform.YandexMapsMobile.YMKCameraUpdateReason {
    return when (this) {
        CameraUpdateReason.GESTURES -> platform.YandexMapsMobile.YMKCameraUpdateReason.YMKCameraUpdateReasonGestures
        CameraUpdateReason.APPLICATION -> platform.YandexMapsMobile.YMKCameraUpdateReason.YMKCameraUpdateReasonApplication
    }
}

