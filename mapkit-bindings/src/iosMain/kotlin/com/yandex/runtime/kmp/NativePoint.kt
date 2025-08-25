package com.yandex.runtime.kmp

import kotlinx.cinterop.CValue
import kotlinx.cinterop.useContents
import platform.CoreGraphics.CGPoint
import platform.CoreGraphics.CGPointMake
import platform.Foundation.NSValue
import platform.UIKit.CGPointValue
import platform.UIKit.valueWithCGPoint

actual class NativePoint(val point: CValue<CGPoint>)

actual val NativePoint.mpX: Float
    get() = point.useContents { x.toFloat() }

actual val NativePoint.mpY: Float
    get() = point.useContents { y.toFloat() }

fun NativePoint.fromKmp(): CValue<CGPoint> {
    return point
}

fun NativePoint.fromKmpOptional(): NSValue {
    return NSValue.valueWithCGPoint(point)
}

fun CValue<CGPoint>.toKmp(): NativePoint {
    return NativePoint(this)
}

fun NSValue.toKmp(): NativePoint {
    return CGPointValue.toKmp()
}

actual object NativePointFactory {
    actual fun create(x: Float, y: Float): NativePoint {
        return NativePoint(CGPointMake(x.toDouble(), y.toDouble()))
    }
}
