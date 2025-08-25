@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp

import com.yandex.runtime.kmp.internal.toInt
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * The ID of a tile on the map.
 */
actual typealias TileId = platform.YandexMapsMobile.YMKTileId

/**
 * The number of the tile horizontally.
 */
actual val TileId.mpX: Int
    get() = x().toInt()
/**
 * The number of the tile vertically.
 */
actual val TileId.mpY: Int
    get() = y().toInt()
/**
 * The number of columns and rows to split the map into.
 */
actual val TileId.mpZ: Int
    get() = z().toInt()

actual object TileIdFactory {
    actual fun create(
        x: Int,
        y: Int,
        z: Int,
    ): TileId {
        return TileId.tileIdWithX(
            x.toULong(),
            y.toULong(),
            z.toULong(),
        )
    }
}

