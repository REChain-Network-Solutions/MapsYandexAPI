@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * Provides an interface to load and control animation of placemark.
 */
expect interface PlacemarkAnimation {
    /**
     * Sets the animated image and icon style. The new animation will be in
     * the paused state.
     */
    fun setIcon(
        image: com.yandex.runtime.kmp.image.AnimatedImageProvider,
        style: com.yandex.mapkit.kmp.map.IconStyle,
    ): Unit

    /**
     * Sets the animated image and icon style. The new animation will be in
     * the paused state.
     *
     * @param onFinished is called when the icon is loaded.
     */
    fun setIcon(
        image: com.yandex.runtime.kmp.image.AnimatedImageProvider,
        style: com.yandex.mapkit.kmp.map.IconStyle,
        onFinished: com.yandex.mapkit.kmp.map.Callback,
    ): Unit

    /**
     * Changes the icon style.
     */
    fun setIconStyle(
        style: com.yandex.mapkit.kmp.map.IconStyle,
    ): Unit


    /**
     * Starts animation. Removes the current play callback. Same as
     * play(null).
     */
    fun play(): Unit

    /**
     * Starts animation and handles the callback.
     *
     * @param onFinished is called when animation finishes and replaces the
     * previous callback.
     */
    fun play(
        onFinished: com.yandex.mapkit.kmp.map.Callback,
    ): Unit

    /**
     * Resumes paused animation. Callback (if any) is NOT removed.
     */
    fun resume(): Unit

    /**
     * Stops animation. Animation returns to the initial paused state.
     */
    fun stop(): Unit

    /**
     * Pauses animation.
     */
    fun pause(): Unit
}

/**
 * If true, animation will be played in the reverse direction. Default
 * value is false.
 */
expect var PlacemarkAnimation.reversed: Boolean

