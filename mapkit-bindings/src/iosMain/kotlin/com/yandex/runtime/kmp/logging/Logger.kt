@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.runtime.kmp.logging

actual object Logger {
    actual fun error(
        str: String,
    ): Unit {
        return platform.YandexMapsMobile.YRTLogger.errorWithStr(
            str,
        )
    }

    actual fun warn(
        str: String,
    ): Unit {
        return platform.YandexMapsMobile.YRTLogger.warnWithStr(
            str,
        )
    }

    actual fun info(
        str: String,
    ): Unit {
        return platform.YandexMapsMobile.YRTLogger.infoWithStr(
            str,
        )
    }

    actual fun debug(
        str: String,
    ): Unit {
        return platform.YandexMapsMobile.YRTLogger.debugWithStr(
            str,
        )
    }
}

