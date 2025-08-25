@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Single chain (group of companies) description.
 */
expect class Chain

/**
 * Chain identifier.
 */
expect val Chain.mpId: String
/**
 * Chain name.
 */
expect val Chain.mpName: String

expect object ChainFactory {
    fun create(
        id: String,
        name: String,
    ): Chain
}

