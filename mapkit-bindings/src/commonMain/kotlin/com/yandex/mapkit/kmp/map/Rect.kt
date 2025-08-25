@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * The rectangle to display on the map.
 */
expect class Rect

/**
 * Minimum rectangle coordinates.
 */
expect val Rect.mpMin: com.yandex.runtime.kmp.NativePoint
/**
 * Maximum rectangle coordinates.
 */
expect val Rect.mpMax: com.yandex.runtime.kmp.NativePoint

expect object RectFactory {
    fun create(
        min: com.yandex.runtime.kmp.NativePoint,
        max: com.yandex.runtime.kmp.NativePoint,
    ): Rect
}

