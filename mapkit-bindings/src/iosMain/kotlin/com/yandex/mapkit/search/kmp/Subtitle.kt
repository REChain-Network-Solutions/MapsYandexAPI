@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

/**
 * Separate subtitle of a certain type.
 */
actual typealias SubtitleItem = platform.YandexMapsMobile.YMKSearchSubtitleItem

/**
 * Subtitle type. For example, "exchange".
 */
actual val SubtitleItem.mpType: String
    get() = type()
/**
 * Short summary text. For example, "USD 57.69/57.3".
 *
 */
actual val SubtitleItem.mpText: String?
    get() = text()
/**
 * Detailed subtitle info. For example, [ {"key": "currency", "value":
 * "USD"}, {"key": "buy", "value": "57.3"}, {"key": "sell", "value":
 * "57.69"} ].
 */
actual val SubtitleItem.mpProperties: kotlin.collections.List<com.yandex.runtime.kmp.KeyValuePair>
    get() = properties().let { it as kotlin.collections.List<platform.YandexMapsMobile.YRTKeyValuePair> }

actual object SubtitleItemFactory {
    actual fun create(
        type: String,
        text: String?,
        properties: kotlin.collections.List<com.yandex.runtime.kmp.KeyValuePair>,
    ): SubtitleItem {
        return SubtitleItem.subtitleItemWithType(
            type,
            text,
            properties.let { it as kotlin.collections.List<*> },
        )
    }
}

/**
 * Subtitle snippet.
 */
actual typealias SubtitleMetadata = platform.YandexMapsMobile.YMKSearchSubtitleMetadata

/**
 * List of subtitles.
 */
actual val SubtitleMetadata.mpSubtitleItems: kotlin.collections.List<com.yandex.mapkit.search.kmp.SubtitleItem>
    get() = subtitleItems().let { it as kotlin.collections.List<platform.YandexMapsMobile.YMKSearchSubtitleItem> }
/**
 * List of subtitles to be displayed in SERP.
 */
actual val SubtitleMetadata.mpSerpSubtitleItems: kotlin.collections.List<com.yandex.mapkit.search.kmp.SubtitleItem>
    get() = serpSubtitleItems().let { it as kotlin.collections.List<platform.YandexMapsMobile.YMKSearchSubtitleItem> }

actual object SubtitleMetadataFactory {
    actual fun create(
        subtitleItems: kotlin.collections.List<com.yandex.mapkit.search.kmp.SubtitleItem>,
        serpSubtitleItems: kotlin.collections.List<com.yandex.mapkit.search.kmp.SubtitleItem>,
    ): SubtitleMetadata {
        return SubtitleMetadata.subtitleMetadataWithSubtitleItems(
            subtitleItems.let { it as kotlin.collections.List<*> },
            serpSubtitleItems.let { it as kotlin.collections.List<*> },
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.subtitleMetadata: SubtitleMetadata?
    get() = impl.getItemOfClass(SubtitleMetadata) as? SubtitleMetadata

