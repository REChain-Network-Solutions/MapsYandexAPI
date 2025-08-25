@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Snippet for company ratings. Score from 0 to 5.
 */
expect class BusinessRating1xObjectMetadata

/**
 * Total number of ratings.
 */
expect val BusinessRating1xObjectMetadata.mpRatings: Int
/**
 * Total number of reviews.
 */
expect val BusinessRating1xObjectMetadata.mpReviews: Int
/**
 * Average rating score for the company (0 to 5).
 *
 */
expect val BusinessRating1xObjectMetadata.mpScore: Float?

expect object BusinessRating1xObjectMetadataFactory {
    fun create(
        ratings: Int,
        reviews: Int,
        score: Float?,
    ): BusinessRating1xObjectMetadata
}

expect val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.businessRating1xObjectMetadata: BusinessRating1xObjectMetadata?

