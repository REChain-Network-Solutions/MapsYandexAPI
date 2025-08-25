@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * A polyline object with adjustable segment colors. Supports outlines,
 * dash, arrows, and hiding parts of the polyline efficiently.
 */
expect interface PolylineMapObject : com.yandex.mapkit.kmp.map.MapObject {
    /**
     * Highlights a subpolyline using the specified color.
     */
    fun select(
        selectionColor: com.yandex.runtime.kmp.Color,
        subpolyline: com.yandex.mapkit.kmp.geometry.Subpolyline,
    ): Unit

    /**
     * Hides the subpolyline, canceling any previous hides.
     */
    fun hide(
        subpolyline: com.yandex.mapkit.kmp.geometry.Subpolyline,
    ): Unit

    /**
     * Hides multiple subpolylines, canceling any previous hides.
     */
    fun hide(
        subpolylines: kotlin.collections.List<com.yandex.mapkit.kmp.geometry.Subpolyline>,
    ): Unit

    /**
     * Sets indexes of colors in palette for line segments. Weights are used
     * for generalization of colors. By default, all segments use palette
     * index 0.
     */
    fun setStrokeColors(
        colors: kotlin.collections.List<Int>,
        weights: kotlin.collections.List<Double>,
    ): Unit

    /**
     * Sets indexes of colors in palette for line segments. All the weights
     * are equal to 1.
     */
    fun setStrokeColors(
        colors: kotlin.collections.List<Int>,
    ): Unit

    /**
     * Sets color in RGBA mode for colorIndex. If the color is not provided
     * for some index, the default value 0x0066FFFF is used.
     */
    fun setPaletteColor(
        colorIndex: Int,
        color: com.yandex.runtime.kmp.Color,
    ): Unit

    /**
     * Returns the palette color for the specified index.
     */
    fun getPaletteColor(
        colorIndex: Int,
    ): com.yandex.runtime.kmp.Color

    /**
     * Sets the polyline color. Effectively sets a single-color palette and
     * sets all segments' palette indices to 0.
     */
    fun setStrokeColor(
        color: com.yandex.runtime.kmp.Color,
    ): Unit

    /**
     * Returns the palette index used by segment with the specified index.
     */
    fun getStrokeColor(
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
    fun addArrow(
        position: com.yandex.mapkit.kmp.geometry.PolylinePosition,
        length: Float,
        fillColor: com.yandex.runtime.kmp.Color,
    ): com.yandex.mapkit.kmp.map.Arrow

    /**
     * Provides arrows.
     */
    fun arrows(): kotlin.collections.List<com.yandex.mapkit.kmp.map.Arrow>
}

/**
 * The polyline's geometry. Should contain at least 2 points. Changing
 * geometry resets polyline color indices to 0.
 */
expect var PolylineMapObject.geometry: com.yandex.mapkit.kmp.geometry.Polyline
/**
 * The polyline's style.
 */
expect var PolylineMapObject.style: com.yandex.mapkit.kmp.map.LineStyle
/**
 * The stroke width in units. Default: 5. The size of a unit is equal to
 * the size of a pixel at the current zoom level when the camera
 * position's tilt is equal to 0 and the scale factor is equal to 1.
 */
@Deprecated("Use LineStyle for actions with properties.")
expect var PolylineMapObject.strokeWidth: Float
/**
 * Maximum length (in units) of the gradient from one color to another.
 * Default: 0.
 */
@Deprecated("Use LineStyle for actions with properties.")
expect var PolylineMapObject.gradientLength: Float
/**
 * The outline color. Default: hexademical RGBA code 0x00000000.
 */
@Deprecated("Use LineStyle for actions with properties.")
expect var PolylineMapObject.outlineColor: com.yandex.runtime.kmp.Color
/**
 * Width of the outline in units. Default: 0.
 */
@Deprecated("Use LineStyle for actions with properties.")
expect var PolylineMapObject.outlineWidth: Float
/**
 * Enables the inner outline if true (a dark border along the edge of
 * the outline). Default: false.
 */
@Deprecated("Use LineStyle for actions with properties.")
expect var PolylineMapObject.innerOutlineEnabled: Boolean
/**
 * Maximum radius of a turn. Measured in units. Default: 10.
 */
@Deprecated("Use LineStyle for actions with properties.")
expect var PolylineMapObject.turnRadius: Float
/**
 * Defines step of arc approximation. Smaller values make polyline
 * smoother. Measured in degrees. Default: 12.
 */
@Deprecated("Use LineStyle for actions with properties.")
expect var PolylineMapObject.arcApproximationStep: Float
/**
 * Length of a dash in units. Default: 0 (dashing is turned off). Arrows
 * are ignored in dashed polylines.
 */
@Deprecated("Use LineStyle for actions with properties.")
expect var PolylineMapObject.dashLength: Float
/**
 * Length of the gap between two dashes in units. Default: 0 (dashing is
 * turned off). Arrows are ignored in dashed polylines.
 */
@Deprecated("Use LineStyle for actions with properties.")
expect var PolylineMapObject.gapLength: Float
/**
 * Offset from the start of the polyline to the reference dash in units.
 * Default: 0.
 */
@Deprecated("Use LineStyle for actions with properties.")
expect var PolylineMapObject.dashOffset: Float

