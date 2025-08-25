@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.layers

actual typealias DataSourceLayer = com.yandex.mapkit.layers.DataSourceLayer

/**
 * Manages visibility of the layer.
 */
actual var DataSourceLayer.active: Boolean
    get() = isActive
    set(value) {
        isActive = value
    }

