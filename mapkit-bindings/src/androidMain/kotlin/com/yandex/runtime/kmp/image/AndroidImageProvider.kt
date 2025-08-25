package com.yandex.runtime.kmp.image

import android.graphics.Bitmap

fun Bitmap.toImageProvider(): ImageProvider {
    return ImageProvider.fromBitmap(this)
}
