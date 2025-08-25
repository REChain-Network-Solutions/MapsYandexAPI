@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

/**
 * Geo object. Can be displayed as a placemark, polyline, polygon, and
 * other, depending on the geometry type.
 */
expect class GeoObject

/**
 * Object name.
 *
 */
expect val GeoObject.mpName: String?
/**
 * The description of the object.
 *
 */
expect val GeoObject.mpDescriptionText: String?
/**
 * The object's geometry.
 */
expect val GeoObject.mpGeometry: kotlin.collections.List<com.yandex.mapkit.kmp.geometry.Geometry>
/**
 * A rectangular box around the object.
 *
 */
expect val GeoObject.mpBoundingBox: com.yandex.mapkit.kmp.geometry.BoundingBox?
/**
 * The attribution of information to a specific author.
 */
expect val GeoObject.mpAttributionMap: kotlin.collections.Map<String, Attribution>
/**
 * The object's metadata.
 */
expect val GeoObject.mpMetadataContainer: com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>
/**
 * The name of the internet resource.
 */
expect val GeoObject.mpAref: kotlin.collections.List<String>

expect object GeoObjectFactory {
    fun create(
        name: String?,
        descriptionText: String?,
        geometry: kotlin.collections.List<com.yandex.mapkit.kmp.geometry.Geometry>,
        boundingBox: com.yandex.mapkit.kmp.geometry.BoundingBox?,
        attributionMap: kotlin.collections.Map<String, Attribution>,
        metadataContainer: com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>,
        aref: kotlin.collections.List<String>,
    ): GeoObject
}

