@file:Suppress("UNCHECKED_CAST")

package com.yandex.runtime.kmp.internal

import com.yandex.runtime.kmp.Error
import com.yandex.runtime.kmp.YRTErrorWrapper
import platform.Foundation.NSError
import platform.YandexMapsMobile.YRTError
import platform.YandexMapsMobile.YRTUnderlyingErrorKey

fun NSError.toKmp(): Error {
    return YRTErrorWrapper(userInfo[YRTUnderlyingErrorKey] as YRTError)
}

fun Error.fromKmp(): NSError {
    // https://a.yandex-team.ru/arcadia/maps/mobile/libs/runtime/common/include/yandex/maps/runtime/ios/make_error.h?rev=r11994723#L11
    val userInfo = mapOf(YRTUnderlyingErrorKey to impl)
    return NSError.errorWithDomain("com.yandex.YandexMapsMobile", 0, userInfo as Map<Any?, *>?)
}
