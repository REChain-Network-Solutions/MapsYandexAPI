@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

expect interface ClusterListener {
    /**
     * This method is called when a new cluster is added to the collection
     * and should customize cluster appearance based on its content.
     */
    fun onClusterAdded(
        cluster: com.yandex.mapkit.kmp.map.Cluster,
    ): Unit
}

