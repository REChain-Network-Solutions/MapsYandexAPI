@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

/**
 * The animation that is used to switch between states.
 */
expect class Animation

/**
 * Animation type.
 */
expect val Animation.mpType: com.yandex.mapkit.kmp.AnimationType
/**
 * Animation duration, in seconds.
 */
expect val Animation.mpDuration: Float

expect object AnimationFactory {
    fun create(
        type: com.yandex.mapkit.kmp.AnimationType,
        duration: Float,
    ): Animation
}

/**
 * Available animation types.
 */
expect enum class AnimationType {
    /**
     * Smooth interpolation between start and finish states.
     */
    SMOOTH,
    /**
     * Movement with constant speed during animation time.
     */
    LINEAR,
}

