@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.runtime.kmp

/**
 * Local error has occurred.
 */
expect interface LocalError : com.yandex.runtime.kmp.Error

/**
 * Disk is full.
 */
expect interface DiskFullError : com.yandex.runtime.kmp.LocalError

/**
 * Disk is corrupted.
 */
expect interface DiskCorruptError : com.yandex.runtime.kmp.LocalError

/**
 * The application does not have the required write permissions.
 */
expect interface DiskWriteAccessError : com.yandex.runtime.kmp.DiskCorruptError

