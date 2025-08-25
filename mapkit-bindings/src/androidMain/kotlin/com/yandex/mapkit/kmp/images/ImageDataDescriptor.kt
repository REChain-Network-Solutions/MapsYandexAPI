@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.images

actual typealias ImageDataDescriptor = com.yandex.mapkit.images.ImageDataDescriptor

actual val ImageDataDescriptor.mpImageId: String
    get() = imageId
actual val ImageDataDescriptor.mpPrimaryColor: com.yandex.runtime.kmp.Color?
    get() = primaryColor
actual val ImageDataDescriptor.mpSecondaryColor: com.yandex.runtime.kmp.Color?
    get() = secondaryColor
actual val ImageDataDescriptor.mpTertiaryColor: com.yandex.runtime.kmp.Color?
    get() = tertiaryColor

actual object ImageDataDescriptorFactory {
    actual fun create(
        imageId: String,
        primaryColor: com.yandex.runtime.kmp.Color?,
        secondaryColor: com.yandex.runtime.kmp.Color?,
        tertiaryColor: com.yandex.runtime.kmp.Color?,
    ): ImageDataDescriptor {
        return ImageDataDescriptor(
            imageId,
            primaryColor,
            secondaryColor,
            tertiaryColor,
        )
    }
}

