@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST", "REDUNDANT_ELSE_IN_WHEN")

package com.yandex.mapkit.kmp

/**
 * Determines how feature participates in conflict resolution.
 */
actual enum class ConflictResolutionMode {
    /**
     * Feature can be hidden (if supported) due to conflict with higher
     * feature but will not conflict with lower features.
     */
    MINOR,
    /**
     * Feature can be hidden (if supported) due to conflict with higher
     * feature and can displace lower features.
     */
    EQUAL,
    /**
     * Feature can NOT be hidden but can displace lower features.
     */
    MAJOR,
    /**
     * Feature doesn't participate in conflict resolution.
     */
    IGNORE,;

    companion object {
        fun toKmp(v: platform.Foundation.NSNumber): ConflictResolutionMode {
            return toKmp(platform.YandexMapsMobile.YMKConflictResolutionMode.byValue(v.unsignedLongValue))
        }

        fun toKmp(v: platform.YandexMapsMobile.YMKConflictResolutionMode): ConflictResolutionMode {
            return when (v) {
                platform.YandexMapsMobile.YMKConflictResolutionMode.YMKConflictResolutionModeMinor -> ConflictResolutionMode.MINOR
                platform.YandexMapsMobile.YMKConflictResolutionMode.YMKConflictResolutionModeEqual -> ConflictResolutionMode.EQUAL
                platform.YandexMapsMobile.YMKConflictResolutionMode.YMKConflictResolutionModeMajor -> ConflictResolutionMode.MAJOR
                platform.YandexMapsMobile.YMKConflictResolutionMode.YMKConflictResolutionModeIgnore -> ConflictResolutionMode.IGNORE
                else -> error("unknown YMKConflictResolutionMode")
            }
        }
    }
}

fun ConflictResolutionMode.fromKmp(): platform.YandexMapsMobile.YMKConflictResolutionMode {
    return when (this) {
        ConflictResolutionMode.MINOR -> platform.YandexMapsMobile.YMKConflictResolutionMode.YMKConflictResolutionModeMinor
        ConflictResolutionMode.EQUAL -> platform.YandexMapsMobile.YMKConflictResolutionMode.YMKConflictResolutionModeEqual
        ConflictResolutionMode.MAJOR -> platform.YandexMapsMobile.YMKConflictResolutionMode.YMKConflictResolutionModeMajor
        ConflictResolutionMode.IGNORE -> platform.YandexMapsMobile.YMKConflictResolutionMode.YMKConflictResolutionModeIgnore
    }
}

