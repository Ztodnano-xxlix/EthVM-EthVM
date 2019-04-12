package com.ethvm.common.extensions

import java.math.BigInteger
import java.nio.ByteBuffer

/**
 * Omitting sign indication byte.
 * <br><br>
 * Instead of {@link org.spongycastle.util.BigIntegers#asUnsignedByteArray(BigInteger)}
 * <br>we use this custom method to avoid an empty array in case of BigInteger.ZERO
 *
 * @return A byte array without a leading zero byte if present in the signed encoding.
 *      BigInteger.ZERO will return an array with length 1 and byte-value 0.
 */
fun BigInteger?.unsignedBytes(): ByteArray? =
  when (this) {
    null -> null
    else -> {
      var data = this.toByteArray()
      if (data.size != 1 && data[0].toInt() == 0) {
        val tmp = ByteArray(data.size - 1)
        System.arraycopy(data, 1, tmp, 0, tmp.size)
        data = tmp
      }
      data
    }
  }

fun BigInteger.byteBuffer(): ByteBuffer = ByteBuffer.wrap(this.toByteArray())

fun BigInteger?.unsignedByteBuffer() = this?.unsignedBytes()?.byteBuffer()

fun BigInteger.toHex() = "0x${this.toString(16)}"
