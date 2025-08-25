@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

/**
 * Describes data attached to features in user layer.
 */
actual typealias UserData = com.yandex.mapkit.UserData

/**
 * A dictionary of data.
 */
actual val UserData.mpData: kotlin.collections.Map<String, String>
    get() = data

actual object UserDataFactory {
    actual fun create(
        data: kotlin.collections.Map<String, String>,
    ): UserData {
        return UserData(
            data,
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.userData: UserData?
    get() = getItem(UserData::class.java)

