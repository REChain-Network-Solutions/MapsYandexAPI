@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Related place information.
 */
actual typealias PlaceInfo = com.yandex.mapkit.search.PlaceInfo

/**
 * Place name.
 */
actual val PlaceInfo.mpName: String
    get() = name
/**
 * Place URI.
 *
 */
actual val PlaceInfo.mpUri: String?
    get() = uri
/**
 * Url template for place photo.
 *
 */
actual val PlaceInfo.mpPhotoUrlTemplate: String?
    get() = photoUrlTemplate
/**
 * Server-generated log identifier.
 *
 */
actual val PlaceInfo.mpLogId: String?
    get() = logId
/**
 * Place position.
 *
 */
actual val PlaceInfo.mpPoint: com.yandex.mapkit.kmp.geometry.Point?
    get() = point
/**
 * Place category as a string.
 *
 */
actual val PlaceInfo.mpCategory: String?
    get() = category
/**
 * Short name for a place.
 *
 */
actual val PlaceInfo.mpShortName: String?
    get() = shortName
/**
 * Place rating as number in 0 to 10 range.
 *
 */
actual val PlaceInfo.mpRating: Float?
    get() = rating
/**
 * Open hours for a place.
 *
 */
actual val PlaceInfo.mpWorkingHours: com.yandex.mapkit.search.kmp.WorkingHours?
    get() = workingHours
/**
 * Formatted address for an organization.
 *
 */
actual val PlaceInfo.mpAddress: String?
    get() = address
/**
 * Tags
 */
actual val PlaceInfo.mpTag: kotlin.collections.List<String>
    get() = tag

actual object PlaceInfoFactory {
    actual fun create(
        name: String,
        uri: String?,
        photoUrlTemplate: String?,
        logId: String?,
        point: com.yandex.mapkit.kmp.geometry.Point?,
        category: String?,
        shortName: String?,
        rating: Float?,
        workingHours: com.yandex.mapkit.search.kmp.WorkingHours?,
        address: String?,
        tag: kotlin.collections.List<String>,
    ): PlaceInfo {
        return PlaceInfo(
            name,
            uri,
            photoUrlTemplate,
            logId,
            point,
            category,
            shortName,
            rating,
            workingHours,
            address,
            tag,
        )
    }
}

