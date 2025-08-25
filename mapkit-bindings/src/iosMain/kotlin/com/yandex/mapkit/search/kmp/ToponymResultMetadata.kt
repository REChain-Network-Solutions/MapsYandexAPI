@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST", "REDUNDANT_ELSE_IN_WHEN")

package com.yandex.mapkit.search.kmp

import com.yandex.runtime.kmp.internal.toDouble
import com.yandex.runtime.kmp.internal.toInt
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * Common info for response from toponym search.
 */
actual typealias ToponymResultMetadata = platform.YandexMapsMobile.YMKSearchToponymResultMetadata

/**
 * Approximate number of found objects.
 */
actual val ToponymResultMetadata.mpFound: Int
    get() = found().toInt()
/**
 * Additional response info.
 *
 */
actual val ToponymResultMetadata.mpResponseInfo: com.yandex.mapkit.search.kmp.ToponymResultMetadataResponseInfo?
    get() = responseInfo()
/**
 * The search coordinates given via 'll' or parsed from 'text' (only in
 * reverse mode).
 *
 */
actual val ToponymResultMetadata.mpReversePoint: com.yandex.mapkit.kmp.geometry.Point?
    get() = reversePoint()

actual object ToponymResultMetadataFactory {
    actual fun create(
        found: Int,
        responseInfo: com.yandex.mapkit.search.kmp.ToponymResultMetadataResponseInfo?,
        reversePoint: com.yandex.mapkit.kmp.geometry.Point?,
    ): ToponymResultMetadata {
        return ToponymResultMetadata.toponymResultMetadataWithFound(
            found.toLong(),
            responseInfo,
            reversePoint,
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.toponymResultMetadata: ToponymResultMetadata?
    get() = impl.getItemOfClass(ToponymResultMetadata) as? ToponymResultMetadata

/**
 * Search mode.
 */
actual enum class ToponymResultMetadataSearchMode {
    /**
     * Search from text to toponym.
     */
    GEOCODE,
    /**
     * Search from coordinates to toponym.
     */
    REVERSE,;

    companion object {
        fun toKmp(v: platform.Foundation.NSNumber): ToponymResultMetadataSearchMode {
            return toKmp(platform.YandexMapsMobile.YMKSearchToponymResultMetadataSearchMode.byValue(v.unsignedLongValue))
        }

        fun toKmp(v: platform.YandexMapsMobile.YMKSearchToponymResultMetadataSearchMode): ToponymResultMetadataSearchMode {
            return when (v) {
                platform.YandexMapsMobile.YMKSearchToponymResultMetadataSearchMode.YMKSearchToponymResultMetadataSearchModeGeocode -> ToponymResultMetadataSearchMode.GEOCODE
                platform.YandexMapsMobile.YMKSearchToponymResultMetadataSearchMode.YMKSearchToponymResultMetadataSearchModeReverse -> ToponymResultMetadataSearchMode.REVERSE
                else -> error("unknown YMKSearchToponymResultMetadataSearchMode")
            }
        }
    }
}

fun ToponymResultMetadataSearchMode.fromKmp(): platform.YandexMapsMobile.YMKSearchToponymResultMetadataSearchMode {
    return when (this) {
        ToponymResultMetadataSearchMode.GEOCODE -> platform.YandexMapsMobile.YMKSearchToponymResultMetadataSearchMode.YMKSearchToponymResultMetadataSearchModeGeocode
        ToponymResultMetadataSearchMode.REVERSE -> platform.YandexMapsMobile.YMKSearchToponymResultMetadataSearchMode.YMKSearchToponymResultMetadataSearchModeReverse
    }
}

/**
 * Additional response info.
 */
actual typealias ToponymResultMetadataResponseInfo = platform.YandexMapsMobile.YMKSearchToponymResultMetadataResponseInfo

/**
 * Search mode.
 */
actual val ToponymResultMetadataResponseInfo.mpMode: com.yandex.mapkit.search.kmp.ToponymResultMetadataSearchMode
    get() = mode().let { com.yandex.mapkit.search.kmp.ToponymResultMetadataSearchMode.toKmp(it) }
/**
 * Search response accuracy.
 *
 */
actual val ToponymResultMetadataResponseInfo.mpAccuracy: Double?
    get() = accuracy()?.toDouble()

actual object ToponymResultMetadataResponseInfoFactory {
    actual fun create(
        mode: com.yandex.mapkit.search.kmp.ToponymResultMetadataSearchMode,
        accuracy: Double?,
    ): ToponymResultMetadataResponseInfo {
        return ToponymResultMetadataResponseInfo.responseInfoWithMode(
            mode.fromKmp(),
            accuracy?.toNSNumber(),
        )
    }
}

