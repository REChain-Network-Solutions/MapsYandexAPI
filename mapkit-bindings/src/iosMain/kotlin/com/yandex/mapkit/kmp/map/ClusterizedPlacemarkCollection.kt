@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

/**
 * A collection that displays large groups of placemarks efficiently.
 * Placemarks that are too close to each other with current zoom are
 * rendered as a single cluster. Placemarks and clusters are not shown
 * until clusterPlacemarks method is called explicitly.
 */
actual interface ClusterizedPlacemarkCollection : com.yandex.mapkit.kmp.map.BaseMapObjectCollection {
    override val impl: platform.YandexMapsMobile.YMKClusterizedPlacemarkCollection

    /**
     * Creates a new empty placemark and adds it to the current collection.
     */
    actual fun addPlacemark(): com.yandex.mapkit.kmp.map.PlacemarkMapObject

    /**
     * Creates a new empty placemark and adds it to the current collection.
     * Callback can be used to setup placemark style and position Callback
     * will be called before MapObjectCollectionListener#onMapObjectAdded
     */
    actual fun addPlacemark(
        placemarkCreatedCallback: com.yandex.mapkit.kmp.map.PlacemarkCreatedCallback,
    ): com.yandex.mapkit.kmp.map.PlacemarkMapObject

    /**
     * Creates new placemarks and adds them to the current collection.
     * Relevant for Android: this method provides better performance for
     * adding a large number of placemarks than multiple calls of
     * addPlacemark.
     */
    actual fun addPlacemarks(
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
    actual fun clusterPlacemarks(
        clusterRadius: Double,
        minZoom: Int,
    ): Unit
}

open class YMKClusterizedPlacemarkCollectionWrapper(override val impl: platform.YandexMapsMobile.YMKClusterizedPlacemarkCollection) : ClusterizedPlacemarkCollection, com.yandex.mapkit.kmp.map.YMKBaseMapObjectCollectionWrapper(impl) {
    override fun addPlacemark(): com.yandex.mapkit.kmp.map.PlacemarkMapObject {
        return impl.addPlacemark().let { com.yandex.mapkit.kmp.map.YMKPlacemarkMapObjectWrapper(it) }
    }

    override fun addPlacemark(
        placemarkCreatedCallback: com.yandex.mapkit.kmp.map.PlacemarkCreatedCallback,
    ): com.yandex.mapkit.kmp.map.PlacemarkMapObject {
        return impl.addPlacemarkWithPlacemarkCreatedCallback(
            placemarkCreatedCallback.let { com.yandex.mapkit.kmp.map.PlacemarkCreatedCallbackWrapper(it) },
        ).let { com.yandex.mapkit.kmp.map.YMKPlacemarkMapObjectWrapper(it) }
    }

    override fun addPlacemarks(
        points: kotlin.collections.List<com.yandex.mapkit.kmp.geometry.Point>,
        image: com.yandex.runtime.kmp.image.ImageProvider,
        style: com.yandex.mapkit.kmp.map.IconStyle,
    ): kotlin.collections.List<com.yandex.mapkit.kmp.map.PlacemarkMapObject> {
        return impl.addPlacemarksWithPoints(
            points.let { it as kotlin.collections.List<*> },
            image.impl,
            style,
        ).let { it as kotlin.collections.List<platform.YandexMapsMobile.YMKPlacemarkMapObject> }.map { it.let { com.yandex.mapkit.kmp.map.YMKPlacemarkMapObjectWrapper(it) } }
    }

    override fun clusterPlacemarks(
        clusterRadius: Double,
        minZoom: Int,
    ): Unit {
        return impl.clusterPlacemarksWithClusterRadius(
            clusterRadius,
            minZoom.toULong(),
        )
    }
}

