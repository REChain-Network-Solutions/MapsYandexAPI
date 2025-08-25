package com.yandex.runtime.kmp.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.yandex.runtime.image.ImageProvider as RuntimeImageProvider

actual typealias ImageProvider = RuntimeImageProvider

actual fun emptyImageProvider(): ImageProvider {
    return ImageProvider.fromBitmap(Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888))
}

actual fun ImageProvider.id() = id

actual fun ByteArray.asImage(): ImageProvider? {
    val decodedBitmap = BitmapFactory.decodeByteArray(this, 0, this.size) ?: return null
    return RuntimeImageProvider.fromBitmap(decodedBitmap)
}
