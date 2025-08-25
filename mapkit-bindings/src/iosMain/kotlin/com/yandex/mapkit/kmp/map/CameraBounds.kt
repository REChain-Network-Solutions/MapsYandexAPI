@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

import com.yandex.runtime.kmp.fromKmp
import com.yandex.runtime.kmp.fromKmpOptional
import com.yandex.runtime.kmp.internal.toFloat
import com.yandex.runtime.kmp.internal.toNSNumber
import com.yandex.runtime.kmp.toKmp

/**
 * The object that is used to interact with the map bounds.
 */
actual interface CameraBounds {
    val impl: platform.YandexMapsMobile.YMKCameraBounds

    /**
     * Minimum available zoom level considering zoom level hint provided via
     * #setMinZoomPreference.
     */
    actual fun getMinZoom(): Float

    /**
     * Maximum available zoom level considering zoom level hint provided via
     * #setMaxZoomPreference
     */
    actual fun getMaxZoom(): Float

    /**
     * Set minimum available zoom level hint.
     */
    actual fun setMinZoomPreference(
        zoom: Float,
    ): Unit

    /**
     * Set maximum available zoom level hint.
     */
    actual fun setMaxZoomPreference(
        zoom: Float,
    ): Unit

    /**
     * Reset minimum and maximum available zoom level hints.
     */
    actual fun resetMinMaxZoomPreference(): Unit

    /**
     * Tells if this object is valid or not. Any method called on an invalid
     * object will throw an exception. The object becomes invalid only on UI
     * thread, and only when its implementation depends on objects already
     * destroyed by now. Please refer to general docs about the interface for
     * details on its invalidation.
     */
    actual fun isValid(): Boolean
}
/**
 * Latitudes should be in range [-89.3, 89.3]. Longitudes should be in
 * range [-180, 180).
 *
 */
actual var CameraBounds.latLngBounds: com.yandex.mapkit.kmp.geometry.BoundingBox?
    get() = impl.latLngBounds
    set(value) {
        impl.latLngBounds = value
    }

open class YMKCameraBoundsWrapper(override val impl: platform.YandexMapsMobile.YMKCameraBounds) : CameraBounds {

    override fun getMinZoom(): Float {
        return impl.getMinZoom()
    }

    override fun getMaxZoom(): Float {
        return impl.getMaxZoom()
    }

    override fun setMinZoomPreference(
        zoom: Float,
    ): Unit {
        return impl.setMinZoomPreferenceWithZoom(
            zoom,
        )
    }

    override fun setMaxZoomPreference(
        zoom: Float,
    ): Unit {
        return impl.setMaxZoomPreferenceWithZoom(
            zoom,
        )
    }

    override fun resetMinMaxZoomPreference(): Unit {
        return impl.resetMinMaxZoomPreference()
    }

    override fun isValid(): Boolean = impl.isValid()
}
