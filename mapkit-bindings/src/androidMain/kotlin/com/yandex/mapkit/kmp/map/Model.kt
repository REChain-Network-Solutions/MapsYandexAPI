@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * describes model presentation of PlacemarkMapObject
 */
actual typealias Model = com.yandex.mapkit.map.Model

/**
 * The style properties (sclae, unitType, etc.) of the model placemark.
 * Note: The current style cannot be modified directly - you must reset
 * it to apply changes.
 */
actual var Model.modelStyle: com.yandex.mapkit.kmp.map.ModelStyle
    get() = modelStyle
    set(value) {
        modelStyle = value
    }

