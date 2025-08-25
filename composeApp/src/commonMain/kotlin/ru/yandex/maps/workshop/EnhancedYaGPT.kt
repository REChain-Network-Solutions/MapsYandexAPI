package ru.yandex.maps.workshop

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.IO
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

/**
 * Расширенный контроллер YaGPT с богатыми возможностями генерации контента
 */
class EnhancedYaGPT(
    private val folderId: String,
    private val apiKey: String
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
     * Генерация полного описания места с историческим контекстом
     */
    suspend fun generateHistoricalDescription(
        placeName: String,
        address: String?,
        category: String?,
        coordinates: String?
    ): HistoricalPlaceDescription = withContext(Dispatchers.IO) {
        val systemPrompt = """
            Ты - эксперт по истории, архитектуре и туризму. Создай полное описание места 
            с историческим контекстом для туристического приложения.
            
            Требования:
            - Длина: 6-8 предложений (200-300 слов)
            - Стиль: Научно-популярный, но увлекательный
            - Структура:
              * Исторический контекст и дата создания
              * Архитектурные особенности
              * Исторические события, связанные с местом
              * Знаменитые люди, связанные с местом
              * Современное значение и использование
              * Интересные факты и легенды
            - Тон: Образовательный, но не сухой
            - Источники: Используй исторические факты
        """.trimIndent()
        
        val userPrompt = """
            Создай историческое описание для места:
            
            Название: $placeName
            Адрес: $address
            Категория: $category
            Координаты: $coordinates
            
            Создай описание в JSON формате с полями:
            - historicalContext: исторический контекст
            - architecturalFeatures: архитектурные особенности
            - historicalEvents: исторические события
            - famousPeople: знаменитые люди
            - modernSignificance: современное значение
            - interestingFacts: интересные факты
            - visitingTips: советы для посетителей
        """.trimIndent()
        
        val request = YaGPTRequest(
            modelUri = "gpt://$folderId/yandexgpt-lite",
            completionOptions = CompletionOptions(
                temperature = 0.7,
                maxTokens = "2000"
            ),
            messages = listOf(
                Message("system", systemPrompt),
                Message("user", userPrompt)
            )
        )
        
        try {
            val response = httpClient.post("https://llm.api.cloud.yandex.net/foundationModels/v1/completion") {
                headers {
                    append("Authorization", "Api-Key $apiKey")
                    append("x-folder-id", folderId)
                }
                contentType(ContentType.Application.Json)
                setBody(Json.encodeToString(YaGPTRequest.serializer(), request))
            }
            
            val yaGPTResponse = Json.decodeFromString<YaGPTResponse>(response.body())
            val responseText = yaGPTResponse.result.alternatives.firstOrNull()?.message?.text 
                ?: "{}"
            
            parseHistoricalDescription(responseText)
            
        } catch (e: Exception) {
            println("Ошибка генерации исторического описания: ${e.message}")
            HistoricalPlaceDescription.getDefault(placeName)
        }
    }
    
    /**
     * Генерация туристического путеводителя
     */
    suspend fun generateTouristGuide(
        placeName: String,
        category: String?,
        amenities: List<String>,
        nearbyAttractions: List<String>
    ): TouristGuide = withContext(Dispatchers.IO) {
        val systemPrompt = """
            Ты - профессиональный гид-экскурсовод с многолетним опытом. Создай 
            подробный туристический путеводитель для места.
            
            Требования:
            - Длина: 8-10 предложений (300-400 слов)
            - Стиль: Практичный, дружелюбный, информативный
            - Структура:
              * Лучшее время для посещения
              * Как добраться
              * Что обязательно посмотреть
              * Сколько времени планировать
              * Что взять с собой
              * Где поесть рядом
              * Где остановиться
              * Ближайшие достопримечательности
            - Тон: Дружелюбный, как от опытного друга
        """.trimIndent()
        
        val userPrompt = """
            Создай туристический путеводитель для места:
            
            Название: $placeName
            Категория: $category
            Удобства: ${amenities.joinToString(", ")}
            Близкие достопримечательности: ${nearbyAttractions.joinToString(", ")}
            
            Создай путеводитель в JSON формате с полями:
            - bestTimeToVisit: лучшее время для посещения
            - howToGetThere: как добраться
            - mustSee: что обязательно посмотреть
            - timeToPlan: сколько времени планировать
            - whatToBring: что взять с собой
            - nearbyFood: где поесть рядом
            - nearbyAccommodation: где остановиться
            - routeSuggestion: предложение маршрута
        """.trimIndent()
        
        val request = YaGPTRequest(
            modelUri = "gpt://$folderId/yandexgpt-lite",
            completionOptions = CompletionOptions(
                temperature = 0.8,
                maxTokens = "2500"
            ),
            messages = listOf(
                Message("system", systemPrompt),
                Message("user", userPrompt)
            )
        )
        
        try {
            val response = httpClient.post("https://llm.api.cloud.yandex.net/foundationModels/v1/completion") {
                headers {
                    append("Authorization", "Api-Key $apiKey")
                    append("x-folder-id", folderId)
                }
                contentType(ContentType.Application.Json)
                setBody(Json.encodeToString(YaGPTRequest.serializer(), request))
            }
            
            val yaGPTResponse = Json.decodeFromString<YaGPTResponse>(response.body())
            val responseText = yaGPTResponse.result.alternatives.firstOrNull()?.message?.text 
                ?: "{}"
            
            parseTouristGuide(responseText)
            
        } catch (e: Exception) {
            println("Ошибка генерации туристического путеводителя: ${e.message}")
            TouristGuide.getDefault(placeName)
        }
    }
    
    /**
     * Генерация культурного контекста места
     */
    suspend fun generateCulturalContext(
        placeName: String,
        category: String?,
        tags: List<String>
    ): CulturalContext = withContext(Dispatchers.IO) {
        val systemPrompt = """
            Ты - эксперт по культуре, искусству и социологии. Создай анализ 
            культурного контекста места.
            
            Требования:
            - Длина: 5-7 предложений (150-250 слов)
            - Стиль: Аналитический, но доступный
            - Структура:
              * Культурное значение места
              * Влияние на местную культуру
              * Связь с традициями
              * Современные культурные события
              * Рекомендации для культурного туризма
            - Тон: Образовательный, аналитический
        """.trimIndent()
        
        val userPrompt = """
            Создай культурный контекст для места:
            
            Название: $placeName
            Категория: $category
            Теги: ${tags.joinToString(", ")}
            
            Создай анализ в JSON формате с полями:
            - culturalSignificance: культурное значение
            - localCultureInfluence: влияние на местную культуру
            - traditionsConnection: связь с традициями
            - modernCulturalEvents: современные культурные события
            - culturalTourismTips: рекомендации для культурного туризма
        """.trimIndent()
        
        val request = YaGPTRequest(
            modelUri = "gpt://$folderId/yandexgpt-lite",
            completionOptions = CompletionOptions(
                temperature = 0.6,
                maxTokens = "1500"
            ),
            messages = listOf(
                Message("system", systemPrompt),
                Message("user", userPrompt)
            )
        )
        
        try {
            val response = httpClient.post("https://llm.api.cloud.yandex.net/foundationModels/v1/completion") {
                headers {
                    append("Authorization", "Api-Key $apiKey")
                    append("x-folder-id", folderId)
                }
                contentType(ContentType.Application.Json)
                setBody(Json.encodeToString(YaGPTRequest.serializer(), request))
            }
            
            val yaGPTResponse = Json.decodeFromString<YaGPTResponse>(response.body())
            val responseText = yaGPTResponse.result.alternatives.firstOrNull()?.message?.text 
                ?: "{}"
            
            parseCulturalContext(responseText)
            
        } catch (e: Exception) {
            println("Ошибка генерации культурного контекста: ${e.message}")
            CulturalContext.getDefault(placeName)
        }
    }
    
    /**
     * Генерация персонализированных рекомендаций
     */
    suspend fun generatePersonalizedRecommendations(
        placeName: String,
        userPreferences: List<String>,
        userAge: Int?,
        userInterests: List<String>
    ): PersonalizedRecommendations = withContext(Dispatchers.IO) {
        val systemPrompt = """
            Ты - персональный туристический консультант. Создай персонализированные 
            рекомендации для посещения места.
            
            Требования:
            - Длина: 4-6 предложений (100-200 слов)
            - Стиль: Персональный, дружелюбный
            - Структура:
              * Почему это место подходит именно этому пользователю
              * Что посмотреть в первую очередь
              * Скрытые места, которые стоит посетить
              * Советы по времени посещения
            - Тон: Персональный, как от друга
        """.trimIndent()
        
        val userPrompt = """
            Создай персонализированные рекомендации для места:
            
            Название места: $placeName
            Предпочтения пользователя: ${userPreferences.joinToString(", ")}
            Возраст: $userAge
            Интересы: ${userInterests.joinToString(", ")}
            
            Создай рекомендации в JSON формате с полями:
            - whyPerfectForUser: почему место подходит пользователю
            - priorityAttractions: что посмотреть в первую очередь
            - hiddenGems: скрытые места
            - timingAdvice: советы по времени
        """.trimIndent()
        
        val request = YaGPTRequest(
            modelUri = "gpt://$folderId/yandexgpt-lite",
            completionOptions = CompletionOptions(
                temperature = 0.9,
                maxTokens = "1500"
            ),
            messages = listOf(
                Message("system", systemPrompt),
                Message("user", userPrompt)
            )
        )
        
        try {
            val response = httpClient.post("https://llm.api.cloud.yandex.net/foundationModels/v1/completion") {
                headers {
                    append("Authorization", "Api-Key $apiKey")
                    append("x-folder-id", folderId)
                }
                contentType(ContentType.Application.Json)
                setBody(Json.encodeToString(YaGPTRequest.serializer(), request))
            }
            
            val yaGPTResponse = Json.decodeFromString<YaGPTResponse>(response.body())
            val responseText = yaGPTResponse.result.alternatives.firstOrNull()?.message?.text 
                ?: "{}"
            
            parsePersonalizedRecommendations(responseText)
            
        } catch (e: Exception) {
            println("Ошибка генерации персонализированных рекомендаций: ${e.message}")
            PersonalizedRecommendations.getDefault(placeName)
        }
    }
    
    // Методы парсинга JSON ответов
    private fun parseHistoricalDescription(jsonText: String): HistoricalPlaceDescription {
        return try {
            // В реальном проекте здесь будет JSON парсинг
            HistoricalPlaceDescription.getDefault("Место")
        } catch (e: Exception) {
            HistoricalPlaceDescription.getDefault("Место")
        }
    }
    
    private fun parseTouristGuide(jsonText: String): TouristGuide {
        return try {
            TouristGuide.getDefault("Место")
        } catch (e: Exception) {
            TouristGuide.getDefault("Место")
        }
    }
    
    private fun parseCulturalContext(jsonText: String): CulturalContext {
        return try {
            CulturalContext.getDefault("Место")
        } catch (e: Exception) {
            CulturalContext.getDefault("Место")
        }
    }
    
    private fun parsePersonalizedRecommendations(jsonText: String): PersonalizedRecommendations {
        return try {
            PersonalizedRecommendations.getDefault("Место")
        } catch (e: Exception) {
            PersonalizedRecommendations.getDefault("Место")
        }
    }
}

// Модели данных для расширенного YaGPT

data class HistoricalPlaceDescription(
    val historicalContext: String,
    val architecturalFeatures: String,
    val historicalEvents: String,
    val famousPeople: String,
    val modernSignificance: String,
    val interestingFacts: String,
    val visitingTips: String
) {
    companion object {
        fun getDefault(placeName: String) = HistoricalPlaceDescription(
            historicalContext = "$placeName имеет богатую историю, уходящую корнями в прошлое.",
            architecturalFeatures = "Место отличается уникальной архитектурой и стилем.",
            historicalEvents = "Здесь происходили важные исторические события.",
            famousPeople = "С этим местом связаны известные исторические личности.",
            modernSignificance = "Сегодня место сохраняет свое культурное значение.",
            interestingFacts = "Интересные факты делают это место особенным.",
            visitingTips = "Рекомендуется посетить в спокойное время для лучшего восприятия."
        )
    }
}

data class TouristGuide(
    val bestTimeToVisit: String,
    val howToGetThere: String,
    val mustSee: String,
    val timeToPlan: String,
    val whatToBring: String,
    val nearbyFood: String,
    val nearbyAccommodation: String,
    val routeSuggestion: String
) {
    companion object {
        fun getDefault(placeName: String) = TouristGuide(
            bestTimeToVisit = "Лучшее время для посещения $placeName - утренние часы или вечер.",
            howToGetThere = "Добраться можно на общественном транспорте или такси.",
            mustSee = "Обязательно посмотрите основные достопримечательности места.",
            timeToPlan = "Планируйте 2-3 часа для полноценного осмотра.",
            whatToBring = "Возьмите с собой фотоаппарат и удобную обувь.",
            nearbyFood = "Рядом есть несколько кафе и ресторанов.",
            nearbyAccommodation = "Вблизи расположены отели и гостиницы.",
            routeSuggestion = "Начните осмотр с главного входа и двигайтесь по часовой стрелке."
        )
    }
}

data class CulturalContext(
    val culturalSignificance: String,
    val localCultureInfluence: String,
    val traditionsConnection: String,
    val modernCulturalEvents: String,
    val culturalTourismTips: String
) {
    companion object {
        fun getDefault(placeName: String) = CulturalContext(
            culturalSignificance = "$placeName имеет важное культурное значение для региона.",
            localCultureInfluence = "Место влияет на развитие местной культуры.",
            traditionsConnection = "Здесь сохраняются древние традиции и обычаи.",
            modernCulturalEvents = "Регулярно проводятся современные культурные мероприятия.",
            culturalTourismTips = "Для культурного туризма рекомендуется изучить историю места заранее."
        )
    }
}

data class PersonalizedRecommendations(
    val whyPerfectForUser: String,
    val priorityAttractions: String,
    val hiddenGems: String,
    val timingAdvice: String
) {
    companion object {
        fun getDefault(placeName: String) = PersonalizedRecommendations(
            whyPerfectForUser = "$placeName идеально подходит для ваших интересов.",
            priorityAttractions: "В первую очередь посмотрите главные достопримечательности.",
            hiddenGems: "Не пропустите скрытые от туристов места.",
            timingAdvice = "Лучше всего посещать в утренние часы."
        )
    }
}

// Модели для YaGPT API (дублируем для совместимости)
@Serializable
data class YaGPTRequest(
    val modelUri: String,
    val completionOptions: CompletionOptions,
    val messages: List<Message>
)

@Serializable
data class CompletionOptions(
    val stream: Boolean = false,
    val temperature: Double = 0.7,
    val maxTokens: String = "2000"
)

@Serializable
data class Message(
    val role: String,
    val text: String
)

@Serializable
data class YaGPTResponse(
    val result: YaGPTResult
)

@Serializable
data class YaGPTResult(
    val alternatives: List<Alternative>,
    val usage: Usage,
    val modelVersion: String
)

@Serializable
data class Alternative(
    val message: Message,
    val status: String
)

@Serializable
data class Usage(
    val inputTextTokens: String,
    val completionTokens: String,
    val totalTokens: String
)
