@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * The style of the polyline.
 */
expect class LineStyle

/**
 * The stroke width in units. Default: 5. The size of a unit is equal to
 * the size of a pixel at the current zoom level when the camera
 * position's tilt is equal to 0 and the scale factor is equal to 1.
 */
expect var LineStyle.mpStrokeWidth: Float
/**
 * Maximum length (in units) of the gradient from one color to another.
 * Default: 0.
 */
expect var LineStyle.mpGradientLength: Float
/**
 * The outline color. Default: hexademical RGBA code 0x00000000.
 */
expect var LineStyle.mpOutlineColor: com.yandex.runtime.kmp.Color
/**
 * Width of the outline in units. Default: 0.
 */
expect var LineStyle.mpOutlineWidth: Float
/**
 * Enables the inner outline if true (a dark border along the edge of
 * the outline). Default: false.
 */
expect var LineStyle.mpInnerOutlineEnabled: Boolean
/**
 * Maximum radius of a turn. Measured in units. Default: 10.
 */
expect var LineStyle.mpTurnRadius: Float
/**
 * Defines step of arc approximation. Smaller values make polyline
 * smoother. Measured in degrees. Default: 12.
 */
expect var LineStyle.mpArcApproximationStep: Float
/**
 * Length of a dash in units. Default: 0 (dashing is turned off). Arrows
 * are ignored in dashed polylines.
 */
expect var LineStyle.mpDashLength: Float
/**
 * Length of the gap between two dashes in units. Default: 0 (dashing is
 * turned off). Arrows are ignored in dashed polylines.
 */
expect var LineStyle.mpGapLength: Float
/**
 * Offset from the start of the polyline to the reference dash in units.
 * Default: 0.
 */
expect var LineStyle.mpDashOffset: Float

expect object LineStyleFactory {
    fun create(
        strokeWidth: Float,
        gradientLength: Float,
        outlineColor: com.yandex.runtime.kmp.Color,
        outlineWidth: Float,
        innerOutlineEnabled: Boolean,
        turnRadius: Float,
        arcApproximationStep: Float,
        dashLength: Float,
        gapLength: Float,
        dashOffset: Float,
    ): LineStyle
}

