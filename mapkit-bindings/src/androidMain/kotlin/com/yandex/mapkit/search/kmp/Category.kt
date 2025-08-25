@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Single category (also known as rubric) description.
 */
actual typealias Category = com.yandex.mapkit.search.Category

/**
 * Category name.
 */
actual val Category.mpName: String
    get() = name
/**
 * Category class. Different categories can have the same class. For
 * example, "Bar", "Cafe" and "Restaurant" categories inhabit
 * "restaurants" category class.
 *
 */
actual val Category.mpCategoryClass: String?
    get() = categoryClass
/**
 * Additional non-structured data for the category.
 */
actual val Category.mpTags: kotlin.collections.List<String>
    get() = tags

actual object CategoryFactory {
    actual fun create(
        name: String,
        categoryClass: String?,
        tags: kotlin.collections.List<String>,
    ): Category {
        return Category(
            name,
            categoryClass,
            tags,
        )
    }
}

