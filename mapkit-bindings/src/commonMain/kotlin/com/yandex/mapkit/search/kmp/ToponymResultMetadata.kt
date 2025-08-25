@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Common info for response from toponym search.
 */
expect class ToponymResultMetadata

/**
 * Approximate number of found objects.
 */
expect val ToponymResultMetadata.mpFound: Int
/**
 * Additional response info.
 *
 */
expect val ToponymResultMetadata.mpResponseInfo: com.yandex.mapkit.search.kmp.ToponymResultMetadataResponseInfo?
/**
 * The search coordinates given via 'll' or parsed from 'text' (only in
 * reverse mode).
 *
 */
expect val ToponymResultMetadata.mpReversePoint: com.yandex.mapkit.kmp.geometry.Point?

expect object ToponymResultMetadataFactory {
    fun create(
        found: Int,
        responseInfo: com.yandex.mapkit.search.kmp.ToponymResultMetadataResponseInfo?,
        reversePoint: com.yandex.mapkit.kmp.geometry.Point?,
    ): ToponymResultMetadata
}

expect val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.toponymResultMetadata: ToponymResultMetadata?

/**
 * Search mode.
 */
expect enum class ToponymResultMetadataSearchMode {
    /**
     * Search from text to toponym.
     */
    GEOCODE,
    /**
     * Search from coordinates to toponym.
     */
    REVERSE,
}

/**
 * Additional response info.
 */
expect class ToponymResultMetadataResponseInfo

/**
 * Search mode.
 */
expect val ToponymResultMetadataResponseInfo.mpMode: com.yandex.mapkit.search.kmp.ToponymResultMetadataSearchMode
/**
 * Search response accuracy.
 *
 */
expect val ToponymResultMetadataResponseInfo.mpAccuracy: Double?

expect object ToponymResultMetadataResponseInfoFactory {
    fun create(
        mode: com.yandex.mapkit.search.kmp.ToponymResultMetadataSearchMode,
        accuracy: Double?,
    ): ToponymResultMetadataResponseInfo
}

