@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * The object that is used to interact with the map.
 */
actual typealias Map = com.yandex.mapkit.map.Map

/**
 * @return Current camera position. Target position must be within
 * latitude [-90, 90] and longitude [-180, 180].
 */
actual val Map.cameraPosition: com.yandex.mapkit.kmp.map.CameraPosition
    get() = cameraPosition
/**
 * @return The map region that is currently visible. Region IS bounded
 * by latitude limits [-90, 90] and IS NOT bounded by longitude limits
 * [-180, 180]. If the longitude exceeds its limits, we see the world's
 * edge and another instance of the world beyond this edge.
 */
actual val Map.visibleRegion: com.yandex.mapkit.kmp.map.VisibleRegion
    get() = visibleRegion
actual val Map.cameraBounds: com.yandex.mapkit.kmp.map.CameraBounds
    get() = cameraBounds
/**
 * If enabled, night mode will reduce map brightness and improve
 * contrast.
 */
actual var Map.nightModeEnabled: Boolean
    get() = isNightModeEnabled
    set(value) {
        isNightModeEnabled = value
    }
/**
 * Enable/disable zoom gestures, for example: - pinch - double tap (zoom
 * in) - tap with two fingers (zoom out)
 */
actual var Map.zoomGesturesEnabled: Boolean
    get() = isZoomGesturesEnabled
    set(value) {
        isZoomGesturesEnabled = value
    }
/**
 * Enable/disable scroll gestures, such as the pan gesture.
 */
actual var Map.scrollGesturesEnabled: Boolean
    get() = isScrollGesturesEnabled
    set(value) {
        isScrollGesturesEnabled = value
    }
/**
 * Enable/disable tilt gestures, such as parallel pan with two fingers.
 */
actual var Map.tiltGesturesEnabled: Boolean
    get() = isTiltGesturesEnabled
    set(value) {
        isTiltGesturesEnabled = value
    }
/**
 * Enable/disable rotation gestures, such as rotation with two fingers.
 */
actual var Map.rotateGesturesEnabled: Boolean
    get() = isRotateGesturesEnabled
    set(value) {
        isRotateGesturesEnabled = value
    }
/**
 * Removes the 300 ms delay in emitting a tap gesture. However, a
 * double-tap will emit a tap gesture along with a double-tap.
 */
actual var Map.fastTapEnabled: Boolean
    get() = isFastTapEnabled
    set(value) {
        isFastTapEnabled = value
    }
/**
 * Sets the base map type.
 */
actual var Map.mapType: com.yandex.mapkit.kmp.map.MapType
    get() = mapType
    set(value) {
        mapType = value
    }
/**
 * @return List of map objects associated with the map. The layerId for
 * this collection can be retrieved via LayerIds.mapObjectsLayerId
 */
actual val Map.mapObjects: com.yandex.mapkit.kmp.map.RootMapObjectCollection
    get() = mapObjects
/**
 * Limits the number of visible basemap POIs.
 *
 */
actual var Map.poiLimit: Int?
    get() = poiLimit
    set(value) {
        poiLimit = value
    }
/**
 * Selects one of predefined map style modes optimized for particular
 * use case(transit, driving, etc). Resets json styles set with
 * setMapStyle. MapMode.Map by deafult.
 */
actual var Map.mode: com.yandex.mapkit.kmp.map.MapMode
    get() = mode
    set(value) {
        mode = value
    }
/**
 * Enables hd mode of displayed content
 */
actual var Map.hdModeEnabled: Boolean
    get() = isHdModeEnabled
    set(value) {
        isHdModeEnabled = value
    }
/**
 * Enables rich textured 3d content on basemap.
 */
actual var Map.awesomeModelsEnabled: Boolean
    get() = isAwesomeModelsEnabled
    set(value) {
        isAwesomeModelsEnabled = value
    }

actual typealias MapCameraCallback = com.yandex.mapkit.map.Map.CameraCallback
