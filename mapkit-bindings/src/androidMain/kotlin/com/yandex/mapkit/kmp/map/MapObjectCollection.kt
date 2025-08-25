@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * A collection of map objects that can hold any set of MapObject items,
 * including nested collections.
 */
actual typealias MapObjectCollection = com.yandex.mapkit.map.MapObjectCollection

actual typealias RootMapObjectCollection = com.yandex.mapkit.map.RootMapObjectCollection

actual var RootMapObjectCollection.conflictResolutionMode: com.yandex.mapkit.kmp.ConflictResolutionMode
    get() = conflictResolutionMode
    set(value) {
        conflictResolutionMode = value
    }

