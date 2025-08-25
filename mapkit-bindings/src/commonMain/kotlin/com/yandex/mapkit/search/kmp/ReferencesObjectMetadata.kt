@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * The type of reference.
 */
expect class ReferenceType

/**
 * Reference ID.
 */
expect val ReferenceType.mpId: String
/**
 * Reference scope.
 */
expect val ReferenceType.mpScope: String

expect object ReferenceTypeFactory {
    fun create(
        id: String,
        scope: String,
    ): ReferenceType
}

/**
 * Reference metadata information.
 */
expect class ReferencesObjectMetadata

/**
 * The  list of references.
 */
expect val ReferencesObjectMetadata.mpReferences: kotlin.collections.List<com.yandex.mapkit.search.kmp.ReferenceType>

expect object ReferencesObjectMetadataFactory {
    fun create(
        references: kotlin.collections.List<com.yandex.mapkit.search.kmp.ReferenceType>,
    ): ReferencesObjectMetadata
}

expect val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.referencesObjectMetadata: ReferencesObjectMetadata?

