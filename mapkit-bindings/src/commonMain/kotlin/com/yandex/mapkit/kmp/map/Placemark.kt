@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * Represents a geo-positioned object on the map.
 */
expect interface PlacemarkMapObject : com.yandex.mapkit.kmp.map.MapObject {

    /**
     * Sets an icon with the default style for the placemark. Switches off
     * and resets model/composite icon/animation/view.
     */
    fun setIcon(
        image: com.yandex.runtime.kmp.image.ImageProvider,
    ): Unit

    /**
     * Sets an icon with the given style for the placemark. Switches off and
     * resets model/composite icon/animation/view.
     */
    fun setIcon(
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
    fun setIcon(
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
    fun setIcon(
        image: com.yandex.runtime.kmp.image.ImageProvider,
        style: com.yandex.mapkit.kmp.map.IconStyle,
        onFinished: com.yandex.mapkit.kmp.map.Callback,
    ): Unit

    /**
     * Changes the icon style. Valid only for the single icon, the view and
     * the animated icon.
     */
    fun setIconStyle(
        style: com.yandex.mapkit.kmp.map.IconStyle,
    ): Unit

    /**
     * Returns CompositeIcon object that can be used to set icons and their
     * styles for the placemark. Switches off and resets
     * icon/model/animation/view.
     */
    fun useCompositeIcon(): com.yandex.mapkit.kmp.map.CompositeIcon

    /**
     * Returns Model object that can be used to set model and its style for
     * the placemark. Switches off and resets icon/composite
     * icon/animation/view.
     */
    fun useModel(): com.yandex.mapkit.kmp.map.Model

    /**
     * Returns PlacemarkAnimation object that can be used to control
     * animation of the placemark. Switches off and resets icon/composite
     * icon/model/view.
     */
    fun useAnimation(): com.yandex.mapkit.kmp.map.PlacemarkAnimation

    /**
     * Sets the view with the default style for the placemark. Switches off
     * and resets icon/composite icon/animation/model.
     */
    fun setView(
        view: com.yandex.runtime.kmp.ui_view.ViewProvider,
    ): Unit

    /**
     * Sets the view with the given style for the placemark. Switches off
     * and resets icon/composite icon/animation/view.
     */
    fun setView(
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
    fun setView(
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
    fun setView(
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
    fun setScaleFunction(
        points: kotlin.collections.List<com.yandex.runtime.kmp.NativePoint>,
    ): Unit

    /**
     * Sets the text for the placemark, current text style is used
     *
     * @param text is a string in UTF-8 encoding
     */
    fun setText(
        text: String,
    ): Unit

    /**
     * Sets the text with the given style for the placemark
     *
     * @param text is a string in UTF-8 encoding
     */
    fun setText(
        text: String,
        style: com.yandex.mapkit.kmp.map.TextStyle,
    ): Unit

    /**
     * Changes the text style.
     */
    fun setTextStyle(
        style: com.yandex.mapkit.kmp.map.TextStyle,
    ): Unit
}

expect var PlacemarkMapObject.geometry: com.yandex.mapkit.kmp.geometry.Point
/**
 * Angle between the direction of an object and the direction to north.
 * Measured in degrees. Default: 0.f.
 */
expect var PlacemarkMapObject.direction: Float
/**
 * Opacity multiplicator for the placemark content. Values below 0 will
 * be set to 0. Default: 1.
 */
expect var PlacemarkMapObject.opacity: Float
