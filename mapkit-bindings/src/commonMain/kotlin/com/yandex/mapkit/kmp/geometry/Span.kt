@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.geometry

/**
 * The panorama field of view.
 */
expect class Span

/**
 * The horizontal view angle.
 */
expect val Span.mpHorizontalAngle: Double
/**
 * The vertical view angle.
 */
expect val Span.mpVerticalAngle: Double

expect object SpanFactory {
    fun create(
        horizontalAngle: Double,
        verticalAngle: Double,
    ): Span
}

