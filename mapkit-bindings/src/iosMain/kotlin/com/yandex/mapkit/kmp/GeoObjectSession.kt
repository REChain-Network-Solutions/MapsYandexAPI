@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST", "REDUNDANT_ELSE_IN_WHEN")

package com.yandex.mapkit.kmp

import com.yandex.runtime.kmp.internal.fromKmp
import com.yandex.runtime.kmp.internal.toKmp
import kotlinx.cinterop.objcPtr

/**
 * The interface for working with the session.
 */
actual interface GeoObjectSession {
    val impl: platform.YandexMapsMobile.YMKGeoObjectSession

    /**
     * Cancels the current request.
     */
    actual fun cancel(): Unit

    /**
     * Retries the last request. Cancels the current request if it is
     * active.
     */
    actual fun retry(
        objListener: com.yandex.mapkit.kmp.GeoObjectSessionGeoObjectListener,
    ): Unit
}

open class YMKGeoObjectSessionWrapper(override val impl: platform.YandexMapsMobile.YMKGeoObjectSession) : GeoObjectSession {
    override fun cancel(): Unit {
        return impl.cancel()
    }

    override fun retry(
        objListener: com.yandex.mapkit.kmp.GeoObjectSessionGeoObjectListener,
    ): Unit {
        return impl.retryWithGeoObjectHandler(
            objListener.let { com.yandex.mapkit.kmp.GeoObjectSessionGeoObjectListenerWrapper(it) },
        )
    }
}

actual interface GeoObjectSessionGeoObjectListener {
    actual fun onGeoObjectResult(
        obj: com.yandex.mapkit.kmp.GeoObject,
    ): Unit
    actual fun onGeoObjectError(
        error: com.yandex.runtime.kmp.Error,
    ): Unit
}

internal class GeoObjectSessionGeoObjectListenerWrapper(val impl: GeoObjectSessionGeoObjectListener): platform.YandexMapsMobile.YMKGeoObjectSessionGeoObjectHandler {
    override fun invoke(
        obj: platform.YandexMapsMobile.YMKGeoObject?,
        error: platform.Foundation.NSError?,
    ) {
        if (error == null) {
            impl.onGeoObjectResult(
                obj!!,
            )
        } else {
            impl.onGeoObjectError(error.toKmp())
        }
    }
}

