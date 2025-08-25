@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * The rectangle to display on the map.
 */
actual typealias Rect = com.yandex.mapkit.map.Rect

/**
 * Minimum rectangle coordinates.
 */
actual val Rect.mpMin: com.yandex.runtime.kmp.NativePoint
    get() = min
/**
 * Maximum rectangle coordinates.
 */
actual val Rect.mpMax: com.yandex.runtime.kmp.NativePoint
    get() = max

actual object RectFactory {
    actual fun create(
        min: com.yandex.runtime.kmp.NativePoint,
        max: com.yandex.runtime.kmp.NativePoint,
    ): Rect {
        return Rect(
            min,
            max,
        )
    }
}

