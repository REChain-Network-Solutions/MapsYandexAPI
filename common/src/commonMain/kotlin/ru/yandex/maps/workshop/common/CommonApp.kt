package ru.yandex.maps.workshop.common

import com.yandex.mapkit.search.kmp.SearchFactory
import com.yandex.mapkit.search.kmp.SearchManagerType
import ru.yandex.maps.workshop.common.additional.context.PlatformContext
import ru.yandex.maps.workshop.common.additional.context.observableSettingsFactory
import ru.yandex.maps.workshop.common.additional.llm.YandexGPTClient
import ru.yandex.maps.workshop.common.internal.PlacemarkRepository
import ru.yandex.maps.workshop.common.screen.MainScreenViewModel

class CommonApp(
    folderId: String,
    iamToken: String,
    context: PlatformContext
) {

    fun createMainViewModel() = MainScreenViewModel(
        descriptionGenerator = descriptionGenerator,
        placemarkRepository = placemarkRepository
    )

    // ======= Private

    private val gptClient: YandexGPTClient by lazy {
        YandexGPTClient(
            folderId = folderId,
            iamToken = iamToken,
        )
    }

    private val searchManager: SearchManager by lazy {
        SearchManager(
            searchManager = SearchFactory.instance.createSearchManager(SearchManagerType.ONLINE),
        )
    }

    private val placemarkRepository: PlacemarkRepository by lazy {
        PlacemarkRepository(
            factory = context.observableSettingsFactory()
        )
    }

    private val descriptionGenerator by lazy {
        DescriptionGenerator(
            yandexGPTClient = gptClient,
            searchManager = searchManager,
            placemarkRepository = placemarkRepository
        )
    }

}
