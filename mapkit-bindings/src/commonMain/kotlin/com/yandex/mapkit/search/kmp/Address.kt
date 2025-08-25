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
expect class Address

/**
 * Human-readable address.
 */
expect val Address.mpFormattedAddress: String
/**
 * Additional address info.
 *
 */
expect val Address.mpAdditionalInfo: String?
/**
 * Postal/Zip code.
 *
 */
expect val Address.mpPostalCode: String?
/**
 * Country code in ISO 3166-1 alpha-2 format (two-letter country code).
 *
 */
expect val Address.mpCountryCode: String?
/**
 * Address component list, see [AddressComponent], may be empty.
 */
expect val Address.mpComponents: kotlin.collections.List<com.yandex.mapkit.search.kmp.AddressComponent>

expect object AddressFactory {
    fun create(
        formattedAddress: String,
        additionalInfo: String?,
        postalCode: String?,
        countryCode: String?,
        components: kotlin.collections.List<com.yandex.mapkit.search.kmp.AddressComponent>,
    ): Address
}

/**
 * Single address component.
 *
 * Component represents a single entry in the administrative hierarchy
 * of the address.
 */
expect class AddressComponent

/**
 * Component name.
 */
expect val AddressComponent.mpName: String
/**
 * Component kinds. May contain both general and specific kind, for
 * example [AddressComponentKind.STATION] and
 * [AddressComponentKind.METRO_STATION].
 */
expect val AddressComponent.mpKinds: kotlin.collections.List<com.yandex.mapkit.search.kmp.AddressComponentKind>

expect object AddressComponentFactory {
    fun create(
        name: String,
        kinds: kotlin.collections.List<com.yandex.mapkit.search.kmp.AddressComponentKind>,
    ): AddressComponent
}

/**
 * An address component kind, for example, large administrative area.
 */
expect enum class AddressComponentKind {
    /**
     * Unknown component kind
     */
    UNKNOWN,
    /**
     * Country component.
     */
    COUNTRY,
    /**
     * Region component.
     */
    REGION,
    /**
     * Province component.
     */
    PROVINCE,
    /**
     * Area component.
     */
    AREA,
    /**
     * Locality component.
     */
    LOCALITY,
    /**
     * District component.
     */
    DISTRICT,
    /**
     * Street component.
     */
    STREET,
    /**
     * House component.
     */
    HOUSE,
    /**
     * Entrance component.
     */
    ENTRANCE,
    /**
     * Indoor level component.
     */
    LEVEL,
    /**
     * Apartment component.
     */
    APARTMENT,
    /**
     * Line component.
     */
    ROUTE,
    /**
     * Generic station component.
     */
    STATION,
    /**
     * Metro station component.
     */
    METRO_STATION,
    /**
     * Railway station component.
     */
    RAILWAY_STATION,
    /**
     * Vegetation component.
     */
    VEGETATION,
    /**
     * Hydro component.
     */
    HYDRO,
    /**
     * Airport component.
     */
    AIRPORT,
    /**
     * Kind for other toponyms, for example cemeteries or some other
     * landmarks, which can't be easily described by kinds.
     */
    OTHER,
}

