@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Contains structured address, formatted address, postal code and
 * country code.
 *
 * Structured address is an ordered list of components (usually
 * administrative hierarchy). Formatted address is a full address as a
 * single string. Formatted address generally can't be obtained from
 * component names. Some components could be ignored (large
 * administrative region) or absent (office number).
 */
actual typealias Address = com.yandex.mapkit.search.Address

/**
 * Human-readable address.
 */
actual val Address.mpFormattedAddress: String
    get() = formattedAddress
/**
 * Additional address info.
 *
 */
actual val Address.mpAdditionalInfo: String?
    get() = additionalInfo
/**
 * Postal/Zip code.
 *
 */
actual val Address.mpPostalCode: String?
    get() = postalCode
/**
 * Country code in ISO 3166-1 alpha-2 format (two-letter country code).
 *
 */
actual val Address.mpCountryCode: String?
    get() = countryCode
/**
 * Address component list, see [AddressComponent], may be empty.
 */
actual val Address.mpComponents: kotlin.collections.List<com.yandex.mapkit.search.kmp.AddressComponent>
    get() = components

actual object AddressFactory {
    actual fun create(
        formattedAddress: String,
        additionalInfo: String?,
        postalCode: String?,
        countryCode: String?,
        components: kotlin.collections.List<com.yandex.mapkit.search.kmp.AddressComponent>,
    ): Address {
        return Address(
            formattedAddress,
            additionalInfo,
            postalCode,
            countryCode,
            components,
        )
    }
}

/**
 * Single address component.
 *
 * Component represents a single entry in the administrative hierarchy
 * of the address.
 */
actual typealias AddressComponent = com.yandex.mapkit.search.Address.Component

/**
 * Component name.
 */
actual val AddressComponent.mpName: String
    get() = name
/**
 * Component kinds. May contain both general and specific kind, for
 * example [AddressComponentKind.STATION] and
 * [AddressComponentKind.METRO_STATION].
 */
actual val AddressComponent.mpKinds: kotlin.collections.List<com.yandex.mapkit.search.kmp.AddressComponentKind>
    get() = kinds

actual object AddressComponentFactory {
    actual fun create(
        name: String,
        kinds: kotlin.collections.List<com.yandex.mapkit.search.kmp.AddressComponentKind>,
    ): AddressComponent {
        return AddressComponent(
            name,
            kinds,
        )
    }
}

/**
 * An address component kind, for example, large administrative area.
 */
actual typealias AddressComponentKind = com.yandex.mapkit.search.Address.Component.Kind

