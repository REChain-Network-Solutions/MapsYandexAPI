@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.runtime.kmp.view

actual object GpuInfoManager {
    actual fun glGpuVendor(): String {
        return platform.YandexMapsMobile.YRTGpuInfoManager.glGpuVendor()
    }

    actual fun glGpuRenderer(): String {
        return platform.YandexMapsMobile.YRTGpuInfoManager.glGpuRenderer()
    }
}

