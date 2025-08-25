@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

/**
 * The ID of a tile on the map.
 */
expect class TileId

/**
 * The number of the tile horizontally.
 */
expect val TileId.mpX: Int
/**
 * The number of the tile vertically.
 */
expect val TileId.mpY: Int
/**
 * The number of columns and rows to split the map into.
 */
expect val TileId.mpZ: Int

expect object TileIdFactory {
    fun create(
        x: Int,
        y: Int,
        z: Int,
    ): TileId
}

