package ru.yandex.maps.workshop.common

import com.yandex.mapkit.kmp.geometry.mpLatitude
import com.yandex.mapkit.kmp.geometry.mpLongitude
import com.yandex.mapkit.kmp.geometry.point
import com.yandex.mapkit.kmp.mpDescriptionText
import com.yandex.mapkit.kmp.mpGeometry
import com.yandex.mapkit.kmp.mpName
import com.yandex.mapkit.utils.distanceTo
import ru.yandex.maps.workshop.common.additional.llm.CompletionRequest
import ru.yandex.maps.workshop.common.additional.llm.Message
import ru.yandex.maps.workshop.common.additional.llm.YandexGPTClient
import ru.yandex.maps.workshop.common.additional.llm.completeText
import ru.yandex.maps.workshop.common.internal.PlacemarkRepository

fun interface DescriptionGenerator {

    suspend fun generateDescription(placemarkId: String): String?

    companion object {

        private const val SEARCH_RADIUS_M = 500.0

        internal operator fun invoke(
            yandexGPTClient: YandexGPTClient,
            searchManager: SearchManager,
            placemarkRepository: PlacemarkRepository,
        ): DescriptionGenerator = DescriptionGenerator { placemarkId ->
            val placemark =
                placemarkRepository.getPlacemark(placemarkId) ?: error("Unknown placemark")

            val searchResponse = searchManager.search(
                query = "достопримечательности, парки, кафе, магазины",
                center = placemark.position,
                radius = SEARCH_RADIUS_M,
            )

            val informationList = searchResponse.mapIndexed { index, obj ->
                val point = obj.mpGeometry.firstNotNullOfOrNull { it.point }
                    ?: error("Geometry is not point")
                """
                $index)
                Название: ${obj.mpName}
                Описание: ${obj.mpDescriptionText}
                Расстояние: ${placemark.position.distanceTo(point)}
            """.trimIndent()
            }
            val info = informationList.joinToString("\n")

            val request = """
                Вот у меня есть точка по координатам ${placemark.position.mpLatitude}, ${placemark.position.mpLongitude}
                Сгенерируй хорошее художественное, человекочитаемое описание для этого места
                $info
                Учти что описание должно быть по-настоящему хорошим и лаконичным
                Давай уложимся в 400 символов.
                Не пиши в описании координаты, но может ты знаешь места рядом, используй это
            """.trimIndent()

            val completionRequest = CompletionRequest(
                messages = listOf(
                    Message(
                        role = "system",
                        text = "Сгенерировать описание места на основе предоставленной пользователем информации",
                    ),

                    Message(
                        role = "user",
                        text = request
                    ),
                ),
            )

            yandexGPTClient.completeText(completionRequest) ?: error("Invalid response")
        }
    }
}
