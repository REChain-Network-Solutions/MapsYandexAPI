@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Link structure. Combines actual link with attribution and type info.
 */
expect class Link

/**
 * Attribution reference.
 *
 */
expect val Link.mpAref: String?
/**
 * Actual link.
 */
expect val Link.mpLink: com.yandex.mapkit.kmp.AttributionLink
/**
 * Link tag. Possible values (non-exhaustive)
 * 'self/social/attribution/showtimes/booking'.
 *
 */
expect val Link.mpTag: String?

expect object LinkFactory {
    fun create(
        aref: String?,
        link: com.yandex.mapkit.kmp.AttributionLink,
        tag: String?,
    ): Link
}

