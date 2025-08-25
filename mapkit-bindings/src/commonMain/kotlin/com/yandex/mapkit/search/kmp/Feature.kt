@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

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
expect class Feature

/**
 * Machine readable feature identifier.
 */
expect val Feature.mpId: String
/**
 * Feature value (depends on feature type).
 */
expect val Feature.mpValue: com.yandex.mapkit.search.kmp.FeatureVariantValue
/**
 * Human readable localized representation.
 *
 */
expect val Feature.mpName: String?
/**
 * Reference to information source providing given feature (see
 * [Attribution])
 *
 */
expect val Feature.mpAref: String?
expect val Feature.mpIconLight: com.yandex.mapkit.kmp.Image?
expect val Feature.mpIconDark: com.yandex.mapkit.kmp.Image?

expect object FeatureFactory {
    fun create(
        id: String,
        value: com.yandex.mapkit.search.kmp.FeatureVariantValue,
        name: String?,
        aref: String?,
        iconLight: com.yandex.mapkit.kmp.Image?,
        iconDark: com.yandex.mapkit.kmp.Image?,
    ): Feature
}

/**
 * Value for enumerated features.
 */
expect class FeatureEnumValue

/**
 * Machine readable value identifier.
 */
expect val FeatureEnumValue.mpId: String
/**
 * Human readable localized representation.
 */
expect val FeatureEnumValue.mpName: String
/**
 * urlTemplate for the image. Available sizes are listed here:
 * http://api.yandex.ru/fotki/doc/format-ref/f-img.xml
 *
 */
expect val FeatureEnumValue.mpImageUrlTemplate: String?
expect val FeatureEnumValue.mpTags: kotlin.collections.List<String>

expect object FeatureEnumValueFactory {
    fun create(
        id: String,
        name: String,
        imageUrlTemplate: String?,
        tags: kotlin.collections.List<String>,
    ): FeatureEnumValue
}

expect class FeatureBooleanValue

expect val FeatureBooleanValue.mpValue: Boolean

expect object FeatureBooleanValueFactory {
    fun create(
        value: Boolean,
    ): FeatureBooleanValue
}

/**
 * A variant combining possible feature values.
 */
expect class FeatureVariantValue

expect val FeatureVariantValue.booleanValue: com.yandex.mapkit.search.kmp.FeatureBooleanValue?
expect val FeatureVariantValue.textValue: kotlin.collections.List<String>?
expect val FeatureVariantValue.enumValue: kotlin.collections.List<com.yandex.mapkit.search.kmp.FeatureEnumValue>?

expect fun FeatureVariantValueFromBooleanValue(booleanValue: com.yandex.mapkit.search.kmp.FeatureBooleanValue): FeatureVariantValue
expect fun FeatureVariantValueFromTextValue(textValue: kotlin.collections.List<String>): FeatureVariantValue
expect fun FeatureVariantValueFromEnumValue(enumValue: kotlin.collections.List<com.yandex.mapkit.search.kmp.FeatureEnumValue>): FeatureVariantValue

/**
 * Collection of features.
 */
expect class FeatureSet

/**
 * IDs for features in the collection.
 */
expect val FeatureSet.mpIds: kotlin.collections.List<String>

expect object FeatureSetFactory {
    fun create(
        ids: kotlin.collections.List<String>,
    ): FeatureSet
}

/**
 * Group of features.
 */
expect class FeatureGroup

/**
 * Group name.
 *
 */
expect val FeatureGroup.mpName: String?
/**
 * IDs for features in the group.
 */
expect val FeatureGroup.mpIds: kotlin.collections.List<String>

expect object FeatureGroupFactory {
    fun create(
        name: String?,
        ids: kotlin.collections.List<String>,
    ): FeatureGroup
}

