@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

/**
 * The interface for working with the session.
 */
expect interface GeoObjectSession {
    /**
     * Cancels the current request.
     */
    fun cancel(): Unit

    /**
     * Retries the last request. Cancels the current request if it is
     * active.
     */
    fun retry(
        objListener: com.yandex.mapkit.kmp.GeoObjectSessionGeoObjectListener,
    ): Unit
}

expect interface GeoObjectSessionGeoObjectListener {
    /**
     * Called when a user is requesting detailed info for the specified
     * object.
     */
    fun onGeoObjectResult(
        obj: com.yandex.mapkit.kmp.GeoObject,
    ): Unit

    /**
     * Called when an error occurs.
     */
    fun onGeoObjectError(
        error: com.yandex.runtime.kmp.Error,
    ): Unit
}

