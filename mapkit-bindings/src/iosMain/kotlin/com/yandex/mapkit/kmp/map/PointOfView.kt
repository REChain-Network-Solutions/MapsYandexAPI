@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST", "REDUNDANT_ELSE_IN_WHEN")

package com.yandex.mapkit.kmp.map

/**
 * The point of view.
 */
actual enum class PointOfView {
    /**
     * Point of View is centered on the screen.
     */
    SCREEN_CENTER,
    /**
     * Point of View has an x-coordinate at focusPoint and a y-coordinate at
     * the center of the screen.
     */
    ADAPT_TO_FOCUS_POINT_HORIZONTALLY,;

    companion object {
        fun toKmp(v: platform.Foundation.NSNumber): PointOfView {
            return toKmp(platform.YandexMapsMobile.YMKPointOfView.byValue(v.unsignedLongValue))
        }

        fun toKmp(v: platform.YandexMapsMobile.YMKPointOfView): PointOfView {
            return when (v) {
                platform.YandexMapsMobile.YMKPointOfView.YMKPointOfViewScreenCenter -> PointOfView.SCREEN_CENTER
                platform.YandexMapsMobile.YMKPointOfView.YMKPointOfViewAdaptToFocusPointHorizontally -> PointOfView.ADAPT_TO_FOCUS_POINT_HORIZONTALLY
                else -> error("unknown YMKPointOfView")
            }
        }
    }
}

fun PointOfView.fromKmp(): platform.YandexMapsMobile.YMKPointOfView {
    return when (this) {
        PointOfView.SCREEN_CENTER -> platform.YandexMapsMobile.YMKPointOfView.YMKPointOfViewScreenCenter
        PointOfView.ADAPT_TO_FOCUS_POINT_HORIZONTALLY -> platform.YandexMapsMobile.YMKPointOfView.YMKPointOfViewAdaptToFocusPointHorizontally
    }
}

