@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Snippet data to get related places info.
 */
expect class RelatedPlacesObjectMetadata

/**
 * List of similar places.
 */
expect val RelatedPlacesObjectMetadata.mpSimilarPlaces: kotlin.collections.List<com.yandex.mapkit.search.kmp.PlaceInfo>

expect object RelatedPlacesObjectMetadataFactory {
    fun create(
        similarPlaces: kotlin.collections.List<com.yandex.mapkit.search.kmp.PlaceInfo>,
    ): RelatedPlacesObjectMetadata
}

expect val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.relatedPlacesObjectMetadata: RelatedPlacesObjectMetadata?

