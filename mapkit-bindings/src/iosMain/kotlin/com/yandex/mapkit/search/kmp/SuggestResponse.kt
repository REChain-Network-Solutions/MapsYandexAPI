@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST", "REDUNDANT_ELSE_IN_WHEN")

package com.yandex.mapkit.search.kmp

import com.yandex.runtime.kmp.internal.toBoolean
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * A single suggested item.
 */
actual typealias SuggestItem = platform.YandexMapsMobile.YMKSuggestItem

/**
 * Suggested object type.
 */
actual val SuggestItem.mpType: com.yandex.mapkit.search.kmp.SuggestItemType
    get() = type().let { com.yandex.mapkit.search.kmp.SuggestItemType.toKmp(it) }
/**
 * Short object name.
 */
actual val SuggestItem.mpTitle: com.yandex.mapkit.kmp.SpannableString
    get() = title()
/**
 * If type is TOPONYM returns reversed toponym hierarchy, if type is
 * BUSINESS returns business address.
 *
 */
actual val SuggestItem.mpSubtitle: com.yandex.mapkit.kmp.SpannableString?
    get() = subtitle()
/**
 * Additional free-form data for suggest item. If type is TOPONYM,
 * returns toponym kind (house/street/locality/...). If type is
 * BUSINESS, returns category class (drugstores/restaurants/...).
 */
actual val SuggestItem.mpTags: kotlin.collections.List<String>
    get() = tags().let { it as kotlin.collections.List<String> }
/**
 * Text to search for.
 */
actual val SuggestItem.mpSearchText: String
    get() = searchText()
/**
 * Text to display if searchText is too technical to display.
 *
 */
actual val SuggestItem.mpDisplayText: String?
    get() = displayText()
/**
 * Element uri, if applicable.
 *
 */
actual val SuggestItem.mpUri: String?
    get() = uri()
/**
 * Element link, if applicable.
 *
 */
actual val SuggestItem.mpLink: String?
    get() = link()
/**
 * Optional distance localized value.
 *
 */
actual val SuggestItem.mpDistance: com.yandex.mapkit.kmp.LocalizedValue?
    get() = distance()
/**
 * If the suggested item respects personalization.
 */
actual val SuggestItem.mpIsPersonal: Boolean
    get() = isPersonal()
/**
 * Action to perform on click/tap/enter.
 */
actual val SuggestItem.mpAction: com.yandex.mapkit.search.kmp.SuggestItemAction
    get() = action().let { com.yandex.mapkit.search.kmp.SuggestItemAction.toKmp(it) }
/**
 * Id for request logging.
 *
 */
actual val SuggestItem.mpLogId: String?
    get() = logId()
/**
 * Item is from offline search.
 */
actual val SuggestItem.mpIsOffline: Boolean
    get() = isOffline()
/**
 * Item is a word suggest item.
 */
actual val SuggestItem.mpIsWordItem: Boolean
    get() = isWordItem()
/**
 * Additional item properties.
 */
actual val SuggestItem.mpProperties: kotlin.collections.List<com.yandex.runtime.kmp.KeyValuePair>
    get() = properties().let { it as kotlin.collections.List<platform.YandexMapsMobile.YRTKeyValuePair> }
/**
 * Position of object.
 *
 */
actual val SuggestItem.mpCenter: com.yandex.mapkit.kmp.geometry.Point?
    get() = center()
/**
 * Detailed subtype of the Business type
 *
 */
actual val SuggestItem.mpBusinessContext: com.yandex.mapkit.search.kmp.SuggestItemBusinessContext?
    get() = businessContext()?.let { com.yandex.mapkit.search.kmp.SuggestItemBusinessContext.toKmp(it) }

actual object SuggestItemFactory {
    actual fun create(
        type: com.yandex.mapkit.search.kmp.SuggestItemType,
        title: com.yandex.mapkit.kmp.SpannableString,
        subtitle: com.yandex.mapkit.kmp.SpannableString?,
        tags: kotlin.collections.List<String>,
        searchText: String,
        displayText: String?,
        uri: String?,
        link: String?,
        distance: com.yandex.mapkit.kmp.LocalizedValue?,
        isPersonal: Boolean,
        action: com.yandex.mapkit.search.kmp.SuggestItemAction,
        logId: String?,
        isOffline: Boolean,
        isWordItem: Boolean,
        properties: kotlin.collections.List<com.yandex.runtime.kmp.KeyValuePair>,
        center: com.yandex.mapkit.kmp.geometry.Point?,
        businessContext: com.yandex.mapkit.search.kmp.SuggestItemBusinessContext?,
    ): SuggestItem {
        return SuggestItem.suggestItemWithType(
            type.fromKmp(),
            title,
            subtitle,
            tags.let { it as kotlin.collections.List<*> },
            searchText,
            displayText,
            uri,
            link,
            distance,
            isPersonal,
            action.fromKmp(),
            logId,
            isOffline,
            isWordItem,
            properties.let { it as kotlin.collections.List<*> },
            center,
            businessContext?.let { platform.Foundation.NSNumber(unsignedLong = it.fromKmp().value) },
        )
    }
}

/**
 * Suggest item type.
 */
actual enum class SuggestItemType {
    /**
     * For backward compatibility in future.
     */
    UNKNOWN,
    /**
     * City, street, house etc.
     */
    TOPONYM,
    /**
     * Company with specific location.
     */
    BUSINESS,
    /**
     * Public transport route number or transit-related keyword.
     */
    TRANSIT,
    /**
     * Web link or deep link
     */
    LINK,;

    companion object {
        fun toKmp(v: platform.Foundation.NSNumber): SuggestItemType {
            return toKmp(platform.YandexMapsMobile.YMKSuggestItemType.byValue(v.unsignedLongValue))
        }

        fun toKmp(v: platform.YandexMapsMobile.YMKSuggestItemType): SuggestItemType {
            return when (v) {
                platform.YandexMapsMobile.YMKSuggestItemType.YMKSuggestItemTypeUnknown -> SuggestItemType.UNKNOWN
                platform.YandexMapsMobile.YMKSuggestItemType.YMKSuggestItemTypeToponym -> SuggestItemType.TOPONYM
                platform.YandexMapsMobile.YMKSuggestItemType.YMKSuggestItemTypeBusiness -> SuggestItemType.BUSINESS
                platform.YandexMapsMobile.YMKSuggestItemType.YMKSuggestItemTypeTransit -> SuggestItemType.TRANSIT
                platform.YandexMapsMobile.YMKSuggestItemType.YMKSuggestItemTypeLink -> SuggestItemType.LINK
                else -> error("unknown YMKSuggestItemType")
            }
        }
    }
}

fun SuggestItemType.fromKmp(): platform.YandexMapsMobile.YMKSuggestItemType {
    return when (this) {
        SuggestItemType.UNKNOWN -> platform.YandexMapsMobile.YMKSuggestItemType.YMKSuggestItemTypeUnknown
        SuggestItemType.TOPONYM -> platform.YandexMapsMobile.YMKSuggestItemType.YMKSuggestItemTypeToponym
        SuggestItemType.BUSINESS -> platform.YandexMapsMobile.YMKSuggestItemType.YMKSuggestItemTypeBusiness
        SuggestItemType.TRANSIT -> platform.YandexMapsMobile.YMKSuggestItemType.YMKSuggestItemTypeTransit
        SuggestItemType.LINK -> platform.YandexMapsMobile.YMKSuggestItemType.YMKSuggestItemTypeLink
    }
}

/**
 * Action to be performed when user selected suggest item.
 */
actual enum class SuggestItemAction {
    /**
     * Immediately search for `text`.
     */
    SEARCH,
    /**
     * Substitute query by `text` for further editing.
     */
    SUBSTITUTE,
    /**
     * Exit suggest session and open link
     */
    FOLLOW_LINK,;

    companion object {
        fun toKmp(v: platform.Foundation.NSNumber): SuggestItemAction {
            return toKmp(platform.YandexMapsMobile.YMKSuggestItemAction.byValue(v.unsignedLongValue))
        }

        fun toKmp(v: platform.YandexMapsMobile.YMKSuggestItemAction): SuggestItemAction {
            return when (v) {
                platform.YandexMapsMobile.YMKSuggestItemAction.YMKSuggestItemActionSearch -> SuggestItemAction.SEARCH
                platform.YandexMapsMobile.YMKSuggestItemAction.YMKSuggestItemActionSubstitute -> SuggestItemAction.SUBSTITUTE
                platform.YandexMapsMobile.YMKSuggestItemAction.YMKSuggestItemActionFollowLink -> SuggestItemAction.FOLLOW_LINK
                else -> error("unknown YMKSuggestItemAction")
            }
        }
    }
}

fun SuggestItemAction.fromKmp(): platform.YandexMapsMobile.YMKSuggestItemAction {
    return when (this) {
        SuggestItemAction.SEARCH -> platform.YandexMapsMobile.YMKSuggestItemAction.YMKSuggestItemActionSearch
        SuggestItemAction.SUBSTITUTE -> platform.YandexMapsMobile.YMKSuggestItemAction.YMKSuggestItemActionSubstitute
        SuggestItemAction.FOLLOW_LINK -> platform.YandexMapsMobile.YMKSuggestItemAction.YMKSuggestItemActionFollowLink
    }
}

/**
 * More detailed info about type=Business response
 */
actual enum class SuggestItemBusinessContext {
    BUSINESS_CONTEXT_UNKNOWN,
    BUSINESS_CONTEXT_ORG1,
    BUSINESS_CONTEXT_RUBRIC,
    BUSINESS_CONTEXT_CHAIN,;

    companion object {
        fun toKmp(v: platform.Foundation.NSNumber): SuggestItemBusinessContext {
            return toKmp(platform.YandexMapsMobile.YMKSuggestItemBusinessContext.byValue(v.unsignedLongValue))
        }

        fun toKmp(v: platform.YandexMapsMobile.YMKSuggestItemBusinessContext): SuggestItemBusinessContext {
            return when (v) {
                platform.YandexMapsMobile.YMKSuggestItemBusinessContext.YMKSuggestItemBusinessContextBusinessContextUnknown -> SuggestItemBusinessContext.BUSINESS_CONTEXT_UNKNOWN
                platform.YandexMapsMobile.YMKSuggestItemBusinessContext.YMKSuggestItemBusinessContextBusinessContextOrg1 -> SuggestItemBusinessContext.BUSINESS_CONTEXT_ORG1
                platform.YandexMapsMobile.YMKSuggestItemBusinessContext.YMKSuggestItemBusinessContextBusinessContextRubric -> SuggestItemBusinessContext.BUSINESS_CONTEXT_RUBRIC
                platform.YandexMapsMobile.YMKSuggestItemBusinessContext.YMKSuggestItemBusinessContextBusinessContextChain -> SuggestItemBusinessContext.BUSINESS_CONTEXT_CHAIN
                else -> error("unknown YMKSuggestItemBusinessContext")
            }
        }
    }
}

fun SuggestItemBusinessContext.fromKmp(): platform.YandexMapsMobile.YMKSuggestItemBusinessContext {
    return when (this) {
        SuggestItemBusinessContext.BUSINESS_CONTEXT_UNKNOWN -> platform.YandexMapsMobile.YMKSuggestItemBusinessContext.YMKSuggestItemBusinessContextBusinessContextUnknown
        SuggestItemBusinessContext.BUSINESS_CONTEXT_ORG1 -> platform.YandexMapsMobile.YMKSuggestItemBusinessContext.YMKSuggestItemBusinessContextBusinessContextOrg1
        SuggestItemBusinessContext.BUSINESS_CONTEXT_RUBRIC -> platform.YandexMapsMobile.YMKSuggestItemBusinessContext.YMKSuggestItemBusinessContextBusinessContextRubric
        SuggestItemBusinessContext.BUSINESS_CONTEXT_CHAIN -> platform.YandexMapsMobile.YMKSuggestItemBusinessContext.YMKSuggestItemBusinessContextBusinessContextChain
    }
}

/**
 * A suggest response
 */
actual typealias SuggestResponse = platform.YandexMapsMobile.YMKSuggestResponse

/**
 * Suggest items.
 */
actual val SuggestResponse.mpItems: kotlin.collections.List<com.yandex.mapkit.search.kmp.SuggestItem>
    get() = items().let { it as kotlin.collections.List<platform.YandexMapsMobile.YMKSuggestItem> }

actual object SuggestResponseFactory {
    actual fun create(
        items: kotlin.collections.List<com.yandex.mapkit.search.kmp.SuggestItem>,
    ): SuggestResponse {
        return SuggestResponse.suggestResponseWithItems(
            items.let { it as kotlin.collections.List<*> },
        )
    }
}

