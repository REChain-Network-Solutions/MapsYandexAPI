@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.layers

/**
 * Interface for working with the layer.
 */
actual interface Layer {
    val impl: platform.YandexMapsMobile.YMKLayer

    /**
     * Removes the layer from the map. The object becomes invalid after
     * that.
     */
    actual fun remove(): Unit

    actual fun dataSourceLayer(): com.yandex.mapkit.kmp.layers.DataSourceLayer

    /**
     * Tells if this object is valid or not. Any method called on an invalid
     * object will throw an exception. The object becomes invalid only on UI
     * thread, and only when its implementation depends on objects already
     * destroyed by now. Please refer to general docs about the interface for
     * details on its invalidation.
     */
    actual fun isValid(): Boolean
}

open class YMKLayerWrapper(override val impl: platform.YandexMapsMobile.YMKLayer) : Layer {
    override fun remove(): Unit {
        return impl.remove()
    }

    override fun dataSourceLayer(): com.yandex.mapkit.kmp.layers.DataSourceLayer {
        return impl.dataSourceLayer().let { com.yandex.mapkit.kmp.layers.YMKDataSourceLayerWrapper(it) }
    }

    override fun isValid(): Boolean = impl.isValid()
}

