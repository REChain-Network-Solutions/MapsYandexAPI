@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Session details.
 */
expect class Showtime

/**
 * Session start time.
 */
expect val Showtime.mpStartTime: com.yandex.mapkit.kmp.Time
/**
 * Ticket price.
 *
 */
expect val Showtime.mpPrice: com.yandex.mapkit.kmp.Money?
/**
 * Ticket id.
 *
 */
expect val Showtime.mpTicketId: String?

expect object ShowtimeFactory {
    fun create(
        startTime: com.yandex.mapkit.kmp.Time,
        price: com.yandex.mapkit.kmp.Money?,
        ticketId: String?,
    ): Showtime
}

/**
 * Event schedule snippet.
 */
expect class ShowtimesObjectMetadata

/**
 * Event title.
 */
expect val ShowtimesObjectMetadata.mpTitle: String
/**
 * List of showtimes.
 */
expect val ShowtimesObjectMetadata.mpShowtimes: kotlin.collections.List<com.yandex.mapkit.search.kmp.Showtime>

expect object ShowtimesObjectMetadataFactory {
    fun create(
        title: String,
        showtimes: kotlin.collections.List<com.yandex.mapkit.search.kmp.Showtime>,
    ): ShowtimesObjectMetadata
}

expect val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.showtimesObjectMetadata: ShowtimesObjectMetadata?

