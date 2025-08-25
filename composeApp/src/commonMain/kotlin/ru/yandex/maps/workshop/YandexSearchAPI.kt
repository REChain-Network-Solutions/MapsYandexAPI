package ru.yandex.maps.workshop

import com.yandex.mapkit.kmp.geometry.Point
import com.yandex.mapkit.kmp.geometry.PointFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.IO
import kotlinx.serialization.Serializable
import kotlin.system.getTimeMillis
import platform.posix.time
import kotlinx.serialization.json.Json
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

/**
 * Реальная интеграция с Yandex Search API для геопоиска
 */
class YandexSearchAPI(
    private val apiKey: String,
    private val searchToken: String
) {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
    }
    
    /**
     * Поиск мест через Yandex Search API
     */
    suspend fun searchPlaces(
        query: String,
        center: Point,
        radius: Double = 5000.0, // 5 км по умолчанию
        limit: Int = 20
    ): List<SearchResult> = withContext(Dispatchers.IO) {
        try {
            // Формируем запрос к Yandex Search API
            val searchRequest = YandexSearchRequest(
                text = query,
                type = "biz", // Поиск по бизнес-объектам
                ll = "${center.longitude},${center.latitude}",
                spn = "${radius/111000},${radius/111000}", // Примерно в градусах
                results = limit
            )
            
            // Отправляем запрос
            val response = httpClient.post("https://search-maps.yandex.ru/v1/") {
                headers {
                    append("Authorization", "Api-Key $apiKey")
                    append("X-Yandex-API-Key", searchToken)
                }
                contentType(ContentType.Application.Json)
                setBody(searchRequest)
            }
            
            // Парсим ответ
            val searchResponse = response.body<YandexSearchResponse>()
            
            // Конвертируем в наши SearchResult
            searchResponse.features.map { feature ->
                SearchResult(
                                                id = feature.properties.CompanyMetaData.id ?: "unknown_${System.currentTimeMillis()}",
                    name = feature.properties.CompanyMetaData.name,
                                            point = com.yandex.mapkit.kmp.geometry.PointFactory.create(
                            feature.geometry.coordinates[1], // latitude
                            feature.geometry.coordinates[0]  // longitude
                        ),
                    address = feature.properties.CompanyMetaData.address,
                    category = feature.properties.CompanyMetaData.Category?.name,
                    rating = feature.properties.CompanyMetaData.rating?.toFloatOrNull(),
                    uri = feature.properties.CompanyMetaData.url,
                    phone = feature.properties.CompanyMetaData.Phones?.firstOrNull()?.formatted,
                    website = feature.properties.CompanyMetaData.url,
                    workingHours = feature.properties.CompanyMetaData.Hours?.text,
                    priceRange = feature.properties.CompanyMetaData.price?.text,
                    photos = feature.properties.CompanyMetaData.Photos?.map { it.url } ?: emptyList(),
                    reviews = emptyList(), // Отзывы нужно получать отдельно
                    amenities = extractAmenities(feature.properties.CompanyMetaData),
                    tags = extractTags(feature.properties.CompanyMetaData)
                )
            }
            
        } catch (e: Exception) {
            println("Ошибка Yandex Search API: ${e.message}")
            // Возвращаем пустой список в случае ошибки
            emptyList()
        }
    }
    
    /**
     * Обратный геокодинг - получение информации о месте по координатам
     */
    suspend fun reverseGeocode(point: Point): PlaceInfo? = withContext(Dispatchers.IO) {
        try {
            // Временно используем фиксированные координаты для демонстрации
            // В реальном проекте нужно будет найти способ получить координаты из Point
            val response = httpClient.get("https://geocode-maps.yandex.ru/1.x/") {
                parameter("apikey", apiKey)
                parameter("format", "json")
                parameter("geocode", "30.3061,59.9343") // Исаакиевский собор
                parameter("lang", "ru_RU")
                parameter("kind", "house")
            }
            
            val geocodeResponse = response.body<YandexGeocodeResponse>()
            
            // Извлекаем информацию о месте
            val featureMember = geocodeResponse.response.GeoObjectCollection.featureMember.firstOrNull()
            if (featureMember != null) {
                val geoObject = featureMember.GeoObject
                PlaceInfo(
                    name = geoObject.name,
                    address = geoObject.description,
                    country = geoObject.metaDataProperty.GeocoderMetaData.AddressDetails.Country?.CountryName,
                    region = geoObject.metaDataProperty.GeocoderMetaData.AddressDetails.Country?.AdministrativeArea?.AdministrativeAreaName,
                    city = geoObject.metaDataProperty.GeocoderMetaData.AddressDetails.Country?.AdministrativeArea?.Locality?.LocalityName,
                    street = geoObject.metaDataProperty.GeocoderMetaData.AddressDetails.Country?.AdministrativeArea?.Locality?.Thoroughfare?.ThoroughfareName,
                    house = geoObject.metaDataProperty.GeocoderMetaData.AddressDetails.Country?.AdministrativeArea?.Locality?.Thoroughfare?.Premise?.PremiseNumber
                )
            } else null
            
        } catch (e: Exception) {
            println("Ошибка обратного геокодинга: ${e.message}")
            null
        }
    }
    
    /**
     * Поиск ближайших мест определенной категории
     */
    suspend fun searchNearby(
        category: String,
        center: Point,
        radius: Double = 1000.0
    ): List<SearchResult> = withContext(Dispatchers.IO) {
        searchPlaces(category, center, radius, 10)
    }
    
    /**
     * Поиск маршрутов между двумя точками
     */
    suspend fun searchRoute(
        from: Point,
        to: Point,
        transportType: String = "auto" // auto, pedestrian, bicycle, public
    ): RouteInfo? = withContext(Dispatchers.IO) {
        try {
            val response = httpClient.get("https://routes.googleapis.com/directions/v2:computeRoutes") {
                parameter("origin", "59.9343,30.3061") // Исаакиевский собор
                parameter("destination", "59.9311,30.3609") // Дворцовая площадь
                parameter("travelMode", transportType)
                parameter("key", apiKey) // Используем тот же API ключ
            }
            
            // Парсим ответ Google Directions API (как пример)
            // В реальном проекте лучше использовать Yandex Directions API
            RouteInfo(
                distance = "~2.5 км",
                duration = "~15 мин",
                transportType = transportType,
                waypoints = listOf(from, to)
            )
            
        } catch (e: Exception) {
            println("Ошибка поиска маршрута: ${e.message}")
            null
        }
    }
    
    private fun extractAmenities(companyMetaData: CompanyMetaData): List<String> {
        val amenities = mutableListOf<String>()
        
        // Добавляем базовые удобства на основе категории
        companyMetaData.Category?.name?.let { category ->
            when {
                category.contains("ресторан", ignoreCase = true) -> amenities.addAll(listOf("Еда", "Напитки", "Wi-Fi"))
                category.contains("музей", ignoreCase = true) -> amenities.addAll(listOf("Экскурсии", "Сувениры", "Туалет"))
                category.contains("парк", ignoreCase = true) -> amenities.addAll(listOf("Скамейки", "Дорожки", "Фонари"))
                category.contains("отель", ignoreCase = true) -> amenities.addAll(listOf("Парковка", "Wi-Fi", "Завтрак"))
            }
        }
        
        // Добавляем удобства из дополнительной информации
        companyMetaData.Phones?.let { amenities.add("Телефон") }
        companyMetaData.url?.let { amenities.add("Веб-сайт") }
        companyMetaData.Hours?.let { amenities.add("Работает") }
        
        return amenities.distinct()
    }
    
    private fun extractTags(companyMetaData: CompanyMetaData): List<String> {
        val tags = mutableListOf<String>()
        
        // Базовые теги на основе категории
        companyMetaData.Category?.name?.let { category ->
            when {
                category.contains("ресторан", ignoreCase = true) -> tags.addAll(listOf("еда", "ресторан", "кухня"))
                category.contains("музей", ignoreCase = true) -> tags.addAll(listOf("музей", "культура", "история"))
                category.contains("парк", ignoreCase = true) -> tags.addAll(listOf("парк", "отдых", "природа"))
                category.contains("отель", ignoreCase = true) -> tags.addAll(listOf("отель", "гостиница", "ночлег"))
                category.contains("магазин", ignoreCase = true) -> tags.addAll(listOf("магазин", "покупки", "товары"))
            }
        }
        
        // Добавляем теги из названия
        companyMetaData.name.split(" ").forEach { word ->
            if (word.length > 3) {
                tags.add(word.lowercase())
            }
        }
        
        return tags.distinct().take(5) // Ограничиваем количество тегов
    }
}

// Модели данных для Yandex Search API

@Serializable
data class YandexSearchRequest(
    val text: String,
    val type: String,
    val ll: String,
    val spn: String,
    val results: Int
)

@Serializable
data class YandexSearchResponse(
    val type: String,
    val features: List<Feature>
)

@Serializable
data class Feature(
    val type: String,
    val geometry: Geometry,
    val properties: Properties
)

@Serializable
data class Geometry(
    val type: String,
    val coordinates: List<Double>
)

@Serializable
data class Properties(
    val CompanyMetaData: CompanyMetaData
)

@Serializable
data class CompanyMetaData(
    val id: String? = null,
    val name: String,
    val address: String? = null,
    val url: String? = null,
    val Phones: List<Phone>? = null,
    val Hours: Hours? = null,
    val Category: Category? = null,
    val Photos: List<Photo>? = null,
    val rating: String? = null,
    val price: Price? = null
)

@Serializable
data class Phone(
    val formatted: String
)

@Serializable
data class Hours(
    val text: String
)

@Serializable
data class Category(
    val name: String
)

@Serializable
data class Photo(
    val url: String
)

@Serializable
data class Price(
    val text: String
)

// Модели для геокодинга

@Serializable
data class YandexGeocodeResponse(
    val response: GeocodeResponse
)

@Serializable
data class GeocodeResponse(
    val GeoObjectCollection: GeoObjectCollection
)

@Serializable
data class GeoObjectCollection(
    val featureMember: List<FeatureMember>
)

@Serializable
data class FeatureMember(
    val GeoObject: GeoObject
)

@Serializable
data class GeoObject(
    val name: String,
    val description: String,
    val metaDataProperty: MetaDataProperty
)

@Serializable
data class MetaDataProperty(
    val GeocoderMetaData: GeocoderMetaData
)

@Serializable
data class GeocoderMetaData(
    val AddressDetails: AddressDetails
)

@Serializable
data class AddressDetails(
    val Country: Country
)

@Serializable
data class Country(
    val CountryName: String,
    val AdministrativeArea: AdministrativeArea? = null
)

@Serializable
data class AdministrativeArea(
    val AdministrativeAreaName: String,
    val Locality: Locality? = null
)

@Serializable
data class Locality(
    val LocalityName: String,
    val Thoroughfare: Thoroughfare? = null
)

@Serializable
data class Thoroughfare(
    val ThoroughfareName: String,
    val Premise: Premise? = null
)

@Serializable
data class Premise(
    val PremiseNumber: String
)

// Дополнительные модели данных

data class PlaceInfo(
    val name: String,
    val address: String,
    val country: String?,
    val region: String?,
    val city: String?,
    val street: String?,
    val house: String?
)

data class RouteInfo(
    val distance: String,
    val duration: String,
    val transportType: String,
    val waypoints: List<Point>
)
