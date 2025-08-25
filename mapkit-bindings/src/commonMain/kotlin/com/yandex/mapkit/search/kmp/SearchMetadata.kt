@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Additional info for search response;
 */
expect class SearchMetadata

/**
 * Approximate number of found objects.
 */
expect val SearchMetadata.mpFound: Int
/**
 * Display type.
 */
expect val SearchMetadata.mpDisplayType: com.yandex.mapkit.search.kmp.DisplayType
/**
 * Bounding box of the response as a whole.
 *
 */
expect val SearchMetadata.mpBoundingBox: com.yandex.mapkit.kmp.geometry.BoundingBox?
/**
 * Server-chosen sorting.
 *
 */
expect val SearchMetadata.mpSort: com.yandex.mapkit.search.kmp.Sort?
/**
 * Geocoder response to the toponym part of the query.
 *
 */
expect val SearchMetadata.mpToponym: com.yandex.mapkit.kmp.GeoObject?
/**
 * Additional info for the response from toponym search.
 *
 */
expect val SearchMetadata.mpToponymResultMetadata: com.yandex.mapkit.search.kmp.ToponymResultMetadata?
/**
 * Additional info for the response from organization search.
 *
 */
expect val SearchMetadata.mpBusinessResultMetadata: com.yandex.mapkit.search.kmp.BusinessResultMetadata?
/**
 * Server-generated request ID.
 */
expect val SearchMetadata.mpReqid: String
/**
 * Server-generated request context.
 */
expect val SearchMetadata.mpContext: String
/**
 * Initial request text.
 */
expect val SearchMetadata.mpRequestText: String
/**
 * Initial request text with correction of spelling mistakes.
 *
 */
expect val SearchMetadata.mpCorrectedRequestText: String?
/**
 * Initial request bounding box.
 *
 */
expect val SearchMetadata.mpRequestBoundingBox: com.yandex.mapkit.kmp.geometry.BoundingBox?

expect object SearchMetadataFactory {
    fun create(
        found: Int,
        displayType: com.yandex.mapkit.search.kmp.DisplayType,
        boundingBox: com.yandex.mapkit.kmp.geometry.BoundingBox?,
        sort: com.yandex.mapkit.search.kmp.Sort?,
        toponym: com.yandex.mapkit.kmp.GeoObject?,
        toponymResultMetadata: com.yandex.mapkit.search.kmp.ToponymResultMetadata?,
        businessResultMetadata: com.yandex.mapkit.search.kmp.BusinessResultMetadata?,
        reqid: String,
        context: String,
        requestText: String,
        correctedRequestText: String?,
        requestBoundingBox: com.yandex.mapkit.kmp.geometry.BoundingBox?,
    ): SearchMetadata
}

expect val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.searchMetadata: SearchMetadata?

