@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

/**
 * Panorama info.
 */
actual typealias Panorama = platform.YandexMapsMobile.YMKSearchPanorama

/**
 * Machine readable panorama identifier.
 */
actual val Panorama.mpId: String
    get() = id()
/**
 * Direction of the panorama center.
 */
actual val Panorama.mpDirection: com.yandex.mapkit.kmp.geometry.Direction
    get() = direction()
/**
 * H-Span and V-Span hints for the panorama player.
 */
actual val Panorama.mpSpan: com.yandex.mapkit.kmp.geometry.Span
    get() = span()
/**
 * Panorama point.
 */
actual val Panorama.mpPoint: com.yandex.mapkit.kmp.geometry.Point
    get() = point()

actual object PanoramaFactory {
    actual fun create(
        id: String,
        direction: com.yandex.mapkit.kmp.geometry.Direction,
        span: com.yandex.mapkit.kmp.geometry.Span,
        point: com.yandex.mapkit.kmp.geometry.Point,
    ): Panorama {
        return Panorama.panoramaWithId(
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
actual typealias PanoramasObjectMetadata = platform.YandexMapsMobile.YMKSearchPanoramasObjectMetadata

/**
 * List of panoramas.
 */
actual val PanoramasObjectMetadata.mpPanoramas: kotlin.collections.List<com.yandex.mapkit.search.kmp.Panorama>
    get() = panoramas().let { it as kotlin.collections.List<platform.YandexMapsMobile.YMKSearchPanorama> }

actual object PanoramasObjectMetadataFactory {
    actual fun create(
        panoramas: kotlin.collections.List<com.yandex.mapkit.search.kmp.Panorama>,
    ): PanoramasObjectMetadata {
        return PanoramasObjectMetadata.panoramasObjectMetadataWithPanoramas(
            panoramas.let { it as kotlin.collections.List<*> },
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.panoramasObjectMetadata: PanoramasObjectMetadata?
    get() = impl.getItemOfClass(PanoramasObjectMetadata) as? PanoramasObjectMetadata

