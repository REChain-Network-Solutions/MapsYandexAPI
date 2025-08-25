@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

actual typealias Search = com.yandex.mapkit.search.Search

actual object SearchFactory {
    actual val instance: com.yandex.mapkit.search.kmp.Search
        get() = com.yandex.mapkit.search.SearchFactory.getInstance()
}

