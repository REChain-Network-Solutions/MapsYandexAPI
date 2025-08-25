package com.yandex.runtime.kmp.image

import platform.YandexMapsMobile.YRTAnimatedImageProviderProtocol

actual abstract class AnimatedImageProvider(open val impl: YRTAnimatedImageProviderProtocol)

actual fun AnimatedImageProvider.id(): String {
    return impl.imageId()!!
}

class AnimatedImageProviderWrapper(override val impl: YRTAnimatedImageProviderProtocol) : AnimatedImageProvider(impl)
