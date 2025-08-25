@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

import com.yandex.runtime.kmp.internal.fromKmp
import com.yandex.runtime.kmp.internal.toKmp
import kotlinx.cinterop.objcPtr

/**
 * Interface to handle remove all user personalized suggest request.
 * Allows request cancellation and retry.
 */
actual interface DeleteAllPersonalSuggestSession {
    val impl: platform.YandexMapsMobile.YMKSearchDeleteAllPersonalSuggestSession

    /**
     * Cancels the current request.
     */
    actual fun cancel(): Unit

    /**
     * Retries the last request. If there is an active request, it is
     * cancelled.
     *
     * @param personalSuggestListener Listener to handle result.
     */
    actual fun retry(
        personalSuggestListener: com.yandex.mapkit.search.kmp.DeleteAllPersonalSuggestSessionListener,
    ): Unit
}

open class YMKSearchDeleteAllPersonalSuggestSessionWrapper(override val impl: platform.YandexMapsMobile.YMKSearchDeleteAllPersonalSuggestSession) : DeleteAllPersonalSuggestSession {
    override fun cancel(): Unit {
        return impl.cancel()
    }

    override fun retry(
        personalSuggestListener: com.yandex.mapkit.search.kmp.DeleteAllPersonalSuggestSessionListener,
    ): Unit {
        return impl.retryWithPersonalSuggestHandler(
            personalSuggestListener.let { com.yandex.mapkit.search.kmp.DeleteAllPersonalSuggestSessionListenerWrapper(it) },
        )
    }
}

actual interface DeleteAllPersonalSuggestSessionListener {
    actual fun onPersonalSuggestSuccess(): Unit
    actual fun onPersonalSuggestError(
        error: com.yandex.runtime.kmp.Error,
    ): Unit
}

internal class DeleteAllPersonalSuggestSessionListenerWrapper(val impl: DeleteAllPersonalSuggestSessionListener): platform.YandexMapsMobile.YMKSearchDeleteAllPersonalSuggestSessionPersonalSuggestHandler {
    override fun invoke(
        error: platform.Foundation.NSError?,
    ) {
        if (error == null) {
            impl.onPersonalSuggestSuccess()
        } else {
            impl.onPersonalSuggestError(error.toKmp())
        }
    }
}

