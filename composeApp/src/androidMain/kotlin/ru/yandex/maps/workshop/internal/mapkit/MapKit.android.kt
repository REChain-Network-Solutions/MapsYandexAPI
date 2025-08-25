package ru.yandex.maps.workshop.internal.mapkit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.kmp.MapKit

@Composable
actual fun rememberAndInitializeMapKit(apiKey: String): MapKit {
    val context = LocalContext.current
    return remember {
        MapKit.setApiKey(apiKey)
        MapKitFactory.initialize(context)
        MapKit.getInstance()
    }
}
