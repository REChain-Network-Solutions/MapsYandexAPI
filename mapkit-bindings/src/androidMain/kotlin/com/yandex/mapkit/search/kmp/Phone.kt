@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Phone type
 */
actual typealias PhoneType = com.yandex.mapkit.search.PhoneType

/**
 * Organization phone.
 */
actual typealias Phone = com.yandex.mapkit.search.Phone

/**
 * Phone type.
 */
actual val Phone.mpType: com.yandex.mapkit.search.kmp.PhoneType
    get() = type
/**
 * Full phone number as human readable string.
 */
actual val Phone.mpFormattedNumber: String
    get() = formattedNumber
/**
 * Some additional info to differentiate multiple phones for single
 * organization.
 *
 */
actual val Phone.mpInfo: String?
    get() = info
/**
 * Phone country code.
 *
 */
actual val Phone.mpCountry: String?
    get() = country
/**
 * Phone prefix.
 *
 */
actual val Phone.mpPrefix: String?
    get() = prefix
/**
 * Phone extension.
 *
 */
actual val Phone.mpExt: String?
    get() = ext
/**
 * Phone number.
 *
 */
actual val Phone.mpNumber: String?
    get() = number

actual object PhoneFactory {
    actual fun create(
        type: com.yandex.mapkit.search.kmp.PhoneType,
        formattedNumber: String,
        info: String?,
        country: String?,
        prefix: String?,
        ext: String?,
        number: String?,
    ): Phone {
        return Phone(
            type,
            formattedNumber,
            info,
            country,
            prefix,
            ext,
            number,
        )
    }
}

