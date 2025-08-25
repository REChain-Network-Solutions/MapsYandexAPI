package ru.yandex.maps.workshop.common.screen

import com.yandex.mapkit.kmp.geometry.Point
import ru.yandex.maps.workshop.common.internal.IconId
import ru.yandex.maps.workshop.common.model.Placemark

data class PlacemarkViewState(
    internal val rawPlacemark: Placemark,
    val isLoading: Boolean
) {
    val id: String get() = rawPlacemark.id
    val title: String get() = rawPlacemark.title
    val description: String? get() = rawPlacemark.description
    val iconId: IconId get() = rawPlacemark.iconId
    val position: Point get() = rawPlacemark.position
}
