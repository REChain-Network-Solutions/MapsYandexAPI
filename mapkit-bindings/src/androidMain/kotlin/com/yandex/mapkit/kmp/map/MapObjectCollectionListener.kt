@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * The map object collection can't be modified in callbacks. A
 * runtime::RuntimeError exception is thrown if this happens.
 */
actual typealias MapObjectCollectionListener = com.yandex.mapkit.map.MapObjectCollectionListener

