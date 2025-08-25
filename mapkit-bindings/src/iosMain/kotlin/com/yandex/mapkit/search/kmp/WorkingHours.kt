@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

import com.yandex.runtime.kmp.internal.toBoolean
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * Describes if an organization is open or closed now.
 */
actual typealias State = platform.YandexMapsMobile.YMKSearchWorkingHoursState

/**
 * Is open right now.
 *
 */
actual val State.mpIsOpenNow: Boolean?
    get() = isOpenNow()?.toBoolean()
/**
 * Human-readable localized description of current state.
 *
 */
actual val State.mpText: String?
    get() = text()
/**
 * Human-readable short localized description of current state.
 *
 */
actual val State.mpShortText: String?
    get() = shortText()
/**
 * One of the following 3 tags: 'break', 'opening_soon', 'closing_soon'.
 * Additional tag values may be added eventually.
 */
actual val State.mpTags: kotlin.collections.List<String>
    get() = tags().let { it as kotlin.collections.List<String> }

actual object StateFactory {
    actual fun create(
        isOpenNow: Boolean?,
        text: String?,
        shortText: String?,
        tags: kotlin.collections.List<String>,
    ): State {
        return State.workingHoursStateWithIsOpenNow(
            isOpenNow?.toNSNumber(),
            text,
            shortText,
            tags.let { it as kotlin.collections.List<*> },
        )
    }
}

/**
 * Open hours for an organization.
 */
actual typealias WorkingHours = platform.YandexMapsMobile.YMKSearchWorkingHours

/**
 * Human-readable localized open hours description.
 */
actual val WorkingHours.mpText: String
    get() = text()
/**
 * Structured open hours information.
 */
actual val WorkingHours.mpAvailabilities: kotlin.collections.List<com.yandex.mapkit.search.kmp.Availability>
    get() = availabilities().let { it as kotlin.collections.List<platform.YandexMapsMobile.YMKSearchAvailability> }
/**
 * Current company working status
 *
 */
actual val WorkingHours.mpState: com.yandex.mapkit.search.kmp.State?
    get() = state()

actual object WorkingHoursFactory {
    actual fun create(
        text: String,
        availabilities: kotlin.collections.List<com.yandex.mapkit.search.kmp.Availability>,
        state: com.yandex.mapkit.search.kmp.State?,
    ): WorkingHours {
        return WorkingHours.workingHoursWithText(
            text,
            availabilities.let { it as kotlin.collections.List<*> },
            state,
        )
    }
}

