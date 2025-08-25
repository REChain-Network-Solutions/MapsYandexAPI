@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST", "REDUNDANT_ELSE_IN_WHEN")

package com.yandex.mapkit.kmp.map

import com.yandex.mapkit.kmp.layers.fromKmp
import kotlinx.cinterop.objcPtr

actual interface BaseTileDataSourceBuilder : com.yandex.mapkit.kmp.map.BaseDataSourceBuilder {
    override val impl: platform.YandexMapsMobile.YMKBaseTileDataSourceBuilder

    actual fun setProjection(
        projection: com.yandex.mapkit.kmp.geometry.geo.Projection,
    ): Unit

    actual fun setZoomRanges(
        zoomRanges: kotlin.collections.List<com.yandex.mapkit.kmp.ZoomRangeIdl>,
    ): Unit

    actual fun setTileFormat(
        format: com.yandex.mapkit.kmp.layers.TileFormat,
    ): Unit
}

open class YMKBaseTileDataSourceBuilderWrapper(override val impl: platform.YandexMapsMobile.YMKBaseTileDataSourceBuilder) : BaseTileDataSourceBuilder, com.yandex.mapkit.kmp.map.YMKBaseDataSourceBuilderWrapper(impl) {

    override fun setProjection(
        projection: com.yandex.mapkit.kmp.geometry.geo.Projection,
    ): Unit {
        return impl.setProjectionWithProjection(
            projection.impl,
        )
    }

    override fun setZoomRanges(
        zoomRanges: kotlin.collections.List<com.yandex.mapkit.kmp.ZoomRangeIdl>,
    ): Unit {
        return impl.setZoomRangesWithZoomRanges(
            zoomRanges.let { it as kotlin.collections.List<*> },
        )
    }

    override fun setTileFormat(
        format: com.yandex.mapkit.kmp.layers.TileFormat,
    ): Unit {
        return impl.setTileFormatWithFormat(
            format.fromKmp(),
        )
    }
}

actual interface TileDataSourceBuilder : com.yandex.mapkit.kmp.map.BaseTileDataSourceBuilder {
    override val impl: platform.YandexMapsMobile.YMKTileDataSourceBuilder
}

open class YMKTileDataSourceBuilderWrapper(override val impl: platform.YandexMapsMobile.YMKTileDataSourceBuilder) : TileDataSourceBuilder, com.yandex.mapkit.kmp.map.YMKBaseTileDataSourceBuilderWrapper(impl) {

}



actual interface CreateTileDataSource {
    actual fun createTileDataSource(
        builder: com.yandex.mapkit.kmp.map.TileDataSourceBuilder,
    ): Unit
}

internal class CreateTileDataSourceWrapper(val impl: CreateTileDataSource): platform.YandexMapsMobile.YMKCreateTileDataSource {
    override fun invoke(
        builder: platform.YandexMapsMobile.YMKTileDataSourceBuilder?,
    ) {
            impl.createTileDataSource(
                builder!!.let { com.yandex.mapkit.kmp.map.YMKTileDataSourceBuilderWrapper(it) },
            )
    }
}

