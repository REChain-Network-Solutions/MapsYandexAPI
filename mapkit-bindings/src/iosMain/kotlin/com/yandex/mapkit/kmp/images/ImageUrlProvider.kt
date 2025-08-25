@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.images

import kotlinx.cinterop.objcPtr

actual interface ImageUrlProvider {
    /**
     * Generates an URL that is used to load described image.
     *
     * This method may be called on any thread. Its implementation must be thread-safe.
     */
    actual fun formatUrl(
        descriptor: com.yandex.mapkit.kmp.images.ImageDataDescriptor,
    ): String
}

class ImageUrlProviderWrapper internal constructor(impl: ImageUrlProvider, @Suppress("UNUSED_PARAMETER") tag: Unit) : platform.darwin.NSObject(), platform.YandexMapsMobile.YMKImagesImageUrlProviderProtocol {
    private val impl = kotlin.native.ref.WeakReference(impl)

    override fun formatUrlWithDescriptor(
        descriptor: platform.YandexMapsMobile.YMKImagesImageDataDescriptor,
    ): String {
        return impl.get()?.formatUrl(
            descriptor,
        ) ?: ""
    }

    internal companion object
}

fun ImageUrlProviderWrapper(impl: ImageUrlProvider): ImageUrlProviderWrapper {
    val pointerToCompanionObject = kotlinx.cinterop.interpretCPointer<kotlinx.cinterop.COpaque>(ImageUrlProviderWrapper.Companion.objcPtr())
    val value = kotlinx.cinterop.autoreleasepool {
        platform.objc.objc_getAssociatedObject(impl, pointerToCompanionObject)
    }

    if (value != null) {
        return value as ImageUrlProviderWrapper
    }

    val result = ImageUrlProviderWrapper(impl, Unit)
    kotlinx.cinterop.autoreleasepool {
        platform.objc.objc_setAssociatedObject(impl, pointerToCompanionObject, result, platform.objc.OBJC_ASSOCIATION_RETAIN)
    }
    return result
}

open class YMKImagesImageUrlProviderProtocolWrapper(val impl: platform.YandexMapsMobile.YMKImagesImageUrlProviderProtocol) : ImageUrlProvider {
    override fun formatUrl(
        descriptor: com.yandex.mapkit.kmp.images.ImageDataDescriptor,
    ): String {
        return impl.formatUrlWithDescriptor(
            descriptor,
        )
    }
}

