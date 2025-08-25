@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Related place information.
 */
expect class PlaceInfo

/**
 * Place name.
 */
expect val PlaceInfo.mpName: String
/**
 * Place URI.
 *
 */
expect val PlaceInfo.mpUri: String?
/**
 * Url template for place photo.
 *
 */
expect val PlaceInfo.mpPhotoUrlTemplate: String?
/**
 * Server-generated log identifier.
 *
 */
expect val PlaceInfo.mpLogId: String?
/**
 * Place position.
 *
 */
expect val PlaceInfo.mpPoint: com.yandex.mapkit.kmp.geometry.Point?
/**
 * Place category as a string.
 *
 */
expect val PlaceInfo.mpCategory: String?
/**
 * Short name for a place.
 *
 */
expect val PlaceInfo.mpShortName: String?
/**
 * Place rating as number in 0 to 10 range.
 *
 */
expect val PlaceInfo.mpRating: Float?
/**
 * Open hours for a place.
 *
 */
expect val PlaceInfo.mpWorkingHours: com.yandex.mapkit.search.kmp.WorkingHours?
/**
 * Formatted address for an organization.
 *
 */
expect val PlaceInfo.mpAddress: String?
/**
 * Tags
 */
expect val PlaceInfo.mpTag: kotlin.collections.List<String>

expect object PlaceInfoFactory {
    fun create(
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
    ): PlaceInfo
}

