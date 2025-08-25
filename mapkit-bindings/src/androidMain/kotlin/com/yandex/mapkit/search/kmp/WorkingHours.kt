@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Describes if an organization is open or closed now.
 */
actual typealias State = com.yandex.mapkit.search.State

/**
 * Is open right now.
 *
 */
actual val State.mpIsOpenNow: Boolean?
    get() = isOpenNow
/**
 * Human-readable localized description of current state.
 *
 */
actual val State.mpText: String?
    get() = text
/**
 * Human-readable short localized description of current state.
 *
 */
actual val State.mpShortText: String?
    get() = shortText
/**
 * One of the following 3 tags: 'break', 'opening_soon', 'closing_soon'.
 * Additional tag values may be added eventually.
 */
actual val State.mpTags: kotlin.collections.List<String>
    get() = tags

actual object StateFactory {
    actual fun create(
        isOpenNow: Boolean?,
        text: String?,
        shortText: String?,
        tags: kotlin.collections.List<String>,
    ): State {
        return State(
            isOpenNow,
            text,
            shortText,
            tags,
        )
    }
}

/**
 * Open hours for an organization.
 */
actual typealias WorkingHours = com.yandex.mapkit.search.WorkingHours

/**
 * Human-readable localized open hours description.
 */
actual val WorkingHours.mpText: String
    get() = text
/**
 * Structured open hours information.
 */
actual val WorkingHours.mpAvailabilities: kotlin.collections.List<com.yandex.mapkit.search.kmp.Availability>
    get() = availabilities
/**
 * Current company working status
 *
 */
actual val WorkingHours.mpState: com.yandex.mapkit.search.kmp.State?
    get() = state

actual object WorkingHoursFactory {
    actual fun create(
        text: String,
        availabilities: kotlin.collections.List<com.yandex.mapkit.search.kmp.Availability>,
        state: com.yandex.mapkit.search.kmp.State?,
    ): WorkingHours {
        return WorkingHours(
            text,
            availabilities,
            state,
        )
    }
}

