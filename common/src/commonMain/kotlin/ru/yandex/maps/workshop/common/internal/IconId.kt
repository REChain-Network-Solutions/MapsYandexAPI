package ru.yandex.maps.workshop.common.internal

import kotlinx.serialization.Serializable

@Serializable
enum class IconId {
    DefaultIcon1,
    DefaultIcon2,
    DefaultIcon3;


    companion object {
        fun randomNewPlacemarkIconId(): IconId = entries
            .random()
    }
}
