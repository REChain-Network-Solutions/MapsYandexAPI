@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Interface denoting ongoing search session. Allows search cancellation
 * and retry.  For many request types allows further searches.
 */
actual typealias Session = com.yandex.mapkit.search.Session

actual typealias SessionSearchListener = com.yandex.mapkit.search.Session.SearchListener

