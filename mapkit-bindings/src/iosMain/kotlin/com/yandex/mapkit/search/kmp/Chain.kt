@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

/**
 * Single chain (group of companies) description.
 */
actual typealias Chain = platform.YandexMapsMobile.YMKSearchChain

/**
 * Chain identifier.
 */
actual val Chain.mpId: String
    get() = id()
/**
 * Chain name.
 */
actual val Chain.mpName: String
    get() = name()

actual object ChainFactory {
    actual fun create(
        id: String,
        name: String,
    ): Chain {
        return Chain.chainWithId(
            id,
            name,
        )
    }
}

