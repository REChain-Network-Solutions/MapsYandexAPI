@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

/**
 * Geo object. Can be displayed as a placemark, polyline, polygon, and
 * other, depending on the geometry type.
 */
actual typealias GeoObject = com.yandex.mapkit.GeoObject

/**
 * Object name.
 *
 */
actual val GeoObject.mpName: String?
    get() = name
/**
 * The description of the object.
 *
 */
actual val GeoObject.mpDescriptionText: String?
    get() = descriptionText
/**
 * The object's geometry.
 */
actual val GeoObject.mpGeometry: kotlin.collections.List<com.yandex.mapkit.kmp.geometry.Geometry>
    get() = geometry
/**
 * A rectangular box around the object.
 *
 */
actual val GeoObject.mpBoundingBox: com.yandex.mapkit.kmp.geometry.BoundingBox?
    get() = boundingBox
/**
 * The attribution of information to a specific author.
 */
actual val GeoObject.mpAttributionMap: kotlin.collections.Map<String, Attribution>
    get() = attributionMap
/**
 * The object's metadata.
 */
actual val GeoObject.mpMetadataContainer: com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>
    get() = metadataContainer
/**
 * The name of the internet resource.
 */
actual val GeoObject.mpAref: kotlin.collections.List<String>
    get() = aref

actual object GeoObjectFactory {
    actual fun create(
        name: String?,
        descriptionText: String?,
        geometry: kotlin.collections.List<com.yandex.mapkit.kmp.geometry.Geometry>,
        boundingBox: com.yandex.mapkit.kmp.geometry.BoundingBox?,
        attributionMap: kotlin.collections.Map<String, Attribution>,
        metadataContainer: com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>,
        aref: kotlin.collections.List<String>,
    ): GeoObject {
        return GeoObject(
            name,
            descriptionText,
            geometry,
            boundingBox,
            attributionMap,
            metadataContainer,
            aref,
        )
    }
}

