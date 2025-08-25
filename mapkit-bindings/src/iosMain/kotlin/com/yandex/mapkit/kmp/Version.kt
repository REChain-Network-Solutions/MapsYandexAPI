@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp

/**
 * Dot-separated version string. Alphabetical symbols are ignored
 * without errors.
 *
 * Examples of correct versions: "1.2.3", "2.0.0-2", "2014.04.25", "42".
 */
actual typealias Version = platform.YandexMapsMobile.YMKVersion

/**
 * Version string.
 */
actual val Version.mpStr: String
    get() = str()

actual object VersionFactory {
    actual fun create(
        str: String,
    ): Version {
        return Version.versionWithStr(
            str,
        )
    }
}

