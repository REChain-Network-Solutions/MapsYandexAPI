package ru.yandex.maps.workshop.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.yandex.mapkit.kmp.map.IconStyle
import com.yandex.mapkit.kmp.map.IconStyleFactory
import com.yandex.runtime.kmp.NativePointFactory
import com.yandex.runtime.kmp.image.ImageProvider
import mapsworkshop.composeapp.generated.resources.Res
import mapsworkshop.composeapp.generated.resources.pin1
import mapsworkshop.composeapp.generated.resources.pin1_selected
import mapsworkshop.composeapp.generated.resources.pin2
import mapsworkshop.composeapp.generated.resources.pin2_selected
import mapsworkshop.composeapp.generated.resources.pin3
import mapsworkshop.composeapp.generated.resources.pin3_selected
import org.jetbrains.compose.resources.imageResource
import ru.yandex.maps.workshop.common.internal.IconId
import ru.yandex.maps.workshop.internal.res.toImageProvider

private data class ImageProviders(

    val pin1Selected: ImageProvider,
    val pin1: ImageProvider,
    val pin2Selected: ImageProvider,
    val pin2: ImageProvider,
    val pin3Selected: ImageProvider,
    val pin3: ImageProvider,
)

class PinIconFactory private constructor(
    private val imageProviders: ImageProviders
) {
    companion object {
        @Composable
        fun create(): PinIconFactory {
            val pin1Selected: ImageProvider = imageResource(Res.drawable.pin1_selected).toImageProvider()
            val pin1: ImageProvider = imageResource(Res.drawable.pin1).toImageProvider()
            val pin2Selected: ImageProvider = imageResource(Res.drawable.pin2_selected).toImageProvider()
            val pin2: ImageProvider = imageResource(Res.drawable.pin2).toImageProvider()
            val pin3Selected: ImageProvider = imageResource(Res.drawable.pin3_selected).toImageProvider()
            val pin3: ImageProvider = imageResource(Res.drawable.pin3).toImageProvider()

            val providers = ImageProviders(
                pin1Selected = pin1Selected,
                pin1 = pin1,
                pin2Selected = pin2Selected,
                pin2 = pin2,
                pin3Selected = pin3Selected,
                pin3 = pin3,
            )

            return remember { PinIconFactory(providers) }
        }
    }

    fun iconAndStyleFor(iconId: IconId, selected: Boolean): Pair<ImageProvider, IconStyle> {
        return iconFor(iconId, selected) to pinStyle(iconId, selected)
    }

    private fun iconFor(iconId: IconId, selected: Boolean): ImageProvider = with(imageProviders) {
        when (iconId) {
            IconId.DefaultIcon3 -> if (selected) pin3Selected else pin3
            IconId.DefaultIcon1 -> if (selected) pin1Selected else pin1
            IconId.DefaultIcon2 -> if (selected) pin2Selected else pin2
        }
    }

    private fun pinStyle(iconId: IconId, selected: Boolean): IconStyle = when (iconId) {
        else -> IconStyleFactory.create(
            anchor = NativePointFactory.create(0.5f, 1f),
            rotationType = null,
            zIndex = null,
            flat = null,
            visible = null,
            scale = null,
            tappableArea = null,
        )
    }
}
