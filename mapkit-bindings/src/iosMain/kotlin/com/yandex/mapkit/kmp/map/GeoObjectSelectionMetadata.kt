@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

import com.yandex.runtime.kmp.internal.toLong
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * Geo object metadata which is needed to select object.
 */
actual typealias GeoObjectSelectionMetadata = platform.YandexMapsMobile.YMKGeoObjectSelectionMetadata

/**
 * Object ID.
 */
actual val GeoObjectSelectionMetadata.mpObjectId: String
    get() = objectId()
/**
 * Data source name.
 */
actual val GeoObjectSelectionMetadata.mpDataSourceName: String
    get() = dataSourceName()
/**
 * Layer ID.
 */
actual val GeoObjectSelectionMetadata.mpLayerId: String
    get() = layerId()
/**
 * Group ID.
 *
 */
actual val GeoObjectSelectionMetadata.mpGroupId: Long?
    get() = groupId()?.toLong()

actual object GeoObjectSelectionMetadataFactory {
    actual fun create(
        objectId: String,
        dataSourceName: String,
        layerId: String,
        groupId: Long?,
    ): GeoObjectSelectionMetadata {
        return GeoObjectSelectionMetadata.geoObjectSelectionMetadataWithObjectId(
            objectId,
            dataSourceName,
            layerId,
            groupId?.toNSNumber(),
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.geoObjectSelectionMetadata: GeoObjectSelectionMetadata?
    get() = impl.getItemOfClass(GeoObjectSelectionMetadata) as? GeoObjectSelectionMetadata

