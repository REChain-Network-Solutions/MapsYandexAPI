@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * A polyline object with adjustable segment colors. Supports outlines,
 * dash, arrows, and hiding parts of the polyline efficiently.
 */
actual typealias PolylineMapObject = com.yandex.mapkit.map.PolylineMapObject

/**
 * The polyline's geometry. Should contain at least 2 points. Changing
 * geometry resets polyline color indices to 0.
 */
actual var PolylineMapObject.geometry: com.yandex.mapkit.kmp.geometry.Polyline
    get() = geometry
    set(value) {
        geometry = value
    }
/**
 * The polyline's style.
 */
actual var PolylineMapObject.style: com.yandex.mapkit.kmp.map.LineStyle
    get() = style
    set(value) {
        style = value
    }
/**
 * The stroke width in units. Default: 5. The size of a unit is equal to
 * the size of a pixel at the current zoom level when the camera
 * position's tilt is equal to 0 and the scale factor is equal to 1.
 */
@Deprecated("Use LineStyle for actions with properties.")
actual var PolylineMapObject.strokeWidth: Float
    get() = strokeWidth
    set(value) {
        strokeWidth = value
    }
/**
 * Maximum length (in units) of the gradient from one color to another.
 * Default: 0.
 */
@Deprecated("Use LineStyle for actions with properties.")
actual var PolylineMapObject.gradientLength: Float
    get() = gradientLength
    set(value) {
        gradientLength = value
    }
/**
 * The outline color. Default: hexademical RGBA code 0x00000000.
 */
@Deprecated("Use LineStyle for actions with properties.")
actual var PolylineMapObject.outlineColor: com.yandex.runtime.kmp.Color
    get() = outlineColor
    set(value) {
        outlineColor = value
    }
/**
 * Width of the outline in units. Default: 0.
 */
@Deprecated("Use LineStyle for actions with properties.")
actual var PolylineMapObject.outlineWidth: Float
    get() = outlineWidth
    set(value) {
        outlineWidth = value
    }
/**
 * Enables the inner outline if true (a dark border along the edge of
 * the outline). Default: false.
 */
@Deprecated("Use LineStyle for actions with properties.")
actual var PolylineMapObject.innerOutlineEnabled: Boolean
    get() = isInnerOutlineEnabled
    set(value) {
        isInnerOutlineEnabled = value
    }
/**
 * Maximum radius of a turn. Measured in units. Default: 10.
 */
@Deprecated("Use LineStyle for actions with properties.")
actual var PolylineMapObject.turnRadius: Float
    get() = turnRadius
    set(value) {
        turnRadius = value
    }
/**
 * Defines step of arc approximation. Smaller values make polyline
 * smoother. Measured in degrees. Default: 12.
 */
@Deprecated("Use LineStyle for actions with properties.")
actual var PolylineMapObject.arcApproximationStep: Float
    get() = arcApproximationStep
    set(value) {
        arcApproximationStep = value
    }
/**
 * Length of a dash in units. Default: 0 (dashing is turned off). Arrows
 * are ignored in dashed polylines.
 */
@Deprecated("Use LineStyle for actions with properties.")
actual var PolylineMapObject.dashLength: Float
    get() = dashLength
    set(value) {
        dashLength = value
    }
/**
 * Length of the gap between two dashes in units. Default: 0 (dashing is
 * turned off). Arrows are ignored in dashed polylines.
 */
@Deprecated("Use LineStyle for actions with properties.")
actual var PolylineMapObject.gapLength: Float
    get() = gapLength
    set(value) {
        gapLength = value
    }
/**
 * Offset from the start of the polyline to the reference dash in units.
 * Default: 0.
 */
@Deprecated("Use LineStyle for actions with properties.")
actual var PolylineMapObject.dashOffset: Float
    get() = dashOffset
    set(value) {
        dashOffset = value
    }

