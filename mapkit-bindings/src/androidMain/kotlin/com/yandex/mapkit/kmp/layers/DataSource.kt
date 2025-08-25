@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.layers

actual typealias BaseDataSource = com.yandex.mapkit.layers.BaseDataSource

/**
 * Stores id of data source.
 */
actual val BaseDataSource.id: String
    get() = id

actual typealias TileDataSource = com.yandex.mapkit.layers.TileDataSource

actual typealias DataSource = com.yandex.mapkit.layers.DataSource

actual typealias DataSourceListener = com.yandex.mapkit.layers.DataSourceListener

