@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp

/**
 * Describes data attached to features in user layer.
 */
actual typealias UserData = platform.YandexMapsMobile.YMKUserData

/**
 * A dictionary of data.
 */
actual val UserData.mpData: kotlin.collections.Map<String, String>
    get() = data().let { it as kotlin.collections.Map<String, String> }

actual object UserDataFactory {
    actual fun create(
        data: kotlin.collections.Map<String, String>,
    ): UserData {
        return UserData.userDataWithData(
            data.let { it as kotlin.collections.Map<Any?, *> },
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.userData: UserData?
    get() = impl.getItemOfClass(UserData) as? UserData

