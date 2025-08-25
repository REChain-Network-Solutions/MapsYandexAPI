@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * Geo object metadata which is needed to select object.
 */
expect class GeoObjectSelectionMetadata

/**
 * Object ID.
 */
expect val GeoObjectSelectionMetadata.mpObjectId: String
/**
 * Data source name.
 */
expect val GeoObjectSelectionMetadata.mpDataSourceName: String
/**
 * Layer ID.
 */
expect val GeoObjectSelectionMetadata.mpLayerId: String
/**
 * Group ID.
 *
 */
expect val GeoObjectSelectionMetadata.mpGroupId: Long?

expect object GeoObjectSelectionMetadataFactory {
    fun create(
        objectId: String,
        dataSourceName: String,
        layerId: String,
        groupId: Long?,
    ): GeoObjectSelectionMetadata
}

expect val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.geoObjectSelectionMetadata: GeoObjectSelectionMetadata?

