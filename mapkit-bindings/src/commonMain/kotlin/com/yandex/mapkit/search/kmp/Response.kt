@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Top level structure for search response.
 */
expect class Response

/**
 * Extended response information.
 */
expect val Response.mpMetadata: com.yandex.mapkit.search.kmp.SearchMetadata
/**
 * List of objects (organizations, toponyms) in search response.
 */
expect val Response.mpCollection: com.yandex.mapkit.kmp.GeoObjectCollection
/**
 * Flag describing if this response was built offline.
 */
expect val Response.mpIsOffline: Boolean

expect object ResponseFactory {
    fun create(
        metadata: com.yandex.mapkit.search.kmp.SearchMetadata,
        collection: com.yandex.mapkit.kmp.GeoObjectCollection,
        isOffline: Boolean,
    ): Response
}

