@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

import com.yandex.runtime.kmp.internal.toInt
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * Additional info for search response;
 */
actual typealias SearchMetadata = platform.YandexMapsMobile.YMKSearchMetadata

/**
 * Approximate number of found objects.
 */
actual val SearchMetadata.mpFound: Int
    get() = found().toInt()
/**
 * Display type.
 */
actual val SearchMetadata.mpDisplayType: com.yandex.mapkit.search.kmp.DisplayType
    get() = displayType().let { com.yandex.mapkit.search.kmp.DisplayType.toKmp(it) }
/**
 * Bounding box of the response as a whole.
 *
 */
actual val SearchMetadata.mpBoundingBox: com.yandex.mapkit.kmp.geometry.BoundingBox?
    get() = boundingBox()
/**
 * Server-chosen sorting.
 *
 */
actual val SearchMetadata.mpSort: com.yandex.mapkit.search.kmp.Sort?
    get() = sort()
/**
 * Geocoder response to the toponym part of the query.
 *
 */
actual val SearchMetadata.mpToponym: com.yandex.mapkit.kmp.GeoObject?
    get() = toponym()
/**
 * Additional info for the response from toponym search.
 *
 */
actual val SearchMetadata.mpToponymResultMetadata: com.yandex.mapkit.search.kmp.ToponymResultMetadata?
    get() = toponymResultMetadata()
/**
 * Additional info for the response from organization search.
 *
 */
actual val SearchMetadata.mpBusinessResultMetadata: com.yandex.mapkit.search.kmp.BusinessResultMetadata?
    get() = businessResultMetadata()
/**
 * Server-generated request ID.
 */
actual val SearchMetadata.mpReqid: String
    get() = reqid()
/**
 * Server-generated request context.
 */
actual val SearchMetadata.mpContext: String
    get() = context()
/**
 * Initial request text.
 */
actual val SearchMetadata.mpRequestText: String
    get() = requestText()
/**
 * Initial request text with correction of spelling mistakes.
 *
 */
actual val SearchMetadata.mpCorrectedRequestText: String?
    get() = correctedRequestText()
/**
 * Initial request bounding box.
 *
 */
actual val SearchMetadata.mpRequestBoundingBox: com.yandex.mapkit.kmp.geometry.BoundingBox?
    get() = requestBoundingBox()

actual object SearchMetadataFactory {
    actual fun create(
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
    ): SearchMetadata {
        return SearchMetadata.searchMetadataWithFound(
            found.toLong(),
            displayType.fromKmp(),
            boundingBox,
            sort,
            toponym,
            toponymResultMetadata,
            businessResultMetadata,
            reqid,
            context,
            requestText,
            correctedRequestText,
            requestBoundingBox,
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.searchMetadata: SearchMetadata?
    get() = impl.getItemOfClass(SearchMetadata) as? SearchMetadata

