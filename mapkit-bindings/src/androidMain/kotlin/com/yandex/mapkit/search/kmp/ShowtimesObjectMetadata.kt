@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Session details.
 */
actual typealias Showtime = com.yandex.mapkit.search.Showtime

/**
 * Session start time.
 */
actual val Showtime.mpStartTime: com.yandex.mapkit.kmp.Time
    get() = startTime
/**
 * Ticket price.
 *
 */
actual val Showtime.mpPrice: com.yandex.mapkit.kmp.Money?
    get() = price
/**
 * Ticket id.
 *
 */
actual val Showtime.mpTicketId: String?
    get() = ticketId

actual object ShowtimeFactory {
    actual fun create(
        startTime: com.yandex.mapkit.kmp.Time,
        price: com.yandex.mapkit.kmp.Money?,
        ticketId: String?,
    ): Showtime {
        return Showtime(
            startTime,
            price,
            ticketId,
        )
    }
}

/**
 * Event schedule snippet.
 */
actual typealias ShowtimesObjectMetadata = com.yandex.mapkit.search.ShowtimesObjectMetadata

/**
 * Event title.
 */
actual val ShowtimesObjectMetadata.mpTitle: String
    get() = title
/**
 * List of showtimes.
 */
actual val ShowtimesObjectMetadata.mpShowtimes: kotlin.collections.List<com.yandex.mapkit.search.kmp.Showtime>
    get() = showtimes

actual object ShowtimesObjectMetadataFactory {
    actual fun create(
        title: String,
        showtimes: kotlin.collections.List<com.yandex.mapkit.search.kmp.Showtime>,
    ): ShowtimesObjectMetadata {
        return ShowtimesObjectMetadata(
            title,
            showtimes,
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.showtimesObjectMetadata: ShowtimesObjectMetadata?
    get() = getItem(ShowtimesObjectMetadata::class.java)

