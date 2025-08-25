package com.yandex.runtime.kmp.internal

import kotlinx.cinterop.addressOf
import kotlinx.cinterop.readBytes
import kotlinx.cinterop.usePinned
import platform.Foundation.NSData
import platform.Foundation.NSDate
import platform.Foundation.NSMutableData
import platform.Foundation.NSNumber
import platform.Foundation.appendBytes

fun NSData.toByteArray(): ByteArray {
    return this.bytes?.readBytes(this.length.toInt())?.copyOf() ?: ByteArray(0)
}

fun ByteArray.toNSData(): NSData {
    var data = NSMutableData()
    this.usePinned { pinned ->
        for (index in indices) {
            data.appendBytes(pinned.addressOf(index), 1u)
        }
    }
    return data
}

fun NSNumber.toBoolean(): Boolean = this.boolValue
fun NSNumber.toInt(): Int = this.intValue
fun NSNumber.toLong(): Long = this.longValue
fun NSNumber.toFloat(): Float = this.floatValue
fun NSNumber.toDouble(): Double = this.doubleValue
fun Boolean.toNSNumber(): NSNumber = NSNumber(this)
fun Int.toNSNumber(): NSNumber = NSNumber(this)
fun Long.toNSNumber(): NSNumber = NSNumber(long = this)
fun Float.toNSNumber(): NSNumber = NSNumber(this)
fun Double.toNSNumber(): NSNumber = NSNumber(this)
