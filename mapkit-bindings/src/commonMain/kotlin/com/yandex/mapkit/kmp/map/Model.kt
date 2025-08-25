@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * describes model presentation of PlacemarkMapObject
 */
expect interface Model {
    /**
     * Sets gltf data provider.
     *
     * The class maintains a strong reference to the object in
     * the 'gltfDataProvider' parameter until it (the class) is invalidated.
     *
     * @param onFinished Called when the model is loaded.
     */
    fun setData(
        gltfDataProvider: com.yandex.runtime.kmp.DataProviderWithId,
        onFinished: com.yandex.mapkit.kmp.map.Callback,
    ): Unit
}

/**
 * The style properties (sclae, unitType, etc.) of the model placemark.
 * Note: The current style cannot be modified directly - you must reset
 * it to apply changes.
 */
expect var Model.modelStyle: com.yandex.mapkit.kmp.map.ModelStyle

