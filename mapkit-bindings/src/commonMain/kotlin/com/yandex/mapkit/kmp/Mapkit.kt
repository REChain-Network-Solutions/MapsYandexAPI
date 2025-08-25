@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

/**
 * Provides access to all services in the SDK.
 *
 * Initialize the MapKit factory before using this class.
 *
 * @attention MapKit holds listener/delegate objects by weak references.
 * You need to have strong references to them somewhere in the client
 * code.
 */
expect interface MapKit {

    /**
     * @hidden
     * Sets the key for API access.
     *
     * @param key Key issued in the Developer Dashboard.
     */
    fun setApiKey(
        key: String,
    ): Unit

    /**
     * Sets the user id. Don't use this method directly. Use
     * `MapKitFactory.setUserId` instead
     *
     * @param id User id is your own identifier for all mapkit requests
     */
    fun setUserId(
        id: String,
    ): Unit

    /**
     * Notifies MapKit when the application resumes the foreground state.
     */
    fun onStart(): Unit

    /**
     * Notifies MapKit when the application pauses and goes to the
     * background.
     */
    fun onStop(): Unit

    /**
     * Notifies MapKit when the application will terminate.
     */
    fun onTerminate(): Unit

    /**
     * @hidden
     * Creates an internal "window" object that is used to show the map.
     *
     * Do not call this method - it is for internal use only. To show the
     * map, please use the corresponding map "view" object.
     */
    fun createMapWindow(
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
    fun createMapWindow(
        platformView: com.yandex.runtime.kmp.view.PlatformView,
        customScaleFactor: Float,
    ): com.yandex.mapkit.kmp.map.MapWindow

    /**
     * Creates an OffscreenMapWindow
     */
    fun createOffscreenMapWindow(
        width: Int,
        height: Int,
    ): com.yandex.mapkit.kmp.map.OffscreenMapWindow

    /**
     * Creates an OffscreenMapWindow with custom scale factor The scale
     * factor is equal to the number of pixels per device-independent point.
     */
    fun createOffscreenMapWindow(
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
    fun isValid(): Boolean

    public companion object {

        public fun setLocale(locale: String?)

        public fun setApiKey(apiKey: String)

        public fun setUserId(userId: String)

        public fun getInstance(): MapKit

    }
}

/**
 * Returns the version of the MapKit bundle.
 */
expect val MapKit.version: String

