@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.runtime.kmp.logging

expect object Logger {
    fun error(
        str: String,
    ): Unit

    fun warn(
        str: String,
    ): Unit

    fun info(
        str: String,
    ): Unit

    fun debug(
        str: String,
    ): Unit
}

