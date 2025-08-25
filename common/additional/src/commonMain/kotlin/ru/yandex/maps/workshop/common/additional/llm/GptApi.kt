package ru.yandex.maps.workshop.common.additional.llm

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class CompletionOptions(
    val stream: Boolean = false,
    val temperature: Double = 0.6,
    val maxTokens: String = "2000",
    val reasoningOptions: ReasoningOptions = ReasoningOptions()
)

@Serializable
data class ReasoningOptions(
    val mode: String = "DISABLED"
)

@Serializable
data class Message(
    val role: String,
    val text: String
)

@Serializable
data class CompletionRequest(
    val completionOptions: CompletionOptions = CompletionOptions(),
    val messages: List<Message>,
    val modelUri: String? = null,
)

@Serializable
data class CompletionResponse(
    val result: CompletionResult
)

@Serializable
data class CompletionResult(
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

class YandexGPTClient(
    private val folderId: String,
    private val iamToken: String
) {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun complete(request: CompletionRequest): CompletionResponse {
        return client.post("https://llm.api.cloud.yandex.net/foundationModels/v1/completion") {
            header("Authorization", "Bearer $iamToken")
            header("Content-Type", "application/json")
            setBody(request.copy(modelUri = "gpt://$folderId/yandexgpt"))
        }.body<CompletionResponse>()
    }
}

suspend fun YandexGPTClient.completeText(request: CompletionRequest) =
    complete(request).result.alternatives.firstOrNull()?.message?.text
