@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

expect interface Search {

    /**
     * Creates a manager that allows to search for various geographical
     * objects using a variety of parameters.
     */
    fun createSearchManager(
        searchManagerType: com.yandex.mapkit.search.kmp.SearchManagerType,
    ): com.yandex.mapkit.search.kmp.SearchManager

    /**
     * Tells if this object is valid or not. Any method called on an invalid
     * object will throw an exception. The object becomes invalid only on UI
     * thread, and only when its implementation depends on objects already
     * destroyed by now. Please refer to general docs about the interface for
     * details on its invalidation.
     */
    fun isValid(): Boolean
}

expect object SearchFactory {
    val instance: com.yandex.mapkit.search.kmp.Search
}

