@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Common info for response from toponym search.
 */
actual typealias ToponymResultMetadata = com.yandex.mapkit.search.ToponymResultMetadata

/**
 * Approximate number of found objects.
 */
actual val ToponymResultMetadata.mpFound: Int
    get() = found
/**
 * Additional response info.
 *
 */
actual val ToponymResultMetadata.mpResponseInfo: com.yandex.mapkit.search.kmp.ToponymResultMetadataResponseInfo?
    get() = responseInfo
/**
 * The search coordinates given via 'll' or parsed from 'text' (only in
 * reverse mode).
 *
 */
actual val ToponymResultMetadata.mpReversePoint: com.yandex.mapkit.kmp.geometry.Point?
    get() = reversePoint

actual object ToponymResultMetadataFactory {
    actual fun create(
        found: Int,
        responseInfo: com.yandex.mapkit.search.kmp.ToponymResultMetadataResponseInfo?,
        reversePoint: com.yandex.mapkit.kmp.geometry.Point?,
    ): ToponymResultMetadata {
        return ToponymResultMetadata(
            found,
            responseInfo,
            reversePoint,
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.toponymResultMetadata: ToponymResultMetadata?
    get() = getItem(ToponymResultMetadata::class.java)

/**
 * Search mode.
 */
actual typealias ToponymResultMetadataSearchMode = com.yandex.mapkit.search.ToponymResultMetadata.SearchMode

/**
 * Additional response info.
 */
actual typealias ToponymResultMetadataResponseInfo = com.yandex.mapkit.search.ToponymResultMetadata.ResponseInfo

/**
 * Search mode.
 */
actual val ToponymResultMetadataResponseInfo.mpMode: com.yandex.mapkit.search.kmp.ToponymResultMetadataSearchMode
    get() = mode
/**
 * Search response accuracy.
 *
 */
actual val ToponymResultMetadataResponseInfo.mpAccuracy: Double?
    get() = accuracy

actual object ToponymResultMetadataResponseInfoFactory {
    actual fun create(
        mode: com.yandex.mapkit.search.kmp.ToponymResultMetadataSearchMode,
        accuracy: Double?,
    ): ToponymResultMetadataResponseInfo {
        return ToponymResultMetadataResponseInfo(
            mode,
            accuracy,
        )
    }
}

