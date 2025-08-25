@file:Suppress("DEPRECATION", "EXTENSION_SHADOWED_BY_MEMBER", "TYPEALIAS_EXPANSION_DEPRECATION")

package com.yandex.mapkit.kmp.map

/**
 * Listens for updates to the camera position.
 */
expect interface CameraListener {
    /**
     * Triggered when the camera position changed.
     *
     * @param map Event source.
     * @param cameraPosition Current camera position.
     * @param cameraUpdateReason The reason of camera update.
     * @param finished True if the camera finished moving, false otherwise.
     * If a movement is cancelled then cameraUpdateReason represents
     * initiator of cancellation.
     */
    fun onCameraPositionChanged(
        map: com.yandex.mapkit.kmp.map.Map,
        cameraPosition: com.yandex.mapkit.kmp.map.CameraPosition,
        cameraUpdateReason: com.yandex.mapkit.kmp.map.CameraUpdateReason,
        finished: Boolean,
    ): Unit
}

