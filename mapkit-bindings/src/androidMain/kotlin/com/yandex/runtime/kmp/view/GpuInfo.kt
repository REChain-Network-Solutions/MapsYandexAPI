@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.runtime.kmp.view

actual object GpuInfoManager {
    actual fun glGpuVendor(): String {
        return com.yandex.runtime.view.GpuInfoManager.glGpuVendor()
    }

    actual fun glGpuRenderer(): String {
        return com.yandex.runtime.view.GpuInfoManager.glGpuRenderer()
    }
}

