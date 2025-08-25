@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Separate subtitle of a certain type.
 */
expect class SubtitleItem

/**
 * Subtitle type. For example, "exchange".
 */
expect val SubtitleItem.mpType: String
/**
 * Short summary text. For example, "USD 57.69/57.3".
 *
 */
expect val SubtitleItem.mpText: String?
/**
 * Detailed subtitle info. For example, [ {"key": "currency", "value":
 * "USD"}, {"key": "buy", "value": "57.3"}, {"key": "sell", "value":
 * "57.69"} ].
 */
expect val SubtitleItem.mpProperties: kotlin.collections.List<com.yandex.runtime.kmp.KeyValuePair>

expect object SubtitleItemFactory {
    fun create(
        type: String,
        text: String?,
        properties: kotlin.collections.List<com.yandex.runtime.kmp.KeyValuePair>,
    ): SubtitleItem
}

/**
 * Subtitle snippet.
 */
expect class SubtitleMetadata

/**
 * List of subtitles.
 */
expect val SubtitleMetadata.mpSubtitleItems: kotlin.collections.List<com.yandex.mapkit.search.kmp.SubtitleItem>
/**
 * List of subtitles to be displayed in SERP.
 */
expect val SubtitleMetadata.mpSerpSubtitleItems: kotlin.collections.List<com.yandex.mapkit.search.kmp.SubtitleItem>

expect object SubtitleMetadataFactory {
    fun create(
        subtitleItems: kotlin.collections.List<com.yandex.mapkit.search.kmp.SubtitleItem>,
        serpSubtitleItems: kotlin.collections.List<com.yandex.mapkit.search.kmp.SubtitleItem>,
    ): SubtitleMetadata
}

expect val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.subtitleMetadata: SubtitleMetadata?

