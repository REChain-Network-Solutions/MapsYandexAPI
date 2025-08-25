@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

/**
 * Additional data for toponym objects.
 */
actual typealias ToponymObjectMetadata = platform.YandexMapsMobile.YMKSearchToponymObjectMetadata

/**
 * Structured toponym address
 */
actual val ToponymObjectMetadata.mpAddress: com.yandex.mapkit.search.kmp.Address
    get() = address()
/**
 * Toponym precision.
 *
 */
actual val ToponymObjectMetadata.mpPrecision: com.yandex.mapkit.search.kmp.Precision?
    get() = precision()?.let { com.yandex.mapkit.search.kmp.Precision.toKmp(it) }
/**
 * Former name for toponym if any.
 *
 */
actual val ToponymObjectMetadata.mpFormerName: String?
    get() = formerName()
/**
 * Point where balloon for the toponym should be shown. Differs for
 * direct and reverse search modes: Direct mode -- toponym center.
 * Reverse mode -- toponym nearest point to the given coordinates.
 */
actual val ToponymObjectMetadata.mpBalloonPoint: com.yandex.mapkit.kmp.geometry.Point
    get() = balloonPoint()
/**
 * Persistent toponym id (available for Yandex-owned regions).
 *
 */
actual val ToponymObjectMetadata.mpId: String?
    get() = id()

actual object ToponymObjectMetadataFactory {
    actual fun create(
        address: com.yandex.mapkit.search.kmp.Address,
        precision: com.yandex.mapkit.search.kmp.Precision?,
        formerName: String?,
        balloonPoint: com.yandex.mapkit.kmp.geometry.Point,
        id: String?,
    ): ToponymObjectMetadata {
        return ToponymObjectMetadata.toponymObjectMetadataWithAddress(
            address,
            precision?.let { platform.Foundation.NSNumber(unsignedLong = it.fromKmp().value) },
            formerName,
            balloonPoint,
            id,
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.toponymObjectMetadata: ToponymObjectMetadata?
    get() = impl.getItemOfClass(ToponymObjectMetadata) as? ToponymObjectMetadata

