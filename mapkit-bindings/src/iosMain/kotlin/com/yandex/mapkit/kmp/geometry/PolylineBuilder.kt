@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.geometry

/**
 * A builder class for polyline elements.
 */
actual interface PolylineBuilder {
    val impl: platform.YandexMapsMobile.YMKPolylineBuilder

    /**
     * Appends a polyline.
     */
    actual fun append(
        polyline: com.yandex.mapkit.kmp.geometry.Polyline,
    ): Unit

    /**
     * Appends a point.
     */
    actual fun append(
        point: com.yandex.mapkit.kmp.geometry.Point,
    ): Unit

    /**
     * Builds a polyline.
     */
    actual fun build(): com.yandex.mapkit.kmp.geometry.Polyline
}

open class YMKPolylineBuilderWrapper(override val impl: platform.YandexMapsMobile.YMKPolylineBuilder) : PolylineBuilder {
    override fun append(
        polyline: com.yandex.mapkit.kmp.geometry.Polyline,
    ): Unit {
        return impl.appendWithPolyline(
            polyline,
        )
    }

    override fun append(
        point: com.yandex.mapkit.kmp.geometry.Point,
    ): Unit {
        return impl.appendWithPoint(
            point,
        )
    }

    override fun build(): com.yandex.mapkit.kmp.geometry.Polyline {
        return impl.build()
    }
}

actual object PolylineBuilderFactory {
    actual fun create(): com.yandex.mapkit.kmp.geometry.PolylineBuilder {
        return platform.YandexMapsMobile.YMKPolylineBuilderFactory.create().let { com.yandex.mapkit.kmp.geometry.YMKPolylineBuilderWrapper(it) }
    }
}

