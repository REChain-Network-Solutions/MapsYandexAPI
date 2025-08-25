@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.geometry

import com.yandex.runtime.kmp.internal.toDouble
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * The panorama field of view.
 */
actual typealias Span = platform.YandexMapsMobile.YMKSpan

/**
 * The horizontal view angle.
 */
actual val Span.mpHorizontalAngle: Double
    get() = horizontalAngle()
/**
 * The vertical view angle.
 */
actual val Span.mpVerticalAngle: Double
    get() = verticalAngle()

actual object SpanFactory {
    actual fun create(
        horizontalAngle: Double,
        verticalAngle: Double,
    ): Span {
        return Span.spanWithHorizontalAngle(
            horizontalAngle,
            verticalAngle,
        )
    }
}

