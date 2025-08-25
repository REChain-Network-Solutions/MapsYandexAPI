@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * Geo object metadata which is needed to select object.
 */
actual typealias GeoObjectSelectionMetadata = com.yandex.mapkit.map.GeoObjectSelectionMetadata

/**
 * Object ID.
 */
actual val GeoObjectSelectionMetadata.mpObjectId: String
    get() = objectId
/**
 * Data source name.
 */
actual val GeoObjectSelectionMetadata.mpDataSourceName: String
    get() = dataSourceName
/**
 * Layer ID.
 */
actual val GeoObjectSelectionMetadata.mpLayerId: String
    get() = layerId
/**
 * Group ID.
 *
 */
actual val GeoObjectSelectionMetadata.mpGroupId: Long?
    get() = groupId

actual object GeoObjectSelectionMetadataFactory {
    actual fun create(
        objectId: String,
        dataSourceName: String,
        layerId: String,
        groupId: Long?,
    ): GeoObjectSelectionMetadata {
        return GeoObjectSelectionMetadata(
            objectId,
            dataSourceName,
            layerId,
            groupId,
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.geoObjectSelectionMetadata: GeoObjectSelectionMetadata?
    get() = getItem(GeoObjectSelectionMetadata::class.java)

