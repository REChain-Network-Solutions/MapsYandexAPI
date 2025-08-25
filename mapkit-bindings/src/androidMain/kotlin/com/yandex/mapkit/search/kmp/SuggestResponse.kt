@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * A single suggested item.
 */
actual typealias SuggestItem = com.yandex.mapkit.search.SuggestItem

/**
 * Suggested object type.
 */
actual val SuggestItem.mpType: com.yandex.mapkit.search.kmp.SuggestItemType
    get() = type
/**
 * Short object name.
 */
actual val SuggestItem.mpTitle: com.yandex.mapkit.kmp.SpannableString
    get() = title
/**
 * If type is TOPONYM returns reversed toponym hierarchy, if type is
 * BUSINESS returns business address.
 *
 */
actual val SuggestItem.mpSubtitle: com.yandex.mapkit.kmp.SpannableString?
    get() = subtitle
/**
 * Additional free-form data for suggest item. If type is TOPONYM,
 * returns toponym kind (house/street/locality/...). If type is
 * BUSINESS, returns category class (drugstores/restaurants/...).
 */
actual val SuggestItem.mpTags: kotlin.collections.List<String>
    get() = tags
/**
 * Text to search for.
 */
actual val SuggestItem.mpSearchText: String
    get() = searchText
/**
 * Text to display if searchText is too technical to display.
 *
 */
actual val SuggestItem.mpDisplayText: String?
    get() = displayText
/**
 * Element uri, if applicable.
 *
 */
actual val SuggestItem.mpUri: String?
    get() = uri
/**
 * Element link, if applicable.
 *
 */
actual val SuggestItem.mpLink: String?
    get() = link
/**
 * Optional distance localized value.
 *
 */
actual val SuggestItem.mpDistance: com.yandex.mapkit.kmp.LocalizedValue?
    get() = distance
/**
 * If the suggested item respects personalization.
 */
actual val SuggestItem.mpIsPersonal: Boolean
    get() = isPersonal
/**
 * Action to perform on click/tap/enter.
 */
actual val SuggestItem.mpAction: com.yandex.mapkit.search.kmp.SuggestItemAction
    get() = action
/**
 * Id for request logging.
 *
 */
actual val SuggestItem.mpLogId: String?
    get() = logId
/**
 * Item is from offline search.
 */
actual val SuggestItem.mpIsOffline: Boolean
    get() = isOffline
/**
 * Item is a word suggest item.
 */
actual val SuggestItem.mpIsWordItem: Boolean
    get() = isWordItem
/**
 * Additional item properties.
 */
actual val SuggestItem.mpProperties: kotlin.collections.List<com.yandex.runtime.kmp.KeyValuePair>
    get() = properties
/**
 * Position of object.
 *
 */
actual val SuggestItem.mpCenter: com.yandex.mapkit.kmp.geometry.Point?
    get() = center
/**
 * Detailed subtype of the Business type
 *
 */
actual val SuggestItem.mpBusinessContext: com.yandex.mapkit.search.kmp.SuggestItemBusinessContext?
    get() = businessContext

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
        return SuggestItem(
            type,
            title,
            subtitle,
            tags,
            searchText,
            displayText,
            uri,
            link,
            distance,
            isPersonal,
            action,
            logId,
            isOffline,
            isWordItem,
            properties,
            center,
            businessContext,
        )
    }
}

/**
 * Suggest item type.
 */
actual typealias SuggestItemType = com.yandex.mapkit.search.SuggestItem.Type

/**
 * Action to be performed when user selected suggest item.
 */
actual typealias SuggestItemAction = com.yandex.mapkit.search.SuggestItem.Action

/**
 * More detailed info about type=Business response
 */
actual typealias SuggestItemBusinessContext = com.yandex.mapkit.search.SuggestItem.BusinessContext


/**
 * A suggest response
 */
actual typealias SuggestResponse = com.yandex.mapkit.search.SuggestResponse

/**
 * Suggest items.
 */
actual val SuggestResponse.mpItems: kotlin.collections.List<com.yandex.mapkit.search.kmp.SuggestItem>
    get() = items

actual object SuggestResponseFactory {
    actual fun create(
        items: kotlin.collections.List<com.yandex.mapkit.search.kmp.SuggestItem>,
    ): SuggestResponse {
        return SuggestResponse(items)
    }
}

