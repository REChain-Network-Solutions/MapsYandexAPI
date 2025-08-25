package ru.yandex.maps.workshop

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import ru.yandex.maps.workshop.common.additional.context.PlatformContext

@Composable
actual fun rememberPlatformContext() : PlatformContext {
    val context = LocalContext.current
    return remember { PlatformContext(context) }
}
