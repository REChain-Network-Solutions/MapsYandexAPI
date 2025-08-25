@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.layers

/**
 * Information about the tapped object.
 */
actual interface GeoObjectTapEvent {
    val impl: platform.YandexMapsMobile.YMKGeoObjectTapEvent

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
 * @return GeoObject The object that was tapped.
 */
actual val GeoObjectTapEvent.geoObject: com.yandex.mapkit.kmp.GeoObject
    get() = impl.geoObject

open class YMKGeoObjectTapEventWrapper(override val impl: platform.YandexMapsMobile.YMKGeoObjectTapEvent) : GeoObjectTapEvent {
    override fun isValid(): Boolean = impl.isValid()
}

