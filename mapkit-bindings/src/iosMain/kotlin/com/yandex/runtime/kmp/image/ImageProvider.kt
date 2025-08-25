package com.yandex.runtime.kmp.image

import com.yandex.runtime.kmp.internal.toNSData
import platform.UIKit.UIImage
import platform.UIKit.UIScreen

actual abstract class ImageProvider(open val impl: UIImage)

class ImageProviderWrapper(override val impl: UIImage) : ImageProvider(impl)

actual fun emptyImageProvider(): ImageProvider {
    return ImageProviderWrapper(UIImage())
}

actual fun ImageProvider.id() = this.impl.hash().toString()

actual fun ByteArray.asImage(): ImageProvider? {
    val data = this.toNSData()
    val platformImage = UIImage.imageWithData(data, UIScreen.mainScreen.scale) ?: return null
    return object : ImageProvider(impl = platformImage) {}
}
