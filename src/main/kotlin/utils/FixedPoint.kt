package me.koendev.utils

import kotlin.math.exp
import kotlin.math.ln

private const val BITS = 64
private const val INTEGER_BITS = 13

@Suppress("Unused", "MemberVisibilityCanBePrivate")
class FixedPoint {
    var number: Long = 0

    constructor(n: Int) {
        this.number = n.toLong()
    }

    constructor(n: Long) {
        this.number = n
    }

    constructor() {
        this.number = 0
    }

    fun floor(): FixedPoint {
        this.number = this.number shr (BITS - INTEGER_BITS - 1)
        this.number = this.number shl (BITS - INTEGER_BITS - 1)
        return this
    }

    fun ceil(): FixedPoint {
        this.number = this.number shr (BITS - INTEGER_BITS - 1)
        this.number++
        this.number = this.number shl (BITS - INTEGER_BITS - 1)

        return this
    }

    fun round(): FixedPoint {
        var absNumber = this.number
        var negative = 0
        if (this.number < 0) {
            negative = 1
            absNumber = absNumber.inv()
            absNumber += 1
        }

        val firstFractionBit = (absNumber shr (BITS - INTEGER_BITS - 2)) and 0x1

        return if (firstFractionBit != (1 xor (1-negative)).toLong()) {
            this.ceil()
        } else {
            this.floor()
        }
    }

    operator fun plus(other: FixedPoint): FixedPoint {
        return FixedPoint(this.number + other.number)
    }

    operator fun minus(other: FixedPoint): FixedPoint {
        return FixedPoint(this.number - other.number)
    }

    operator fun times(other: FixedPoint): FixedPoint {
        val res = FixedPoint()

        // BigInteger impl or just raw dog it with Long multiplication?

        return res
    }

    fun toDouble(): Double {
        var res = 0.0
        var absNumber = this.number
        var negative = false
        if (this.number < 0) {
            negative = true

            absNumber = absNumber.inv()
            absNumber += 1
        }

        var i = BITS - 2
        while (i >= 0) {
            val bit = (absNumber shr i--) and 1
            if (bit == 1L) {
                res += exp(ln(2.0) * (i-BITS+3))
            }
        }

        res *= 1L shl (INTEGER_BITS-1)

        if (negative) {
            res *= -1
        }

        return res
    }
}