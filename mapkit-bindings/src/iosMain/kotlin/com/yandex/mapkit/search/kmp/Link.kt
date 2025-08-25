@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

/**
 * Link structure. Combines actual link with attribution and type info.
 */
actual typealias Link = platform.YandexMapsMobile.YMKSearchLink

/**
 * Attribution reference.
 *
 */
actual val Link.mpAref: String?
    get() = aref()
/**
 * Actual link.
 */
actual val Link.mpLink: com.yandex.mapkit.kmp.AttributionLink
    get() = link()
/**
 * Link tag. Possible values (non-exhaustive)
 * 'self/social/attribution/showtimes/booking'.
 *
 */
actual val Link.mpTag: String?
    get() = tag()

actual object LinkFactory {
    actual fun create(
        aref: String?,
        link: com.yandex.mapkit.kmp.AttributionLink,
        tag: String?,
    ): Link {
        return Link.linkWithAref(
            aref,
            link,
            tag,
        )
    }
}

