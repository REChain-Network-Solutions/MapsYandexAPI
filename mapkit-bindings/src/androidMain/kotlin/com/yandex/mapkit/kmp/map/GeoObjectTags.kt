@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * Geo object tags.
 */
actual typealias GeoObjectTags = com.yandex.mapkit.map.GeoObjectTags

actual val GeoObjectTags.mpTags: kotlin.collections.List<String>
    get() = tags

actual object GeoObjectTagsFactory {
    actual fun create(
        tags: kotlin.collections.List<String>,
    ): GeoObjectTags {
        return GeoObjectTags(
            tags,
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.geoObjectTags: GeoObjectTags?
    get() = getItem(GeoObjectTags::class.java)

