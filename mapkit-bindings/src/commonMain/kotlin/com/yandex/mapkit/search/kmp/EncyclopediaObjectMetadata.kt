@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Snippet with encyclopedia data.
 */
expect class EncyclopediaObjectMetadata

/**
 * Encyclopedia article title.
 *
 */
expect val EncyclopediaObjectMetadata.mpTitle: String?
/**
 * Encyclopedia article body.
 *
 */
expect val EncyclopediaObjectMetadata.mpDescription: String?
/**
 * Attribution information.
 *
 */
expect val EncyclopediaObjectMetadata.mpAttribution: com.yandex.mapkit.kmp.Attribution?

expect object EncyclopediaObjectMetadataFactory {
    fun create(
        title: String?,
        description: String?,
        attribution: com.yandex.mapkit.kmp.Attribution?,
    ): EncyclopediaObjectMetadata
}

expect val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.encyclopediaObjectMetadata: EncyclopediaObjectMetadata?

