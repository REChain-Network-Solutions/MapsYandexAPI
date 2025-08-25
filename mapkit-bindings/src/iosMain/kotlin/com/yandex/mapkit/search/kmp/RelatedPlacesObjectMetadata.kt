@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

/**
 * Snippet data to get related places info.
 */
actual typealias RelatedPlacesObjectMetadata = platform.YandexMapsMobile.YMKSearchRelatedPlacesObjectMetadata

/**
 * List of similar places.
 */
actual val RelatedPlacesObjectMetadata.mpSimilarPlaces: kotlin.collections.List<com.yandex.mapkit.search.kmp.PlaceInfo>
    get() = similarPlaces().let { it as kotlin.collections.List<platform.YandexMapsMobile.YMKSearchPlaceInfo> }

actual object RelatedPlacesObjectMetadataFactory {
    actual fun create(
        similarPlaces: kotlin.collections.List<com.yandex.mapkit.search.kmp.PlaceInfo>,
    ): RelatedPlacesObjectMetadata {
        return RelatedPlacesObjectMetadata.relatedPlacesObjectMetadataWithSimilarPlaces(
            similarPlaces.let { it as kotlin.collections.List<*> },
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.relatedPlacesObjectMetadata: RelatedPlacesObjectMetadata?
    get() = impl.getItemOfClass(RelatedPlacesObjectMetadata) as? RelatedPlacesObjectMetadata

