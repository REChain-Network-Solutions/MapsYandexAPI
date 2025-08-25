@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.kmp

import com.yandex.runtime.kmp.internal.toInt
import com.yandex.runtime.kmp.internal.toNSNumber

/**
 * A string that supports spans in it.
 */
actual typealias SpannableString = platform.YandexMapsMobile.YMKSpannableString

/**
 * The text of the spannable string.
 */
actual val SpannableString.mpText: String
    get() = text()
/**
 * The spans in stored text.
 */
actual val SpannableString.mpSpans: kotlin.collections.List<com.yandex.mapkit.kmp.SpannableStringSpan>
    get() = spans().let { it as kotlin.collections.List<platform.YandexMapsMobile.YMKSpannableStringSpan> }

actual object SpannableStringFactory {
    actual fun create(
        text: String,
        spans: kotlin.collections.List<com.yandex.mapkit.kmp.SpannableStringSpan>,
    ): SpannableString {
        return SpannableString.spannableStringWithText(
            text,
            spans.let { it as kotlin.collections.List<*> },
        )
    }
}

/**
 * A span of text in the SpannableString.
 */
actual typealias SpannableStringSpan = platform.YandexMapsMobile.YMKSpannableStringSpan

/**
 * The index of the beginning symbol of the span.
 */
actual val SpannableStringSpan.mpBegin: Int
    get() = begin().toInt()
/**
 * The index of the ending symbol of the span.
 */
actual val SpannableStringSpan.mpEnd: Int
    get() = end().toInt()

actual object SpannableStringSpanFactory {
    actual fun create(
        begin: Int,
        end: Int,
    ): SpannableStringSpan {
        return SpannableStringSpan.spanWithBegin(
            begin.toLong(),
            end.toLong(),
        )
    }
}

