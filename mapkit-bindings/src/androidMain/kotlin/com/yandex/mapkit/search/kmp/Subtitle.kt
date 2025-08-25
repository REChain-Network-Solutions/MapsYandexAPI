@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Separate subtitle of a certain type.
 */
actual typealias SubtitleItem = com.yandex.mapkit.search.SubtitleItem

/**
 * Subtitle type. For example, "exchange".
 */
actual val SubtitleItem.mpType: String
    get() = type
/**
 * Short summary text. For example, "USD 57.69/57.3".
 *
 */
actual val SubtitleItem.mpText: String?
    get() = text
/**
 * Detailed subtitle info. For example, [ {"key": "currency", "value":
 * "USD"}, {"key": "buy", "value": "57.3"}, {"key": "sell", "value":
 * "57.69"} ].
 */
actual val SubtitleItem.mpProperties: kotlin.collections.List<com.yandex.runtime.kmp.KeyValuePair>
    get() = properties

actual object SubtitleItemFactory {
    actual fun create(
        type: String,
        text: String?,
        properties: kotlin.collections.List<com.yandex.runtime.kmp.KeyValuePair>,
    ): SubtitleItem {
        return SubtitleItem(
            type,
            text,
            properties,
        )
    }
}

/**
 * Subtitle snippet.
 */
actual typealias SubtitleMetadata = com.yandex.mapkit.search.SubtitleMetadata

/**
 * List of subtitles.
 */
actual val SubtitleMetadata.mpSubtitleItems: kotlin.collections.List<com.yandex.mapkit.search.kmp.SubtitleItem>
    get() = subtitleItems
/**
 * List of subtitles to be displayed in SERP.
 */
actual val SubtitleMetadata.mpSerpSubtitleItems: kotlin.collections.List<com.yandex.mapkit.search.kmp.SubtitleItem>
    get() = serpSubtitleItems

actual object SubtitleMetadataFactory {
    actual fun create(
        subtitleItems: kotlin.collections.List<com.yandex.mapkit.search.kmp.SubtitleItem>,
        serpSubtitleItems: kotlin.collections.List<com.yandex.mapkit.search.kmp.SubtitleItem>,
    ): SubtitleMetadata {
        return SubtitleMetadata(
            subtitleItems,
            serpSubtitleItems,
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.subtitleMetadata: SubtitleMetadata?
    get() = getItem(SubtitleMetadata::class.java)

