@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

/**
 * The ID of a tile on the map.
 */
actual typealias TileId = com.yandex.mapkit.TileId

/**
 * The number of the tile horizontally.
 */
actual val TileId.mpX: Int
    get() = x
/**
 * The number of the tile vertically.
 */
actual val TileId.mpY: Int
    get() = y
/**
 * The number of columns and rows to split the map into.
 */
actual val TileId.mpZ: Int
    get() = z

actual object TileIdFactory {
    actual fun create(
        x: Int,
        y: Int,
        z: Int,
    ): TileId {
        return TileId(
            x,
            y,
            z,
        )
    }
}

