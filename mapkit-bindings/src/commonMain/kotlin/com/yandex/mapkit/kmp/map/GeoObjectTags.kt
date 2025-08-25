@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * Geo object tags.
 */
expect class GeoObjectTags

expect val GeoObjectTags.mpTags: kotlin.collections.List<String>

expect object GeoObjectTagsFactory {
    fun create(
        tags: kotlin.collections.List<String>,
    ): GeoObjectTags
}

expect val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.geoObjectTags: GeoObjectTags?

