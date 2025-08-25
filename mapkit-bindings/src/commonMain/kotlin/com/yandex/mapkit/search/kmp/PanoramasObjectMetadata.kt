@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Panorama info.
 */
expect class Panorama

/**
 * Machine readable panorama identifier.
 */
expect val Panorama.mpId: String
/**
 * Direction of the panorama center.
 */
expect val Panorama.mpDirection: com.yandex.mapkit.kmp.geometry.Direction
/**
 * H-Span and V-Span hints for the panorama player.
 */
expect val Panorama.mpSpan: com.yandex.mapkit.kmp.geometry.Span
/**
 * Panorama point.
 */
expect val Panorama.mpPoint: com.yandex.mapkit.kmp.geometry.Point

expect object PanoramaFactory {
    fun create(
        id: String,
        direction: com.yandex.mapkit.kmp.geometry.Direction,
        span: com.yandex.mapkit.kmp.geometry.Span,
        point: com.yandex.mapkit.kmp.geometry.Point,
    ): Panorama
}

/**
 * Snippet data to get panoramas info.
 */
expect class PanoramasObjectMetadata

/**
 * List of panoramas.
 */
expect val PanoramasObjectMetadata.mpPanoramas: kotlin.collections.List<com.yandex.mapkit.search.kmp.Panorama>

expect object PanoramasObjectMetadataFactory {
    fun create(
        panoramas: kotlin.collections.List<com.yandex.mapkit.search.kmp.Panorama>,
    ): PanoramasObjectMetadata
}

expect val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.panoramasObjectMetadata: PanoramasObjectMetadata?

