@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.layers

import com.yandex.runtime.kmp.internal.toByteArray
import com.yandex.runtime.kmp.internal.toNSData
import kotlinx.cinterop.objcPtr

actual interface BaseDataSource {
    val impl: platform.YandexMapsMobile.YMKBaseDataSource

    /**
     * Tells if this object is valid or not. Any method called on an invalid
     * object will throw an exception. The object becomes invalid only on UI
     * thread, and only when its implementation depends on objects already
     * destroyed by now. Please refer to general docs about the interface for
     * details on its invalidation.
     */
    actual fun isValid(): Boolean
}
/**
 * Stores id of data source.
 */
actual val BaseDataSource.id: String
    get() = impl.id

open class YMKBaseDataSourceWrapper(override val impl: platform.YandexMapsMobile.YMKBaseDataSource) : BaseDataSource {
    override fun isValid(): Boolean = impl.isValid()
}

actual interface TileDataSource : com.yandex.mapkit.kmp.layers.BaseDataSource {
    override val impl: platform.YandexMapsMobile.YMKTileDataSource

    /**
     * Invalidates data source and reloads all tiles. Must not be called if
     * DataSource does not support versioning: LayerOptions.versionSupport =
     * false;
     *
     * This method may be called on any thread. Its implementation must be thread-safe.
     */
    actual fun invalidate(
        version: String,
    ): Unit
}

open class YMKTileDataSourceWrapper(override val impl: platform.YandexMapsMobile.YMKTileDataSource) : TileDataSource, com.yandex.mapkit.kmp.layers.YMKBaseDataSourceWrapper(impl) {
    override fun invalidate(
        version: String,
    ): Unit {
        return impl.invalidateWithVersion(
            version,
        )
    }
}

actual interface DataSource : com.yandex.mapkit.kmp.layers.BaseDataSource {
    override val impl: platform.YandexMapsMobile.YMKDataSource

    /**
     * Updates all data. This method works synchronously and blocks UI
     * thread. It is intended for passing not more than 500kB of data;
     * otherwise, it will affect the responsiveness of UI and map.
     */
    actual fun setData(
        data: ByteArray,
    ): Unit
}

open class YMKDataSourceWrapper(override val impl: platform.YandexMapsMobile.YMKDataSource) : DataSource, com.yandex.mapkit.kmp.layers.YMKBaseDataSourceWrapper(impl) {
    override fun setData(
        data: ByteArray,
    ): Unit {
        return impl.setDataWithData(
            data.toNSData(),
        )
    }
}

actual interface DataSourceListener {
    actual fun onDataSourceUpdated(
        dataSource: com.yandex.mapkit.kmp.layers.BaseDataSource,
    ): Unit
}

class DataSourceListenerWrapper internal constructor(impl: DataSourceListener, @Suppress("UNUSED_PARAMETER") tag: Unit) : platform.darwin.NSObject(), platform.YandexMapsMobile.YMKDataSourceListenerProtocol {
    private val impl = kotlin.native.ref.WeakReference(impl)

    override fun onDataSourceUpdatedWithDataSource(
        dataSource: platform.YandexMapsMobile.YMKBaseDataSource,
    ): Unit {
        return impl.get()?.onDataSourceUpdated(
            dataSource.let { com.yandex.mapkit.kmp.layers.YMKBaseDataSourceWrapper(it) },
        ) ?: Unit
    }

    internal companion object
}

fun DataSourceListenerWrapper(impl: DataSourceListener): DataSourceListenerWrapper {
    val pointerToCompanionObject = kotlinx.cinterop.interpretCPointer<kotlinx.cinterop.COpaque>(DataSourceListenerWrapper.Companion.objcPtr())
    val value = kotlinx.cinterop.autoreleasepool {
        platform.objc.objc_getAssociatedObject(impl, pointerToCompanionObject)
    }

    if (value != null) {
        return value as DataSourceListenerWrapper
    }

    val result = DataSourceListenerWrapper(impl, Unit)
    kotlinx.cinterop.autoreleasepool {
        platform.objc.objc_setAssociatedObject(impl, pointerToCompanionObject, result, platform.objc.OBJC_ASSOCIATION_RETAIN)
    }
    return result
}

open class YMKDataSourceListenerProtocolWrapper(val impl: platform.YandexMapsMobile.YMKDataSourceListenerProtocol) : DataSourceListener {
    override fun onDataSourceUpdated(
        dataSource: com.yandex.mapkit.kmp.layers.BaseDataSource,
    ): Unit {
        return impl.onDataSourceUpdatedWithDataSource(
            dataSource.impl,
        )
    }
}

