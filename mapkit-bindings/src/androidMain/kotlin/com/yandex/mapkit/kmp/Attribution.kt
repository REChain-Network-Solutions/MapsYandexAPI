@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

/**
 * Details about the source of information.
 */
actual typealias Attribution = com.yandex.mapkit.Attribution

/**
 * Additional information about the author.
 *
 */
actual val Attribution.mpAuthor: com.yandex.mapkit.kmp.AttributionAuthor?
    get() = author
/**
 * Link to a specific page on the author's site. To link to the website
 * as a whole, use author.uri.
 *
 */
actual val Attribution.mpLink: com.yandex.mapkit.kmp.AttributionLink?
    get() = link
actual val Attribution.mpAvatarImage: com.yandex.mapkit.kmp.Image?
    get() = avatarImage

actual object AttributionFactory {
    actual fun create(
        author: com.yandex.mapkit.kmp.AttributionAuthor?,
        link: com.yandex.mapkit.kmp.AttributionLink?,
        avatarImage: com.yandex.mapkit.kmp.Image?,
    ): Attribution {
        return Attribution(
            author,
            link,
            avatarImage,
        )
    }
}

actual typealias AttributionAuthor = com.yandex.mapkit.Attribution.Author

actual val AttributionAuthor.mpName: String
    get() = name
/**
 * A reference to the author's site.
 *
 */
actual val AttributionAuthor.mpUri: String?
    get() = uri
/**
 * Author's email. Must contain at least one @ symbol.
 *
 */
actual val AttributionAuthor.mpEmail: String?
    get() = email

actual object AttributionAuthorFactory {
    actual fun create(
        name: String,
        uri: String?,
        email: String?,
    ): AttributionAuthor {
        return AttributionAuthor(
            name,
            uri,
            email,
        )
    }
}

actual typealias AttributionLink = com.yandex.mapkit.Attribution.Link

actual val AttributionLink.mpHref: String
    get() = href

actual object AttributionLinkFactory {
    actual fun create(
        href: String,
    ): AttributionLink {
        return AttributionLink(
            href,
        )
    }
}

