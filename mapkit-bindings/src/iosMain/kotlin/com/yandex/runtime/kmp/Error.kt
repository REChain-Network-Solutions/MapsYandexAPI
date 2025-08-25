@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.runtime.kmp

/**
 * An error has occurred.
 */
actual interface Error {
    val impl: platform.YandexMapsMobile.YRTError

    /**
     * Tells if this object is valid or not. Any method called on an invalid
     * object will throw an exception. The object becomes invalid only on UI
     * thread, and only when its implementation depends on objects already
     * destroyed by now. Please refer to general docs about the interface for
     * details on its invalidation.
     */
    actual fun isValid(): Boolean
}

open class YRTErrorWrapper(override val impl: platform.YandexMapsMobile.YRTError) : Error {
    override fun isValid(): Boolean = impl.isValid()
}

