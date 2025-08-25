@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

expect class OrgOwnershipObjectMetadata

expect val OrgOwnershipObjectMetadata.mpCanBeClaimed: Boolean?

expect object OrgOwnershipObjectMetadataFactory {
    fun create(
        canBeClaimed: Boolean?,
    ): OrgOwnershipObjectMetadata
}

expect val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.orgOwnershipObjectMetadata: OrgOwnershipObjectMetadata?

