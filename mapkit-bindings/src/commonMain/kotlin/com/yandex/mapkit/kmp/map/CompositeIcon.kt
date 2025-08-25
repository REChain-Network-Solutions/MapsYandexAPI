@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * Combines multiple icons into one.
 */
expect interface CompositeIcon {
    /**
     * Creates or resets a named layer with an icon and its style.
     */
    fun setIcon(
        name: String,
        image: com.yandex.runtime.kmp.image.ImageProvider,
        style: com.yandex.mapkit.kmp.map.IconStyle,
    ): Unit

    /**
     * Creates or resets a named layer that contains an icon and its style.
     *
     * @param onFinished Called when an icon is loaded.
     */
    fun setIcon(
        name: String,
        image: com.yandex.runtime.kmp.image.ImageProvider,
        style: com.yandex.mapkit.kmp.map.IconStyle,
        onFinished: com.yandex.mapkit.kmp.map.Callback,
    ): Unit

    /**
     * Changes the icon style for a specific layer.
     */
    fun setIconStyle(
        name: String,
        style: com.yandex.mapkit.kmp.map.IconStyle,
    ): Unit

    /**
     * Removes the named layer.
     */
    fun removeIcon(
        name: String,
    ): Unit

    /**
     * Removes all layers.
     */
    fun removeAll(): Unit
}

