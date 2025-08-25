@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.runtime.kmp

/**
 * Provides any data.
 */
expect interface DataProviderWithId {
    /**
     * Use the same id for the identical data, to prevent repeated loading
     * of the same data into RAM and VRAM.
     *
     * This method may be called on any thread. Its implementation must be thread-safe.
     */
    fun providerId(): String

    /**
     * Returns data.
     *
     * This method will be called on a background thread.
     */
    fun load(): ByteArray
}

