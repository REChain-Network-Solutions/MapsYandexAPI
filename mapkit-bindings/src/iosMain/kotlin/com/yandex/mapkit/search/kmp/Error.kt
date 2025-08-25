@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

import com.yandex.runtime.kmp.internal.fromKmp
import com.yandex.runtime.kmp.internal.toKmp

/**
 * No cache available for offline search for the given request.
 */
actual interface CacheUnavailableError : com.yandex.runtime.kmp.Error {
    override val impl: platform.YandexMapsMobile.YMKSearchCacheUnavailableError
}

open class YMKSearchCacheUnavailableErrorWrapper(override val impl: platform.YandexMapsMobile.YMKSearchCacheUnavailableError) : CacheUnavailableError, com.yandex.runtime.kmp.YRTErrorWrapper(impl)

