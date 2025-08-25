@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

import com.yandex.runtime.kmp.fromKmp
import com.yandex.runtime.kmp.fromKmpOptional
import com.yandex.runtime.kmp.internal.toFloat
import com.yandex.runtime.kmp.internal.toNSNumber
import com.yandex.runtime.kmp.toKmp

/**
 * Represents a geo-positioned object on the map.
 */
actual interface PlacemarkMapObject : com.yandex.mapkit.kmp.map.MapObject {
    override val impl: platform.YandexMapsMobile.YMKPlacemarkMapObject

    /**
     * Sets an icon with the default style for the placemark. Switches off
     * and resets model/composite icon/animation/view.
     */
    actual fun setIcon(
        image: com.yandex.runtime.kmp.image.ImageProvider,
    ): Unit

    /**
     * Sets an icon with the given style for the placemark. Switches off and
     * resets model/composite icon/animation/view.
     */
    actual fun setIcon(
        image: com.yandex.runtime.kmp.image.ImageProvider,
        style: com.yandex.mapkit.kmp.map.IconStyle,
    ): Unit

    /**
     * Sets an icon with the default style for the placemark. Switches off
     * and resets model/composite icon/animation/view. The callback is
     * called immediately after the image finished loading. This means you
     * can, for example, change the placemark visibility with a new icon.
     *
     * @param onFinished Called when the icon is loaded.
     */
    actual fun setIcon(
        image: com.yandex.runtime.kmp.image.ImageProvider,
        onFinished: com.yandex.mapkit.kmp.map.Callback,
    ): Unit

    /**
     * Sets an icon with the given style for the placemark. Switches off and
     * resets model/composite icon/animation/view. The callback is called
     * immediately after the image finished loading. This means you can, for
     * example, change the placemark visibility with a new icon.
     *
     * @param onFinished Called when the icon is loaded.
     */
    actual fun setIcon(
        image: com.yandex.runtime.kmp.image.ImageProvider,
        style: com.yandex.mapkit.kmp.map.IconStyle,
        onFinished: com.yandex.mapkit.kmp.map.Callback,
    ): Unit

    /**
     * Changes the icon style. Valid only for the single icon, the view and
     * the animated icon.
     */
    actual fun setIconStyle(
        style: com.yandex.mapkit.kmp.map.IconStyle,
    ): Unit

    /**
     * Returns CompositeIcon object that can be used to set icons and their
     * styles for the placemark. Switches off and resets
     * icon/model/animation/view.
     */
    actual fun useCompositeIcon(): com.yandex.mapkit.kmp.map.CompositeIcon

    /**
     * Returns Model object that can be used to set model and its style for
     * the placemark. Switches off and resets icon/composite
     * icon/animation/view.
     */
    actual fun useModel(): com.yandex.mapkit.kmp.map.Model

    /**
     * Returns PlacemarkAnimation object that can be used to control
     * animation of the placemark. Switches off and resets icon/composite
     * icon/model/view.
     */
    actual fun useAnimation(): com.yandex.mapkit.kmp.map.PlacemarkAnimation

    /**
     * Sets the view with the default style for the placemark. Switches off
     * and resets icon/composite icon/animation/model.
     */
    actual fun setView(
        view: com.yandex.runtime.kmp.ui_view.ViewProvider,
    ): Unit

    /**
     * Sets the view with the given style for the placemark. Switches off
     * and resets icon/composite icon/animation/view.
     */
    actual fun setView(
        view: com.yandex.runtime.kmp.ui_view.ViewProvider,
        style: com.yandex.mapkit.kmp.map.IconStyle,
    ): Unit

    /**
     * Sets the view with the default style for the placemark. Switches off
     * and resets icon/composite icon/animation/view. The callback will be
     * called immediately after the view finished loading.
     *
     * @param onFinished Called when the icon is loaded.
     */
    actual fun setView(
        view: com.yandex.runtime.kmp.ui_view.ViewProvider,
        onFinished: com.yandex.mapkit.kmp.map.Callback,
    ): Unit

    /**
     * Sets the view with the given style for the placemark. Switches off
     * and resets icon/composite icon/animation/view. The callback will be
     * called immediately after the view finished loading.
     *
     * @param onFinished Called when the icon is loaded.
     */
    actual fun setView(
        view: com.yandex.runtime.kmp.ui_view.ViewProvider,
        style: com.yandex.mapkit.kmp.map.IconStyle,
        onFinished: com.yandex.mapkit.kmp.map.Callback,
    ): Unit

    /**
     * Sets piecewise linear scale, depending on the zoom. The 'points' must
     * be sorted by x; x coordinates must be unique. If zoom <
     * minZoom(points) or zoom > maxZoom(points), it is set within the
     * defined bounds before applying the function. By default, the scale
     * function is defined by a single point (1, 1). If points is null or
     * points.empty(), it resets the function to the default. If
     * points.size() == 1, the scale is constant and equals point.y.
     */
    actual fun setScaleFunction(
        points: kotlin.collections.List<com.yandex.runtime.kmp.NativePoint>,
    ): Unit

    /**
     * Sets the text for the placemark, current text style is used
     *
     * @param text is a string in UTF-8 encoding
     */
    actual fun setText(
        text: String,
    ): Unit

    /**
     * Sets the text with the given style for the placemark
     *
     * @param text is a string in UTF-8 encoding
     */
    actual fun setText(
        text: String,
        style: com.yandex.mapkit.kmp.map.TextStyle,
    ): Unit

    /**
     * Changes the text style.
     */
    actual fun setTextStyle(
        style: com.yandex.mapkit.kmp.map.TextStyle,
    ): Unit
}
actual var PlacemarkMapObject.geometry: com.yandex.mapkit.kmp.geometry.Point
    get() = impl.geometry
    set(value) {
        impl.geometry = value
    }
/**
 * Angle between the direction of an object and the direction to north.
 * Measured in degrees. Default: 0.f.
 */
actual var PlacemarkMapObject.direction: Float
    get() = impl.direction
    set(value) {
        impl.direction = value
    }
/**
 * Opacity multiplicator for the placemark content. Values below 0 will
 * be set to 0. Default: 1.
 */
actual var PlacemarkMapObject.opacity: Float
    get() = impl.opacity
    set(value) {
        impl.opacity = value
    }

open class YMKPlacemarkMapObjectWrapper(override val impl: platform.YandexMapsMobile.YMKPlacemarkMapObject) : PlacemarkMapObject, com.yandex.mapkit.kmp.map.YMKMapObjectWrapper(impl) {
    override fun setIcon(
        image: com.yandex.runtime.kmp.image.ImageProvider,
    ): Unit {
        return impl.setIconWithImage(
            image.impl,
        )
    }

    override fun setIcon(
        image: com.yandex.runtime.kmp.image.ImageProvider,
        style: com.yandex.mapkit.kmp.map.IconStyle,
    ): Unit {
        return impl.setIconWithImage(
            image.impl,
            style,
        )
    }

    override fun setIcon(
        image: com.yandex.runtime.kmp.image.ImageProvider,
        onFinished: com.yandex.mapkit.kmp.map.Callback,
    ): Unit {
        return impl.setIconWithImage(
            image.impl,
            onFinished.let { com.yandex.mapkit.kmp.map.CallbackWrapper(it) },
        )
    }

    override fun setIcon(
        image: com.yandex.runtime.kmp.image.ImageProvider,
        style: com.yandex.mapkit.kmp.map.IconStyle,
        onFinished: com.yandex.mapkit.kmp.map.Callback,
    ): Unit {
        return impl.setIconWithImage(
            image.impl,
            style,
            onFinished.let { com.yandex.mapkit.kmp.map.CallbackWrapper(it) },
        )
    }

    override fun setIconStyle(
        style: com.yandex.mapkit.kmp.map.IconStyle,
    ): Unit {
        return impl.setIconStyleWithStyle(
            style,
        )
    }

    override fun useCompositeIcon(): com.yandex.mapkit.kmp.map.CompositeIcon {
        return impl.useCompositeIcon().let { com.yandex.mapkit.kmp.map.YMKCompositeIconWrapper(it) }
    }

    override fun useModel(): com.yandex.mapkit.kmp.map.Model {
        return impl.useModel().let { com.yandex.mapkit.kmp.map.YMKModelWrapper(it) }
    }

    override fun useAnimation(): com.yandex.mapkit.kmp.map.PlacemarkAnimation {
        return impl.useAnimation().let { com.yandex.mapkit.kmp.map.YMKPlacemarkAnimationWrapper(it) }
    }

    override fun setView(
        view: com.yandex.runtime.kmp.ui_view.ViewProvider,
    ): Unit {
        return impl.setViewWithView(
            view,
        )
    }

    override fun setView(
        view: com.yandex.runtime.kmp.ui_view.ViewProvider,
        style: com.yandex.mapkit.kmp.map.IconStyle,
    ): Unit {
        return impl.setViewWithView(
            view,
            style,
        )
    }

    override fun setView(
        view: com.yandex.runtime.kmp.ui_view.ViewProvider,
        onFinished: com.yandex.mapkit.kmp.map.Callback,
    ): Unit {
        return impl.setViewWithView(
            view,
            onFinished.let { com.yandex.mapkit.kmp.map.CallbackWrapper(it) },
        )
    }

    override fun setView(
        view: com.yandex.runtime.kmp.ui_view.ViewProvider,
        style: com.yandex.mapkit.kmp.map.IconStyle,
        onFinished: com.yandex.mapkit.kmp.map.Callback,
    ): Unit {
        return impl.setViewWithView(
            view,
            style,
            onFinished.let { com.yandex.mapkit.kmp.map.CallbackWrapper(it) },
        )
    }

    override fun setScaleFunction(
        points: kotlin.collections.List<com.yandex.runtime.kmp.NativePoint>,
    ): Unit {
        return impl.setScaleFunctionWithPoints(
            points.map { it.fromKmpOptional() }.let { it as kotlin.collections.List<*> },
        )
    }

    override fun setText(
        text: String,
    ): Unit {
        return impl.setTextWithText(
            text,
        )
    }

    override fun setText(
        text: String,
        style: com.yandex.mapkit.kmp.map.TextStyle,
    ): Unit {
        return impl.setTextWithText(
            text,
            style,
        )
    }

    override fun setTextStyle(
        style: com.yandex.mapkit.kmp.map.TextStyle,
    ): Unit {
        return impl.setTextStyleWithStyle(
            style,
        )
    }
}

