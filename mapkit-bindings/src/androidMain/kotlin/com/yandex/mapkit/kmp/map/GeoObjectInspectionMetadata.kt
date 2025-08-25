@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * Metadata type added to all objects returned by Map.visibleObjects
 */
actual typealias GeoObjectInspectionMetadata = com.yandex.mapkit.map.GeoObjectInspectionMetadata

actual val GeoObjectInspectionMetadata.mpLayerId: String
    get() = layerId
actual val GeoObjectInspectionMetadata.mpObjectType: com.yandex.mapkit.kmp.map.GeoObjectInspectionMetadataObjectType
    get() = objectType

actual object GeoObjectInspectionMetadataFactory {
    actual fun create(
        layerId: String,
        objectType: com.yandex.mapkit.kmp.map.GeoObjectInspectionMetadataObjectType,
    ): GeoObjectInspectionMetadata {
        return GeoObjectInspectionMetadata(
            layerId,
            objectType,
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.geoObjectInspectionMetadata: GeoObjectInspectionMetadata?
    get() = getItem(GeoObjectInspectionMetadata::class.java)

actual typealias GeoObjectInspectionMetadataObjectType = com.yandex.mapkit.map.GeoObjectInspectionMetadata.ObjectType

