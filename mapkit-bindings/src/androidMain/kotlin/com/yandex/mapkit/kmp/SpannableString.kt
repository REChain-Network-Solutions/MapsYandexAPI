@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp

/**
 * A string that supports spans in it.
 */
actual typealias SpannableString = com.yandex.mapkit.SpannableString

/**
 * The text of the spannable string.
 */
actual val SpannableString.mpText: String
    get() = text
/**
 * The spans in stored text.
 */
actual val SpannableString.mpSpans: kotlin.collections.List<com.yandex.mapkit.kmp.SpannableStringSpan>
    get() = spans

actual object SpannableStringFactory {
    actual fun create(
        text: String,
        spans: kotlin.collections.List<com.yandex.mapkit.kmp.SpannableStringSpan>,
    ): SpannableString {
        return SpannableString(
            text,
            spans,
        )
    }
}

/**
 * A span of text in the SpannableString.
 */
actual typealias SpannableStringSpan = com.yandex.mapkit.SpannableString.Span

/**
 * The index of the beginning symbol of the span.
 */
actual val SpannableStringSpan.mpBegin: Int
    get() = begin
/**
 * The index of the ending symbol of the span.
 */
actual val SpannableStringSpan.mpEnd: Int
    get() = end

actual object SpannableStringSpanFactory {
    actual fun create(
        begin: Int,
        end: Int,
    ): SpannableStringSpan {
        return SpannableStringSpan(
            begin,
            end,
        )
    }
}

