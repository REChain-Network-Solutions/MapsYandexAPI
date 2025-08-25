@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.layers

/**
 * Interface for working with the layer.
 */
expect interface Layer {
    /**
     * Removes the layer from the map. The object becomes invalid after
     * that.
     */
    fun remove(): Unit

    fun dataSourceLayer(): com.yandex.mapkit.kmp.layers.DataSourceLayer

    /**
     * Tells if this object is valid or not. Any method called on an invalid
     * object will throw an exception. The object becomes invalid only on UI
     * thread, and only when its implementation depends on objects already
     * destroyed by now. Please refer to general docs about the interface for
     * details on its invalidation.
     */
    fun isValid(): Boolean
}

