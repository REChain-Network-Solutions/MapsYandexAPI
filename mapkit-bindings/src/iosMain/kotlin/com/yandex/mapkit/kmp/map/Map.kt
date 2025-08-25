@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST", "REDUNDANT_ELSE_IN_WHEN")

package com.yandex.mapkit.kmp.map

import com.yandex.runtime.kmp.internal.toBoolean
import com.yandex.runtime.kmp.internal.toFloat
import com.yandex.runtime.kmp.internal.toInt
import com.yandex.runtime.kmp.internal.toNSNumber
import kotlinx.cinterop.objcPtr

/**
 * The object that is used to interact with the map.
 */
actual interface Map {
    val impl: platform.YandexMapsMobile.YMKMap

    /**
     * Calculates the camera position that projects the specified geometry
     * into the current focusRect, or the full view if the focusRect is not
     * set.
     */
    actual fun cameraPosition(
        geometry: com.yandex.mapkit.kmp.geometry.Geometry,
    ): com.yandex.mapkit.kmp.map.CameraPosition

    /**
     * Calculates the camera position that projects the specified geometry
     * into the custom focusRect.
     */
    actual fun cameraPosition(
        geometry: com.yandex.mapkit.kmp.geometry.Geometry,
        focusRect: com.yandex.mapkit.kmp.ScreenRect,
    ): com.yandex.mapkit.kmp.map.CameraPosition

    /**
     * @return Camera position that projects the specified geometry into the
     * custom focusRect, with custom azimuth and tilt camera parameters. If
     * focus rect is not provided, current focus rect is used.
     */
    actual fun cameraPosition(
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
    actual fun visibleRegion(
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
    actual fun move(
        cameraPosition: com.yandex.mapkit.kmp.map.CameraPosition,
        animation: com.yandex.mapkit.kmp.Animation,
        cameraCallback: com.yandex.mapkit.kmp.map.MapCameraCallback?,
    ): Unit

    /**
     * Immediately changes the camera position. Can cancel a previous
     * unfinished movement.
     */
    actual fun move(
        cameraPosition: com.yandex.mapkit.kmp.map.CameraPosition,
    ): Unit

    /**
     * Adds input listeners.
     *
     * The class does not retain the object in the 'inputListener' parameter.
     * It is your responsibility to maintain a strong reference to
     * the target object while it is attached to a class.
     */
    actual fun addInputListener(
        inputListener: com.yandex.mapkit.kmp.map.InputListener,
    ): Unit

    /**
     * Removes input listeners.
     */
    actual fun removeInputListener(
        inputListener: com.yandex.mapkit.kmp.map.InputListener,
    ): Unit

    /**
     * Adds camera listeners.
     *
     * The class does not retain the object in the 'cameraListener' parameter.
     * It is your responsibility to maintain a strong reference to
     * the target object while it is attached to a class.
     */
    actual fun addCameraListener(
        cameraListener: com.yandex.mapkit.kmp.map.CameraListener,
    ): Unit

    /**
     * Removes camera listeners.
     */
    actual fun removeCameraListener(
        cameraListener: com.yandex.mapkit.kmp.map.CameraListener,
    ): Unit

    /**
     * Sets a map loaded listener.
     *
     * The class does not retain the object in the 'mapLoadedListener' parameter.
     * It is your responsibility to maintain a strong reference to
     * the target object while it is attached to a class.
     */
    actual fun setMapLoadedListener(
        mapLoadedListener: com.yandex.mapkit.kmp.map.MapLoadedListener?,
    ): Unit

    /**
     * Adds a tap listener that is used to obtain brief geo object info.
     *
     * The class does not retain the object in the 'tapListener' parameter.
     * It is your responsibility to maintain a strong reference to
     * the target object while it is attached to a class.
     */
    actual fun addTapListener(
        tapListener: com.yandex.mapkit.kmp.layers.GeoObjectTapListener,
    ): Unit

    /**
     * Removes a tap listener that is used to obtain brief geo object info.
     */
    actual fun removeTapListener(
        tapListener: com.yandex.mapkit.kmp.layers.GeoObjectTapListener,
    ): Unit

    /**
     * Resets the currently selected geo object.
     */
    actual fun deselectGeoObject(): Unit

    /**
     * Selects a geo object with the specified objectId in the specified
     * layerId. If the object is not currently on the screen, it is selected
     * anyway, but the user will not actually see that. You need to move the
     * camera in addition to this call to be sure that the selected object
     * is visible for the user. GeoObjectSelectionMetadata can be extracted
     * from the geo object's metadata container when the user taps on a geo
     * object.
     */
    actual fun selectGeoObject(
        selectionMetaData: com.yandex.mapkit.kmp.map.GeoObjectSelectionMetadata,
    ): Unit

    /**
     * Applies JSON style transformations to the map. Same as setMapStyle(0,
     * style). Affects VectorMap and Hybrid map types. Set to empty string
     * to clear previous styling. Returns true if the style was successfully
     * parsed, and false otherwise. If the returned value is false, the
     * current map style remains unchanged.
     */
    actual fun setMapStyle(
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
    actual fun setMapStyle(
        id: Int,
        style: String,
    ): Boolean

    /**
     * Resets all JSON style transformations applied to the map.
     */
    actual fun resetMapStyles(): Unit

    /**
     * Forces the map to be flat. true - All loaded tiles start showing the
     * "flatten out" animation; all new tiles do not start 3D animation.
     * false - All tiles start showing the "rise up" animation.
     */
    actual fun set2DMode(
        enable: Boolean,
    ): Unit

    /**
     * Creates a new independent map object collection linked to the
     * specified layer ID. Sublayers will be added after corresponding
     * sublayers of the topmost layer.
     */
    actual fun addMapObjectLayer(
        layerId: String,
    ): com.yandex.mapkit.kmp.map.RootMapObjectCollection

    /**
     * Provides map projection
     */
    actual fun projection(): com.yandex.mapkit.kmp.geometry.geo.Projection

    /**
     * Erases tiles, caches, etc. Does not trigger the next frame
     * generation.
     */
    actual fun wipe(): Unit

    /**
     * @hidden
     * Starts capturing tile load metrics.
     */
    actual fun startTileLoadMetricsCapture(): Unit

    /**
     * @hidden
     * Stops capturing tile load metrics and returns captured metrics as a
     * string.
     */
    actual fun stopTileLoadMetricsCapture(): String

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
 * @return Current camera position. Target position must be within
 * latitude [-90, 90] and longitude [-180, 180].
 */
actual val Map.cameraPosition: com.yandex.mapkit.kmp.map.CameraPosition
    get() = impl.cameraPosition
/**
 * @return The map region that is currently visible. Region IS bounded
 * by latitude limits [-90, 90] and IS NOT bounded by longitude limits
 * [-180, 180]. If the longitude exceeds its limits, we see the world's
 * edge and another instance of the world beyond this edge.
 */
actual val Map.visibleRegion: com.yandex.mapkit.kmp.map.VisibleRegion
    get() = impl.visibleRegion
actual val Map.cameraBounds: com.yandex.mapkit.kmp.map.CameraBounds
    get() = impl.cameraBounds.let { com.yandex.mapkit.kmp.map.YMKCameraBoundsWrapper(it) }
/**
 * If enabled, night mode will reduce map brightness and improve
 * contrast.
 */
actual var Map.nightModeEnabled: Boolean
    get() = impl.nightModeEnabled
    set(value) {
        impl.nightModeEnabled = value
    }
/**
 * Enable/disable zoom gestures, for example: - pinch - double tap (zoom
 * in) - tap with two fingers (zoom out)
 */
actual var Map.zoomGesturesEnabled: Boolean
    get() = impl.zoomGesturesEnabled
    set(value) {
        impl.zoomGesturesEnabled = value
    }
/**
 * Enable/disable scroll gestures, such as the pan gesture.
 */
actual var Map.scrollGesturesEnabled: Boolean
    get() = impl.scrollGesturesEnabled
    set(value) {
        impl.scrollGesturesEnabled = value
    }
/**
 * Enable/disable tilt gestures, such as parallel pan with two fingers.
 */
actual var Map.tiltGesturesEnabled: Boolean
    get() = impl.tiltGesturesEnabled
    set(value) {
        impl.tiltGesturesEnabled = value
    }
/**
 * Enable/disable rotation gestures, such as rotation with two fingers.
 */
actual var Map.rotateGesturesEnabled: Boolean
    get() = impl.rotateGesturesEnabled
    set(value) {
        impl.rotateGesturesEnabled = value
    }
/**
 * Removes the 300 ms delay in emitting a tap gesture. However, a
 * double-tap will emit a tap gesture along with a double-tap.
 */
actual var Map.fastTapEnabled: Boolean
    get() = impl.fastTapEnabled
    set(value) {
        impl.fastTapEnabled = value
    }
/**
 * Sets the base map type.
 */
actual var Map.mapType: com.yandex.mapkit.kmp.map.MapType
    get() = impl.mapType.let { com.yandex.mapkit.kmp.map.MapType.toKmp(it) }
    set(value) {
        impl.mapType = value.fromKmp()
    }
/**
 * @return List of map objects associated with the map. The layerId for
 * this collection can be retrieved via LayerIds.mapObjectsLayerId
 */
actual val Map.mapObjects: com.yandex.mapkit.kmp.map.RootMapObjectCollection
    get() = impl.mapObjects.let { com.yandex.mapkit.kmp.map.YMKRootMapObjectCollectionWrapper(it) }
/**
 * Limits the number of visible basemap POIs.
 *
 */
actual var Map.poiLimit: Int?
    get() = impl.poiLimit?.toInt()
    set(value) {
        impl.poiLimit = value?.toNSNumber()
    }
/**
 * Selects one of predefined map style modes optimized for particular
 * use case(transit, driving, etc). Resets json styles set with
 * setMapStyle. MapMode.Map by deafult.
 */
actual var Map.mode: com.yandex.mapkit.kmp.map.MapMode
    get() = impl.mode.let { com.yandex.mapkit.kmp.map.MapMode.toKmp(it) }
    set(value) {
        impl.mode = value.fromKmp()
    }
/**
 * Enables hd mode of displayed content
 */
actual var Map.hdModeEnabled: Boolean
    get() = impl.hdModeEnabled
    set(value) {
        impl.hdModeEnabled = value
    }
/**
 * Enables rich textured 3d content on basemap.
 */
actual var Map.awesomeModelsEnabled: Boolean
    get() = impl.awesomeModelsEnabled
    set(value) {
        impl.awesomeModelsEnabled = value
    }

open class YMKMapWrapper(override val impl: platform.YandexMapsMobile.YMKMap) : Map {
    override fun cameraPosition(
        geometry: com.yandex.mapkit.kmp.geometry.Geometry,
    ): com.yandex.mapkit.kmp.map.CameraPosition {
        return impl.cameraPositionWithGeometry(
            geometry,
        )
    }

    override fun cameraPosition(
        geometry: com.yandex.mapkit.kmp.geometry.Geometry,
        focusRect: com.yandex.mapkit.kmp.ScreenRect,
    ): com.yandex.mapkit.kmp.map.CameraPosition {
        return impl.cameraPositionWithGeometry(
            geometry,
            focusRect,
        )
    }

    override fun cameraPosition(
        geometry: com.yandex.mapkit.kmp.geometry.Geometry,
        azimuth: Float,
        tilt: Float,
        focusRect: com.yandex.mapkit.kmp.ScreenRect?,
    ): com.yandex.mapkit.kmp.map.CameraPosition {
        return impl.cameraPositionWithGeometry(
            geometry,
            azimuth,
            tilt,
            focusRect,
        )
    }

    override fun visibleRegion(
        cameraPosition: com.yandex.mapkit.kmp.map.CameraPosition,
    ): com.yandex.mapkit.kmp.map.VisibleRegion {
        return impl.visibleRegionWithCameraPosition(
            cameraPosition,
        )
    }

    override fun move(
        cameraPosition: com.yandex.mapkit.kmp.map.CameraPosition,
        animation: com.yandex.mapkit.kmp.Animation,
        cameraCallback: com.yandex.mapkit.kmp.map.MapCameraCallback?,
    ): Unit {
        return impl.moveWithCameraPosition(
            cameraPosition,
            animation,
            cameraCallback?.let { com.yandex.mapkit.kmp.map.MapCameraCallbackWrapper(it) },
        )
    }

    override fun move(
        cameraPosition: com.yandex.mapkit.kmp.map.CameraPosition,
    ): Unit {
        return impl.moveWithCameraPosition(
            cameraPosition,
        )
    }

    override fun addInputListener(
        inputListener: com.yandex.mapkit.kmp.map.InputListener,
    ): Unit {
        return impl.addInputListenerWithInputListener(
            inputListener.let { com.yandex.mapkit.kmp.map.InputListenerWrapper(it) },
        )
    }

    override fun removeInputListener(
        inputListener: com.yandex.mapkit.kmp.map.InputListener,
    ): Unit {
        return impl.removeInputListenerWithInputListener(
            inputListener.let { com.yandex.mapkit.kmp.map.InputListenerWrapper(it) },
        )
    }

    override fun addCameraListener(
        cameraListener: com.yandex.mapkit.kmp.map.CameraListener,
    ): Unit {
        return impl.addCameraListenerWithCameraListener(
            cameraListener.let { com.yandex.mapkit.kmp.map.CameraListenerWrapper(it) },
        )
    }

    override fun removeCameraListener(
        cameraListener: com.yandex.mapkit.kmp.map.CameraListener,
    ): Unit {
        return impl.removeCameraListenerWithCameraListener(
            cameraListener.let { com.yandex.mapkit.kmp.map.CameraListenerWrapper(it) },
        )
    }

    override fun setMapLoadedListener(
        mapLoadedListener: com.yandex.mapkit.kmp.map.MapLoadedListener?,
    ): Unit {
        return impl.setMapLoadedListenerWithMapLoadedListener(
            mapLoadedListener?.let { com.yandex.mapkit.kmp.map.MapLoadedListenerWrapper(it) },
        )
    }

    override fun addTapListener(
        tapListener: com.yandex.mapkit.kmp.layers.GeoObjectTapListener,
    ): Unit {
        return impl.addTapListenerWithTapListener(
            tapListener.let { com.yandex.mapkit.kmp.layers.GeoObjectTapListenerWrapper(it) },
        )
    }

    override fun removeTapListener(
        tapListener: com.yandex.mapkit.kmp.layers.GeoObjectTapListener,
    ): Unit {
        return impl.removeTapListenerWithTapListener(
            tapListener.let { com.yandex.mapkit.kmp.layers.GeoObjectTapListenerWrapper(it) },
        )
    }

    override fun deselectGeoObject(): Unit {
        return impl.deselectGeoObject()
    }

    override fun selectGeoObject(
        selectionMetaData: com.yandex.mapkit.kmp.map.GeoObjectSelectionMetadata,
    ): Unit {
        return impl.selectGeoObjectWithSelectionMetaData(
            selectionMetaData,
        )
    }

    override fun setMapStyle(
        style: String,
    ): Boolean {
        return impl.setMapStyleWithStyle(
            style,
        )
    }

    override fun setMapStyle(
        id: Int,
        style: String,
    ): Boolean {
        return impl.setMapStyleWithId(
            id.toLong(),
            style,
        )
    }

    override fun resetMapStyles(): Unit {
        return impl.resetMapStyles()
    }

    override fun set2DMode(
        enable: Boolean,
    ): Unit {
        return impl.set2DModeWithEnable(
            enable,
        )
    }

    override fun addMapObjectLayer(
        layerId: String,
    ): com.yandex.mapkit.kmp.map.RootMapObjectCollection {
        return impl.addMapObjectLayerWithLayerId(
            layerId,
        ).let { com.yandex.mapkit.kmp.map.YMKRootMapObjectCollectionWrapper(it) }
    }

    override fun projection(): com.yandex.mapkit.kmp.geometry.geo.Projection {
        return impl.projection().let { com.yandex.mapkit.kmp.geometry.geo.YMKProjectionWrapper(it) }
    }

    override fun wipe(): Unit {
        return impl.wipe()
    }

    override fun startTileLoadMetricsCapture(): Unit {
        return impl.startTileLoadMetricsCapture()
    }

    override fun stopTileLoadMetricsCapture(): String {
        return impl.stopTileLoadMetricsCapture()
    }

    override fun isValid(): Boolean = impl.isValid()
}

actual interface MapCameraCallback {
    actual fun onMoveFinished(
        completed: Boolean,
    ): Unit
}

internal class MapCameraCallbackWrapper(val impl: MapCameraCallback): platform.YandexMapsMobile.YMKMapCameraCallback {
    override fun invoke(
        completed: Boolean,
    ) {
            impl.onMoveFinished(
                completed!!,
            )
    }
}
