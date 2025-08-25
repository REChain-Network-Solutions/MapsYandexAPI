@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.kmp.map.MapWindow
import com.yandex.mapkit.kmp.map.OffscreenMapWindow
import com.yandex.runtime.kmp.view.PlatformView

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

    val versionString: String

    actual companion object {

        actual fun setLocale(locale: String?) {
            MapKitFactory.setLocale(locale)
        }

        actual fun setApiKey(apiKey: String) {
            MapKitFactory.setApiKey(apiKey)
        }

        actual fun setUserId(userId: String) {
            MapKitFactory.setUserId(userId)
        }

        actual fun getInstance(): MapKit = MapKitFactory.getInstance().let(::MapkitWrapper)
    }
}

class MapkitWrapper(val impl: com.yandex.mapkit.MapKit) : MapKit {

    override val versionString: String
        get() = impl.version

    override fun setApiKey(key: String) {
        impl.setApiKey(key)
    }

    override fun setUserId(id: String) {
        impl.setUserId(id)
    }

    override fun onStart() {
        impl.onStart()
    }

    override fun onStop() {
        impl.onStop()
    }

    override fun onTerminate() {
        impl.onTerminate()
    }

    override fun createMapWindow(platformView: PlatformView): MapWindow {
        return impl.createMapWindow(platformView)
    }

    override fun createMapWindow(
        platformView: PlatformView,
        customScaleFactor: Float
    ): MapWindow {
        return impl.createMapWindow(platformView, customScaleFactor)
    }

    override fun createOffscreenMapWindow(
        width: Int,
        height: Int
    ): OffscreenMapWindow {
        return impl.createOffscreenMapWindow(width, height)
    }

    override fun createOffscreenMapWindow(
        width: Int,
        height: Int,
        customScaleFactor: Float
    ): OffscreenMapWindow {
        return impl.createOffscreenMapWindow(width, height, customScaleFactor)
    }

    override fun isValid(): Boolean {
        return impl.isValid
    }
}


/**
 * Returns the version of the MapKit bundle.
 */
actual val MapKit.version: String
    get() = versionString

