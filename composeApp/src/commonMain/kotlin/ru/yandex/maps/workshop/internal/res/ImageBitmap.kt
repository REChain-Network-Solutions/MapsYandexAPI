package ru.yandex.maps.workshop.internal.res

import androidx.compose.ui.graphics.ImageBitmap
import com.yandex.runtime.kmp.image.ImageProvider

expect fun ImageBitmap.toImageProvider(): ImageProvider

