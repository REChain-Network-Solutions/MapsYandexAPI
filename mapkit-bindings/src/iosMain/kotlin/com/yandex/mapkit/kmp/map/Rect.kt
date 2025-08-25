@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

import com.yandex.runtime.kmp.fromKmp
import com.yandex.runtime.kmp.fromKmpOptional
import com.yandex.runtime.kmp.toKmp

/**
 * The rectangle to display on the map.
 */
actual typealias Rect = platform.YandexMapsMobile.YMKRect

/**
 * Minimum rectangle coordinates.
 */
actual val Rect.mpMin: com.yandex.runtime.kmp.NativePoint
    get() = min().toKmp()
/**
 * Maximum rectangle coordinates.
 */
actual val Rect.mpMax: com.yandex.runtime.kmp.NativePoint
    get() = max().toKmp()

actual object RectFactory {
    actual fun create(
        min: com.yandex.runtime.kmp.NativePoint,
        max: com.yandex.runtime.kmp.NativePoint,
    ): Rect {
        return Rect.rectWithMin(
            min.fromKmp(),
            max.fromKmp(),
        )
    }
}

