@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp.map

import com.yandex.runtime.kmp.internal.toBoolean
import com.yandex.runtime.kmp.internal.toDouble
import com.yandex.runtime.kmp.internal.toFloat
import com.yandex.runtime.kmp.internal.toInt
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * A polyline object with adjustable segment colors. Supports outlines,
 * dash, arrows, and hiding parts of the polyline efficiently.
 */
actual interface PolylineMapObject : com.yandex.mapkit.kmp.map.MapObject {
    override val impl: platform.YandexMapsMobile.YMKPolylineMapObject

    /**
     * Highlights a subpolyline using the specified color.
     */
    actual fun select(
        selectionColor: com.yandex.runtime.kmp.Color,
        subpolyline: com.yandex.mapkit.kmp.geometry.Subpolyline,
    ): Unit

    /**
     * Hides the subpolyline, canceling any previous hides.
     */
    actual fun hide(
        subpolyline: com.yandex.mapkit.kmp.geometry.Subpolyline,
    ): Unit

    /**
     * Hides multiple subpolylines, canceling any previous hides.
     */
    actual fun hide(
        subpolylines: kotlin.collections.List<com.yandex.mapkit.kmp.geometry.Subpolyline>,
    ): Unit

    /**
     * Sets indexes of colors in palette for line segments. Weights are used
     * for generalization of colors. By default, all segments use palette
     * index 0.
     */
    actual fun setStrokeColors(
        colors: kotlin.collections.List<Int>,
        weights: kotlin.collections.List<Double>,
    ): Unit

    /**
     * Sets indexes of colors in palette for line segments. All the weights
     * are equal to 1.
     */
    actual fun setStrokeColors(
        colors: kotlin.collections.List<Int>,
    ): Unit

    /**
     * Sets color in RGBA mode for colorIndex. If the color is not provided
     * for some index, the default value 0x0066FFFF is used.
     */
    actual fun setPaletteColor(
        colorIndex: Int,
        color: com.yandex.runtime.kmp.Color,
    ): Unit

    /**
     * Returns the palette color for the specified index.
     */
    actual fun getPaletteColor(
        colorIndex: Int,
    ): com.yandex.runtime.kmp.Color

    /**
     * Sets the polyline color. Effectively sets a single-color palette and
     * sets all segments' palette indices to 0.
     */
    actual fun setStrokeColor(
        color: com.yandex.runtime.kmp.Color,
    ): Unit

    /**
     * Returns the palette index used by segment with the specified index.
     */
    actual fun getStrokeColor(
        segmentIndex: Int,
    ): Int

    /**
     * Adds an arrow.
     *
     * @param position Coordinates of the center of the arrow.
     * @param length Overall length of the arrow (including the tip) in
     * units.
     * @param fillColor Color of the arrow. Adding arrows disables dash for
     * this polyline.
     */
    actual fun addArrow(
        position: com.yandex.mapkit.kmp.geometry.PolylinePosition,
        length: Float,
        fillColor: com.yandex.runtime.kmp.Color,
    ): com.yandex.mapkit.kmp.map.Arrow

    /**
     * Provides arrows.
     */
    actual fun arrows(): kotlin.collections.List<com.yandex.mapkit.kmp.map.Arrow>
}
/**
 * The polyline's geometry. Should contain at least 2 points. Changing
 * geometry resets polyline color indices to 0.
 */
actual var PolylineMapObject.geometry: com.yandex.mapkit.kmp.geometry.Polyline
    get() = impl.geometry
    set(value) {
        impl.geometry = value
    }
/**
 * The polyline's style.
 */
actual var PolylineMapObject.style: com.yandex.mapkit.kmp.map.LineStyle
    get() = impl.style
    set(value) {
        impl.style = value
    }
/**
 * The stroke width in units. Default: 5. The size of a unit is equal to
 * the size of a pixel at the current zoom level when the camera
 * position's tilt is equal to 0 and the scale factor is equal to 1.
 */
@Deprecated("Use LineStyle for actions with properties.")
actual var PolylineMapObject.strokeWidth: Float
    get() = impl.strokeWidth
    set(value) {
        impl.strokeWidth = value
    }
/**
 * Maximum length (in units) of the gradient from one color to another.
 * Default: 0.
 */
@Deprecated("Use LineStyle for actions with properties.")
actual var PolylineMapObject.gradientLength: Float
    get() = impl.gradientLength
    set(value) {
        impl.gradientLength = value
    }
/**
 * The outline color. Default: hexademical RGBA code 0x00000000.
 */
@Deprecated("Use LineStyle for actions with properties.")
actual var PolylineMapObject.outlineColor: com.yandex.runtime.kmp.Color
    get() = impl.outlineColor
    set(value) {
        impl.outlineColor = value
    }
/**
 * Width of the outline in units. Default: 0.
 */
@Deprecated("Use LineStyle for actions with properties.")
actual var PolylineMapObject.outlineWidth: Float
    get() = impl.outlineWidth
    set(value) {
        impl.outlineWidth = value
    }
/**
 * Enables the inner outline if true (a dark border along the edge of
 * the outline). Default: false.
 */
@Deprecated("Use LineStyle for actions with properties.")
actual var PolylineMapObject.innerOutlineEnabled: Boolean
    get() = impl.innerOutlineEnabled
    set(value) {
        impl.innerOutlineEnabled = value
    }
/**
 * Maximum radius of a turn. Measured in units. Default: 10.
 */
@Deprecated("Use LineStyle for actions with properties.")
actual var PolylineMapObject.turnRadius: Float
    get() = impl.turnRadius
    set(value) {
        impl.turnRadius = value
    }
/**
 * Defines step of arc approximation. Smaller values make polyline
 * smoother. Measured in degrees. Default: 12.
 */
@Deprecated("Use LineStyle for actions with properties.")
actual var PolylineMapObject.arcApproximationStep: Float
    get() = impl.arcApproximationStep
    set(value) {
        impl.arcApproximationStep = value
    }
/**
 * Length of a dash in units. Default: 0 (dashing is turned off). Arrows
 * are ignored in dashed polylines.
 */
@Deprecated("Use LineStyle for actions with properties.")
actual var PolylineMapObject.dashLength: Float
    get() = impl.dashLength
    set(value) {
        impl.dashLength = value
    }
/**
 * Length of the gap between two dashes in units. Default: 0 (dashing is
 * turned off). Arrows are ignored in dashed polylines.
 */
@Deprecated("Use LineStyle for actions with properties.")
actual var PolylineMapObject.gapLength: Float
    get() = impl.gapLength
    set(value) {
        impl.gapLength = value
    }
/**
 * Offset from the start of the polyline to the reference dash in units.
 * Default: 0.
 */
@Deprecated("Use LineStyle for actions with properties.")
actual var PolylineMapObject.dashOffset: Float
    get() = impl.dashOffset
    set(value) {
        impl.dashOffset = value
    }

open class YMKPolylineMapObjectWrapper(override val impl: platform.YandexMapsMobile.YMKPolylineMapObject) : PolylineMapObject, com.yandex.mapkit.kmp.map.YMKMapObjectWrapper(impl) {
    override fun select(
        selectionColor: com.yandex.runtime.kmp.Color,
        subpolyline: com.yandex.mapkit.kmp.geometry.Subpolyline,
    ): Unit {
        return impl.selectWithSelectionColor(
            selectionColor,
            subpolyline,
        )
    }

    override fun hide(
        subpolyline: com.yandex.mapkit.kmp.geometry.Subpolyline,
    ): Unit {
        return impl.hideWithSubpolyline(
            subpolyline,
        )
    }

    override fun hide(
        subpolylines: kotlin.collections.List<com.yandex.mapkit.kmp.geometry.Subpolyline>,
    ): Unit {
        return impl.hideWithSubpolylines(
            subpolylines.let { it as kotlin.collections.List<*> },
        )
    }

    override fun setStrokeColors(
        colors: kotlin.collections.List<Int>,
        weights: kotlin.collections.List<Double>,
    ): Unit {
        return impl.setStrokeColorsWithColors(
            colors.map { it.toNSNumber() }.let { it as kotlin.collections.List<*> },
            weights.map { it.toNSNumber() }.let { it as kotlin.collections.List<*> },
        )
    }

    override fun setStrokeColors(
        colors: kotlin.collections.List<Int>,
    ): Unit {
        return impl.setStrokeColorsWithColors(
            colors.map { it.toNSNumber() }.let { it as kotlin.collections.List<*> },
        )
    }

    override fun setPaletteColor(
        colorIndex: Int,
        color: com.yandex.runtime.kmp.Color,
    ): Unit {
        return impl.setPaletteColorWithColorIndex(
            colorIndex.toULong(),
            color,
        )
    }

    override fun getPaletteColor(
        colorIndex: Int,
    ): com.yandex.runtime.kmp.Color {
        return impl.getPaletteColorWithColorIndex(
            colorIndex.toULong(),
        )
    }

    override fun setStrokeColor(
        color: com.yandex.runtime.kmp.Color,
    ): Unit {
        return impl.setStrokeColorWithColor(
            color,
        )
    }

    override fun getStrokeColor(
        segmentIndex: Int,
    ): Int {
        return impl.getStrokeColorWithSegmentIndex(
            segmentIndex.toULong(),
        ).toInt()
    }

    override fun addArrow(
        position: com.yandex.mapkit.kmp.geometry.PolylinePosition,
        length: Float,
        fillColor: com.yandex.runtime.kmp.Color,
    ): com.yandex.mapkit.kmp.map.Arrow {
        return impl.addArrowWithPosition(
            position,
            length,
            fillColor,
        ).let { com.yandex.mapkit.kmp.map.YMKArrowWrapper(it) }
    }

    override fun arrows(): kotlin.collections.List<com.yandex.mapkit.kmp.map.Arrow> {
        return impl.arrows().let { it as kotlin.collections.List<platform.YandexMapsMobile.YMKArrow> }.map { it.let { com.yandex.mapkit.kmp.map.YMKArrowWrapper(it) } }
    }
}

