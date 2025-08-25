@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

/**
 * The animation that is used to switch between states.
 */
actual typealias Animation = com.yandex.mapkit.Animation

/**
 * Animation type.
 */
actual val Animation.mpType: com.yandex.mapkit.kmp.AnimationType
    get() = type
/**
 * Animation duration, in seconds.
 */
actual val Animation.mpDuration: Float
    get() = duration

actual object AnimationFactory {
    actual fun create(
        type: com.yandex.mapkit.kmp.AnimationType,
        duration: Float,
    ): Animation {
        return Animation(
            type,
            duration,
        )
    }
}

/**
 * Available animation types.
 */
actual typealias AnimationType = com.yandex.mapkit.Animation.Type

