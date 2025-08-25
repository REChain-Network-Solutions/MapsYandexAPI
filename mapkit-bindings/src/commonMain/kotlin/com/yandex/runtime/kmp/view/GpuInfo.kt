@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.runtime.kmp.view

expect object GpuInfoManager {
    fun glGpuVendor(): String

    fun glGpuRenderer(): String
}

