@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * The style of the polyline.
 */
actual typealias LineStyle = com.yandex.mapkit.map.LineStyle

/**
 * The stroke width in units. Default: 5. The size of a unit is equal to
 * the size of a pixel at the current zoom level when the camera
 * position's tilt is equal to 0 and the scale factor is equal to 1.
 */
actual var LineStyle.mpStrokeWidth: Float
    get() = strokeWidth
    set(value) {
        strokeWidth = value
    }
/**
 * Maximum length (in units) of the gradient from one color to another.
 * Default: 0.
 */
actual var LineStyle.mpGradientLength: Float
    get() = gradientLength
    set(value) {
        gradientLength = value
    }
/**
 * The outline color. Default: hexademical RGBA code 0x00000000.
 */
actual var LineStyle.mpOutlineColor: com.yandex.runtime.kmp.Color
    get() = outlineColor
    set(value) {
        outlineColor = value
    }
/**
 * Width of the outline in units. Default: 0.
 */
actual var LineStyle.mpOutlineWidth: Float
    get() = outlineWidth
    set(value) {
        outlineWidth = value
    }
/**
 * Enables the inner outline if true (a dark border along the edge of
 * the outline). Default: false.
 */
actual var LineStyle.mpInnerOutlineEnabled: Boolean
    get() = innerOutlineEnabled
    set(value) {
        innerOutlineEnabled = value
    }
/**
 * Maximum radius of a turn. Measured in units. Default: 10.
 */
actual var LineStyle.mpTurnRadius: Float
    get() = turnRadius
    set(value) {
        turnRadius = value
    }
/**
 * Defines step of arc approximation. Smaller values make polyline
 * smoother. Measured in degrees. Default: 12.
 */
actual var LineStyle.mpArcApproximationStep: Float
    get() = arcApproximationStep
    set(value) {
        arcApproximationStep = value
    }
/**
 * Length of a dash in units. Default: 0 (dashing is turned off). Arrows
 * are ignored in dashed polylines.
 */
actual var LineStyle.mpDashLength: Float
    get() = dashLength
    set(value) {
        dashLength = value
    }
/**
 * Length of the gap between two dashes in units. Default: 0 (dashing is
 * turned off). Arrows are ignored in dashed polylines.
 */
actual var LineStyle.mpGapLength: Float
    get() = gapLength
    set(value) {
        gapLength = value
    }
/**
 * Offset from the start of the polyline to the reference dash in units.
 * Default: 0.
 */
actual var LineStyle.mpDashOffset: Float
    get() = dashOffset
    set(value) {
        dashOffset = value
    }

actual object LineStyleFactory {
    actual fun create(
        strokeWidth: Float,
        gradientLength: Float,
        outlineColor: com.yandex.runtime.kmp.Color,
        outlineWidth: Float,
        innerOutlineEnabled: Boolean,
        turnRadius: Float,
        arcApproximationStep: Float,
        dashLength: Float,
        gapLength: Float,
        dashOffset: Float
    ): LineStyle {
        return LineStyle(
            strokeWidth,
            gradientLength,
            outlineColor,
            outlineWidth,
            innerOutlineEnabled,
            turnRadius,
            arcApproximationStep,
            dashLength,
            gapLength,
            dashOffset,
        )
    }
}

