@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.runtime.kmp

/**
 * Universal key-value data.
 */
expect class KeyValuePair

/**
 * Data key. For example, "currency".
 */
expect val KeyValuePair.mpKey: String
/**
 * Data value. For example, "USD".
 */
expect val KeyValuePair.mpValue: String

expect object KeyValuePairFactory {
    fun create(
        key: String,
        value: String,
    ): KeyValuePair
}

