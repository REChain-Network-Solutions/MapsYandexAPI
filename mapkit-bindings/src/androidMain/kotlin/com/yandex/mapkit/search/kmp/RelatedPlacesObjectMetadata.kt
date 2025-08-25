@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Snippet data to get related places info.
 */
actual typealias RelatedPlacesObjectMetadata = com.yandex.mapkit.search.RelatedPlacesObjectMetadata

/**
 * List of similar places.
 */
actual val RelatedPlacesObjectMetadata.mpSimilarPlaces: kotlin.collections.List<com.yandex.mapkit.search.kmp.PlaceInfo>
    get() = similarPlaces

actual object RelatedPlacesObjectMetadataFactory {
    actual fun create(
        similarPlaces: kotlin.collections.List<com.yandex.mapkit.search.kmp.PlaceInfo>,
    ): RelatedPlacesObjectMetadata {
        return RelatedPlacesObjectMetadata(
            similarPlaces,
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.relatedPlacesObjectMetadata: RelatedPlacesObjectMetadata?
    get() = getItem(RelatedPlacesObjectMetadata::class.java)

