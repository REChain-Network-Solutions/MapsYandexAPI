@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Result display type.
 */
expect enum class DisplayType {
    /**
     * Each result makes sense without the others (that is toponyms), they
     * probably shouldn'be displayed as a group, and probably there's no
     * need to make additional requests after user moves the map or zooms in
     * or out.
     */
    SINGLE,
    /**
     * Results are meaningful as a group (that is category query), they
     * should be displayed all together, and it makes sense to send
     * additional requests after user changes visible map region.
     */
    MULTIPLE,
}

