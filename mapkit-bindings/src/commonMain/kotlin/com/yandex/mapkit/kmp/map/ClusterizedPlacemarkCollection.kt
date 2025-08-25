@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * A collection that displays large groups of placemarks efficiently.
 * Placemarks that are too close to each other with current zoom are
 * rendered as a single cluster. Placemarks and clusters are not shown
 * until clusterPlacemarks method is called explicitly.
 */
expect interface ClusterizedPlacemarkCollection : com.yandex.mapkit.kmp.map.BaseMapObjectCollection {
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
     * Creates new placemarks and adds them to the current collection.
     * Relevant for Android: this method provides better performance for
     * adding a large number of placemarks than multiple calls of
     * addPlacemark.
     */
    fun addPlacemarks(
        points: kotlin.collections.List<com.yandex.mapkit.kmp.geometry.Point>,
        image: com.yandex.runtime.kmp.image.ImageProvider,
        style: com.yandex.mapkit.kmp.map.IconStyle,
    ): kotlin.collections.List<com.yandex.mapkit.kmp.map.PlacemarkMapObject>

    /**
     * Updates clustered representations of placemark groups. This method
     * must be called explicitly to render placemarks and clusters when
     * collection is created and update them after collection change.
     *
     * @param clusterRadius Minimal distance in units between objects that
     * remain separate. The size of the unit is equal to the size of a pixel
     * when the camera position's tilt is equal to 0 and the scale factor is
     * equal to 1.
     * @param minZoom Minimal zoom level that displays clusters. All
     * placemarks will be rendered separately at more detailed zoom levels.
     * The value will be clipped between 0 and 19 (most detailed zoom).
     */
    fun clusterPlacemarks(
        clusterRadius: Double,
        minZoom: Int,
    ): Unit
}

