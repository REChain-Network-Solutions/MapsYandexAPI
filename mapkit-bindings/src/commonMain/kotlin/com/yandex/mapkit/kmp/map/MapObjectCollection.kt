@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * A collection of map objects that can hold any set of MapObject items,
 * including nested collections.
 */
expect interface MapObjectCollection : com.yandex.mapkit.kmp.map.BaseMapObjectCollection {
    /**
     * Creates a new empty placemark and adds it to the current collection.
     */
    fun addPlacemark(): com.yandex.mapkit.kmp.map.PlacemarkMapObject

    /**
     * Creates a new empty placemark and adds it to the current collection.
     * Callback can be used to setup placemark style and position Callback
     * will be called before MapObjectCollectionListener#onMapObjectAdded
     */
    fun addPlacemark(
        placemarkCreatedCallback: com.yandex.mapkit.kmp.map.PlacemarkCreatedCallback,
    ): com.yandex.mapkit.kmp.map.PlacemarkMapObject

    /**
     * Creates a new polyline and adds it to the current collection.
     */
    fun addPolyline(
        polyline: com.yandex.mapkit.kmp.geometry.Polyline,
    ): com.yandex.mapkit.kmp.map.PolylineMapObject

    /**
     * Creates a new polyline with an empty geometry and adds it to the
     * current collection.
     */
    fun addPolyline(): com.yandex.mapkit.kmp.map.PolylineMapObject

    /**
     * Creates a new polygon and adds it to the current collection.
     */
    fun addPolygon(
        polygon: com.yandex.mapkit.kmp.geometry.Polygon,
    ): com.yandex.mapkit.kmp.map.PolygonMapObject

    /**
     * Creates a new circle and adds it to the current collection.
     */
    fun addCircle(
        circle: com.yandex.mapkit.kmp.geometry.Circle,
    ): com.yandex.mapkit.kmp.map.CircleMapObject

    /**
     * Creates a new nested collection of map objects.
     */
    fun addCollection(): com.yandex.mapkit.kmp.map.MapObjectCollection

    /**
     * Creates a new nested collection of clusterized placemarks.
     *
     * The class does not retain the object in the 'clusterListener' parameter.
     * It is your responsibility to maintain a strong reference to
     * the target object while it is attached to a class.
     *
     * @param clusterListener Listener that controls cluster appearance once
     * they are added to the map.
     */
    fun addClusterizedPlacemarkCollection(
        clusterListener: com.yandex.mapkit.kmp.map.ClusterListener,
    ): com.yandex.mapkit.kmp.map.ClusterizedPlacemarkCollection

    /**
     * A styler for all placemarks in this collection, including placemarks
     * in child collections.
     */
    fun placemarksStyler(): com.yandex.mapkit.kmp.map.PlacemarksStyler
}

expect interface RootMapObjectCollection : com.yandex.mapkit.kmp.map.MapObjectCollection

expect var RootMapObjectCollection.conflictResolutionMode: com.yandex.mapkit.kmp.ConflictResolutionMode

