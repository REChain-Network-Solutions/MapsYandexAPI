@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

actual typealias Image = com.yandex.mapkit.Image

/**
 * urlTemplate for the image.
 */
actual val Image.mpUrlTemplate: String
    get() = urlTemplate
/**
 * Image sizes.
 */
actual val Image.mpSizes: kotlin.collections.List<com.yandex.mapkit.kmp.ImageImageSize>
    get() = sizes
/**
 * Image tags.
 */
actual val Image.mpTags: kotlin.collections.List<String>
    get() = tags

actual object ImageFactory {
    actual fun create(
        urlTemplate: String,
        sizes: kotlin.collections.List<com.yandex.mapkit.kmp.ImageImageSize>,
        tags: kotlin.collections.List<String>,
    ): Image {
        return Image(
            urlTemplate,
            sizes,
            tags,
        )
    }
}

actual typealias ImageImageSize = com.yandex.mapkit.Image.ImageSize

actual val ImageImageSize.mpSize: String
    get() = size
actual val ImageImageSize.mpWidth: Int?
    get() = width
actual val ImageImageSize.mpHeight: Int?
    get() = height

actual object ImageImageSizeFactory {
    actual fun create(
        size: String,
        width: Int?,
        height: Int?,
    ): ImageImageSize {
        return ImageImageSize(
            size,
            width,
            height,
        )
    }
}

