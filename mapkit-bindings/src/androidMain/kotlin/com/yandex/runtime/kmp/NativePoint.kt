package com.yandex.runtime.kmp

import android.graphics.PointF

actual typealias NativePoint = PointF

actual val NativePoint.mpX: Float
    get() = x

actual val NativePoint.mpY: Float
    get() = y

actual object NativePointFactory {
    actual fun create(x: Float, y: Float) = PointF(x, y)
}
