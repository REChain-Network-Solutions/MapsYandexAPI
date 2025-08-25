@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Single chain (group of companies) description.
 */
actual typealias Chain = com.yandex.mapkit.search.Chain

/**
 * Chain identifier.
 */
actual val Chain.mpId: String
    get() = id
/**
 * Chain name.
 */
actual val Chain.mpName: String
    get() = name

actual object ChainFactory {
    actual fun create(
        id: String,
        name: String,
    ): Chain {
        return Chain(
            id,
            name,
        )
    }
}

