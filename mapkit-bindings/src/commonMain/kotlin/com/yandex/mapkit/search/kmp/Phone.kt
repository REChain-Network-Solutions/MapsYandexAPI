@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Phone type
 */
expect enum class PhoneType {
    /**
     * Phone.
     */
    PHONE,
    /**
     * Fax.
     */
    FAX,
    /**
     * Both phone and fax.
     */
    PHONE_FAX,
}

/**
 * Organization phone.
 */
expect class Phone

/**
 * Phone type.
 */
expect val Phone.mpType: com.yandex.mapkit.search.kmp.PhoneType
/**
 * Full phone number as human readable string.
 */
expect val Phone.mpFormattedNumber: String
/**
 * Some additional info to differentiate multiple phones for single
 * organization.
 *
 */
expect val Phone.mpInfo: String?
/**
 * Phone country code.
 *
 */
expect val Phone.mpCountry: String?
/**
 * Phone prefix.
 *
 */
expect val Phone.mpPrefix: String?
/**
 * Phone extension.
 *
 */
expect val Phone.mpExt: String?
/**
 * Phone number.
 *
 */
expect val Phone.mpNumber: String?

expect object PhoneFactory {
    fun create(
        type: com.yandex.mapkit.search.kmp.PhoneType,
        formattedNumber: String,
        info: String?,
        country: String?,
        prefix: String?,
        ext: String?,
        number: String?,
    ): Phone
}

