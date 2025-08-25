@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST", "REDUNDANT_ELSE_IN_WHEN")

package com.yandex.mapkit.kmp

import com.yandex.runtime.kmp.internal.toFloat
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * The animation that is used to switch between states.
 */
actual typealias Animation = platform.YandexMapsMobile.YMKAnimation

/**
 * Animation type.
 */
actual val Animation.mpType: com.yandex.mapkit.kmp.AnimationType
    get() = type().let { com.yandex.mapkit.kmp.AnimationType.toKmp(it) }
/**
 * Animation duration, in seconds.
 */
actual val Animation.mpDuration: Float
    get() = duration()

actual object AnimationFactory {
    actual fun create(
        type: com.yandex.mapkit.kmp.AnimationType,
        duration: Float,
    ): Animation {
        return Animation.animationWithType(
            type.fromKmp(),
            duration,
        )
    }
}

/**
 * Available animation types.
 */
actual enum class AnimationType {
    /**
     * Smooth interpolation between start and finish states.
     */
    SMOOTH,
    /**
     * Movement with constant speed during animation time.
     */
    LINEAR,;

    companion object {
        fun toKmp(v: platform.Foundation.NSNumber): AnimationType {
            return toKmp(platform.YandexMapsMobile.YMKAnimationType.byValue(v.unsignedLongValue))
        }

        fun toKmp(v: platform.YandexMapsMobile.YMKAnimationType): AnimationType {
            return when (v) {
                platform.YandexMapsMobile.YMKAnimationType.YMKAnimationTypeSmooth -> AnimationType.SMOOTH
                platform.YandexMapsMobile.YMKAnimationType.YMKAnimationTypeLinear -> AnimationType.LINEAR
                else -> error("unknown YMKAnimationType")
            }
        }
    }
}

fun AnimationType.fromKmp(): platform.YandexMapsMobile.YMKAnimationType {
    return when (this) {
        AnimationType.SMOOTH -> platform.YandexMapsMobile.YMKAnimationType.YMKAnimationTypeSmooth
        AnimationType.LINEAR -> platform.YandexMapsMobile.YMKAnimationType.YMKAnimationTypeLinear
    }
}

