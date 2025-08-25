@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

import com.yandex.mapkit.kmp.fromKmp

/**
 * A collection of map objects that can hold any set of MapObject items,
 * including nested collections.
 */
actual interface MapObjectCollection : com.yandex.mapkit.kmp.map.BaseMapObjectCollection {
    override val impl: platform.YandexMapsMobile.YMKMapObjectCollection

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
     * Creates a new polyline and adds it to the current collection.
     */
    actual fun addPolyline(
        polyline: com.yandex.mapkit.kmp.geometry.Polyline,
    ): com.yandex.mapkit.kmp.map.PolylineMapObject

    /**
     * Creates a new polyline with an empty geometry and adds it to the
     * current collection.
     */
    actual fun addPolyline(): com.yandex.mapkit.kmp.map.PolylineMapObject

    /**
     * Creates a new polygon and adds it to the current collection.
     */
    actual fun addPolygon(
        polygon: com.yandex.mapkit.kmp.geometry.Polygon,
    ): com.yandex.mapkit.kmp.map.PolygonMapObject

    /**
     * Creates a new circle and adds it to the current collection.
     */
    actual fun addCircle(
        circle: com.yandex.mapkit.kmp.geometry.Circle,
    ): com.yandex.mapkit.kmp.map.CircleMapObject

    /**
     * Creates a new nested collection of map objects.
     */
    actual fun addCollection(): com.yandex.mapkit.kmp.map.MapObjectCollection

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
    actual fun addClusterizedPlacemarkCollection(
        clusterListener: com.yandex.mapkit.kmp.map.ClusterListener,
    ): com.yandex.mapkit.kmp.map.ClusterizedPlacemarkCollection

    /**
     * A styler for all placemarks in this collection, including placemarks
     * in child collections.
     */
    actual fun placemarksStyler(): com.yandex.mapkit.kmp.map.PlacemarksStyler
}

open class YMKMapObjectCollectionWrapper(override val impl: platform.YandexMapsMobile.YMKMapObjectCollection) : MapObjectCollection, com.yandex.mapkit.kmp.map.YMKBaseMapObjectCollectionWrapper(impl) {
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

    override fun addPolyline(
        polyline: com.yandex.mapkit.kmp.geometry.Polyline,
    ): com.yandex.mapkit.kmp.map.PolylineMapObject {
        return impl.addPolylineWithPolyline(
            polyline,
        ).let { com.yandex.mapkit.kmp.map.YMKPolylineMapObjectWrapper(it) }
    }

    override fun addPolyline(): com.yandex.mapkit.kmp.map.PolylineMapObject {
        return impl.addPolyline().let { com.yandex.mapkit.kmp.map.YMKPolylineMapObjectWrapper(it) }
    }

    override fun addPolygon(
        polygon: com.yandex.mapkit.kmp.geometry.Polygon,
    ): com.yandex.mapkit.kmp.map.PolygonMapObject {
        return impl.addPolygonWithPolygon(
            polygon,
        ).let { com.yandex.mapkit.kmp.map.YMKPolygonMapObjectWrapper(it) }
    }

    override fun addCircle(
        circle: com.yandex.mapkit.kmp.geometry.Circle,
    ): com.yandex.mapkit.kmp.map.CircleMapObject {
        return impl.addCircleWithCircle(
            circle,
        ).let { com.yandex.mapkit.kmp.map.YMKCircleMapObjectWrapper(it) }
    }

    override fun addCollection(): com.yandex.mapkit.kmp.map.MapObjectCollection {
        return impl.addCollection().let { com.yandex.mapkit.kmp.map.YMKMapObjectCollectionWrapper(it) }
    }

    override fun addClusterizedPlacemarkCollection(
        clusterListener: com.yandex.mapkit.kmp.map.ClusterListener,
    ): com.yandex.mapkit.kmp.map.ClusterizedPlacemarkCollection {
        return impl.addClusterizedPlacemarkCollectionWithClusterListener(
            clusterListener.let { com.yandex.mapkit.kmp.map.ClusterListenerWrapper(it) },
        ).let { com.yandex.mapkit.kmp.map.YMKClusterizedPlacemarkCollectionWrapper(it) }
    }

    override fun placemarksStyler(): com.yandex.mapkit.kmp.map.PlacemarksStyler {
        return impl.placemarksStyler().let { com.yandex.mapkit.kmp.map.YMKPlacemarksStylerWrapper(it) }
    }
}

actual interface RootMapObjectCollection : com.yandex.mapkit.kmp.map.MapObjectCollection {
    override val impl: platform.YandexMapsMobile.YMKRootMapObjectCollection
}
actual var RootMapObjectCollection.conflictResolutionMode: com.yandex.mapkit.kmp.ConflictResolutionMode
    get() = impl.conflictResolutionMode.let { com.yandex.mapkit.kmp.ConflictResolutionMode.toKmp(it) }
    set(value) {
        impl.conflictResolutionMode = value.fromKmp()
    }

open class YMKRootMapObjectCollectionWrapper(override val impl: platform.YandexMapsMobile.YMKRootMapObjectCollection) : RootMapObjectCollection, com.yandex.mapkit.kmp.map.YMKMapObjectCollectionWrapper(impl)

