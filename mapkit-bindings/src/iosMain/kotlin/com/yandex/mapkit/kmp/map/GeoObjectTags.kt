@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

/**
 * Geo object tags.
 */
actual typealias GeoObjectTags = platform.YandexMapsMobile.YMKGeoObjectTags

actual val GeoObjectTags.mpTags: kotlin.collections.List<String>
    get() = tags().let { it as kotlin.collections.List<String> }

actual object GeoObjectTagsFactory {
    actual fun create(
        tags: kotlin.collections.List<String>,
    ): GeoObjectTags {
        return GeoObjectTags.geoObjectTagsWithTags(
            tags.let { it as kotlin.collections.List<*> },
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.geoObjectTags: GeoObjectTags?
    get() = impl.getItemOfClass(GeoObjectTags) as? GeoObjectTags

