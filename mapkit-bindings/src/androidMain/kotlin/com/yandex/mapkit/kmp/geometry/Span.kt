@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.geometry

/**
 * The panorama field of view.
 */
actual typealias Span = com.yandex.mapkit.geometry.Span

/**
 * The horizontal view angle.
 */
actual val Span.mpHorizontalAngle: Double
    get() = horizontalAngle
/**
 * The vertical view angle.
 */
actual val Span.mpVerticalAngle: Double
    get() = verticalAngle

actual object SpanFactory {
    actual fun create(
        horizontalAngle: Double,
        verticalAngle: Double,
    ): Span {
        return Span(
            horizontalAngle,
            verticalAngle,
        )
    }
}

