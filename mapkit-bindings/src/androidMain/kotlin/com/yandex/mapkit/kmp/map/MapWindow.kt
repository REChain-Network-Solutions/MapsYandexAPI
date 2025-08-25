@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * Handles the MapWindow element.
 */
actual typealias MapWindow = com.yandex.mapkit.map.MapWindow

/**
 * Gets the map interface.
 */
actual val MapWindow.map: com.yandex.mapkit.kmp.map.Map
    get() = map
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
    get() = focusRect
    set(value) {
        focusRect = value
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
    get() = focusPoint
    set(value) {
        focusPoint = value
    }
/**
 * Defines the focus point of gestures. Actual behaviour depends on
 * gestureFocusPointMode. If the point is not set, the source point of
 * the gesture will be used as the focus point. Default: none.
 *
 */
actual var MapWindow.gestureFocusPoint: com.yandex.mapkit.kmp.ScreenPoint?
    get() = gestureFocusPoint
    set(value) {
        gestureFocusPoint = value
    }
/**
 * Specifies the way provided gesture focus point affects gestures.
 * Default: TapGestures.
 */
actual var MapWindow.gestureFocusPointMode: com.yandex.mapkit.kmp.map.GestureFocusPointMode
    get() = gestureFocusPointMode
    set(value) {
        gestureFocusPointMode = value
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
    get() = pointOfView
    set(value) {
        pointOfView = value
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
    get() = focusRegion
/**
 * @hidden
 * Defines the scale factor, which equals the number of pixels per
 * device-independent point.
 */
actual var MapWindow.scaleFactor: Float
    get() = scaleFactor
    set(value) {
        scaleFactor = value
    }

/**
 * Wraps [MapWindow] without its own view to render. Allows to render
 * map on additional surfaces in separate processes without having to
 * create MapView control in the main process.
 */
actual typealias OffscreenMapWindow = com.yandex.mapkit.map.OffscreenMapWindow

actual val OffscreenMapWindow.mapWindow: com.yandex.mapkit.kmp.map.MapWindow
    get() = mapWindow

