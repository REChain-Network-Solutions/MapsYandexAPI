@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.layers

expect interface DataSourceLayer {

    /**
     * Clears all cached tiles and starts new requests for tiles that are
     * displayed.
     */
    fun clear(): Unit

    /**
     * Applies JSON style transformation to the layer. Replaces previous
     * styling with the specified ID (if such exists). Stylings are applied
     * in an ascending order. Set to empty string to clear previous styling
     * with the specified ID. Returns true if the style was successfully
     * parsed and false otherwise. If the returned value is false, the
     * current style remains unchanged.
     */
    fun setStyle(
        id: Int,
        style: String,
    ): Boolean

    /**
     * Resets all JSON style transformations applied to the layer.
     */
    fun resetStyles(): Unit

    /**
     * Sets layer loaded listener.
     *
     * The class does not retain the object in the 'layerLoadedListener' parameter.
     * It is your responsibility to maintain a strong reference to
     * the target object while it is attached to a class.
     */
    fun setLayerLoadedListener(
        layerLoadedListener: com.yandex.mapkit.kmp.layers.LayerLoadedListener?,
    ): Unit

    /**
     * Removes the data source layer from the parent layer. The object
     * becomes invalid after that.
     */
    fun remove(): Unit

    /**
     * Sets data source listener. Use it to invalidate data source.
     * Temporary solution until https://st.yandex-team.ru/MAPSMOBCORE-20531
     * is done
     *
     * The class does not retain the object in the 'dataSourceListener' parameter.
     * It is your responsibility to maintain a strong reference to
     * the target object while it is attached to a class.
     */
    fun setDataSourceListener(
        dataSourceListener: com.yandex.mapkit.kmp.layers.DataSourceListener?,
    ): Unit

    /**
     * Tells if this object is valid or not. Any method called on an invalid
     * object will throw an exception. The object becomes invalid only on UI
     * thread, and only when its implementation depends on objects already
     * destroyed by now. Please refer to general docs about the interface for
     * details on its invalidation.
     */
    fun isValid(): Boolean
}

/**
 * Manages visibility of the layer.
 */
expect var DataSourceLayer.active: Boolean

