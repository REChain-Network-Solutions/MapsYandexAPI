@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp

import com.yandex.runtime.kmp.internal.toInt
import com.yandex.runtime.kmp.internal.toNSNumber

actual typealias Image = platform.YandexMapsMobile.YMKImage

/**
 * urlTemplate for the image.
 */
actual val Image.mpUrlTemplate: String
    get() = urlTemplate()
/**
 * Image sizes.
 */
actual val Image.mpSizes: kotlin.collections.List<com.yandex.mapkit.kmp.ImageImageSize>
    get() = sizes().let { it as kotlin.collections.List<platform.YandexMapsMobile.YMKImageSize> }
/**
 * Image tags.
 */
actual val Image.mpTags: kotlin.collections.List<String>
    get() = tags().let { it as kotlin.collections.List<String> }

actual object ImageFactory {
    actual fun create(
        urlTemplate: String,
        sizes: kotlin.collections.List<com.yandex.mapkit.kmp.ImageImageSize>,
        tags: kotlin.collections.List<String>,
    ): Image {
        return Image.imageWithUrlTemplate(
            urlTemplate,
            sizes.let { it as kotlin.collections.List<*> },
            tags.let { it as kotlin.collections.List<*> },
        )
    }
}

actual typealias ImageImageSize = platform.YandexMapsMobile.YMKImageSize

actual val ImageImageSize.mpSize: String
    get() = size()
actual val ImageImageSize.mpWidth: Int?
    get() = width()?.toInt()
actual val ImageImageSize.mpHeight: Int?
    get() = height()?.toInt()

actual object ImageImageSizeFactory {
    actual fun create(
        size: String,
        width: Int?,
        height: Int?,
    ): ImageImageSize {
        return ImageImageSize.sizeWithSize(
            size,
            width?.toNSNumber(),
            height?.toNSNumber(),
        )
    }
}

