@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

actual typealias OrgOwnershipObjectMetadata = com.yandex.mapkit.search.OrgOwnershipObjectMetadata

actual val OrgOwnershipObjectMetadata.mpCanBeClaimed: Boolean?
    get() = canBeClaimed

actual object OrgOwnershipObjectMetadataFactory {
    actual fun create(
        canBeClaimed: Boolean?,
    ): OrgOwnershipObjectMetadata {
        return OrgOwnershipObjectMetadata(
            canBeClaimed,
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.orgOwnershipObjectMetadata: OrgOwnershipObjectMetadata?
    get() = getItem(OrgOwnershipObjectMetadata::class.java)

