@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "UNCHECKED_CAST", "UNNECESSARY_NOT_NULL_ASSERTION", "USELESS_CAST")

package com.yandex.mapkit.search.kmp

/**
 * Single category (also known as rubric) description.
 */
actual typealias Category = platform.YandexMapsMobile.YMKSearchCategory

/**
 * Category name.
 */
actual val Category.mpName: String
    get() = name()
/**
 * Category class. Different categories can have the same class. For
 * example, "Bar", "Cafe" and "Restaurant" categories inhabit
 * "restaurants" category class.
 *
 */
actual val Category.mpCategoryClass: String?
    get() = categoryClass()
/**
 * Additional non-structured data for the category.
 */
actual val Category.mpTags: kotlin.collections.List<String>
    get() = tags().let { it as kotlin.collections.List<String> }

actual object CategoryFactory {
    actual fun create(
        name: String,
        categoryClass: String?,
        tags: kotlin.collections.List<String>,
    ): Category {
        return Category.categoryWithName(
            name,
            categoryClass,
            tags.let { it as kotlin.collections.List<*> },
        )
    }
}

