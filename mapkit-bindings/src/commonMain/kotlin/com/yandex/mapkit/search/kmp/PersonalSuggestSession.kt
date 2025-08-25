@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Interface to handle remove all user personalized suggest request.
 * Allows request cancellation and retry.
 */
expect interface DeleteAllPersonalSuggestSession {
    /**
     * Cancels the current request.
     */
    fun cancel(): Unit

    /**
     * Retries the last request. If there is an active request, it is
     * cancelled.
     *
     * @param personalSuggestListener Listener to handle result.
     */
    fun retry(
        personalSuggestListener: com.yandex.mapkit.search.kmp.DeleteAllPersonalSuggestSessionListener,
    ): Unit
}

expect interface DeleteAllPersonalSuggestSessionListener {
    /**
     * Callback for result processing.
     */
    fun onPersonalSuggestSuccess(): Unit

    /**
     * Callback for error processing.
     *
     * @param error Error information.
     */
    fun onPersonalSuggestError(
        error: com.yandex.runtime.kmp.Error,
    ): Unit
}

