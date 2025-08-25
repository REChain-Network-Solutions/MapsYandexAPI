@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

import com.yandex.runtime.kmp.internal.toInt
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * Snippet for company-related photos (becoming obsolete).
 */
actual typealias BusinessPhotoObjectMetadata = platform.YandexMapsMobile.YMKSearchBusinessPhotoObjectMetadata

/**
 * Number of photos for the organisation. (see PhotosManager for
 * details)
 */
actual val BusinessPhotoObjectMetadata.mpCount: Int
    get() = count().toInt()
/**
 * List of photos for the company (usually first three)
 */
actual val BusinessPhotoObjectMetadata.mpPhotos: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessPhotoObjectMetadataPhoto>
    get() = photos().let { it as kotlin.collections.List<platform.YandexMapsMobile.YMKSearchBusinessPhotoObjectMetadataPhoto> }

actual object BusinessPhotoObjectMetadataFactory {
    actual fun create(
        count: Int,
        photos: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessPhotoObjectMetadataPhoto>,
    ): BusinessPhotoObjectMetadata {
        return BusinessPhotoObjectMetadata.businessPhotoObjectMetadataWithCount(
            count.toULong(),
            photos.let { it as kotlin.collections.List<*> },
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.businessPhotoObjectMetadata: BusinessPhotoObjectMetadata?
    get() = impl.getItemOfClass(BusinessPhotoObjectMetadata) as? BusinessPhotoObjectMetadata

/**
 * Information about single photos.
 */
actual typealias BusinessPhotoObjectMetadataPhoto = platform.YandexMapsMobile.YMKSearchBusinessPhotoObjectMetadataPhoto

/**
 * To get a valid download link use the value of id + /[size], where
 * [size] is one of: 1. XXXS 2. XXS 3. XS 4. S 5. M 6. L 7. XL 8. XXL 9.
 * XXXL 10. orig
 */
actual val BusinessPhotoObjectMetadataPhoto.mpId: String
    get() = id()
/**
 * Photo links.
 */
actual val BusinessPhotoObjectMetadataPhoto.mpLinks: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessPhotoObjectMetadataPhotoPhotoLink>
    get() = links().let { it as kotlin.collections.List<platform.YandexMapsMobile.YMKSearchPhotoPhotoLink> }

actual object BusinessPhotoObjectMetadataPhotoFactory {
    actual fun create(
        id: String,
        links: kotlin.collections.List<com.yandex.mapkit.search.kmp.BusinessPhotoObjectMetadataPhotoPhotoLink>,
    ): BusinessPhotoObjectMetadataPhoto {
        return BusinessPhotoObjectMetadataPhoto.photoWithId(
            id,
            links.let { it as kotlin.collections.List<*> },
        )
    }
}

/**
 * Photo link details.
 */
actual typealias BusinessPhotoObjectMetadataPhotoPhotoLink = platform.YandexMapsMobile.YMKSearchPhotoPhotoLink

/**
 * Optional link type, for example "panorama".
 *
 */
actual val BusinessPhotoObjectMetadataPhotoPhotoLink.mpType: String?
    get() = type()
/**
 * Link URI.
 */
actual val BusinessPhotoObjectMetadataPhotoPhotoLink.mpUri: String
    get() = uri()

actual object BusinessPhotoObjectMetadataPhotoPhotoLinkFactory {
    actual fun create(
        type: String?,
        uri: String,
    ): BusinessPhotoObjectMetadataPhotoPhotoLink {
        return BusinessPhotoObjectMetadataPhotoPhotoLink.photoLinkWithType(
            type,
            uri,
        )
    }
}

