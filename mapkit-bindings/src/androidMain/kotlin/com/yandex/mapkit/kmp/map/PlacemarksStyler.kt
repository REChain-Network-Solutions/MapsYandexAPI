@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * Represents a styler for all placemarks in the map object collection,
 * including placemarks in child collections. When a new placemark is
 * added to a collection, the placemark tries to get style properties
 * from the collection where it has been added. If this collection
 * doesn't have a style property, the placemark tries to get it from the
 * closest parent collection. If none of these collections have a
 * specific style property, the placemark sets the default value for
 * this property.
 */
actual typealias PlacemarksStyler = com.yandex.mapkit.map.PlacemarksStyler

