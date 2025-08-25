@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

actual interface BaseDataSourceBuilder {
    val impl: platform.YandexMapsMobile.YMKBaseDataSourceBuilder

    /**
     * The class does not retain the object in the 'urlProvider' parameter.
     * It is your responsibility to maintain a strong reference to
     * the target object while it is attached to a class.
     */
    actual fun setImageUrlProvider(
        urlProvider: com.yandex.mapkit.kmp.images.ImageUrlProvider,
    ): Unit

    /**
     * Tells if this object is valid or not. Any method called on an invalid
     * object will throw an exception. The object becomes invalid only on UI
     * thread, and only when its implementation depends on objects already
     * destroyed by now. Please refer to general docs about the interface for
     * details on its invalidation.
     */
    actual fun isValid(): Boolean
}

open class YMKBaseDataSourceBuilderWrapper(override val impl: platform.YandexMapsMobile.YMKBaseDataSourceBuilder) : BaseDataSourceBuilder {
    override fun setImageUrlProvider(
        urlProvider: com.yandex.mapkit.kmp.images.ImageUrlProvider,
    ): Unit {
        return impl.setImageUrlProviderWithUrlProvider(
            urlProvider.let { com.yandex.mapkit.kmp.images.ImageUrlProviderWrapper(it) },
        )
    }

    override fun isValid(): Boolean = impl.isValid()
}

