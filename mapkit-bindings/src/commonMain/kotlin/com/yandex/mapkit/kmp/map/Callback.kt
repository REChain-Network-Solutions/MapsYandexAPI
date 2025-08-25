@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

expect interface Callback {
    /**
     * The callback is invoked when the task is completed successfully.
     */
    fun onTaskFinished(): Unit
}

