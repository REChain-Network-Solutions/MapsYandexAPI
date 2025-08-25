@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST", "REDUNDANT_ELSE_IN_WHEN")

package com.yandex.mapkit.kmp.map

import com.yandex.runtime.kmp.internal.toFloat
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * The style of the model.
 */
actual typealias ModelStyle = platform.YandexMapsMobile.YMKModelStyle

/**
 * Scale the model by this value.
 */
actual var ModelStyle.mpScale: Float
    get() = scale()
    set(value) {
        scale = value
    }
/**
 * Unit type of the model.
 */
actual var ModelStyle.mpUnitType: com.yandex.mapkit.kmp.map.ModelStyleUnitType
    get() = unitType().let { com.yandex.mapkit.kmp.map.ModelStyleUnitType.toKmp(it) }
    set(value) {
        unitType = value.fromKmp()
    }
/**
 * Defines should it be rendered with buildings from ground layer.
 */
actual var ModelStyle.mpRenderMode: com.yandex.mapkit.kmp.map.ModelStyleRenderMode
    get() = renderMode().let { com.yandex.mapkit.kmp.map.ModelStyleRenderMode.toKmp(it) }
    set(value) {
        renderMode = value.fromKmp()
    }
/**
 * Name of variant to render model with. See KHR_materials_variants gltf
 * extension. This works only for glTF models.
 *
 */
actual var ModelStyle.mpVariantName: String?
    get() = variantName()
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
        return ModelStyle.modelStyleWithScale(
            scale,
            unitType.fromKmp(),
            renderMode.fromKmp(),
            variantName,
        )
    }
}

actual enum class ModelStyleUnitType {
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
    METER,;

    companion object {
        fun toKmp(v: platform.Foundation.NSNumber): ModelStyleUnitType {
            return toKmp(platform.YandexMapsMobile.YMKModelStyleUnitType.byValue(v.unsignedLongValue))
        }

        fun toKmp(v: platform.YandexMapsMobile.YMKModelStyleUnitType): ModelStyleUnitType {
            return when (v) {
                platform.YandexMapsMobile.YMKModelStyleUnitType.YMKModelStyleUnitTypeUnit -> ModelStyleUnitType.UNIT
                platform.YandexMapsMobile.YMKModelStyleUnitType.YMKModelStyleUnitTypeNormalized -> ModelStyleUnitType.NORMALIZED
                platform.YandexMapsMobile.YMKModelStyleUnitType.YMKModelStyleUnitTypeMeter -> ModelStyleUnitType.METER
                else -> error("unknown YMKModelStyleUnitType")
            }
        }
    }
}

fun ModelStyleUnitType.fromKmp(): platform.YandexMapsMobile.YMKModelStyleUnitType {
    return when (this) {
        ModelStyleUnitType.UNIT -> platform.YandexMapsMobile.YMKModelStyleUnitType.YMKModelStyleUnitTypeUnit
        ModelStyleUnitType.NORMALIZED -> platform.YandexMapsMobile.YMKModelStyleUnitType.YMKModelStyleUnitTypeNormalized
        ModelStyleUnitType.METER -> platform.YandexMapsMobile.YMKModelStyleUnitType.YMKModelStyleUnitTypeMeter
    }
}

actual enum class ModelStyleRenderMode {
    /**
     * Model should be rendered with buildings from ground layer.
     */
    BUILDING,
    /**
     * Model should be rendered within separate sublayer.
     */
    USER_MODEL,;

    companion object {
        fun toKmp(v: platform.Foundation.NSNumber): ModelStyleRenderMode {
            return toKmp(platform.YandexMapsMobile.YMKModelStyleRenderMode.byValue(v.unsignedLongValue))
        }

        fun toKmp(v: platform.YandexMapsMobile.YMKModelStyleRenderMode): ModelStyleRenderMode {
            return when (v) {
                platform.YandexMapsMobile.YMKModelStyleRenderMode.YMKModelStyleRenderModeBuilding -> ModelStyleRenderMode.BUILDING
                platform.YandexMapsMobile.YMKModelStyleRenderMode.YMKModelStyleRenderModeUserModel -> ModelStyleRenderMode.USER_MODEL
                else -> error("unknown YMKModelStyleRenderMode")
            }
        }
    }
}

fun ModelStyleRenderMode.fromKmp(): platform.YandexMapsMobile.YMKModelStyleRenderMode {
    return when (this) {
        ModelStyleRenderMode.BUILDING -> platform.YandexMapsMobile.YMKModelStyleRenderMode.YMKModelStyleRenderModeBuilding
        ModelStyleRenderMode.USER_MODEL -> platform.YandexMapsMobile.YMKModelStyleRenderMode.YMKModelStyleRenderModeUserModel
    }
}

