package com.yandex.runtime.kmp

actual interface TypeDictionary<T> {
    val impl: platform.YandexMapsMobile.YRTTypeDictionary
}

class YRTTypeDictionaryWrapper<T>(override val impl: platform.YandexMapsMobile.YRTTypeDictionary) : TypeDictionary<T>
