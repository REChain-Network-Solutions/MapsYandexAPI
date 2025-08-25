@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

/**
 * Snippet with encyclopedia data.
 */
actual typealias EncyclopediaObjectMetadata = platform.YandexMapsMobile.YMKSearchEncyclopediaObjectMetadata

/**
 * Encyclopedia article title.
 *
 */
actual val EncyclopediaObjectMetadata.mpTitle: String?
    get() = title()
/**
 * Encyclopedia article body.
 *
 */
actual val EncyclopediaObjectMetadata.mpDescription: String?
    get() = description()
/**
 * Attribution information.
 *
 */
actual val EncyclopediaObjectMetadata.mpAttribution: com.yandex.mapkit.kmp.Attribution?
    get() = attribution()

actual object EncyclopediaObjectMetadataFactory {
    actual fun create(
        title: String?,
        description: String?,
        attribution: com.yandex.mapkit.kmp.Attribution?,
    ): EncyclopediaObjectMetadata {
        return EncyclopediaObjectMetadata.encyclopediaObjectMetadataWithTitle(
            title,
            description,
            attribution,
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.encyclopediaObjectMetadata: EncyclopediaObjectMetadata?
    get() = impl.getItemOfClass(EncyclopediaObjectMetadata) as? EncyclopediaObjectMetadata

