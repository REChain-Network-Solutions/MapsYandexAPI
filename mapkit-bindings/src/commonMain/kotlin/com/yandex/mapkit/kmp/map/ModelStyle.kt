@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * The style of the model.
 */
expect class ModelStyle

/**
 * Scale the model by this value.
 */
expect var ModelStyle.mpScale: Float
/**
 * Unit type of the model.
 */
expect var ModelStyle.mpUnitType: com.yandex.mapkit.kmp.map.ModelStyleUnitType
/**
 * Defines should it be rendered with buildings from ground layer.
 */
expect var ModelStyle.mpRenderMode: com.yandex.mapkit.kmp.map.ModelStyleRenderMode
/**
 * Name of variant to render model with. See KHR_materials_variants gltf
 * extension. This works only for glTF models.
 *
 */
expect var ModelStyle.mpVariantName: String?

expect object ModelStyleFactory {
    fun create(
        scale: Float,
        unitType: com.yandex.mapkit.kmp.map.ModelStyleUnitType,
        renderMode: com.yandex.mapkit.kmp.map.ModelStyleRenderMode,
        variantName: String?,
    ): ModelStyle
}

expect enum class ModelStyleUnitType {
    /**
     * The model is given in units. The size of a unit is equal to the size
     * of a pixel at the current zoom level when the camera position's tilt
     * is equal to 0 and the scale factor is equal to 1.
     */
    UNIT,
    /**
     * Scale model proportionally to fit into 1x1x1 box.
     */
    @Deprecated("Use UnitType.Unit instead.")
    NORMALIZED,
    /**
     * The model is given in meters.
     */
    METER,
}

expect enum class ModelStyleRenderMode {
    /**
     * Model should be rendered with buildings from ground layer.
     */
    BUILDING,
    /**
     * Model should be rendered within separate sublayer.
     */
    USER_MODEL,
}

