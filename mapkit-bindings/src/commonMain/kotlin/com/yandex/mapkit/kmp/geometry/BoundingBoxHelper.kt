@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.geometry

expect object BoundingBoxHelper {
    /**
     * Gets bounds based on a bounding box.
     */
    fun getBounds(
        bbox: com.yandex.mapkit.kmp.geometry.BoundingBox,
    ): com.yandex.mapkit.kmp.geometry.BoundingBox

    /**
     * Gets bounds based on a point.
     */
    fun getBounds(
        point: com.yandex.mapkit.kmp.geometry.Point,
    ): com.yandex.mapkit.kmp.geometry.BoundingBox

    /**
     * Gets bounds based on a polyline.
     */
    fun getBounds(
        polyline: com.yandex.mapkit.kmp.geometry.Polyline,
    ): com.yandex.mapkit.kmp.geometry.BoundingBox

    /**
     * Gets bounds based on a linear ring.
     */
    fun getBounds(
        ring: com.yandex.mapkit.kmp.geometry.LinearRing,
    ): com.yandex.mapkit.kmp.geometry.BoundingBox

    /**
     * Gets bounds based on a polygon.
     */
    fun getBounds(
        polygon: com.yandex.mapkit.kmp.geometry.Polygon,
    ): com.yandex.mapkit.kmp.geometry.BoundingBox

    /**
     * Gets bounds based on two bounding boxes.
     */
    fun getBounds(
        first: com.yandex.mapkit.kmp.geometry.BoundingBox,
        second: com.yandex.mapkit.kmp.geometry.BoundingBox,
    ): com.yandex.mapkit.kmp.geometry.BoundingBox
}

