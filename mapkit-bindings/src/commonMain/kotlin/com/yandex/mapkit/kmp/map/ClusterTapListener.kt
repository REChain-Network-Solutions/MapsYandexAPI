@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

expect interface ClusterTapListener {
    /**
     * Called when cluster is tapped. Return true if the event was handled.
     * Otherwise it will be passed to underlying objects.
     */
    fun onClusterTap(
        cluster: com.yandex.mapkit.kmp.map.Cluster,
    ): Boolean
}

