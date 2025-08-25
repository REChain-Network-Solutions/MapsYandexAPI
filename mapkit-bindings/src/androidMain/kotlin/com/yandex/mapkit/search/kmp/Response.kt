@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Top level structure for search response.
 */
actual typealias Response = com.yandex.mapkit.search.Response

/**
 * Extended response information.
 */
actual val Response.mpMetadata: com.yandex.mapkit.search.kmp.SearchMetadata
    get() = metadata
/**
 * List of objects (organizations, toponyms) in search response.
 */
actual val Response.mpCollection: com.yandex.mapkit.kmp.GeoObjectCollection
    get() = collection
/**
 * Flag describing if this response was built offline.
 */
actual val Response.mpIsOffline: Boolean
    get() = isOffline

actual object ResponseFactory {
    actual fun create(
        metadata: com.yandex.mapkit.search.kmp.SearchMetadata,
        collection: com.yandex.mapkit.kmp.GeoObjectCollection,
        isOffline: Boolean,
    ): Response {
        return Response(
            metadata,
            collection,
            isOffline,
        )
    }
}

