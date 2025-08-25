@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.search.kmp

/**
 * Single category (also known as rubric) description.
 */
expect class Category

/**
 * Category name.
 */
expect val Category.mpName: String
/**
 * Category class. Different categories can have the same class. For
 * example, "Bar", "Cafe" and "Restaurant" categories inhabit
 * "restaurants" category class.
 *
 */
expect val Category.mpCategoryClass: String?
/**
 * Additional non-structured data for the category.
 */
expect val Category.mpTags: kotlin.collections.List<String>

expect object CategoryFactory {
    fun create(
        name: String,
        categoryClass: String?,
        tags: kotlin.collections.List<String>,
    ): Category
}

