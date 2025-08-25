package ru.yandex.maps.workshop.common.screen

import com.yandex.mapkit.kmp.geometry.mpLatitude
import com.yandex.mapkit.kmp.geometry.mpLongitude
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.yandex.maps.workshop.common.DescriptionGenerator
import ru.yandex.maps.workshop.common.additional.udf.Event
import ru.yandex.maps.workshop.common.additional.udf.Reducer
import ru.yandex.maps.workshop.common.additional.udf.ViewModel
import ru.yandex.maps.workshop.common.internal.IconId
import ru.yandex.maps.workshop.common.internal.PlacemarkRepository
import ru.yandex.maps.workshop.common.model.Placemark

private val ScreenReducer: Reducer<MainScreenViewModel.State> get() = { state, event ->
    when (event) {
        is BaseMainScreenEvent -> state.reduce(event)
        is SelectPlacemarkEvent -> state.copy(selectedPlacemarkId = event.id)
        else -> state
    }
}

class MainScreenViewModel internal constructor(
    private val placemarkRepository: PlacemarkRepository,
    private val descriptionGenerator: DescriptionGenerator
): ViewModel<MainScreenViewModel.State>(
    initialState = State.Initial,
    reducer = ScreenReducer
) {

    // ===== Initialize

    init {
        listenForDescriptionGeneration()
        listenPlacemarksRepository()
    }

    override fun sideEffect(event: Event) {
        when (event) {
           is LongTapEvent -> {
               val index = viewStates().value.placemarks.size
               val id = "long_tap_placemark_${event.point.mpLatitude}_${event.point.mpLongitude}"
               val randomIconId = IconId.randomNewPlacemarkIconId()
               val title = "Новое событие $index"
               placemarkRepository.addOrUpdatePlacemark(
                   Placemark(
                       id = id,
                       latitude = event.point.mpLatitude,
                       longitude = event.point.mpLongitude,
                       title = title,
                       iconId = randomIconId
                   )
               )
           }
        }
    }

    // ===== State

    data class State(
        internal val rawPlacemarks: List<Placemark>,
        internal val loadingPlacemarkIds: Set<String>,
        val selectedPlacemarkId: String?,
        val processingIds: Set<String>
    ) {

        val placemarks: List<PlacemarkViewState> = rawPlacemarks.map {
            PlacemarkViewState(it, loadingPlacemarkIds.contains(it.id))
        }

        companion object {
            val Initial = State(
                rawPlacemarks = emptyList(),
                loadingPlacemarkIds = emptySet(),
                processingIds = emptySet(),
                selectedPlacemarkId = null,
            )
        }
    }

    // region ===== Private

    private fun listenPlacemarksRepository() {
        placemarkRepository.placemarks.onEach {
            store.dispatch(SetRawPlacemarks(it))
        }.launchIn(viewModelScope)
    }

    private fun listenForDescriptionGeneration() {
        viewStates()
            .map { it.loadingPlacemarkIds to it.processingIds }
            .distinctUntilChanged()
            .onEach { (loadingIds, processingIds) ->
                val newToProcess = loadingIds - processingIds
                newToProcess.forEach { placemarkId ->
                    viewModelScope.launch {
                        val description = generateDescription(placemarkId)
                        store.dispatch(
                            GeneratePlacemarkDescriptionEndEvent(id = placemarkId)
                        )
                        placemarkRepository.updateDescription(placemarkId, description)
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    private suspend fun generateDescription(placemarkId: String): String? {
        return runCatching {
            descriptionGenerator.generateDescription(placemarkId)
        }.onSuccess {
            placemarkRepository.updateDescription(placemarkId, it)
        }.onFailure {
            println(it.message)
        }.getOrNull()
    }

    // endregion
}
