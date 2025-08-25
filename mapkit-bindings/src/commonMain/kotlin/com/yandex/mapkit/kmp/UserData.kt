@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

/**
 * Describes data attached to features in user layer.
 */
expect class UserData

/**
 * A dictionary of data.
 */
expect val UserData.mpData: kotlin.collections.Map<String, String>

expect object UserDataFactory {
    fun create(
        data: kotlin.collections.Map<String, String>,
    ): UserData
}

expect val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.userData: UserData?

