@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.geometry

/**
 * A builder class for polyline elements.
 */
expect interface PolylineBuilder {
    /**
     * Appends a polyline.
     */
    fun append(
        polyline: com.yandex.mapkit.kmp.geometry.Polyline,
    ): Unit

    /**
     * Appends a point.
     */
    fun append(
        point: com.yandex.mapkit.kmp.geometry.Point,
    ): Unit

    /**
     * Builds a polyline.
     */
    fun build(): com.yandex.mapkit.kmp.geometry.Polyline
}

expect object PolylineBuilderFactory {
    fun create(): com.yandex.mapkit.kmp.geometry.PolylineBuilder
}

