package ru.yandex.maps.workshop.internal.res

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import com.yandex.runtime.kmp.image.ImageProvider

@Composable
expect fun imageProvider(
    size: DpSize,
    content: @Composable () -> Unit
): ImageProvider

@Composable
expect fun rememberComposeMapObjectRenderer(): ComposeMapObjectRenderer

expect class ComposeMapObjectRenderer {
    internal fun toImageProvider(
        size: DpSize,
        content: @Composable () -> Unit
    ): ImageProvider
}
