@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.layers

expect interface BaseDataSource {
    /**
     * Tells if this object is valid or not. Any method called on an invalid
     * object will throw an exception. The object becomes invalid only on UI
     * thread, and only when its implementation depends on objects already
     * destroyed by now. Please refer to general docs about the interface for
     * details on its invalidation.
     */
    fun isValid(): Boolean
}

/**
 * Stores id of data source.
 */
expect val BaseDataSource.id: String

expect interface TileDataSource : com.yandex.mapkit.kmp.layers.BaseDataSource {
    /**
     * Invalidates data source and reloads all tiles. Must not be called if
     * DataSource does not support versioning: LayerOptions.versionSupport =
     * false;
     *
     * This method may be called on any thread. Its implementation must be thread-safe.
     */
    fun invalidate(
        version: String,
    ): Unit
}

expect interface DataSource : com.yandex.mapkit.kmp.layers.BaseDataSource {
    /**
     * Updates all data. This method works synchronously and blocks UI
     * thread. It is intended for passing not more than 500kB of data;
     * otherwise, it will affect the responsiveness of UI and map.
     */
    fun setData(
        data: ByteArray,
    ): Unit
}

expect interface DataSourceListener {
    fun onDataSourceUpdated(
        dataSource: com.yandex.mapkit.kmp.layers.BaseDataSource,
    ): Unit
}

