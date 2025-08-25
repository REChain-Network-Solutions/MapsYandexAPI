@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

expect object SearchUtils {
    fun makeBusinessUri(
        oid: String,
    ): String
}

