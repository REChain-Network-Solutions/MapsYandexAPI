@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.runtime.kmp

import com.yandex.runtime.kmp.internal.toByteArray
import com.yandex.runtime.kmp.internal.toNSData
import kotlinx.cinterop.objcPtr

/**
 * Provides any data.
 */
actual interface DataProviderWithId {
    /**
     * Use the same id for the identical data, to prevent repeated loading
     * of the same data into RAM and VRAM.
     *
     * This method may be called on any thread. Its implementation must be thread-safe.
     */
    actual fun providerId(): String

    /**
     * Returns data.
     *
     * This method will be called on a background thread.
     */
    actual fun load(): ByteArray
}

class DataProviderWithIdWrapper(val impl: DataProviderWithId) : platform.darwin.NSObject(), platform.YandexMapsMobile.YRTDataProviderWithIdProtocol {
    override fun providerId(): String {
        return impl.providerId()
    }

    override fun load(): platform.Foundation.NSData {
        return impl.load().toNSData()
    }

    internal companion object
}

open class YRTDataProviderWithIdProtocolWrapper(val impl: platform.YandexMapsMobile.YRTDataProviderWithIdProtocol) : DataProviderWithId {
    override fun providerId(): String {
        return impl.providerId()
    }

    override fun load(): ByteArray {
        return impl.load().toByteArray()
    }
}

