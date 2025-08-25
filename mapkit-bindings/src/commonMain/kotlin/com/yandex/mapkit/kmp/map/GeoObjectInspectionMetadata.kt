@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * Metadata type added to all objects returned by Map.visibleObjects
 */
expect class GeoObjectInspectionMetadata

expect val GeoObjectInspectionMetadata.mpLayerId: String
expect val GeoObjectInspectionMetadata.mpObjectType: com.yandex.mapkit.kmp.map.GeoObjectInspectionMetadataObjectType

expect object GeoObjectInspectionMetadataFactory {
    fun create(
        layerId: String,
        objectType: com.yandex.mapkit.kmp.map.GeoObjectInspectionMetadataObjectType,
    ): GeoObjectInspectionMetadata
}

expect val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.geoObjectInspectionMetadata: GeoObjectInspectionMetadata?

expect enum class GeoObjectInspectionMetadataObjectType {
    POINT,
    POLYLINE,
    POLYGON,
    CIRCLE,
}

