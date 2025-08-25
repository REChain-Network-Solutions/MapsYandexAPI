@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

/**
 * A collection of geo objects. Allows you to group geo objects for
 * adding them to the map, setting options, etc. Collections are geo
 * objects too.
 */
actual typealias GeoObjectCollection = com.yandex.mapkit.GeoObjectCollection

/**
 * The bounds around the collection of objects.
 *
 */
actual val GeoObjectCollection.mpBoundingBox: com.yandex.mapkit.kmp.geometry.BoundingBox?
    get() = boundingBox
/**
 * The metadata for the objects.
 */
actual val GeoObjectCollection.mpMetadataContainer: com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>
    get() = metadataContainer
actual val GeoObjectCollection.mpChildren: kotlin.collections.List<com.yandex.mapkit.kmp.GeoObjectCollectionItem>
    get() = children

actual object GeoObjectCollectionFactory {
    actual fun create(
        boundingBox: com.yandex.mapkit.kmp.geometry.BoundingBox?,
        metadataContainer: com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>,
        children: kotlin.collections.List<com.yandex.mapkit.kmp.GeoObjectCollectionItem>,
    ): GeoObjectCollection {
        return GeoObjectCollection(
            boundingBox,
            metadataContainer,
            children,
        )
    }
}

/**
 * The geo objects in the collection.
 */
actual typealias GeoObjectCollectionItem = com.yandex.mapkit.GeoObjectCollection.Item

actual val GeoObjectCollectionItem.obj: com.yandex.mapkit.kmp.GeoObject?
    get() = obj
actual val GeoObjectCollectionItem.collection: com.yandex.mapkit.kmp.GeoObjectCollection?
    get() = collection

actual fun GeoObjectCollectionItemFromObj(obj: com.yandex.mapkit.kmp.GeoObject): GeoObjectCollectionItem = com.yandex.mapkit.GeoObjectCollection.Item.fromObj(obj)
actual fun GeoObjectCollectionItemFromCollection(collection: com.yandex.mapkit.kmp.GeoObjectCollection): GeoObjectCollectionItem = com.yandex.mapkit.GeoObjectCollection.Item.fromCollection(collection)

