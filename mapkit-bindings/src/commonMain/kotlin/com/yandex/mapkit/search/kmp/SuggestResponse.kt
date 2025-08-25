@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * A single suggested item.
 */
expect class SuggestItem

/**
 * Suggested object type.
 */
expect val SuggestItem.mpType: com.yandex.mapkit.search.kmp.SuggestItemType
/**
 * Short object name.
 */
expect val SuggestItem.mpTitle: com.yandex.mapkit.kmp.SpannableString
/**
 * If type is TOPONYM returns reversed toponym hierarchy, if type is
 * BUSINESS returns business address.
 *
 */
expect val SuggestItem.mpSubtitle: com.yandex.mapkit.kmp.SpannableString?
/**
 * Additional free-form data for suggest item. If type is TOPONYM,
 * returns toponym kind (house/street/locality/...). If type is
 * BUSINESS, returns category class (drugstores/restaurants/...).
 */
expect val SuggestItem.mpTags: kotlin.collections.List<String>
/**
 * Text to search for.
 */
expect val SuggestItem.mpSearchText: String
/**
 * Text to display if searchText is too technical to display.
 *
 */
expect val SuggestItem.mpDisplayText: String?
/**
 * Element uri, if applicable.
 *
 */
expect val SuggestItem.mpUri: String?
/**
 * Element link, if applicable.
 *
 */
expect val SuggestItem.mpLink: String?
/**
 * Optional distance localized value.
 *
 */
expect val SuggestItem.mpDistance: com.yandex.mapkit.kmp.LocalizedValue?
/**
 * If the suggested item respects personalization.
 */
expect val SuggestItem.mpIsPersonal: Boolean
/**
 * Action to perform on click/tap/enter.
 */
expect val SuggestItem.mpAction: com.yandex.mapkit.search.kmp.SuggestItemAction
/**
 * Id for request logging.
 *
 */
expect val SuggestItem.mpLogId: String?
/**
 * Item is from offline search.
 */
expect val SuggestItem.mpIsOffline: Boolean
/**
 * Item is a word suggest item.
 */
expect val SuggestItem.mpIsWordItem: Boolean
/**
 * Additional item properties.
 */
expect val SuggestItem.mpProperties: kotlin.collections.List<com.yandex.runtime.kmp.KeyValuePair>
/**
 * Position of object.
 *
 */
expect val SuggestItem.mpCenter: com.yandex.mapkit.kmp.geometry.Point?
/**
 * Detailed subtype of the Business type
 *
 */
expect val SuggestItem.mpBusinessContext: com.yandex.mapkit.search.kmp.SuggestItemBusinessContext?

expect object SuggestItemFactory {
    fun create(
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
    ): SuggestItem
}

/**
 * Suggest item type.
 */
expect enum class SuggestItemType {
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
    LINK,
}

/**
 * Action to be performed when user selected suggest item.
 */
expect enum class SuggestItemAction {
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
    FOLLOW_LINK,
}

/**
 * More detailed info about type=Business response
 */
expect enum class SuggestItemBusinessContext {
    BUSINESS_CONTEXT_UNKNOWN,
    BUSINESS_CONTEXT_ORG1,
    BUSINESS_CONTEXT_RUBRIC,
    BUSINESS_CONTEXT_CHAIN,
}

/**
 * A suggested group of items
 */



/**
 * A suggest response
 */
expect class SuggestResponse

/**
 * Suggest items.
 */
expect val SuggestResponse.mpItems: kotlin.collections.List<com.yandex.mapkit.search.kmp.SuggestItem>

expect object SuggestResponseFactory {
    fun create(
        items: kotlin.collections.List<com.yandex.mapkit.search.kmp.SuggestItem>,
    ): SuggestResponse
}

