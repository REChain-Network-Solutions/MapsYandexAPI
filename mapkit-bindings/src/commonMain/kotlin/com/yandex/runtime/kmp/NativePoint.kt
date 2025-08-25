package com.yandex.runtime.kmp

expect class NativePoint

expect val NativePoint.mpX: Float

expect val NativePoint.mpY: Float

expect object NativePointFactory {
    fun create(x: Float, y: Float): NativePoint
}
