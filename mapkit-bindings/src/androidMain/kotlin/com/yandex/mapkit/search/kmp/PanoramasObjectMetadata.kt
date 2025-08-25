@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Panorama info.
 */
actual typealias Panorama = com.yandex.mapkit.search.Panorama

/**
 * Machine readable panorama identifier.
 */
actual val Panorama.mpId: String
    get() = id
/**
 * Direction of the panorama center.
 */
actual val Panorama.mpDirection: com.yandex.mapkit.kmp.geometry.Direction
    get() = direction
/**
 * H-Span and V-Span hints for the panorama player.
 */
actual val Panorama.mpSpan: com.yandex.mapkit.kmp.geometry.Span
    get() = span
/**
 * Panorama point.
 */
actual val Panorama.mpPoint: com.yandex.mapkit.kmp.geometry.Point
    get() = point

actual object PanoramaFactory {
    actual fun create(
        id: String,
        direction: com.yandex.mapkit.kmp.geometry.Direction,
        span: com.yandex.mapkit.kmp.geometry.Span,
        point: com.yandex.mapkit.kmp.geometry.Point,
    ): Panorama {
        return Panorama(
            id,
            direction,
            span,
            point,
        )
    }
}

/**
 * Snippet data to get panoramas info.
 */
actual typealias PanoramasObjectMetadata = com.yandex.mapkit.search.PanoramasObjectMetadata

/**
 * List of panoramas.
 */
actual val PanoramasObjectMetadata.mpPanoramas: kotlin.collections.List<com.yandex.mapkit.search.kmp.Panorama>
    get() = panoramas

actual object PanoramasObjectMetadataFactory {
    actual fun create(
        panoramas: kotlin.collections.List<com.yandex.mapkit.search.kmp.Panorama>,
    ): PanoramasObjectMetadata {
        return PanoramasObjectMetadata(
            panoramas,
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.panoramasObjectMetadata: PanoramasObjectMetadata?
    get() = getItem(PanoramasObjectMetadata::class.java)

