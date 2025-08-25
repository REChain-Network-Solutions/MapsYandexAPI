package ru.yandex.maps.workshop.internal.res

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.renderComposeScene
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpSize
import com.yandex.runtime.kmp.image.ImageProvider
import ru.yandex.maps.workshop.internal.res.ComposeMapObjectRenderer
import ru.yandex.maps.workshop.internal.res.rememberComposeMapObjectRenderer
import ru.yandex.maps.workshop.internal.res.toImageProvider

@Composable
actual fun imageProvider(
    size: DpSize,
    content: @Composable () -> Unit
): ImageProvider {
    val renderer = rememberComposeMapObjectRenderer()
    return remember(size, content) { renderer.toImageProvider(size, content) }
}

@Composable
actual fun rememberComposeMapObjectRenderer(): ComposeMapObjectRenderer {
    val density = LocalDensity.current
    return remember { ComposeMapObjectRenderer(density) }
}

actual class ComposeMapObjectRenderer(
    private val density: Density,
) {
    internal actual fun toImageProvider(
        size: DpSize,
        content: @Composable () -> Unit
    ): ImageProvider {
        return renderComposeScene(
            width = with(density) { size.width.roundToPx() },
            height = with(density) { size.height.roundToPx() },
            density = density,
            content = content
        ).toComposeImageBitmap().toImageProvider()
    }
}
