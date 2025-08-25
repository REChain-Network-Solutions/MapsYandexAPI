@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.runtime.kmp

/**
 * Universal key-value data.
 */
actual typealias KeyValuePair = platform.YandexMapsMobile.YRTKeyValuePair

/**
 * Data key. For example, "currency".
 */
actual val KeyValuePair.mpKey: String
    get() = key()
/**
 * Data value. For example, "USD".
 */
actual val KeyValuePair.mpValue: String
    get() = value()

actual object KeyValuePairFactory {
    actual fun create(
        key: String,
        value: String,
    ): KeyValuePair {
        return KeyValuePair.keyValuePairWithKey(
            key,
            value,
        )
    }
}

