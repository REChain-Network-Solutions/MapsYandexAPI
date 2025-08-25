package com.yandex.mapkit.utils

import com.yandex.mapkit.kmp.geometry.Point
import com.yandex.mapkit.kmp.geometry.mpLatitude
import com.yandex.mapkit.kmp.geometry.mpLongitude
import kotlin.jvm.JvmName
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

fun Point.distanceTo(other: Point): Double {
    return distanceBetween(mpLatitude, mpLongitude, other.mpLatitude, other.mpLongitude)
}

fun distanceBetween(
    lat1: Double, lon1: Double,
    lat2: Double, lon2: Double
): Double {
    val earthRadius = 6_371_000.0

    val dLat = (lat2 - lat1).degreesToRadians()
    val dLon = (lon2 - lon1).degreesToRadians()

    val a = sin(dLat / 2).pow(2) +
        cos(lat1.degreesToRadians()) *
        cos(lat2.degreesToRadians()) *
        sin(dLon / 2).pow(2)

    val c = 2 * atan2(sqrt(a), sqrt(1 - a))

    return earthRadius * c
}

@JvmName("degreesToRadiansShared")
fun Double.degreesToRadians() = this * PI / 180.0

fun degreesToRadians(degrees: Double): Double {
    return degrees * (PI / 180.0)
}
