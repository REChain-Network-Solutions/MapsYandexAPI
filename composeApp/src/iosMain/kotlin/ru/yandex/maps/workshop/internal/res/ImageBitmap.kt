package ru.yandex.maps.workshop.internal.res

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asSkiaBitmap
import com.yandex.runtime.kmp.image.ImageProvider
import com.yandex.runtime.kmp.image.ImageProviderWrapper
import ru.yandex.maps.workshop.internal.utils.toUIImage

actual fun ImageBitmap.toImageProvider(): ImageProvider {
    return ImageProviderWrapper(asSkiaBitmap().toUIImage())
}
