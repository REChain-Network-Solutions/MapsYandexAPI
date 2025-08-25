@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

import com.yandex.runtime.kmp.internal.fromKmp
import com.yandex.runtime.kmp.internal.toKmp
import kotlinx.cinterop.objcPtr

actual interface SuggestSession {
    val impl: platform.YandexMapsMobile.YMKSearchSuggestSession

    /**
     * Begin a suggest request. The current request is cancelled, if
     * present.
     *
     * @param text Text to get suggestions for.
     * @param window Current map window position.
     * @param suggestOptions Various additional suggest parameters. See the
     * [SuggestOptions] definition for details.
     * @param suggestListener Function called when the result is ready.
     */
    actual fun suggest(
        text: String,
        window: com.yandex.mapkit.kmp.geometry.BoundingBox,
        suggestOptions: com.yandex.mapkit.search.kmp.SuggestOptions,
        suggestListener: com.yandex.mapkit.search.kmp.SuggestSessionSuggestListener,
    ): Unit

    /**
     * Cancels current suggest request and resets internal state. Next
     * queries via this suggest session will be seen as a new suggest
     * session from backend point of view.
     */
    actual fun reset(): Unit
}

open class YMKSearchSuggestSessionWrapper(override val impl: platform.YandexMapsMobile.YMKSearchSuggestSession) : SuggestSession {
    override fun suggest(
        text: String,
        window: com.yandex.mapkit.kmp.geometry.BoundingBox,
        suggestOptions: com.yandex.mapkit.search.kmp.SuggestOptions,
        suggestListener: com.yandex.mapkit.search.kmp.SuggestSessionSuggestListener,
    ): Unit {
        return impl.suggestWithText(
            text,
            window,
            suggestOptions,
            suggestListener.let { com.yandex.mapkit.search.kmp.SuggestSessionSuggestListenerWrapper(it) },
        )
    }

    override fun reset(): Unit {
        return impl.reset()
    }
}

actual interface SuggestSessionSuggestListener {
    actual fun onResponse(
        suggest: com.yandex.mapkit.search.kmp.SuggestResponse,
    ): Unit
    actual fun onError(
        error: com.yandex.runtime.kmp.Error,
    ): Unit
}

internal class SuggestSessionSuggestListenerWrapper(val impl: SuggestSessionSuggestListener): platform.YandexMapsMobile.YMKSearchSuggestSessionResponseHandler {
    override fun invoke(
        suggest: platform.YandexMapsMobile.YMKSuggestResponse?,
        error: platform.Foundation.NSError?,
    ) {
        if (error == null) {
            impl.onResponse(
                suggest!!,
            )
        } else {
            impl.onError(error.toKmp())
        }
    }
}

