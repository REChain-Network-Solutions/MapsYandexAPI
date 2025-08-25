@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST", "REDUNDANT_ELSE_IN_WHEN")

package com.yandex.mapkit.search.kmp

/**
 * Phone type
 */
actual enum class PhoneType {
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
    PHONE_FAX,;

    companion object {
        fun toKmp(v: platform.Foundation.NSNumber): PhoneType {
            return toKmp(platform.YandexMapsMobile.YMKSearchPhoneType.byValue(v.unsignedLongValue))
        }

        fun toKmp(v: platform.YandexMapsMobile.YMKSearchPhoneType): PhoneType {
            return when (v) {
                platform.YandexMapsMobile.YMKSearchPhoneType.YMKSearchPhoneTypePhone -> PhoneType.PHONE
                platform.YandexMapsMobile.YMKSearchPhoneType.YMKSearchPhoneTypeFax -> PhoneType.FAX
                platform.YandexMapsMobile.YMKSearchPhoneType.YMKSearchPhoneTypePhoneFax -> PhoneType.PHONE_FAX
                else -> error("unknown YMKSearchPhoneType")
            }
        }
    }
}

fun PhoneType.fromKmp(): platform.YandexMapsMobile.YMKSearchPhoneType {
    return when (this) {
        PhoneType.PHONE -> platform.YandexMapsMobile.YMKSearchPhoneType.YMKSearchPhoneTypePhone
        PhoneType.FAX -> platform.YandexMapsMobile.YMKSearchPhoneType.YMKSearchPhoneTypeFax
        PhoneType.PHONE_FAX -> platform.YandexMapsMobile.YMKSearchPhoneType.YMKSearchPhoneTypePhoneFax
    }
}

/**
 * Organization phone.
 */
actual typealias Phone = platform.YandexMapsMobile.YMKSearchPhone

/**
 * Phone type.
 */
actual val Phone.mpType: com.yandex.mapkit.search.kmp.PhoneType
    get() = type().let { com.yandex.mapkit.search.kmp.PhoneType.toKmp(it) }
/**
 * Full phone number as human readable string.
 */
actual val Phone.mpFormattedNumber: String
    get() = formattedNumber()
/**
 * Some additional info to differentiate multiple phones for single
 * organization.
 *
 */
actual val Phone.mpInfo: String?
    get() = info()
/**
 * Phone country code.
 *
 */
actual val Phone.mpCountry: String?
    get() = country()
/**
 * Phone prefix.
 *
 */
actual val Phone.mpPrefix: String?
    get() = prefix()
/**
 * Phone extension.
 *
 */
actual val Phone.mpExt: String?
    get() = ext()
/**
 * Phone number.
 *
 */
actual val Phone.mpNumber: String?
    get() = number()

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
        return Phone.phoneWithType(
            type.fromKmp(),
            formattedNumber,
            info,
            country,
            prefix,
            ext,
            number,
        )
    }
}

