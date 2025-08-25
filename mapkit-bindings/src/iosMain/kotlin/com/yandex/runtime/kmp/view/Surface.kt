@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.runtime.kmp.view

import com.yandex.runtime.kmp.fromKmp
import com.yandex.runtime.kmp.fromKmpOptional
import com.yandex.runtime.kmp.toKmp

/**
 * Here is android only implementation
 */
actual interface Surface {
    val impl: platform.YandexMapsMobile.YRTSurface
}
/**
 * Anchor shifts the region of the frame that will be rendered on the
 * surface. Anchor coordinates are within [0; 1] bounds. Position of
 * frame’s focusPoint will match specified anchorPoint in the surface:
 * - (0, 0) - left bottom corner of the surface; - (1, 1) - right top
 * corner of the surface; Default value is (0.5, 0.5)
 */
actual var Surface.anchorPoint: com.yandex.runtime.kmp.NativePoint
    get() = impl.anchorPoint.toKmp()
    set(value) {
        impl.anchorPoint = value.fromKmp()
    }

open class YRTSurfaceWrapper(override val impl: platform.YandexMapsMobile.YRTSurface) : Surface

