@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

actual object SearchUtils {
    actual fun makeBusinessUri(
        oid: String,
    ): String {
        return com.yandex.mapkit.search.SearchUtils.makeBusinessUri(
            oid,
        )
    }
}

