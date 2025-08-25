@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST", "REDUNDANT_ELSE_IN_WHEN")

package com.yandex.mapkit.kmp.map

import kotlinx.cinterop.objcPtr

actual interface Callback {
    actual fun onTaskFinished(): Unit
}

internal class CallbackWrapper(val impl: Callback): platform.YandexMapsMobile.YMKCallback {
    override fun invoke() {
            impl.onTaskFinished()
    }
}

