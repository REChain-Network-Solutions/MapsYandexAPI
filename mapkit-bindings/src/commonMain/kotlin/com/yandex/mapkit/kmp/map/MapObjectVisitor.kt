@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * Used to traverse over the children of the MapObjectCollection.
 */
expect interface MapObjectVisitor {
    /**
     * Called when a placemark is visited.
     */
    fun onPlacemarkVisited(
        placemark: com.yandex.mapkit.kmp.map.PlacemarkMapObject,
    ): Unit

    /**
     * Called when a polyline is visited.
     */
    fun onPolylineVisited(
        polyline: com.yandex.mapkit.kmp.map.PolylineMapObject,
    ): Unit

    /**
     * Called when a polygon is visited.
     */
    fun onPolygonVisited(
        polygon: com.yandex.mapkit.kmp.map.PolygonMapObject,
    ): Unit

    /**
     * Called when a circle is visited.
     */
    fun onCircleVisited(
        circle: com.yandex.mapkit.kmp.map.CircleMapObject,
    ): Unit

    /**
     * Called for every child collection. The collection is ignored if this
     * method returns false.
     */
    fun onCollectionVisitStart(
        collection: com.yandex.mapkit.kmp.map.MapObjectCollection,
    ): Boolean

    /**
     * Called for visited collections only. If an exception occurred during
     * the visit, the method might be skipped.
     */
    fun onCollectionVisitEnd(
        collection: com.yandex.mapkit.kmp.map.MapObjectCollection,
    ): Unit

    /**
     * Called for clusterized placemark collection. The collection is
     * ignored if this method returns false.
     */
    fun onClusterizedCollectionVisitStart(
        collection: com.yandex.mapkit.kmp.map.ClusterizedPlacemarkCollection,
    ): Boolean

    /**
     * Called for visited clusterized placemark collections only. If an
     * exception occurred during the visit, the method might be skipped.
     */
    fun onClusterizedCollectionVisitEnd(
        collection: com.yandex.mapkit.kmp.map.ClusterizedPlacemarkCollection,
    ): Unit
}

