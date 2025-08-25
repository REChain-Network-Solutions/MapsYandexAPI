package ru.yandex.maps.workshop

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import ru.yandex.maps.workshop.common.additional.context.PlatformContext

@Composable
actual fun rememberPlatformContext() : PlatformContext {
    return remember { PlatformContext() }
}
