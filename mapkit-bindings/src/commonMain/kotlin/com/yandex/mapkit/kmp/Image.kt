@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

expect class Image

/**
 * urlTemplate for the image.
 */
expect val Image.mpUrlTemplate: String
/**
 * Image sizes.
 */
expect val Image.mpSizes: kotlin.collections.List<com.yandex.mapkit.kmp.ImageImageSize>
/**
 * Image tags.
 */
expect val Image.mpTags: kotlin.collections.List<String>

expect object ImageFactory {
    fun create(
        urlTemplate: String,
        sizes: kotlin.collections.List<com.yandex.mapkit.kmp.ImageImageSize>,
        tags: kotlin.collections.List<String>,
    ): Image
}

expect class ImageImageSize

expect val ImageImageSize.mpSize: String
expect val ImageImageSize.mpWidth: Int?
expect val ImageImageSize.mpHeight: Int?

expect object ImageImageSizeFactory {
    fun create(
        size: String,
        width: Int?,
        height: Int?,
    ): ImageImageSize
}

