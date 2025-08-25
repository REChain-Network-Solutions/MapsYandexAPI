@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Describes if an organization is open or closed now.
 */
expect class State

/**
 * Is open right now.
 *
 */
expect val State.mpIsOpenNow: Boolean?
/**
 * Human-readable localized description of current state.
 *
 */
expect val State.mpText: String?
/**
 * Human-readable short localized description of current state.
 *
 */
expect val State.mpShortText: String?
/**
 * One of the following 3 tags: 'break', 'opening_soon', 'closing_soon'.
 * Additional tag values may be added eventually.
 */
expect val State.mpTags: kotlin.collections.List<String>

expect object StateFactory {
    fun create(
        isOpenNow: Boolean?,
        text: String?,
        shortText: String?,
        tags: kotlin.collections.List<String>,
    ): State
}

/**
 * Open hours for an organization.
 */
expect class WorkingHours

/**
 * Human-readable localized open hours description.
 */
expect val WorkingHours.mpText: String
/**
 * Structured open hours information.
 */
expect val WorkingHours.mpAvailabilities: kotlin.collections.List<com.yandex.mapkit.search.kmp.Availability>
/**
 * Current company working status
 *
 */
expect val WorkingHours.mpState: com.yandex.mapkit.search.kmp.State?

expect object WorkingHoursFactory {
    fun create(
        text: String,
        availabilities: kotlin.collections.List<com.yandex.mapkit.search.kmp.Availability>,
        state: com.yandex.mapkit.search.kmp.State?,
    ): WorkingHours
}

