@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.runtime.kmp

/**
 * Local error has occurred.
 */
actual typealias LocalError = com.yandex.runtime.LocalError

/**
 * Disk is full.
 */
actual typealias DiskFullError = com.yandex.runtime.DiskFullError

/**
 * Disk is corrupted.
 */
actual typealias DiskCorruptError = com.yandex.runtime.DiskCorruptError

/**
 * The application does not have the required write permissions.
 */
actual typealias DiskWriteAccessError = com.yandex.runtime.DiskWriteAccessError

