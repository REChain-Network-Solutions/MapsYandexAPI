@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

actual interface Search {
    val impl: platform.YandexMapsMobile.YMKSearch


    /**
     * Creates a manager that allows to search for various geographical
     * objects using a variety of parameters.
     */
    actual fun createSearchManager(
        searchManagerType: com.yandex.mapkit.search.kmp.SearchManagerType,
    ): com.yandex.mapkit.search.kmp.SearchManager

    /**
     * Tells if this object is valid or not. Any method called on an invalid
     * object will throw an exception. The object becomes invalid only on UI
     * thread, and only when its implementation depends on objects already
     * destroyed by now. Please refer to general docs about the interface for
     * details on its invalidation.
     */
    actual fun isValid(): Boolean
}

open class YMKSearchWrapper(override val impl: platform.YandexMapsMobile.YMKSearch) : Search {

    override fun createSearchManager(
        searchManagerType: com.yandex.mapkit.search.kmp.SearchManagerType,
    ): com.yandex.mapkit.search.kmp.SearchManager {
        return impl.createSearchManagerWithSearchManagerType(
            searchManagerType.fromKmp(),
        ).let { com.yandex.mapkit.search.kmp.YMKSearchManagerWrapper(it) }
    }

    override fun isValid(): Boolean = impl.isValid()
}

actual object SearchFactory {
    actual val instance: com.yandex.mapkit.search.kmp.Search
        get() = platform.YandexMapsMobile.YMKSearchFactory.instance().let { com.yandex.mapkit.search.kmp.YMKSearchWrapper(it) }
}

