@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

actual object SearchUtils {
    actual fun makeBusinessUri(
        oid: String,
    ): String {
        return platform.YandexMapsMobile.YMKSearchUtils.makeBusinessUriWithOid(
            oid,
        )
    }
}

