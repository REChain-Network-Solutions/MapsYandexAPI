@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

import com.yandex.runtime.kmp.internal.toFloat
import com.yandex.runtime.kmp.internal.toInt
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * Snippet for company ratings. Score from 0 to 5.
 */
actual typealias BusinessRating1xObjectMetadata = platform.YandexMapsMobile.YMKSearchBusinessRating1xObjectMetadata

/**
 * Total number of ratings.
 */
actual val BusinessRating1xObjectMetadata.mpRatings: Int
    get() = ratings().toInt()
/**
 * Total number of reviews.
 */
actual val BusinessRating1xObjectMetadata.mpReviews: Int
    get() = reviews().toInt()
/**
 * Average rating score for the company (0 to 5).
 *
 */
actual val BusinessRating1xObjectMetadata.mpScore: Float?
    get() = score()?.toFloat()

actual object BusinessRating1xObjectMetadataFactory {
    actual fun create(
        ratings: Int,
        reviews: Int,
        score: Float?,
    ): BusinessRating1xObjectMetadata {
        return BusinessRating1xObjectMetadata.businessRating1xObjectMetadataWithRatings(
            ratings.toULong(),
            reviews.toULong(),
            score?.toNSNumber(),
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.businessRating1xObjectMetadata: BusinessRating1xObjectMetadata?
    get() = impl.getItemOfClass(BusinessRating1xObjectMetadata) as? BusinessRating1xObjectMetadata

