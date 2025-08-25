@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST", "REDUNDANT_ELSE_IN_WHEN")

package com.yandex.mapkit.kmp.map

/**
 * Metadata type added to all objects returned by Map.visibleObjects
 */
actual typealias GeoObjectInspectionMetadata = platform.YandexMapsMobile.YMKGeoObjectInspectionMetadata

actual val GeoObjectInspectionMetadata.mpLayerId: String
    get() = layerId()
actual val GeoObjectInspectionMetadata.mpObjectType: com.yandex.mapkit.kmp.map.GeoObjectInspectionMetadataObjectType
    get() = objectType().let { com.yandex.mapkit.kmp.map.GeoObjectInspectionMetadataObjectType.toKmp(it) }

actual object GeoObjectInspectionMetadataFactory {
    actual fun create(
        layerId: String,
        objectType: com.yandex.mapkit.kmp.map.GeoObjectInspectionMetadataObjectType,
    ): GeoObjectInspectionMetadata {
        return GeoObjectInspectionMetadata.geoObjectInspectionMetadataWithLayerId(
            layerId,
            objectType.fromKmp(),
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.geoObjectInspectionMetadata: GeoObjectInspectionMetadata?
    get() = impl.getItemOfClass(GeoObjectInspectionMetadata) as? GeoObjectInspectionMetadata

actual enum class GeoObjectInspectionMetadataObjectType {
    POINT,
    POLYLINE,
    POLYGON,
    CIRCLE,;

    companion object {
        fun toKmp(v: platform.Foundation.NSNumber): GeoObjectInspectionMetadataObjectType {
            return toKmp(platform.YandexMapsMobile.YMKGeoObjectInspectionMetadataObjectType.byValue(v.unsignedLongValue))
        }

        fun toKmp(v: platform.YandexMapsMobile.YMKGeoObjectInspectionMetadataObjectType): GeoObjectInspectionMetadataObjectType {
            return when (v) {
                platform.YandexMapsMobile.YMKGeoObjectInspectionMetadataObjectType.YMKGeoObjectInspectionMetadataObjectTypePoint -> GeoObjectInspectionMetadataObjectType.POINT
                platform.YandexMapsMobile.YMKGeoObjectInspectionMetadataObjectType.YMKGeoObjectInspectionMetadataObjectTypePolyline -> GeoObjectInspectionMetadataObjectType.POLYLINE
                platform.YandexMapsMobile.YMKGeoObjectInspectionMetadataObjectType.YMKGeoObjectInspectionMetadataObjectTypePolygon -> GeoObjectInspectionMetadataObjectType.POLYGON
                platform.YandexMapsMobile.YMKGeoObjectInspectionMetadataObjectType.YMKGeoObjectInspectionMetadataObjectTypeCircle -> GeoObjectInspectionMetadataObjectType.CIRCLE
                else -> error("unknown YMKGeoObjectInspectionMetadataObjectType")
            }
        }
    }
}

fun GeoObjectInspectionMetadataObjectType.fromKmp(): platform.YandexMapsMobile.YMKGeoObjectInspectionMetadataObjectType {
    return when (this) {
        GeoObjectInspectionMetadataObjectType.POINT -> platform.YandexMapsMobile.YMKGeoObjectInspectionMetadataObjectType.YMKGeoObjectInspectionMetadataObjectTypePoint
        GeoObjectInspectionMetadataObjectType.POLYLINE -> platform.YandexMapsMobile.YMKGeoObjectInspectionMetadataObjectType.YMKGeoObjectInspectionMetadataObjectTypePolyline
        GeoObjectInspectionMetadataObjectType.POLYGON -> platform.YandexMapsMobile.YMKGeoObjectInspectionMetadataObjectType.YMKGeoObjectInspectionMetadataObjectTypePolygon
        GeoObjectInspectionMetadataObjectType.CIRCLE -> platform.YandexMapsMobile.YMKGeoObjectInspectionMetadataObjectType.YMKGeoObjectInspectionMetadataObjectTypeCircle
    }
}

