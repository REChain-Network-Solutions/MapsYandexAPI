@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST", "REDUNDANT_ELSE_IN_WHEN")

package com.yandex.mapkit.kmp.map

/**
 * The way rotation is handled.
 */
actual enum class RotationType {
    /**
     * Ignores the placemark direction; stable in screen space.
     */
    NO_ROTATION,
    /**
     * Follows the placemark direction. For non-flat placemarks, the
     * direction vector is projected onto the screen plane.
     */
    ROTATE,;

    companion object {
        fun toKmp(v: platform.Foundation.NSNumber): RotationType {
            return toKmp(platform.YandexMapsMobile.YMKRotationType.byValue(v.unsignedLongValue))
        }

        fun toKmp(v: platform.YandexMapsMobile.YMKRotationType): RotationType {
            return when (v) {
                platform.YandexMapsMobile.YMKRotationType.YMKRotationTypeNoRotation -> RotationType.NO_ROTATION
                platform.YandexMapsMobile.YMKRotationType.YMKRotationTypeRotate -> RotationType.ROTATE
                else -> error("unknown YMKRotationType")
            }
        }
    }
}

fun RotationType.fromKmp(): platform.YandexMapsMobile.YMKRotationType {
    return when (this) {
        RotationType.NO_ROTATION -> platform.YandexMapsMobile.YMKRotationType.YMKRotationTypeNoRotation
        RotationType.ROTATE -> platform.YandexMapsMobile.YMKRotationType.YMKRotationTypeRotate
    }
}

