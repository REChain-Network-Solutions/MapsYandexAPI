@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Snippet for company-related photos (becoming obsolete).
 */
expect class BusinessPhotoObjectMetadata

/**
 * Number of photos for the organisation. (see PhotosManager for
 * details)
 */
expect val BusinessPhotoObjectMetadata.mpCount: Int
/**
 * List of photos for the company (usually first three)
 */
expect val BusinessPhotoObjectMetadata.mpPhotos: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessPhotoObjectMetadataPhoto>

expect object BusinessPhotoObjectMetadataFactory {
    fun create(
        count: Int,
        photos: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessPhotoObjectMetadataPhoto>,
    ): BusinessPhotoObjectMetadata
}

expect val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.businessPhotoObjectMetadata: BusinessPhotoObjectMetadata?

/**
 * Information about single photos.
 */
expect class BusinessPhotoObjectMetadataPhoto

/**
 * To get a valid download link use the value of id + /[size], where
 * [size] is one of: 1. XXXS 2. XXS 3. XS 4. S 5. M 6. L 7. XL 8. XXL 9.
 * XXXL 10. orig
 */
expect val BusinessPhotoObjectMetadataPhoto.mpId: String
/**
 * Photo links.
 */
expect val BusinessPhotoObjectMetadataPhoto.mpLinks: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessPhotoObjectMetadataPhotoPhotoLink>

expect object BusinessPhotoObjectMetadataPhotoFactory {
    fun create(
        id: String,
        links: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessPhotoObjectMetadataPhotoPhotoLink>,
    ): BusinessPhotoObjectMetadataPhoto
}

/**
 * Photo link details.
 */
expect class BusinessPhotoObjectMetadataPhotoPhotoLink

/**
 * Optional link type, for example "panorama".
 *
 */
expect val BusinessPhotoObjectMetadataPhotoPhotoLink.mpType: String?
/**
 * Link URI.
 */
expect val BusinessPhotoObjectMetadataPhotoPhotoLink.mpUri: String

expect object BusinessPhotoObjectMetadataPhotoPhotoLinkFactory {
    fun create(
        type: String?,
        uri: String,
    ): BusinessPhotoObjectMetadataPhotoPhotoLink
}

