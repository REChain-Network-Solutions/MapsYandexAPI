@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.images

expect interface ImageUrlProvider {
    /**
     * Generates an URL that is used to load described image.
     *
     * This method may be called on any thread. Its implementation must be thread-safe.
     */
    fun formatUrl(
        descriptor: com.yandex.mapkit.kmp.images.ImageDataDescriptor,
    ): String
}

