@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.runtime.kmp

import com.yandex.runtime.kmp.internal.fromKmp
import com.yandex.runtime.kmp.internal.toKmp

/**
 * Local error has occurred.
 */
actual interface LocalError : com.yandex.runtime.kmp.Error {
    override val impl: platform.YandexMapsMobile.YRTLocalError
}

open class YRTLocalErrorWrapper(override val impl: platform.YandexMapsMobile.YRTLocalError) : LocalError, com.yandex.runtime.kmp.YRTErrorWrapper(impl)

/**
 * Disk is full.
 */
actual interface DiskFullError : com.yandex.runtime.kmp.LocalError {
    override val impl: platform.YandexMapsMobile.YRTDiskFullError
}

open class YRTDiskFullErrorWrapper(override val impl: platform.YandexMapsMobile.YRTDiskFullError) : DiskFullError, com.yandex.runtime.kmp.YRTLocalErrorWrapper(impl)

/**
 * Disk is corrupted.
 */
actual interface DiskCorruptError : com.yandex.runtime.kmp.LocalError {
    override val impl: platform.YandexMapsMobile.YRTDiskCorruptError
}

open class YRTDiskCorruptErrorWrapper(override val impl: platform.YandexMapsMobile.YRTDiskCorruptError) : DiskCorruptError, com.yandex.runtime.kmp.YRTLocalErrorWrapper(impl)

/**
 * The application does not have the required write permissions.
 */
actual interface DiskWriteAccessError : com.yandex.runtime.kmp.DiskCorruptError {
    override val impl: platform.YandexMapsMobile.YRTDiskWriteAccessError
}

open class YRTDiskWriteAccessErrorWrapper(override val impl: platform.YandexMapsMobile.YRTDiskWriteAccessError) : DiskWriteAccessError, com.yandex.runtime.kmp.YRTDiskCorruptErrorWrapper(impl)

