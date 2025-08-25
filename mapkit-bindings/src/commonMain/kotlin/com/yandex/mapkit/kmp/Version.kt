@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

/**
 * Dot-separated version string. Alphabetical symbols are ignored
 * without errors.
 *
 * Examples of correct versions: "1.2.3", "2.0.0-2", "2014.04.25", "42".
 */
expect class Version

/**
 * Version string.
 */
expect val Version.mpStr: String

expect object VersionFactory {
    fun create(
        str: String,
    ): Version
}

