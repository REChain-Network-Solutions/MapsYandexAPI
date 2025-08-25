@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

/**
 * A string that supports spans in it.
 */
expect class SpannableString

/**
 * The text of the spannable string.
 */
expect val SpannableString.mpText: String
/**
 * The spans in stored text.
 */
expect val SpannableString.mpSpans: kotlin.collections.List<com.yandex.mapkit.kmp.SpannableStringSpan>

expect object SpannableStringFactory {
    fun create(
        text: String,
        spans: kotlin.collections.List<com.yandex.mapkit.kmp.SpannableStringSpan>,
    ): SpannableString
}

/**
 * A span of text in the SpannableString.
 */
expect class SpannableStringSpan

/**
 * The index of the beginning symbol of the span.
 */
expect val SpannableStringSpan.mpBegin: Int
/**
 * The index of the ending symbol of the span.
 */
expect val SpannableStringSpan.mpEnd: Int

expect object SpannableStringSpanFactory {
    fun create(
        begin: Int,
        end: Int,
    ): SpannableStringSpan
}

