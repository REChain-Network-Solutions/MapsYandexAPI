package ru.yandex.maps.workshop.internal.res

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import com.yandex.runtime.kmp.image.ImageProvider

actual fun ImageBitmap.toImageProvider(): ImageProvider {
    return ImageProvider.fromBitmap(asAndroidBitmap())
}
