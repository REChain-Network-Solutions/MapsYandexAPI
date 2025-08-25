@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

/**
 * Details about the source of information.
 */
expect class Attribution

/**
 * Additional information about the author.
 *
 */
expect val Attribution.mpAuthor: com.yandex.mapkit.kmp.AttributionAuthor?
/**
 * Link to a specific page on the author's site. To link to the website
 * as a whole, use author.uri.
 *
 */
expect val Attribution.mpLink: com.yandex.mapkit.kmp.AttributionLink?
expect val Attribution.mpAvatarImage: com.yandex.mapkit.kmp.Image?

expect object AttributionFactory {
    fun create(
        author: com.yandex.mapkit.kmp.AttributionAuthor?,
        link: com.yandex.mapkit.kmp.AttributionLink?,
        avatarImage: com.yandex.mapkit.kmp.Image?,
    ): Attribution
}

expect class AttributionAuthor

expect val AttributionAuthor.mpName: String
/**
 * A reference to the author's site.
 *
 */
expect val AttributionAuthor.mpUri: String?
/**
 * Author's email. Must contain at least one @ symbol.
 *
 */
expect val AttributionAuthor.mpEmail: String?

expect object AttributionAuthorFactory {
    fun create(
        name: String,
        uri: String?,
        email: String?,
    ): AttributionAuthor
}

expect class AttributionLink

expect val AttributionLink.mpHref: String

expect object AttributionLinkFactory {
    fun create(
        href: String,
    ): AttributionLink
}

