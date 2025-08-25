@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.runtime.kmp

/**
 * Universal key-value data.
 */
actual typealias KeyValuePair = com.yandex.runtime.KeyValuePair

/**
 * Data key. For example, "currency".
 */
actual val KeyValuePair.mpKey: String
    get() = key
/**
 * Data value. For example, "USD".
 */
actual val KeyValuePair.mpValue: String
    get() = value

actual object KeyValuePairFactory {
    actual fun create(
        key: String,
        value: String,
    ): KeyValuePair {
        return KeyValuePair(
            key,
            value,
        )
    }
}

