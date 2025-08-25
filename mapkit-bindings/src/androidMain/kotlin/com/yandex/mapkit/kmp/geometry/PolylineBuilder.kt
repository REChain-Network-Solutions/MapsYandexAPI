@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.geometry

/**
 * A builder class for polyline elements.
 */
actual typealias PolylineBuilder = com.yandex.mapkit.geometry.PolylineBuilder

actual object PolylineBuilderFactory {
    actual fun create(): com.yandex.mapkit.kmp.geometry.PolylineBuilder {
        return com.yandex.mapkit.geometry.PolylineBuilderFactory.create()
    }
}

