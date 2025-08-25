@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp

/**
 * A collection of geo objects. Allows you to group geo objects for
 * adding them to the map, setting options, etc. Collections are geo
 * objects too.
 */
actual typealias GeoObjectCollection = platform.YandexMapsMobile.YMKGeoObjectCollection

/**
 * The bounds around the collection of objects.
 *
 */
actual val GeoObjectCollection.mpBoundingBox: com.yandex.mapkit.kmp.geometry.BoundingBox?
    get() = boundingBox()
/**
 * The metadata for the objects.
 */
actual val GeoObjectCollection.mpMetadataContainer: com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>
    get() = metadataContainer().let { com.yandex.runtime.kmp.YRTTypeDictionaryWrapper<com.yandex.mapkit.kmp.BaseMetadata>(it) }
actual val GeoObjectCollection.mpChildren: kotlin.collections.List<com.yandex.mapkit.kmp.GeoObjectCollectionItem>
    get() = children().let { it as kotlin.collections.List<platform.YandexMapsMobile.YMKGeoObjectCollectionItem> }

actual object GeoObjectCollectionFactory {
    actual fun create(
        boundingBox: com.yandex.mapkit.kmp.geometry.BoundingBox?,
        metadataContainer: com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>,
        children: kotlin.collections.List<com.yandex.mapkit.kmp.GeoObjectCollectionItem>,
    ): GeoObjectCollection {
        return GeoObjectCollection.geoObjectCollectionWithBoundingBox(
            boundingBox,
            metadataContainer.impl,
            children.let { it as kotlin.collections.List<*> },
        )
    }
}

/**
 * The geo objects in the collection.
 */
actual typealias GeoObjectCollectionItem = platform.YandexMapsMobile.YMKGeoObjectCollectionItem

actual val GeoObjectCollectionItem.obj: com.yandex.mapkit.kmp.GeoObject?
    get() = obj
actual val GeoObjectCollectionItem.collection: com.yandex.mapkit.kmp.GeoObjectCollection?
    get() = collection

actual fun GeoObjectCollectionItemFromObj(obj: com.yandex.mapkit.kmp.GeoObject): GeoObjectCollectionItem = platform.YandexMapsMobile.YMKGeoObjectCollectionItem.itemWithObj(obj)
actual fun GeoObjectCollectionItemFromCollection(collection: com.yandex.mapkit.kmp.GeoObjectCollection): GeoObjectCollectionItem = platform.YandexMapsMobile.YMKGeoObjectCollectionItem.itemWithCollection(collection)

