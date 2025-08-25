package ru.yandex.maps.workshop.internal.mapkit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.yandex.mapkit.kmp.MapKit

@Composable
actual fun rememberAndInitializeMapKit(apiKey: String): MapKit {
    return remember {
        MapKit.setApiKey(apiKey)
        MapKit.getInstance()
    }
}
