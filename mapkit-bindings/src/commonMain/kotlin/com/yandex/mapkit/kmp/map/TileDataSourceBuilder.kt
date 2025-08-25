@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

expect interface BaseTileDataSourceBuilder : com.yandex.mapkit.kmp.map.BaseDataSourceBuilder {

    fun setProjection(
        projection: com.yandex.mapkit.kmp.geometry.geo.Projection,
    ): Unit

    fun setZoomRanges(
        zoomRanges: kotlin.collections.List<com.yandex.mapkit.kmp.ZoomRangeIdl>,
    ): Unit

    fun setTileFormat(
        format: com.yandex.mapkit.kmp.layers.TileFormat,
    ): Unit
}

expect interface TileDataSourceBuilder : com.yandex.mapkit.kmp.map.BaseTileDataSourceBuilder

expect interface CreateTileDataSource {
    /**
     * User defined factory, which sets up TileDataSourceBuilder. Will be
     * called only once.
     */
    fun createTileDataSource(
        builder: com.yandex.mapkit.kmp.map.TileDataSourceBuilder,
    ): Unit
}

