@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

/**
 * A collection of geo objects. Allows you to group geo objects for
 * adding them to the map, setting options, etc. Collections are geo
 * objects too.
 */
expect class GeoObjectCollection

/**
 * The bounds around the collection of objects.
 *
 */
expect val GeoObjectCollection.mpBoundingBox: com.yandex.mapkit.kmp.geometry.BoundingBox?
/**
 * The metadata for the objects.
 */
expect val GeoObjectCollection.mpMetadataContainer: com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>
expect val GeoObjectCollection.mpChildren: kotlin.collections.List<com.yandex.mapkit.kmp.GeoObjectCollectionItem>

expect object GeoObjectCollectionFactory {
    fun create(
        boundingBox: com.yandex.mapkit.kmp.geometry.BoundingBox?,
        metadataContainer: com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>,
        children: kotlin.collections.List<com.yandex.mapkit.kmp.GeoObjectCollectionItem>,
    ): GeoObjectCollection
}

/**
 * The geo objects in the collection.
 */
expect class GeoObjectCollectionItem

expect val GeoObjectCollectionItem.obj: com.yandex.mapkit.kmp.GeoObject?
expect val GeoObjectCollectionItem.collection: com.yandex.mapkit.kmp.GeoObjectCollection?

expect fun GeoObjectCollectionItemFromObj(obj: com.yandex.mapkit.kmp.GeoObject): GeoObjectCollectionItem
expect fun GeoObjectCollectionItemFromCollection(collection: com.yandex.mapkit.kmp.GeoObjectCollection): GeoObjectCollectionItem

