@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

expect interface SuggestSession {
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
    fun suggest(
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
    fun reset(): Unit
}

expect interface SuggestSessionSuggestListener {
    /**
     * Callback for results processing.
     *
     * @param suggest List of suggest results.
     */
    fun onResponse(
        suggest: com.yandex.mapkit.search.kmp.SuggestResponse,
    ): Unit

    /**
     * Callback for error processing.
     *
     * @param error Error information.
     */
    fun onError(
        error: com.yandex.runtime.kmp.Error,
    ): Unit
}

