@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

import com.yandex.runtime.kmp.internal.toBoolean
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * Provides an interface to load and control animation of placemark.
 */
actual interface PlacemarkAnimation {
    val impl: platform.YandexMapsMobile.YMKPlacemarkAnimation

    /**
     * Sets the animated image and icon style. The new animation will be in
     * the paused state.
     */
    actual fun setIcon(
        image: com.yandex.runtime.kmp.image.AnimatedImageProvider,
        style: com.yandex.mapkit.kmp.map.IconStyle,
    ): Unit

    /**
     * Sets the animated image and icon style. The new animation will be in
     * the paused state.
     *
     * @param onFinished is called when the icon is loaded.
     */
    actual fun setIcon(
        image: com.yandex.runtime.kmp.image.AnimatedImageProvider,
        style: com.yandex.mapkit.kmp.map.IconStyle,
        onFinished: com.yandex.mapkit.kmp.map.Callback,
    ): Unit

    /**
     * Changes the icon style.
     */
    actual fun setIconStyle(
        style: com.yandex.mapkit.kmp.map.IconStyle,
    ): Unit

    /**
     * Starts animation. Removes the current play callback. Same as
     * play(null).
     */
    actual fun play(): Unit

    /**
     * Starts animation and handles the callback.
     *
     * @param onFinished is called when animation finishes and replaces the
     * previous callback.
     */
    actual fun play(
        onFinished: com.yandex.mapkit.kmp.map.Callback,
    ): Unit

    /**
     * Resumes paused animation. Callback (if any) is NOT removed.
     */
    actual fun resume(): Unit

    /**
     * Stops animation. Animation returns to the initial paused state.
     */
    actual fun stop(): Unit

    /**
     * Pauses animation.
     */
    actual fun pause(): Unit
}
/**
 * If true, animation will be played in the reverse direction. Default
 * value is false.
 */
actual var PlacemarkAnimation.reversed: Boolean
    get() = impl.reversed
    set(value) {
        impl.reversed = value
    }

open class YMKPlacemarkAnimationWrapper(override val impl: platform.YandexMapsMobile.YMKPlacemarkAnimation) : PlacemarkAnimation {
    override fun setIcon(
        image: com.yandex.runtime.kmp.image.AnimatedImageProvider,
        style: com.yandex.mapkit.kmp.map.IconStyle,
    ): Unit {
        return impl.setIconWithImage(
            image.impl,
            style,
        )
    }

    override fun setIcon(
        image: com.yandex.runtime.kmp.image.AnimatedImageProvider,
        style: com.yandex.mapkit.kmp.map.IconStyle,
        onFinished: com.yandex.mapkit.kmp.map.Callback,
    ): Unit {
        return impl.setIconWithImage(
            image.impl,
            style,
            onFinished.let { com.yandex.mapkit.kmp.map.CallbackWrapper(it) },
        )
    }

    override fun setIconStyle(
        style: com.yandex.mapkit.kmp.map.IconStyle,
    ): Unit {
        return impl.setIconStyleWithStyle(
            style,
        )
    }

    override fun play(): Unit {
        return impl.play()
    }

    override fun play(
        onFinished: com.yandex.mapkit.kmp.map.Callback,
    ): Unit {
        return impl.playWithCallback(
            onFinished.let { com.yandex.mapkit.kmp.map.CallbackWrapper(it) },
        )
    }

    override fun resume(): Unit {
        return impl.resume()
    }

    override fun stop(): Unit {
        return impl.stop()
    }

    override fun pause(): Unit {
        return impl.pause()
    }
}

