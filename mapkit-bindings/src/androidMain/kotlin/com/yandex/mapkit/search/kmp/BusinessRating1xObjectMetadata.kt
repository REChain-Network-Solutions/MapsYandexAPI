@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Snippet for company ratings. Score from 0 to 5.
 */
actual typealias BusinessRating1xObjectMetadata = com.yandex.mapkit.search.BusinessRating1xObjectMetadata

/**
 * Total number of ratings.
 */
actual val BusinessRating1xObjectMetadata.mpRatings: Int
    get() = ratings
/**
 * Total number of reviews.
 */
actual val BusinessRating1xObjectMetadata.mpReviews: Int
    get() = reviews
/**
 * Average rating score for the company (0 to 5).
 *
 */
actual val BusinessRating1xObjectMetadata.mpScore: Float?
    get() = score

actual object BusinessRating1xObjectMetadataFactory {
    actual fun create(
        ratings: Int,
        reviews: Int,
        score: Float?,
    ): BusinessRating1xObjectMetadata {
        return BusinessRating1xObjectMetadata(
            ratings,
            reviews,
            score,
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.businessRating1xObjectMetadata: BusinessRating1xObjectMetadata?
    get() = getItem(BusinessRating1xObjectMetadata::class.java)

