@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.runtime.kmp.logging

actual object Logger {
    actual fun error(
        str: String,
    ): Unit {
        return com.yandex.runtime.logging.Logger.error(
            str,
        )
    }

    actual fun warn(
        str: String,
    ): Unit {
        return com.yandex.runtime.logging.Logger.warn(
            str,
        )
    }

    actual fun info(
        str: String,
    ): Unit {
        return com.yandex.runtime.logging.Logger.info(
            str,
        )
    }

    actual fun debug(
        str: String,
    ): Unit {
        return com.yandex.runtime.logging.Logger.debug(
            str,
        )
    }
}

