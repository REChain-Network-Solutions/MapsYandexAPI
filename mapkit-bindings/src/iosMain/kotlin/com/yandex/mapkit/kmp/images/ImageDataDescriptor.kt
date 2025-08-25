@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.images

actual typealias ImageDataDescriptor = platform.YandexMapsMobile.YMKImagesImageDataDescriptor

actual val ImageDataDescriptor.mpImageId: String
    get() = imageId()
actual val ImageDataDescriptor.mpPrimaryColor: com.yandex.runtime.kmp.Color?
    get() = primaryColor()
actual val ImageDataDescriptor.mpSecondaryColor: com.yandex.runtime.kmp.Color?
    get() = secondaryColor()
actual val ImageDataDescriptor.mpTertiaryColor: com.yandex.runtime.kmp.Color?
    get() = tertiaryColor()

actual object ImageDataDescriptorFactory {
    actual fun create(
        imageId: String,
        primaryColor: com.yandex.runtime.kmp.Color?,
        secondaryColor: com.yandex.runtime.kmp.Color?,
        tertiaryColor: com.yandex.runtime.kmp.Color?,
    ): ImageDataDescriptor {
        return ImageDataDescriptor.imageDataDescriptorWithImageId(
            imageId,
            primaryColor,
            secondaryColor,
            tertiaryColor,
        )
    }
}

