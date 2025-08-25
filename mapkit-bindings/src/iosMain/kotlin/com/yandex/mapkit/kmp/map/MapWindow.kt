@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

/**
 * Handles the MapWindow element.
 */
actual interface MapWindow {
    val impl: platform.YandexMapsMobile.YMKMapWindow

    /**
     * Window width in physical pixels
     */
    actual fun width(): Int

    /**
     * Window height in physical pixels
     */
    actual fun height(): Int

    /**
     * Transforms the position from world coordinates to screen coordinates.
     *
     * @param worldPoint Latitude and longitude information.
     *
     * @return The point in screen space corresponding to worldPoint;
     * returns none if the point is behind the camera.
     */
    actual fun worldToScreen(
        worldPoint: com.yandex.mapkit.kmp.geometry.Point,
    ): com.yandex.mapkit.kmp.ScreenPoint?

    /**
     * Transforms coordinates from screen space to world space.
     *
     * @param screenPoint The point in screen coordinates relative to the
     * top left of the map. These coordinates are in physical pixels and not
     * in device independent (virtual) pixels.
     *
     * @return Latitude and longitude information.
     */
    actual fun screenToWorld(
        screenPoint: com.yandex.mapkit.kmp.ScreenPoint,
    ): com.yandex.mapkit.kmp.geometry.Point?

    /**
     * Adds a SizeChangedListener.
     *
     * The class does not retain the object in the 'sizeChangedListener' parameter.
     * It is your responsibility to maintain a strong reference to
     * the target object while it is attached to a class.
     */
    actual fun addSizeChangedListener(
        sizeChangedListener: com.yandex.mapkit.kmp.map.SizeChangedListener,
    ): Unit

    /**
     * Removes a SizeChangedListener.
     */
    actual fun removeSizeChangedListener(
        sizeChangedListener: com.yandex.mapkit.kmp.map.SizeChangedListener,
    ): Unit

    /**
     * Allows to reduce CPU/GPU/battery usage in specific scenarios, where
     * lower framerate is acceptable. Valid range: (0, 60]. Default: 60.
     */
    actual fun setMaxFps(
        fps: Float,
    ): Unit

    /**
     * @hidden
     * Starts capturing performance metrics.
     */
    actual fun startPerformanceMetricsCapture(): Unit

    /**
     * @hidden
     * Stops capturing performance metrics and returns captured metrics as a
     * string.
     */
    actual fun stopPerformanceMetricsCapture(): String

    /**
     * @hidden
     * Starts capturing performance metrics.
     */
    actual fun startMemoryMetricsCapture(): Unit

    /**
     * @hidden
     * Stops capturing performance metrics and returns captured metrics as a
     * string.
     */
    actual fun stopMemoryMetricsCapture(): String

    /**
     * Adds additional surface to render frames on. A part of the frame with
     * center in focusPoint will be sent to surface. Dimesions of this part
     * are determined by dimensions of surface. If surface larger than map,
     * the map will be scaled to fit the surface This method is android only
     */
    actual fun addSurface(
        surface: com.yandex.runtime.kmp.view.Surface,
    ): Unit

    /**
     * Removes external surface. This method is android only
     */
    actual fun removeSurface(
        surface: com.yandex.runtime.kmp.view.Surface,
    ): Unit

    /**
     * Tells if this object is valid or not. Any method called on an invalid
     * object will throw an exception. The object becomes invalid only on UI
     * thread, and only when its implementation depends on objects already
     * destroyed by now. Please refer to general docs about the interface for
     * details on its invalidation.
     */
    actual fun isValid(): Boolean
}
/**
 * Gets the map interface.
 */
actual val MapWindow.map: com.yandex.mapkit.kmp.map.Map
    get() = impl.map.let { com.yandex.mapkit.kmp.map.YMKMapWrapper(it) }
/**
 * When using controls that overlay the map view, calculating the proper
 * camera position can be tricky. This property simplifies the task by
 * defining the area of interest (the focus rectangle) inside the view.
 * Map methods that calculate the camera position based on a world
 * bounding box ensure that this bounding box will fit into the
 * focusRect.
 *
 * For example, when using a semi-transparent control that overlays the
 * top half of the map view, define the focus rectangle as the lower
 * half of the view to ensure that object of interest appear in the
 * lower half of map view. In addition, if focusPoint is null all camera
 * movements will have the center of the lower half as their target.
 *
 * If focusRect is null, the whole map view is used instead.
 *
 * On iOS, if you change the focus rectangle in the
 * viewDidLayoutSubviews callback, it's recommended to call
 * MapView.layoutIfNeeded just before that action.
 *
 */
actual var MapWindow.focusRect: com.yandex.mapkit.kmp.ScreenRect?
    get() = impl.focusRect
    set(value) {
        impl.focusRect = value
    }
/**
 * The point on the screen that corresponds to camera position. Changing
 * camera position or focusPoint makes the new camera target appear
 * exactly at the focusPoint on screen.
 *
 * If focusPoint is null, the center of focusRect is used instead.
 *
 */
actual var MapWindow.focusPoint: com.yandex.mapkit.kmp.ScreenPoint?
    get() = impl.focusPoint
    set(value) {
        impl.focusPoint = value
    }
/**
 * Defines the focus point of gestures. Actual behaviour depends on
 * gestureFocusPointMode. If the point is not set, the source point of
 * the gesture will be used as the focus point. Default: none.
 *
 */
actual var MapWindow.gestureFocusPoint: com.yandex.mapkit.kmp.ScreenPoint?
    get() = impl.gestureFocusPoint
    set(value) {
        impl.gestureFocusPoint = value
    }
/**
 * Specifies the way provided gesture focus point affects gestures.
 * Default: TapGestures.
 */
actual var MapWindow.gestureFocusPointMode: com.yandex.mapkit.kmp.map.GestureFocusPointMode
    get() = impl.gestureFocusPointMode.let { com.yandex.mapkit.kmp.map.GestureFocusPointMode.toKmp(it) }
    set(value) {
        impl.gestureFocusPointMode = value.fromKmp()
    }
/**
 * Defines the position of the point of view. Cameras use perspective
 * projection, which causes perspective deformations. Perspective
 * projection has an axis, and points on this axis are not affected by
 * perspective deformations. This axis is a line parallel to the view's
 * direction, so its projection to the screen is a point - the "point of
 * view". By default, this point is at the center of the screen, but
 * some applications might want to set it to the center of focusRect.
 * Use this flag to do so. Default: ScreenCenter
 */
actual var MapWindow.pointOfView: com.yandex.mapkit.kmp.map.PointOfView
    get() = impl.pointOfView.let { com.yandex.mapkit.kmp.map.PointOfView.toKmp(it) }
    set(value) {
        impl.pointOfView = value.fromKmp()
    }
/**
 * Gets the focused region.
 * @return A region that corresponds to the current focusRect or the
 * visible region if focusRect is not set. Region IS bounded by latitude
 * limits [-90, 90] and IS NOT bounded by longitude limits [-180, 180].
 * If longitude exceeds its limits, we see the world's edge and another
 * instance of the world beyond this edge.
 */
actual val MapWindow.focusRegion: com.yandex.mapkit.kmp.map.VisibleRegion
    get() = impl.focusRegion
/**
 * @hidden
 * Defines the scale factor, which equals the number of pixels per
 * device-independent point.
 */
actual var MapWindow.scaleFactor: Float
    get() = impl.scaleFactor
    set(value) {
        impl.scaleFactor = value
    }

open class YMKMapWindowWrapper(override val impl: platform.YandexMapsMobile.YMKMapWindow) : MapWindow {
    override fun width(): Int {
        return impl.width().toInt()
    }

    override fun height(): Int {
        return impl.height().toInt()
    }

    override fun worldToScreen(
        worldPoint: com.yandex.mapkit.kmp.geometry.Point,
    ): com.yandex.mapkit.kmp.ScreenPoint? {
        return impl.worldToScreenWithWorldPoint(
            worldPoint,
        )
    }

    override fun screenToWorld(
        screenPoint: com.yandex.mapkit.kmp.ScreenPoint,
    ): com.yandex.mapkit.kmp.geometry.Point? {
        return impl.screenToWorldWithScreenPoint(
            screenPoint,
        )
    }

    override fun addSizeChangedListener(
        sizeChangedListener: com.yandex.mapkit.kmp.map.SizeChangedListener,
    ): Unit {
        return impl.addSizeChangedListenerWithSizeChangedListener(
            sizeChangedListener.let { com.yandex.mapkit.kmp.map.SizeChangedListenerWrapper(it) },
        )
    }

    override fun removeSizeChangedListener(
        sizeChangedListener: com.yandex.mapkit.kmp.map.SizeChangedListener,
    ): Unit {
        return impl.removeSizeChangedListenerWithSizeChangedListener(
            sizeChangedListener.let { com.yandex.mapkit.kmp.map.SizeChangedListenerWrapper(it) },
        )
    }

    override fun setMaxFps(
        fps: Float,
    ): Unit {
        return impl.setMaxFpsWithFps(
            fps,
        )
    }

    override fun startPerformanceMetricsCapture(): Unit {
        return impl.startPerformanceMetricsCapture()
    }

    override fun stopPerformanceMetricsCapture(): String {
        return impl.stopPerformanceMetricsCapture()
    }

    override fun startMemoryMetricsCapture(): Unit {
        return impl.startMemoryMetricsCapture()
    }

    override fun stopMemoryMetricsCapture(): String {
        return impl.stopMemoryMetricsCapture()
    }

    override fun addSurface(
        surface: com.yandex.runtime.kmp.view.Surface,
    ): Unit {
        return impl.addSurfaceWithSurface(
            surface.impl,
        )
    }

    override fun removeSurface(
        surface: com.yandex.runtime.kmp.view.Surface,
    ): Unit {
        return impl.removeSurfaceWithSurface(
            surface.impl,
        )
    }

    override fun isValid(): Boolean = impl.isValid()
}

/**
 * Wraps [MapWindow] without its own view to render. Allows to render
 * map on additional surfaces in separate processes without having to
 * create MapView control in the main process.
 */
actual interface OffscreenMapWindow {
    val impl: platform.YandexMapsMobile.YMKOffscreenMapWindow

    actual fun captureScreenshot(): com.yandex.runtime.kmp.image.Bitmap
}
actual val OffscreenMapWindow.mapWindow: com.yandex.mapkit.kmp.map.MapWindow
    get() = impl.mapWindow.let { com.yandex.mapkit.kmp.map.YMKMapWindowWrapper(it) }

open class YMKOffscreenMapWindowWrapper(override val impl: platform.YandexMapsMobile.YMKOffscreenMapWindow) : OffscreenMapWindow {
    override fun captureScreenshot(): com.yandex.runtime.kmp.image.Bitmap {
        return impl.captureScreenshot()
    }
}
