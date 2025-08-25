package ru.yandex.maps.workshop.common.internal

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.getStringOrNullFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.serialization.json.Json
import ru.yandex.maps.workshop.common.additional.settings.ObservableSettingsFactory
import ru.yandex.maps.workshop.common.model.Placemark

internal class PlacemarkRepository(factory: ObservableSettingsFactory) {

    val settings = factory.create("placemarks")

    private val json: Json = Json { ignoreUnknownKeys = true }
    val repositoryScope = CoroutineScope(Dispatchers.Default)

    val placemarks: StateFlow<List<Placemark>> = placemarksFlow().stateIn(
        scope = repositoryScope,
        started = SharingStarted.Eagerly,
        initialValue = emptyList(),
    )

    private companion object {
        const val KEY = "placemarks_json"
    }

    init {
        if (loadPlacemarks().isEmpty()) {
            savePlacemarks(listOf(Placemark.WorkshopPlacemark))
        }
    }

    fun getPlacemark(id: String): Placemark? {
        return loadPlacemarks().find { it.id == id }
    }

    fun addOrUpdatePlacemark(placemark: Placemark) {
        val placemarks = loadPlacemarks().filter { it.id != placemark.id }
        savePlacemarks(placemarks + placemark)
    }

    fun savePlacemarks(placemarks: List<Placemark>) {
        val str = json.encodeToString(placemarks)
        settings.putString(KEY, str)
    }

    fun updateDescription(placemarkId: String, description: String?) {
        val placemarks = loadPlacemarks()
        val updatedPlacemarks = placemarks.map {
            if (it.id == placemarkId) {
                it.copy(description = description)
            } else {
                it
            }
        }
        savePlacemarks(updatedPlacemarks)
    }

    fun loadPlacemarks(): List<Placemark> {
        val str = settings.getStringOrNull(KEY) ?: return emptyList()
        val placemarks: List<Placemark> = try {
            json.decodeFromString(str)
        } catch (_: Throwable) {
            emptyList()
        }
        return placemarks
    }

    @OptIn(ExperimentalSettingsApi::class)
    private fun placemarksFlow(): Flow<List<Placemark>> =
        settings.getStringOrNullFlow(KEY)
            .flowOn(Dispatchers.IO)
            .map { str ->
                str?.let {
                    try {
                        json.decodeFromString(it)
                    } catch (_: Throwable) {
                        emptyList()
                    }
                } ?: emptyList()
            }
}
