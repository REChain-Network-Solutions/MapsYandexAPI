@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

import com.yandex.runtime.kmp.internal.toBoolean
import com.yandex.runtime.kmp.internal.toNSNumber
import kotlinx.cinterop.objcPtr

/**
 * Used to traverse over the children of the MapObjectCollection.
 */
actual interface MapObjectVisitor {
    /**
     * Called when a placemark is visited.
     */
    actual fun onPlacemarkVisited(
        placemark: com.yandex.mapkit.kmp.map.PlacemarkMapObject,
    ): Unit

    /**
     * Called when a polyline is visited.
     */
    actual fun onPolylineVisited(
        polyline: com.yandex.mapkit.kmp.map.PolylineMapObject,
    ): Unit

    /**
     * Called when a polygon is visited.
     */
    actual fun onPolygonVisited(
        polygon: com.yandex.mapkit.kmp.map.PolygonMapObject,
    ): Unit

    /**
     * Called when a circle is visited.
     */
    actual fun onCircleVisited(
        circle: com.yandex.mapkit.kmp.map.CircleMapObject,
    ): Unit

    /**
     * Called for every child collection. The collection is ignored if this
     * method returns false.
     */
    actual fun onCollectionVisitStart(
        collection: com.yandex.mapkit.kmp.map.MapObjectCollection,
    ): Boolean

    /**
     * Called for visited collections only. If an exception occurred during
     * the visit, the method might be skipped.
     */
    actual fun onCollectionVisitEnd(
        collection: com.yandex.mapkit.kmp.map.MapObjectCollection,
    ): Unit

    /**
     * Called for clusterized placemark collection. The collection is
     * ignored if this method returns false.
     */
    actual fun onClusterizedCollectionVisitStart(
        collection: com.yandex.mapkit.kmp.map.ClusterizedPlacemarkCollection,
    ): Boolean

    /**
     * Called for visited clusterized placemark collections only. If an
     * exception occurred during the visit, the method might be skipped.
     */
    actual fun onClusterizedCollectionVisitEnd(
        collection: com.yandex.mapkit.kmp.map.ClusterizedPlacemarkCollection,
    ): Unit
}

class MapObjectVisitorWrapper internal constructor(impl: MapObjectVisitor, @Suppress("UNUSED_PARAMETER") tag: Unit) : platform.darwin.NSObject(), platform.YandexMapsMobile.YMKMapObjectVisitorProtocol {
    private val impl = kotlin.native.ref.WeakReference(impl)

    override fun onPlacemarkVisitedWithPlacemark(
        placemark: platform.YandexMapsMobile.YMKPlacemarkMapObject,
    ): Unit {
        return impl.get()?.onPlacemarkVisited(
            placemark.let { com.yandex.mapkit.kmp.map.YMKPlacemarkMapObjectWrapper(it) },
        ) ?: Unit
    }

    override fun onPolylineVisitedWithPolyline(
        polyline: platform.YandexMapsMobile.YMKPolylineMapObject,
    ): Unit {
        return impl.get()?.onPolylineVisited(
            polyline.let { com.yandex.mapkit.kmp.map.YMKPolylineMapObjectWrapper(it) },
        ) ?: Unit
    }

    override fun onPolygonVisitedWithPolygon(
        polygon: platform.YandexMapsMobile.YMKPolygonMapObject,
    ): Unit {
        return impl.get()?.onPolygonVisited(
            polygon.let { com.yandex.mapkit.kmp.map.YMKPolygonMapObjectWrapper(it) },
        ) ?: Unit
    }

    override fun onCircleVisitedWithCircle(
        circle: platform.YandexMapsMobile.YMKCircleMapObject,
    ): Unit {
        return impl.get()?.onCircleVisited(
            circle.let { com.yandex.mapkit.kmp.map.YMKCircleMapObjectWrapper(it) },
        ) ?: Unit
    }

    override fun onCollectionVisitStartWithCollection(
        collection: platform.YandexMapsMobile.YMKMapObjectCollection,
    ): Boolean {
        return impl.get()?.onCollectionVisitStart(
            collection.let { com.yandex.mapkit.kmp.map.YMKMapObjectCollectionWrapper(it) },
        ) ?: false
    }

    override fun onCollectionVisitEndWithCollection(
        collection: platform.YandexMapsMobile.YMKMapObjectCollection,
    ): Unit {
        return impl.get()?.onCollectionVisitEnd(
            collection.let { com.yandex.mapkit.kmp.map.YMKMapObjectCollectionWrapper(it) },
        ) ?: Unit
    }

    override fun onClusterizedCollectionVisitStartWithCollection(
        collection: platform.YandexMapsMobile.YMKClusterizedPlacemarkCollection,
    ): Boolean {
        return impl.get()?.onClusterizedCollectionVisitStart(
            collection.let { com.yandex.mapkit.kmp.map.YMKClusterizedPlacemarkCollectionWrapper(it) },
        ) ?: false
    }

    override fun onClusterizedCollectionVisitEndWithCollection(
        collection: platform.YandexMapsMobile.YMKClusterizedPlacemarkCollection,
    ): Unit {
        return impl.get()?.onClusterizedCollectionVisitEnd(
            collection.let { com.yandex.mapkit.kmp.map.YMKClusterizedPlacemarkCollectionWrapper(it) },
        ) ?: Unit
    }

    internal companion object
}

fun MapObjectVisitorWrapper(impl: MapObjectVisitor): MapObjectVisitorWrapper {
    val pointerToCompanionObject = kotlinx.cinterop.interpretCPointer<kotlinx.cinterop.COpaque>(MapObjectVisitorWrapper.Companion.objcPtr())
    val value = kotlinx.cinterop.autoreleasepool {
        platform.objc.objc_getAssociatedObject(impl, pointerToCompanionObject)
    }

    if (value != null) {
        return value as MapObjectVisitorWrapper
    }

    val result = MapObjectVisitorWrapper(impl, Unit)
    kotlinx.cinterop.autoreleasepool {
        platform.objc.objc_setAssociatedObject(impl, pointerToCompanionObject, result, platform.objc.OBJC_ASSOCIATION_RETAIN)
    }
    return result
}

open class YMKMapObjectVisitorProtocolWrapper(val impl: platform.YandexMapsMobile.YMKMapObjectVisitorProtocol) : MapObjectVisitor {
    override fun onPlacemarkVisited(
        placemark: com.yandex.mapkit.kmp.map.PlacemarkMapObject,
    ): Unit {
        return impl.onPlacemarkVisitedWithPlacemark(
            placemark.impl,
        )
    }

    override fun onPolylineVisited(
        polyline: com.yandex.mapkit.kmp.map.PolylineMapObject,
    ): Unit {
        return impl.onPolylineVisitedWithPolyline(
            polyline.impl,
        )
    }

    override fun onPolygonVisited(
        polygon: com.yandex.mapkit.kmp.map.PolygonMapObject,
    ): Unit {
        return impl.onPolygonVisitedWithPolygon(
            polygon.impl,
        )
    }

    override fun onCircleVisited(
        circle: com.yandex.mapkit.kmp.map.CircleMapObject,
    ): Unit {
        return impl.onCircleVisitedWithCircle(
            circle.impl,
        )
    }

    override fun onCollectionVisitStart(
        collection: com.yandex.mapkit.kmp.map.MapObjectCollection,
    ): Boolean {
        return impl.onCollectionVisitStartWithCollection(
            collection.impl,
        )
    }

    override fun onCollectionVisitEnd(
        collection: com.yandex.mapkit.kmp.map.MapObjectCollection,
    ): Unit {
        return impl.onCollectionVisitEndWithCollection(
            collection.impl,
        )
    }

    override fun onClusterizedCollectionVisitStart(
        collection: com.yandex.mapkit.kmp.map.ClusterizedPlacemarkCollection,
    ): Boolean {
        return impl.onClusterizedCollectionVisitStartWithCollection(
            collection.impl,
        )
    }

    override fun onClusterizedCollectionVisitEnd(
        collection: com.yandex.mapkit.kmp.map.ClusterizedPlacemarkCollection,
    ): Unit {
        return impl.onClusterizedCollectionVisitEndWithCollection(
            collection.impl,
        )
    }
}

