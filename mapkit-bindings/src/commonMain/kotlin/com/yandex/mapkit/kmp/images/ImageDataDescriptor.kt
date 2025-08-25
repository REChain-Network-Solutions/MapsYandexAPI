@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.images

expect class ImageDataDescriptor

expect val ImageDataDescriptor.mpImageId: String
expect val ImageDataDescriptor.mpPrimaryColor: com.yandex.runtime.kmp.Color?
expect val ImageDataDescriptor.mpSecondaryColor: com.yandex.runtime.kmp.Color?
expect val ImageDataDescriptor.mpTertiaryColor: com.yandex.runtime.kmp.Color?

expect object ImageDataDescriptorFactory {
    fun create(
        imageId: String,
        primaryColor: com.yandex.runtime.kmp.Color?,
        secondaryColor: com.yandex.runtime.kmp.Color?,
        tertiaryColor: com.yandex.runtime.kmp.Color?,
    ): ImageDataDescriptor
}

