@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

import com.yandex.runtime.kmp.internal.toBoolean
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * Top level structure for search response.
 */
actual typealias Response = platform.YandexMapsMobile.YMKSearchResponse

/**
 * Extended response information.
 */
actual val Response.mpMetadata: com.yandex.mapkit.search.kmp.SearchMetadata
    get() = metadata()
/**
 * List of objects (organizations, toponyms) in search response.
 */
actual val Response.mpCollection: com.yandex.mapkit.kmp.GeoObjectCollection
    get() = collection()
/**
 * Flag describing if this response was built offline.
 */
actual val Response.mpIsOffline: Boolean
    get() = isOffline()

actual object ResponseFactory {
    actual fun create(
        metadata: com.yandex.mapkit.search.kmp.SearchMetadata,
        collection: com.yandex.mapkit.kmp.GeoObjectCollection,
        isOffline: Boolean,
    ): Response {
        return Response.responseWithMetadata(
            metadata,
            collection,
            isOffline,
        )
    }
}

