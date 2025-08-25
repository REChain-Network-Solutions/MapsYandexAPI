@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

/**
 * The type of reference.
 */
actual typealias ReferenceType = platform.YandexMapsMobile.YMKSearchReferenceType

/**
 * Reference ID.
 */
actual val ReferenceType.mpId: String
    get() = id()
/**
 * Reference scope.
 */
actual val ReferenceType.mpScope: String
    get() = scope()

actual object ReferenceTypeFactory {
    actual fun create(
        id: String,
        scope: String,
    ): ReferenceType {
        return ReferenceType.referenceTypeWithId(
            id,
            scope,
        )
    }
}

/**
 * Reference metadata information.
 */
actual typealias ReferencesObjectMetadata = platform.YandexMapsMobile.YMKSearchReferencesObjectMetadata

/**
 * The  list of references.
 */
actual val ReferencesObjectMetadata.mpReferences: kotlin.collections.List<com.yandex.mapkit.search.kmp.ReferenceType>
    get() = references().let { it as kotlin.collections.List<platform.YandexMapsMobile.YMKSearchReferenceType> }

actual object ReferencesObjectMetadataFactory {
    actual fun create(
        references: kotlin.collections.List<com.yandex.mapkit.search.kmp.ReferenceType>,
    ): ReferencesObjectMetadata {
        return ReferencesObjectMetadata.referencesObjectMetadataWithReferences(
            references.let { it as kotlin.collections.List<*> },
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.referencesObjectMetadata: ReferencesObjectMetadata?
    get() = impl.getItemOfClass(ReferencesObjectMetadata) as? ReferencesObjectMetadata

