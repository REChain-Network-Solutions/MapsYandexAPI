@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * The style of the model.
 */
actual typealias ModelStyle = com.yandex.mapkit.map.ModelStyle

/**
 * Scale the model by this value.
 */
actual var ModelStyle.mpScale: Float
    get() = scale
    set(value) {
        scale = value
    }
/**
 * Unit type of the model.
 */
actual var ModelStyle.mpUnitType: com.yandex.mapkit.kmp.map.ModelStyleUnitType
    get() = unitType
    set(value) {
        unitType = value
    }
/**
 * Defines should it be rendered with buildings from ground layer.
 */
actual var ModelStyle.mpRenderMode: com.yandex.mapkit.kmp.map.ModelStyleRenderMode
    get() = renderMode
    set(value) {
        renderMode = value
    }
/**
 * Name of variant to render model with. See KHR_materials_variants gltf
 * extension. This works only for glTF models.
 *
 */
actual var ModelStyle.mpVariantName: String?
    get() = variantName
    set(value) {
        variantName = value
    }

actual object ModelStyleFactory {
    actual fun create(
        scale: Float,
        unitType: com.yandex.mapkit.kmp.map.ModelStyleUnitType,
        renderMode: com.yandex.mapkit.kmp.map.ModelStyleRenderMode,
        variantName: String?,
    ): ModelStyle {
        return ModelStyle(
            scale,
            unitType,
            renderMode,
            variantName,
        )
    }
}

actual typealias ModelStyleUnitType = com.yandex.mapkit.map.ModelStyle.UnitType

actual typealias ModelStyleRenderMode = com.yandex.mapkit.map.ModelStyle.RenderMode

