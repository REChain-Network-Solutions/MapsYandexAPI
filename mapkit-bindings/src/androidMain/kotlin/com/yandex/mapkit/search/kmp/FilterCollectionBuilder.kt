@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Builder for filter collection.
 */
actual typealias FilterCollectionBuilder = com.yandex.mapkit.search.FilterCollectionBuilder

actual object FilterCollectionUtils {
    /**
     * Creates new [FilterCollectionBuilder].
     * @return builder for [FilterCollection].
     */
    actual fun createFilterCollectionBuilder(): com.yandex.mapkit.search.kmp.FilterCollectionBuilder {
        return com.yandex.mapkit.search.FilterCollectionUtils.createFilterCollectionBuilder()
    }
}

