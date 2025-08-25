@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST", "REDUNDANT_ELSE_IN_WHEN")

package com.yandex.mapkit.kmp.layers

actual enum class TileFormat {
    PNG,
    JPG,
    VECTOR2,
    VECTOR3,
    GEO_JSON,;

    companion object {
        fun toKmp(v: platform.Foundation.NSNumber): TileFormat {
            return toKmp(platform.YandexMapsMobile.YMKTileFormat.byValue(v.unsignedLongValue))
        }

        fun toKmp(v: platform.YandexMapsMobile.YMKTileFormat): TileFormat {
            return when (v) {
                platform.YandexMapsMobile.YMKTileFormat.YMKTileFormatPng -> TileFormat.PNG
                platform.YandexMapsMobile.YMKTileFormat.YMKTileFormatJpg -> TileFormat.JPG
                platform.YandexMapsMobile.YMKTileFormat.YMKTileFormatVector2 -> TileFormat.VECTOR2
                platform.YandexMapsMobile.YMKTileFormat.YMKTileFormatVector3 -> TileFormat.VECTOR3
                platform.YandexMapsMobile.YMKTileFormat.YMKTileFormatGeoJson -> TileFormat.GEO_JSON
                else -> error("unknown YMKTileFormat")
            }
        }
    }
}

fun TileFormat.fromKmp(): platform.YandexMapsMobile.YMKTileFormat {
    return when (this) {
        TileFormat.PNG -> platform.YandexMapsMobile.YMKTileFormat.YMKTileFormatPng
        TileFormat.JPG -> platform.YandexMapsMobile.YMKTileFormat.YMKTileFormatJpg
        TileFormat.VECTOR2 -> platform.YandexMapsMobile.YMKTileFormat.YMKTileFormatVector2
        TileFormat.VECTOR3 -> platform.YandexMapsMobile.YMKTileFormat.YMKTileFormatVector3
        TileFormat.GEO_JSON -> platform.YandexMapsMobile.YMKTileFormat.YMKTileFormatGeoJson
    }
}

