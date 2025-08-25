@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

/**
 * Session details.
 */
actual typealias Showtime = platform.YandexMapsMobile.YMKSearchShowtime

/**
 * Session start time.
 */
actual val Showtime.mpStartTime: com.yandex.mapkit.kmp.Time
    get() = startTime()
/**
 * Ticket price.
 *
 */
actual val Showtime.mpPrice: com.yandex.mapkit.kmp.Money?
    get() = price()
/**
 * Ticket id.
 *
 */
actual val Showtime.mpTicketId: String?
    get() = ticketId()

actual object ShowtimeFactory {
    actual fun create(
        startTime: com.yandex.mapkit.kmp.Time,
        price: com.yandex.mapkit.kmp.Money?,
        ticketId: String?,
    ): Showtime {
        return Showtime.showtimeWithStartTime(
            startTime,
            price,
            ticketId,
        )
    }
}

/**
 * Event schedule snippet.
 */
actual typealias ShowtimesObjectMetadata = platform.YandexMapsMobile.YMKSearchShowtimesObjectMetadata

/**
 * Event title.
 */
actual val ShowtimesObjectMetadata.mpTitle: String
    get() = title()
/**
 * List of showtimes.
 */
actual val ShowtimesObjectMetadata.mpShowtimes: kotlin.collections.List<com.yandex.mapkit.search.kmp.Showtime>
    get() = showtimes().let { it as kotlin.collections.List<platform.YandexMapsMobile.YMKSearchShowtime> }

actual object ShowtimesObjectMetadataFactory {
    actual fun create(
        title: String,
        showtimes: kotlin.collections.List<com.yandex.mapkit.search.kmp.Showtime>,
    ): ShowtimesObjectMetadata {
        return ShowtimesObjectMetadata.showtimesObjectMetadataWithTitle(
            title,
            showtimes.let { it as kotlin.collections.List<*> },
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.showtimesObjectMetadata: ShowtimesObjectMetadata?
    get() = impl.getItemOfClass(ShowtimesObjectMetadata) as? ShowtimesObjectMetadata

