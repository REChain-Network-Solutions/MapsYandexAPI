@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.geometry

actual object BoundingBoxHelper {
    /**
     * Gets bounds based on a bounding box.
     */
    actual fun getBounds(
        bbox: com.yandex.mapkit.kmp.geometry.BoundingBox,
    ): com.yandex.mapkit.kmp.geometry.BoundingBox {
        return com.yandex.mapkit.geometry.BoundingBoxHelper.getBounds(
            bbox,
        )
    }

    /**
     * Gets bounds based on a point.
     */
    actual fun getBounds(
        point: com.yandex.mapkit.kmp.geometry.Point,
    ): com.yandex.mapkit.kmp.geometry.BoundingBox {
        return com.yandex.mapkit.geometry.BoundingBoxHelper.getBounds(
            point,
        )
    }

    /**
     * Gets bounds based on a polyline.
     */
    actual fun getBounds(
        polyline: com.yandex.mapkit.kmp.geometry.Polyline,
    ): com.yandex.mapkit.kmp.geometry.BoundingBox {
        return com.yandex.mapkit.geometry.BoundingBoxHelper.getBounds(
            polyline,
        )
    }

    /**
     * Gets bounds based on a linear ring.
     */
    actual fun getBounds(
        ring: com.yandex.mapkit.kmp.geometry.LinearRing,
    ): com.yandex.mapkit.kmp.geometry.BoundingBox {
        return com.yandex.mapkit.geometry.BoundingBoxHelper.getBounds(
            ring,
        )
    }

    /**
     * Gets bounds based on a polygon.
     */
    actual fun getBounds(
        polygon: com.yandex.mapkit.kmp.geometry.Polygon,
    ): com.yandex.mapkit.kmp.geometry.BoundingBox {
        return com.yandex.mapkit.geometry.BoundingBoxHelper.getBounds(
            polygon,
        )
    }

    /**
     * Gets bounds based on two bounding boxes.
     */
    actual fun getBounds(
        first: com.yandex.mapkit.kmp.geometry.BoundingBox,
        second: com.yandex.mapkit.kmp.geometry.BoundingBox,
    ): com.yandex.mapkit.kmp.geometry.BoundingBox {
        return com.yandex.mapkit.geometry.BoundingBoxHelper.getBounds(
            first,
            second,
        )
    }
}

