package com.yandex.runtime.kmp.image

expect abstract class ImageProvider

expect fun emptyImageProvider(): ImageProvider

expect fun ImageProvider.id(): String

expect fun ByteArray.asImage(): ImageProvider?
