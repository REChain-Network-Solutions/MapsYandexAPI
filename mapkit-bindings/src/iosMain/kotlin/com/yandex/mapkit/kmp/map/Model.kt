@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

/**
 * describes model presentation of PlacemarkMapObject
 */
actual interface Model {
    val impl: platform.YandexMapsMobile.YMKModel

    /**
     * Sets gltf data provider.
     *
     * The class maintains a strong reference to the object in
     * the 'gltfDataProvider' parameter until it (the class) is invalidated.
     *
     * @param onFinished Called when the model is loaded.
     */
    actual fun setData(
        gltfDataProvider: com.yandex.runtime.kmp.DataProviderWithId,
        onFinished: com.yandex.mapkit.kmp.map.Callback,
    ): Unit
}
/**
 * The style properties (sclae, unitType, etc.) of the model placemark.
 * Note: The current style cannot be modified directly - you must reset
 * it to apply changes.
 */
actual var Model.modelStyle: com.yandex.mapkit.kmp.map.ModelStyle
    get() = impl.modelStyle
    set(value) {
        impl.modelStyle = value
    }

open class YMKModelWrapper(override val impl: platform.YandexMapsMobile.YMKModel) : Model {
    override fun setData(
        gltfDataProvider: com.yandex.runtime.kmp.DataProviderWithId,
        onFinished: com.yandex.mapkit.kmp.map.Callback,
    ): Unit {
        return impl.setDataWithGltfDataProvider(
            gltfDataProvider.let { com.yandex.runtime.kmp.DataProviderWithIdWrapper(it) },
            onFinished.let { com.yandex.mapkit.kmp.map.CallbackWrapper(it) },
        )
    }
}

