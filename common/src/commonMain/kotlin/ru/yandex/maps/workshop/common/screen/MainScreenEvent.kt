package ru.yandex.maps.workshop.common.screen

import com.yandex.mapkit.kmp.geometry.Point
import ru.yandex.maps.workshop.common.additional.udf.Event

interface MainScreenEvent : Event
data class GeneratePlacemarkDescriptionEvent(val id: String) : MainScreenEvent, BaseMainScreenEvent

data class SelectPlacemarkEvent(val id: String) : MainScreenEvent

data class LongTapEvent(val point: Point): MainScreenEvent
