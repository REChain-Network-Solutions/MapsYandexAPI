@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST", "REDUNDANT_ELSE_IN_WHEN")

package com.yandex.runtime.kmp.view

/**
 * @hidden
 * Graphics API used by View
 */
actual enum class GraphicsAPIType {
    OPEN_GL,
    VULKAN,;

    companion object {
        fun toKmp(v: platform.Foundation.NSNumber): GraphicsAPIType {
            return toKmp(platform.YandexMapsMobile.YRTGraphicsAPIType.byValue(v.unsignedLongValue))
        }

        fun toKmp(v: platform.YandexMapsMobile.YRTGraphicsAPIType): GraphicsAPIType {
            return when (v) {
                platform.YandexMapsMobile.YRTGraphicsAPIType.YRTGraphicsAPITypeOpenGl -> GraphicsAPIType.OPEN_GL
                platform.YandexMapsMobile.YRTGraphicsAPIType.YRTGraphicsAPITypeVulkan -> GraphicsAPIType.VULKAN
                else -> error("unknown YRTGraphicsAPIType")
            }
        }
    }
}

fun GraphicsAPIType.fromKmp(): platform.YandexMapsMobile.YRTGraphicsAPIType {
    return when (this) {
        GraphicsAPIType.OPEN_GL -> platform.YandexMapsMobile.YRTGraphicsAPIType.YRTGraphicsAPITypeOpenGl
        GraphicsAPIType.VULKAN -> platform.YandexMapsMobile.YRTGraphicsAPIType.YRTGraphicsAPITypeVulkan
    }
}

