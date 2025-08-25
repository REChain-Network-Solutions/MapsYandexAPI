@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

import com.yandex.runtime.kmp.internal.toBoolean
import com.yandex.runtime.kmp.internal.toNSNumber

actual typealias OrgOwnershipObjectMetadata = platform.YandexMapsMobile.YMKSearchOrgOwnershipObjectMetadata

actual val OrgOwnershipObjectMetadata.mpCanBeClaimed: Boolean?
    get() = canBeClaimed()?.toBoolean()

actual object OrgOwnershipObjectMetadataFactory {
    actual fun create(
        canBeClaimed: Boolean?,
    ): OrgOwnershipObjectMetadata {
        return OrgOwnershipObjectMetadata.orgOwnershipObjectMetadataWithCanBeClaimed(
            canBeClaimed?.toNSNumber(),
        )
    }
}

actual val com.yandex.runtime.kmp.TypeDictionary<com.yandex.mapkit.kmp.BaseMetadata>.orgOwnershipObjectMetadata: OrgOwnershipObjectMetadata?
    get() = impl.getItemOfClass(OrgOwnershipObjectMetadata) as? OrgOwnershipObjectMetadata

