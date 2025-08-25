@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

/**
 * Combines multiple icons into one.
 */
actual interface CompositeIcon  {
    val impl: platform.YandexMapsMobile.YMKCompositeIcon

    /**
     * Creates or resets a named layer with an icon and its style.
     */
    actual fun setIcon(
        name: String,
        image: com.yandex.runtime.kmp.image.ImageProvider,
        style: com.yandex.mapkit.kmp.map.IconStyle,
    ): Unit

    /**
     * Creates or resets a named layer that contains an icon and its style.
     *
     * @param onFinished Called when an icon is loaded.
     */
    actual fun setIcon(
        name: String,
        image: com.yandex.runtime.kmp.image.ImageProvider,
        style: com.yandex.mapkit.kmp.map.IconStyle,
        onFinished: com.yandex.mapkit.kmp.map.Callback,
    ): Unit

    /**
     * Changes the icon style for a specific layer.
     */
    actual fun setIconStyle(
        name: String,
        style: com.yandex.mapkit.kmp.map.IconStyle,
    ): Unit

    /**
     * Removes the named layer.
     */
    actual fun removeIcon(
        name: String,
    ): Unit

    /**
     * Removes all layers.
     */
    actual fun removeAll(): Unit
}

open class YMKCompositeIconWrapper(override val impl: platform.YandexMapsMobile.YMKCompositeIcon) : CompositeIcon {
    override fun setIcon(
        name: String,
        image: com.yandex.runtime.kmp.image.ImageProvider,
        style: com.yandex.mapkit.kmp.map.IconStyle,
    ): Unit {
        return impl.setIconWithName(
            name,
            image.impl,
            style,
        )
    }

    override fun setIcon(
        name: String,
        image: com.yandex.runtime.kmp.image.ImageProvider,
        style: com.yandex.mapkit.kmp.map.IconStyle,
        onFinished: com.yandex.mapkit.kmp.map.Callback,
    ): Unit {
        return impl.setIconWithName(
            name,
            image.impl,
            style,
            onFinished.let { com.yandex.mapkit.kmp.map.CallbackWrapper(it) },
        )
    }

    override fun setIconStyle(
        name: String,
        style: com.yandex.mapkit.kmp.map.IconStyle,
    ): Unit {
        return impl.setIconStyleWithName(
            name,
            style,
        )
    }

    override fun removeIcon(
        name: String,
    ): Unit {
        return impl.removeIconWithName(
            name,
        )
    }

    override fun removeAll(): Unit {
        return impl.removeAll()
    }
}

