@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * The object that is used to interact with the map.
 */
expect interface Map {

    /**
     * Calculates the camera position that projects the specified geometry
     * into the current focusRect, or the full view if the focusRect is not
     * set.
     */
    fun cameraPosition(
        geometry: com.yandex.mapkit.kmp.geometry.Geometry,
    ): com.yandex.mapkit.kmp.map.CameraPosition

    /**
     * Calculates the camera position that projects the specified geometry
     * into the custom focusRect.
     */
    fun cameraPosition(
        geometry: com.yandex.mapkit.kmp.geometry.Geometry,
        focusRect: com.yandex.mapkit.kmp.ScreenRect,
    ): com.yandex.mapkit.kmp.map.CameraPosition

    /**
     * @return Camera position that projects the specified geometry into the
     * custom focusRect, with custom azimuth and tilt camera parameters. If
     * focus rect is not provided, current focus rect is used.
     */
    fun cameraPosition(
        geometry: com.yandex.mapkit.kmp.geometry.Geometry,
        azimuth: Float,
        tilt: Float,
        focusRect: com.yandex.mapkit.kmp.ScreenRect?,
    ): com.yandex.mapkit.kmp.map.CameraPosition

    /**
     * @return The map region that is visible from the given camera
     * position. Region IS bounded by latitude limits [-90, 90] and IS NOT
     * bounded by longitude limits [-180, 180]. If the longitude exceeds its
     * limits, we see the world's edge and another instance of the world
     * beyond this edge.
     */
    fun visibleRegion(
        cameraPosition: com.yandex.mapkit.kmp.map.CameraPosition,
    ): com.yandex.mapkit.kmp.map.VisibleRegion

    /**
     * Changes camera position. Can cancel a previous unfinished movement.
     *
     * @param animation Required. Defines animation parameters. @see
     * mapkit.Animation for more details.
     * @param cameraCallback A function that takes the bool argument marking
     * the camera action complete. Invoked when: <ul><li>A camera action is
     * cancelled (for example, as a result of a subsequent request for
     * camera movement), passing false as an argument.</li> <li>A camera
     * action finished successfully, passing true as an argument.</li></ul>
     */
    fun move(
        cameraPosition: com.yandex.mapkit.kmp.map.CameraPosition,
        animation: com.yandex.mapkit.kmp.Animation,
        cameraCallback: com.yandex.mapkit.kmp.map.MapCameraCallback?,
    ): Unit

    /**
     * Immediately changes the camera position. Can cancel a previous
     * unfinished movement.
     */
    fun move(
        cameraPosition: com.yandex.mapkit.kmp.map.CameraPosition,
    ): Unit

    /**
     * Adds input listeners.
     *
     * The class does not retain the object in the 'inputListener' parameter.
     * It is your responsibility to maintain a strong reference to
     * the target object while it is attached to a class.
     */
    fun addInputListener(
        inputListener: com.yandex.mapkit.kmp.map.InputListener,
    ): Unit

    /**
     * Removes input listeners.
     */
    fun removeInputListener(
        inputListener: com.yandex.mapkit.kmp.map.InputListener,
    ): Unit

    /**
     * Adds camera listeners.
     *
     * The class does not retain the object in the 'cameraListener' parameter.
     * It is your responsibility to maintain a strong reference to
     * the target object while it is attached to a class.
     */
    fun addCameraListener(
        cameraListener: com.yandex.mapkit.kmp.map.CameraListener,
    ): Unit

    /**
     * Removes camera listeners.
     */
    fun removeCameraListener(
        cameraListener: com.yandex.mapkit.kmp.map.CameraListener,
    ): Unit

    /**
     * Sets a map loaded listener.
     *
     * The class does not retain the object in the 'mapLoadedListener' parameter.
     * It is your responsibility to maintain a strong reference to
     * the target object while it is attached to a class.
     */
    fun setMapLoadedListener(
        mapLoadedListener: com.yandex.mapkit.kmp.map.MapLoadedListener?,
    ): Unit

    /**
     * Adds a tap listener that is used to obtain brief geo object info.
     *
     * The class does not retain the object in the 'tapListener' parameter.
     * It is your responsibility to maintain a strong reference to
     * the target object while it is attached to a class.
     */
    fun addTapListener(
        tapListener: com.yandex.mapkit.kmp.layers.GeoObjectTapListener,
    ): Unit

    /**
     * Removes a tap listener that is used to obtain brief geo object info.
     */
    fun removeTapListener(
        tapListener: com.yandex.mapkit.kmp.layers.GeoObjectTapListener,
    ): Unit

    /**
     * Resets the currently selected geo object.
     */
    fun deselectGeoObject(): Unit

    /**
     * Selects a geo object with the specified objectId in the specified
     * layerId. If the object is not currently on the screen, it is selected
     * anyway, but the user will not actually see that. You need to move the
     * camera in addition to this call to be sure that the selected object
     * is visible for the user. GeoObjectSelectionMetadata can be extracted
     * from the geo object's metadata container when the user taps on a geo
     * object.
     */
    fun selectGeoObject(
        selectionMetaData: com.yandex.mapkit.kmp.map.GeoObjectSelectionMetadata,
    ): Unit

    /**
     * Applies JSON style transformations to the map. Same as setMapStyle(0,
     * style). Affects VectorMap and Hybrid map types. Set to empty string
     * to clear previous styling. Returns true if the style was successfully
     * parsed, and false otherwise. If the returned value is false, the
     * current map style remains unchanged.
     */
    fun setMapStyle(
        style: String,
    ): Boolean

    /**
     * Applies JSON style transformations to the map. Replaces previous
     * styling with the specified ID (if such exists). Stylings are applied
     * in an ascending order. Affects VectorMap and Hybrid map types. Set to
     * empty string to clear previous styling with the specified ID. Returns
     * true if the style was successfully parsed, and false otherwise. If
     * the returned value is false, the current map style remains unchanged.
     */
    fun setMapStyle(
        id: Int,
        style: String,
    ): Boolean

    /**
     * Resets all JSON style transformations applied to the map.
     */
    fun resetMapStyles(): Unit

    /**
     * Forces the map to be flat. true - All loaded tiles start showing the
     * "flatten out" animation; all new tiles do not start 3D animation.
     * false - All tiles start showing the "rise up" animation.
     */
    fun set2DMode(
        enable: Boolean,
    ): Unit

    /**
     * Creates a new independent map object collection linked to the
     * specified layer ID. Sublayers will be added after corresponding
     * sublayers of the topmost layer.
     */
    fun addMapObjectLayer(
        layerId: String,
    ): com.yandex.mapkit.kmp.map.RootMapObjectCollection

    /**
     * Provides map projection
     */
    fun projection(): com.yandex.mapkit.kmp.geometry.geo.Projection

    /**
     * Erases tiles, caches, etc. Does not trigger the next frame
     * generation.
     */
    fun wipe(): Unit

    /**
     * @hidden
     * Starts capturing tile load metrics.
     */
    fun startTileLoadMetricsCapture(): Unit

    /**
     * @hidden
     * Stops capturing tile load metrics and returns captured metrics as a
     * string.
     */
    fun stopTileLoadMetricsCapture(): String

    /**
     * Tells if this object is valid or not. Any method called on an invalid
     * object will throw an exception. The object becomes invalid only on UI
     * thread, and only when its implementation depends on objects already
     * destroyed by now. Please refer to general docs about the interface for
     * details on its invalidation.
     */
    fun isValid(): Boolean
}

/**
 * @return Current camera position. Target position must be within
 * latitude [-90, 90] and longitude [-180, 180].
 */
expect val Map.cameraPosition: com.yandex.mapkit.kmp.map.CameraPosition
/**
 * @return The map region that is currently visible. Region IS bounded
 * by latitude limits [-90, 90] and IS NOT bounded by longitude limits
 * [-180, 180]. If the longitude exceeds its limits, we see the world's
 * edge and another instance of the world beyond this edge.
 */
expect val Map.visibleRegion: com.yandex.mapkit.kmp.map.VisibleRegion
expect val Map.cameraBounds: com.yandex.mapkit.kmp.map.CameraBounds
/**
 * If enabled, night mode will reduce map brightness and improve
 * contrast.
 */
expect var Map.nightModeEnabled: Boolean
/**
 * Enable/disable zoom gestures, for example: - pinch - double tap (zoom
 * in) - tap with two fingers (zoom out)
 */
expect var Map.zoomGesturesEnabled: Boolean
/**
 * Enable/disable scroll gestures, such as the pan gesture.
 */
expect var Map.scrollGesturesEnabled: Boolean
/**
 * Enable/disable tilt gestures, such as parallel pan with two fingers.
 */
expect var Map.tiltGesturesEnabled: Boolean
/**
 * Enable/disable rotation gestures, such as rotation with two fingers.
 */
expect var Map.rotateGesturesEnabled: Boolean
/**
 * Removes the 300 ms delay in emitting a tap gesture. However, a
 * double-tap will emit a tap gesture along with a double-tap.
 */
expect var Map.fastTapEnabled: Boolean
/**
 * Sets the base map type.
 */
expect var Map.mapType: com.yandex.mapkit.kmp.map.MapType
/**
 * @return List of map objects associated with the map. The layerId for
 * this collection can be retrieved via LayerIds.mapObjectsLayerId
 */
expect val Map.mapObjects: com.yandex.mapkit.kmp.map.RootMapObjectCollection
/**
 * Limits the number of visible basemap POIs.
 *
 */
expect var Map.poiLimit: Int?
/**
 * Selects one of predefined map style modes optimized for particular
 * use case(transit, driving, etc). Resets json styles set with
 * setMapStyle. MapMode.Map by deafult.
 */
expect var Map.mode: com.yandex.mapkit.kmp.map.MapMode
/**
 * Enables hd mode of displayed content
 */
expect var Map.hdModeEnabled: Boolean
/**
 * Enables rich textured 3d content on basemap.
 */
expect var Map.awesomeModelsEnabled: Boolean

expect interface MapCameraCallback {
    /**
     * Called when the move is finished.
     */
    fun onMoveFinished(
        completed: Boolean,
    ): Unit
}

