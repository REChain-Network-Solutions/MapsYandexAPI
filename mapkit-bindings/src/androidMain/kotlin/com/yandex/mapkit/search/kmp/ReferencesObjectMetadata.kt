@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * The type of reference.
 */
actual typealias ReferenceType = com.yandex.mapkit.search.ReferenceType

/**
 * Reference ID.
 */
actual val ReferenceType.mpId: String
    get() = id
/**
 * Reference scope.
 */
actual val ReferenceType.mpScope: String
    get() = scope

actual object ReferenceTypeFactory {
    actual fun create(
        id: String,
        scope: String,
    ): ReferenceType {
        return ReferenceType(
            id,
            scope,
        )
    }
}

/**
 * Reference metadata information.
 */
actual typealias ReferencesObjectMetadata = com.yandex.mapkit.search.ReferencesObjectMetadata

/**
 * The  list of references.
 */
actual val ReferencesObjectMetadata.mpReferences: kotlin.collections.List<com.yandex.mapkit.search.kmp.ReferenceType>
    get() = references

actual object ReferencesObjectMetadataFactory {
    actual fun create(
        references: kotlin.collections.List<com.yandex.mapkit.search.kmp.ReferenceType>,
    ): ReferencesObjectMetadata {
        return ReferencesObjectMetadata(
            references,
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.referencesObjectMetadata: ReferencesObjectMetadata?
    get() = getItem(ReferencesObjectMetadata::class.java)

