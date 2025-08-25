@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Snippet for company-related photos (becoming obsolete).
 */
actual typealias BusinessPhotoObjectMetadata = com.yandex.mapkit.search.BusinessPhotoObjectMetadata

/**
 * Number of photos for the organisation. (see PhotosManager for
 * details)
 */
actual val BusinessPhotoObjectMetadata.mpCount: Int
    get() = count
/**
 * List of photos for the company (usually first three)
 */
actual val BusinessPhotoObjectMetadata.mpPhotos: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessPhotoObjectMetadataPhoto>
    get() = photos

actual object BusinessPhotoObjectMetadataFactory {
    actual fun create(
        count: Int,
        photos: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessPhotoObjectMetadataPhoto>,
    ): BusinessPhotoObjectMetadata {
        return BusinessPhotoObjectMetadata(
            count,
            photos,
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.businessPhotoObjectMetadata: BusinessPhotoObjectMetadata?
    get() = getItem(BusinessPhotoObjectMetadata::class.java)

/**
 * Information about single photos.
 */
actual typealias BusinessPhotoObjectMetadataPhoto = com.yandex.mapkit.search.BusinessPhotoObjectMetadata.Photo

/**
 * To get a valid download link use the value of id + /[size], where
 * [size] is one of: 1. XXXS 2. XXS 3. XS 4. S 5. M 6. L 7. XL 8. XXL 9.
 * XXXL 10. orig
 */
actual val BusinessPhotoObjectMetadataPhoto.mpId: String
    get() = id
/**
 * Photo links.
 */
actual val BusinessPhotoObjectMetadataPhoto.mpLinks: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessPhotoObjectMetadataPhotoPhotoLink>
    get() = links

actual object BusinessPhotoObjectMetadataPhotoFactory {
    actual fun create(
        id: String,
        links: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessPhotoObjectMetadataPhotoPhotoLink>,
    ): BusinessPhotoObjectMetadataPhoto {
        return BusinessPhotoObjectMetadataPhoto(
            id,
            links,
        )
    }
}

/**
 * Photo link details.
 */
actual typealias BusinessPhotoObjectMetadataPhotoPhotoLink = com.yandex.mapkit.search.BusinessPhotoObjectMetadata.Photo.PhotoLink

/**
 * Optional link type, for example "panorama".
 *
 */
actual val BusinessPhotoObjectMetadataPhotoPhotoLink.mpType: String?
    get() = type
/**
 * Link URI.
 */
actual val BusinessPhotoObjectMetadataPhotoPhotoLink.mpUri: String
    get() = uri

actual object BusinessPhotoObjectMetadataPhotoPhotoLinkFactory {
    actual fun create(
        type: String?,
        uri: String,
    ): BusinessPhotoObjectMetadataPhotoPhotoLink {
        return BusinessPhotoObjectMetadataPhotoPhotoLink(
            type,
            uri,
        )
    }
}

