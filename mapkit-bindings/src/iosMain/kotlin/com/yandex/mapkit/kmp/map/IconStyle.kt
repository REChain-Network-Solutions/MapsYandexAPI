@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

import com.yandex.runtime.kmp.fromKmp
import com.yandex.runtime.kmp.fromKmpOptional
import com.yandex.runtime.kmp.internal.toBoolean
import com.yandex.runtime.kmp.internal.toFloat
import com.yandex.runtime.kmp.internal.toNSNumber
import com.yandex.runtime.kmp.toKmp

/**
 * The style of created icons.
 */
actual typealias IconStyle = platform.YandexMapsMobile.YMKIconStyle

/**
 * An anchor is used to alter image placement. Normalized: (0.0f, 0.0f)
 * denotes the top left image corner; (1.0f, 1.0f) denotes bottom right.
 * Default is (0.5f, 0.5f).
 *
 */
actual var IconStyle.mpAnchor: com.yandex.runtime.kmp.NativePoint?
    get() = anchor()?.toKmp()
    set(value) {
        anchor = value?.fromKmpOptional()
    }
/**
 * Icon rotation type. Default: NoRotation.
 *
 */
actual var IconStyle.mpRotationType: com.yandex.mapkit.kmp.map.RotationType?
    get() = rotationType()?.let { com.yandex.mapkit.kmp.map.RotationType.toKmp(it) }
    set(value) {
        rotationType = value?.let { platform.Foundation.NSNumber(unsignedLong = it.fromKmp().value) }
    }
/**
 * Z-index of the icon, relative to the placemark's z-index.
 *
 */
actual var IconStyle.mpZIndex: Float?
    get() = zIndex()?.toFloat()
    set(value) {
        zIndex = value?.toNSNumber()
    }
/**
 * If true, the icon is displayed on the map surface. If false, the icon
 * is displayed on the screen surface. Default: false.
 *
 */
actual var IconStyle.mpFlat: Boolean?
    get() = flat()?.toBoolean()
    set(value) {
        flat = value?.toNSNumber()
    }
/**
 * Sets icon visibility. Default: true.
 *
 */
actual var IconStyle.mpVisible: Boolean?
    get() = visible()?.toBoolean()
    set(value) {
        visible = value?.toNSNumber()
    }
/**
 * Scale of the icon. Default: 1.0f.
 *
 */
actual var IconStyle.mpScale: Float?
    get() = scale()?.toFloat()
    set(value) {
        scale = value?.toNSNumber()
    }
/**
 * Tappable area on the icon. Coordinates are measured the same way as
 * anchor coordinates. If rect is empty or invalid, the icon will not
 * process taps. By default, icons process all taps.
 *
 */
actual var IconStyle.mpTappableArea: com.yandex.mapkit.kmp.map.Rect?
    get() = tappableArea()
    set(value) {
        tappableArea = value
    }

actual object IconStyleFactory {
    actual fun create(
        anchor: com.yandex.runtime.kmp.NativePoint?,
        rotationType: com.yandex.mapkit.kmp.map.RotationType?,
        zIndex: Float?,
        flat: Boolean?,
        visible: Boolean?,
        scale: Float?,
        tappableArea: com.yandex.mapkit.kmp.map.Rect?,
    ): IconStyle {
        return IconStyle.iconStyleWithAnchor(
            anchor?.fromKmpOptional(),
            rotationType?.let { platform.Foundation.NSNumber(unsignedLong = it.fromKmp().value) },
            zIndex?.toNSNumber(),
            flat?.toNSNumber(),
            visible?.toNSNumber(),
            scale?.toNSNumber(),
            tappableArea,
        )
    }
}

