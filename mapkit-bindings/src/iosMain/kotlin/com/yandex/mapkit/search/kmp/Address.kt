@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST", "REDUNDANT_ELSE_IN_WHEN")

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
actual typealias Address = platform.YandexMapsMobile.YMKSearchAddress

/**
 * Human-readable address.
 */
actual val Address.mpFormattedAddress: String
    get() = formattedAddress()
/**
 * Additional address info.
 *
 */
actual val Address.mpAdditionalInfo: String?
    get() = additionalInfo()
/**
 * Postal/Zip code.
 *
 */
actual val Address.mpPostalCode: String?
    get() = postalCode()
/**
 * Country code in ISO 3166-1 alpha-2 format (two-letter country code).
 *
 */
actual val Address.mpCountryCode: String?
    get() = countryCode()
/**
 * Address component list, see [AddressComponent], may be empty.
 */
actual val Address.mpComponents: kotlin.collections.List<com.yandex.mapkit.search.kmp.AddressComponent>
    get() = components().let { it as kotlin.collections.List<platform.YandexMapsMobile.YMKSearchAddressComponent> }

actual object AddressFactory {
    actual fun create(
        formattedAddress: String,
        additionalInfo: String?,
        postalCode: String?,
        countryCode: String?,
        components: kotlin.collections.List<com.yandex.mapkit.search.kmp.AddressComponent>,
    ): Address {
        return Address.addressWithFormattedAddress(
            formattedAddress,
            additionalInfo,
            postalCode,
            countryCode,
            components.let { it as kotlin.collections.List<*> },
        )
    }
}

/**
 * Single address component.
 *
 * Component represents a single entry in the administrative hierarchy
 * of the address.
 */
actual typealias AddressComponent = platform.YandexMapsMobile.YMKSearchAddressComponent

/**
 * Component name.
 */
actual val AddressComponent.mpName: String
    get() = name()
/**
 * Component kinds. May contain both general and specific kind, for
 * example [AddressComponentKind.STATION] and
 * [AddressComponentKind.METRO_STATION].
 */
actual val AddressComponent.mpKinds: kotlin.collections.List<com.yandex.mapkit.search.kmp.AddressComponentKind>
    get() = kinds().let { it as kotlin.collections.List<platform.Foundation.NSNumber> }.map { it.let { com.yandex.mapkit.search.kmp.AddressComponentKind.toKmp(it) } }

actual object AddressComponentFactory {
    actual fun create(
        name: String,
        kinds: kotlin.collections.List<com.yandex.mapkit.search.kmp.AddressComponentKind>,
    ): AddressComponent {
        return AddressComponent.componentWithName(
            name,
            kinds.map { it.let { platform.Foundation.NSNumber(unsignedLong = it.fromKmp().value) } }.let { it as kotlin.collections.List<*> },
        )
    }
}

/**
 * An address component kind, for example, large administrative area.
 */
actual enum class AddressComponentKind {
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
    OTHER,;

    companion object {
        fun toKmp(v: platform.Foundation.NSNumber): AddressComponentKind {
            return toKmp(platform.YandexMapsMobile.YMKSearchComponentKind.byValue(v.unsignedLongValue))
        }

        fun toKmp(v: platform.YandexMapsMobile.YMKSearchComponentKind): AddressComponentKind {
            return when (v) {
                platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindUnknown -> AddressComponentKind.UNKNOWN
                platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindCountry -> AddressComponentKind.COUNTRY
                platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindRegion -> AddressComponentKind.REGION
                platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindProvince -> AddressComponentKind.PROVINCE
                platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindArea -> AddressComponentKind.AREA
                platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindLocality -> AddressComponentKind.LOCALITY
                platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindDistrict -> AddressComponentKind.DISTRICT
                platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindStreet -> AddressComponentKind.STREET
                platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindHouse -> AddressComponentKind.HOUSE
                platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindEntrance -> AddressComponentKind.ENTRANCE
                platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindLevel -> AddressComponentKind.LEVEL
                platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindApartment -> AddressComponentKind.APARTMENT
                platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindRoute -> AddressComponentKind.ROUTE
                platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindStation -> AddressComponentKind.STATION
                platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindMetroStation -> AddressComponentKind.METRO_STATION
                platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindRailwayStation -> AddressComponentKind.RAILWAY_STATION
                platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindVegetation -> AddressComponentKind.VEGETATION
                platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindHydro -> AddressComponentKind.HYDRO
                platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindAirport -> AddressComponentKind.AIRPORT
                platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindOther -> AddressComponentKind.OTHER
                else -> error("unknown YMKSearchComponentKind")
            }
        }
    }
}

fun AddressComponentKind.fromKmp(): platform.YandexMapsMobile.YMKSearchComponentKind {
    return when (this) {
        AddressComponentKind.UNKNOWN -> platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindUnknown
        AddressComponentKind.COUNTRY -> platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindCountry
        AddressComponentKind.REGION -> platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindRegion
        AddressComponentKind.PROVINCE -> platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindProvince
        AddressComponentKind.AREA -> platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindArea
        AddressComponentKind.LOCALITY -> platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindLocality
        AddressComponentKind.DISTRICT -> platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindDistrict
        AddressComponentKind.STREET -> platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindStreet
        AddressComponentKind.HOUSE -> platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindHouse
        AddressComponentKind.ENTRANCE -> platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindEntrance
        AddressComponentKind.LEVEL -> platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindLevel
        AddressComponentKind.APARTMENT -> platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindApartment
        AddressComponentKind.ROUTE -> platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindRoute
        AddressComponentKind.STATION -> platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindStation
        AddressComponentKind.METRO_STATION -> platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindMetroStation
        AddressComponentKind.RAILWAY_STATION -> platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindRailwayStation
        AddressComponentKind.VEGETATION -> platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindVegetation
        AddressComponentKind.HYDRO -> platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindHydro
        AddressComponentKind.AIRPORT -> platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindAirport
        AddressComponentKind.OTHER -> platform.YandexMapsMobile.YMKSearchComponentKind.YMKSearchComponentKindOther
    }
}

