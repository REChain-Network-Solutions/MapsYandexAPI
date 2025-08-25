@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

import com.yandex.runtime.kmp.internal.toBoolean
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * Describes some common feature of organizations. Can be of three
 * types:
 *
 * - boolean (like on/off switch, as for free Wi-Fi availability).
 *
 * - enumerated (can have multiple values at once, like cuisine types in
 * a cafe).
 *
 * - text (like enumerated but with any strings instead of predefined
 * values).
 */
actual typealias Feature = platform.YandexMapsMobile.YMKSearchFeature

/**
 * Machine readable feature identifier.
 */
actual val Feature.mpId: String
    get() = id()
/**
 * Feature value (depends on feature type).
 */
actual val Feature.mpValue: com.yandex.mapkit.search.kmp.FeatureVariantValue
    get() = value()
/**
 * Human readable localized representation.
 *
 */
actual val Feature.mpName: String?
    get() = name()
/**
 * Reference to information source providing given feature (see
 * [Attribution])
 *
 */
actual val Feature.mpAref: String?
    get() = aref()
actual val Feature.mpIconLight: com.yandex.mapkit.kmp.Image?
    get() = iconLight()
actual val Feature.mpIconDark: com.yandex.mapkit.kmp.Image?
    get() = iconDark()

actual object FeatureFactory {
    actual fun create(
        id: String,
        value: com.yandex.mapkit.search.kmp.FeatureVariantValue,
        name: String?,
        aref: String?,
        iconLight: com.yandex.mapkit.kmp.Image?,
        iconDark: com.yandex.mapkit.kmp.Image?,
    ): Feature {
        return Feature.featureWithId(
            id,
            value,
            name,
            aref,
            iconLight,
            iconDark,
        )
    }
}

/**
 * Value for enumerated features.
 */
actual typealias FeatureEnumValue = platform.YandexMapsMobile.YMKSearchFeatureEnumValue

/**
 * Machine readable value identifier.
 */
actual val FeatureEnumValue.mpId: String
    get() = id()
/**
 * Human readable localized representation.
 */
actual val FeatureEnumValue.mpName: String
    get() = name()
/**
 * urlTemplate for the image. Available sizes are listed here:
 * http://api.yandex.ru/fotki/doc/format-ref/f-img.xml
 *
 */
actual val FeatureEnumValue.mpImageUrlTemplate: String?
    get() = imageUrlTemplate()
actual val FeatureEnumValue.mpTags: kotlin.collections.List<String>
    get() = tags().let { it as kotlin.collections.List<String> }

actual object FeatureEnumValueFactory {
    actual fun create(
        id: String,
        name: String,
        imageUrlTemplate: String?,
        tags: kotlin.collections.List<String>,
    ): FeatureEnumValue {
        return FeatureEnumValue.enumValueWithId(
            id,
            name,
            imageUrlTemplate,
            tags.let { it as kotlin.collections.List<*> },
        )
    }
}

actual typealias FeatureBooleanValue = platform.YandexMapsMobile.YMKSearchFeatureBooleanValue

actual val FeatureBooleanValue.mpValue: Boolean
    get() = value()

actual object FeatureBooleanValueFactory {
    actual fun create(
        value: Boolean,
    ): FeatureBooleanValue {
        return FeatureBooleanValue.booleanValueWithValue(
            value,
        )
    }
}

/**
 * A variant combining possible feature values.
 */
actual typealias FeatureVariantValue = platform.YandexMapsMobile.YMKSearchFeatureVariantValue

actual val FeatureVariantValue.booleanValue: com.yandex.mapkit.search.kmp.FeatureBooleanValue?
    get() = booleanValue
actual val FeatureVariantValue.textValue: kotlin.collections.List<String>?
    get() = textValue.let { it as kotlin.collections.List<String> }
actual val FeatureVariantValue.enumValue: kotlin.collections.List<com.yandex.mapkit.search.kmp.FeatureEnumValue>?
    get() = enumValue.let { it as kotlin.collections.List<platform.YandexMapsMobile.YMKSearchFeatureEnumValue> }

actual fun FeatureVariantValueFromBooleanValue(booleanValue: com.yandex.mapkit.search.kmp.FeatureBooleanValue): FeatureVariantValue = platform.YandexMapsMobile.YMKSearchFeatureVariantValue.variantValueWithBooleanValue(booleanValue)
actual fun FeatureVariantValueFromTextValue(textValue: kotlin.collections.List<String>): FeatureVariantValue = platform.YandexMapsMobile.YMKSearchFeatureVariantValue.variantValueWithTextValue(textValue)
actual fun FeatureVariantValueFromEnumValue(enumValue: kotlin.collections.List<com.yandex.mapkit.search.kmp.FeatureEnumValue>): FeatureVariantValue = platform.YandexMapsMobile.YMKSearchFeatureVariantValue.variantValueWithEnumValue(enumValue)

/**
 * Collection of features.
 */
actual typealias FeatureSet = platform.YandexMapsMobile.YMKSearchFeatureSet

/**
 * IDs for features in the collection.
 */
actual val FeatureSet.mpIds: kotlin.collections.List<String>
    get() = ids().let { it as kotlin.collections.List<String> }

actual object FeatureSetFactory {
    actual fun create(
        ids: kotlin.collections.List<String>,
    ): FeatureSet {
        return FeatureSet.featureSetWithIds(
            ids.let { it as kotlin.collections.List<*> },
        )
    }
}

/**
 * Group of features.
 */
actual typealias FeatureGroup = platform.YandexMapsMobile.YMKSearchFeatureGroup

/**
 * Group name.
 *
 */
actual val FeatureGroup.mpName: String?
    get() = name()
/**
 * IDs for features in the group.
 */
actual val FeatureGroup.mpIds: kotlin.collections.List<String>
    get() = ids().let { it as kotlin.collections.List<String> }

actual object FeatureGroupFactory {
    actual fun create(
        name: String?,
        ids: kotlin.collections.List<String>,
    ): FeatureGroup {
        return FeatureGroup.featureGroupWithName(
            name,
            ids.let { it as kotlin.collections.List<*> },
        )
    }
}

