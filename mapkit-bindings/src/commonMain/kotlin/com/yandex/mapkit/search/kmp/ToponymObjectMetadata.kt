@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Additional data for toponym objects.
 */
expect class ToponymObjectMetadata

/**
 * Structured toponym address
 */
expect val ToponymObjectMetadata.mpAddress: com.yandex.mapkit.search.kmp.Address
/**
 * Toponym precision.
 *
 */
expect val ToponymObjectMetadata.mpPrecision: com.yandex.mapkit.search.kmp.Precision?
/**
 * Former name for toponym if any.
 *
 */
expect val ToponymObjectMetadata.mpFormerName: String?
/**
 * Point where balloon for the toponym should be shown. Differs for
 * direct and reverse search modes: Direct mode -- toponym center.
 * Reverse mode -- toponym nearest point to the given coordinates.
 */
expect val ToponymObjectMetadata.mpBalloonPoint: com.yandex.mapkit.kmp.geometry.Point
/**
 * Persistent toponym id (available for Yandex-owned regions).
 *
 */
expect val ToponymObjectMetadata.mpId: String?

expect object ToponymObjectMetadataFactory {
    fun create(
        address: com.yandex.mapkit.search.kmp.Address,
        precision: com.yandex.mapkit.search.kmp.Precision?,
        formerName: String?,
        balloonPoint: com.yandex.mapkit.kmp.geometry.Point,
        id: String?,
    ): ToponymObjectMetadata
}

expect val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.toponymObjectMetadata: ToponymObjectMetadata?

