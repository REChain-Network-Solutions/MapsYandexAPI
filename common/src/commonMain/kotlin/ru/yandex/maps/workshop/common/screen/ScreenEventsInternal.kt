package ru.yandex.maps.workshop.common.screen

import ru.yandex.maps.workshop.common.model.Placemark

internal sealed interface BaseMainScreenEvent : MainScreenEvent

internal data class SetRawPlacemarks(val placemarks: List<Placemark>) : BaseMainScreenEvent

internal data class GeneratePlacemarkDescriptionEndEvent(val id: String) : BaseMainScreenEvent

internal data class StartProcessingEvent(val id: String) : BaseMainScreenEvent

internal fun MainScreenViewModel.State.reduce(
    event: BaseMainScreenEvent
): MainScreenViewModel.State = when (event) {

    is SetRawPlacemarks -> {
        copy(
            rawPlacemarks = event.placemarks,
            selectedPlacemarkId = (
                    event.placemarks.map(Placemark::id) -
                            placemarks.map(PlacemarkViewState::id)
                    )
                .firstOrNull()
                ?: selectedPlacemarkId
                ?: placemarks.firstOrNull()?.id
        )
    }

    is GeneratePlacemarkDescriptionEvent -> copy(
        loadingPlacemarkIds = loadingPlacemarkIds + event.id
    )

    is GeneratePlacemarkDescriptionEndEvent -> copy(
        loadingPlacemarkIds = loadingPlacemarkIds - event.id,
        processingIds = processingIds - event.id
    )

    is StartProcessingEvent -> copy(
        processingIds = processingIds + event.id
    )
}
