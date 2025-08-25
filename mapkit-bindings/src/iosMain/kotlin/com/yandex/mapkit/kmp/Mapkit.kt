@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp

import platform.YandexMapsMobile.setApiKey
import platform.YandexMapsMobile.setLocale
import platform.YandexMapsMobile.setUserId
import platform.YandexMapsMobile.sharedInstance

/**
 * Provides access to all services in the SDK.
 *
 * Initialize the MapKit factory before using this class.
 *
 * @attention MapKit holds listener/delegate objects by weak references.
 * You need to have strong references to them somewhere in the client
 * code.
 */
actual interface MapKit {
    val impl: platform.YandexMapsMobile.YMKMapKit

    /**
     * @hidden
     * Sets the key for API access.
     *
     * @param key Key issued in the Developer Dashboard.
     */
    actual fun setApiKey(
        key: String,
    ): Unit

    /**
     * Sets the user id. Don't use this method directly. Use
     * `MapKitFactory.setUserId` instead
     *
     * @param id User id is your own identifier for all mapkit requests
     */
    actual fun setUserId(
        id: String,
    ): Unit

    /**
     * Notifies MapKit when the application resumes the foreground state.
     */
    actual fun onStart(): Unit

    /**
     * Notifies MapKit when the application pauses and goes to the
     * background.
     */
    actual fun onStop(): Unit

    /**
     * Notifies MapKit when the application will terminate.
     */
    actual fun onTerminate(): Unit

    /**
     * @hidden
     * Creates an internal "window" object that is used to show the map.
     *
     * Do not call this method - it is for internal use only. To show the
     * map, please use the corresponding map "view" object.
     */
    actual fun createMapWindow(
        platformView: com.yandex.runtime.kmp.view.PlatformView,
    ): com.yandex.mapkit.kmp.map.MapWindow

    /**
     * @hidden
     * Creates an internal "window" object that is used to show the map with
     * a custom scale factor. The scale factor is equal to the number of
     * pixels per device-independent point.
     *
     * Do not call this method - it is for internal use only. To show the
     * map, please use the corresponding map "view" object.
     */
    actual fun createMapWindow(
        platformView: com.yandex.runtime.kmp.view.PlatformView,
        customScaleFactor: Float,
    ): com.yandex.mapkit.kmp.map.MapWindow

    /**
     * Creates an OffscreenMapWindow
     */
    actual fun createOffscreenMapWindow(
        width: Int,
        height: Int,
    ): com.yandex.mapkit.kmp.map.OffscreenMapWindow

    /**
     * Creates an OffscreenMapWindow with custom scale factor The scale
     * factor is equal to the number of pixels per device-independent point.
     */
    actual fun createOffscreenMapWindow(
        width: Int,
        height: Int,
        customScaleFactor: Float,
    ): com.yandex.mapkit.kmp.map.OffscreenMapWindow

    /**
     * Tells if this object is valid or not. Any method called on an invalid
     * object will throw an exception. The object becomes invalid only on UI
     * thread, and only when its implementation depends on objects already
     * destroyed by now. Please refer to general docs about the interface for
     * details on its invalidation.
     */
    actual fun isValid(): Boolean

    actual companion object {

        actual fun setLocale(locale: String?) {
            platform.YandexMapsMobile.YMKMapKit.setLocale(locale)
        }

        actual fun setApiKey(apiKey: String) {
            platform.YandexMapsMobile.YMKMapKit.setApiKey(apiKey)
        }

        actual fun setUserId(userId: String) {
            platform.YandexMapsMobile.YMKMapKit.setUserId(userId)
        }

        actual fun getInstance(): MapKit =
            platform.YandexMapsMobile.YMKMapKit.sharedInstance().let(::YMKMapKitWrapper)
    }
}

/**
 * Returns the version of the MapKit bundle.
 */
actual val MapKit.version: String
    get() = impl.version

open class YMKMapKitWrapper(override val impl: platform.YandexMapsMobile.YMKMapKit) : MapKit {

    override fun setApiKey(
        key: String,
    ): Unit {
        return impl.setApiKeyWithKey(
            key,
        )
    }

    override fun setUserId(
        id: String,
    ): Unit {
        return impl.setUserIdWithId(
            id,
        )
    }

    override fun onStart(): Unit {
        return impl.onStart()
    }

    override fun onStop(): Unit {
        return impl.onStop()
    }

    override fun onTerminate(): Unit {
        return impl.onTerminate()
    }

    override fun createMapWindow(
        platformView: com.yandex.runtime.kmp.view.PlatformView,
    ): com.yandex.mapkit.kmp.map.MapWindow {
        return impl.createMapWindowWithPlatformView(
            platformView,
        ).let { com.yandex.mapkit.kmp.map.YMKMapWindowWrapper(it) }
    }

    override fun createMapWindow(
        platformView: com.yandex.runtime.kmp.view.PlatformView,
        customScaleFactor: Float,
    ): com.yandex.mapkit.kmp.map.MapWindow {
        return impl.createMapWindowWithPlatformView(
            platformView,
            customScaleFactor,
        ).let { com.yandex.mapkit.kmp.map.YMKMapWindowWrapper(it) }
    }

    override fun createOffscreenMapWindow(
        width: Int,
        height: Int,
    ): com.yandex.mapkit.kmp.map.OffscreenMapWindow {
        return impl.createOffscreenMapWindowWithWidth(
            width.toLong(),
            height.toLong(),
        ).let { com.yandex.mapkit.kmp.map.YMKOffscreenMapWindowWrapper(it) }
    }

    override fun createOffscreenMapWindow(
        width: Int,
        height: Int,
        customScaleFactor: Float,
    ): com.yandex.mapkit.kmp.map.OffscreenMapWindow {
        return impl.createOffscreenMapWindowWithWidth(
            width.toLong(),
            height.toLong(),
            customScaleFactor,
        ).let { com.yandex.mapkit.kmp.map.YMKOffscreenMapWindowWrapper(it) }
    }

    override fun isValid(): Boolean = impl.isValid()
}

