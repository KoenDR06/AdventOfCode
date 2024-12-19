package me.koendev.utils

import java.io.Serializable
import java.math.BigInteger
import java.security.MessageDigest

@Suppress("Unused")
fun <T> T.println(): T {
    println(this)
    return this
}

@Suppress("Unused")
fun <T> T.wait(): T {
    readln()
    return this
}

@Suppress("Unused")
fun findLCM(a: Long, b: Long): Long {
    val larger = if (a > b) a else b
    val maxLcm = a * b
    var lcm = larger
    while (lcm <= maxLcm) {
        if (lcm % a == 0L && lcm % b == 0L) {
            return lcm
        }
        lcm += larger
    }
    return maxLcm
}

@Suppress("Unused")
fun findLCM(numbers: MutableList<Long>): Long {
    var result = numbers[0]
    for (i in 1..<numbers.size) {
        result = findLCM(result, numbers[i])
    }
    return result
}

@Suppress("Unused")
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)

@Suppress("Unused")
fun <T> List<T>.permutations(): List<List<T>> =
    if(isEmpty()) listOf(emptyList())
    else mutableListOf<List<T>>().also{ result ->
        for(i in this.indices){
            (this - this[i]).permutations().forEach {
                result.add(it + this[i])
            }
        }
    }

@Suppress("Unused")
fun MutableList<Int>.diffs(): List<Int> {
    val res = mutableListOf<Int>()

    for (index in 0..< this.size-1) {
        res.add(this[index] - this[index+1])
    }
    return res
}

@Suppress("Unused")
data class MutablePair<A, B>(
    var first: A,
    var second: B
) : Serializable

@Suppress("Unused")
data class MutableTriple<A, B, C>(
    var first: A,
    var second: B,
    var third: C
) : Serializable

@Suppress("Unused")
fun LongRange.length() = this.last - this.first + 1

@Suppress("Unused")
fun pow(base: Int, exp: Int): Int = (1..exp).fold(1) { acc, _ -> acc * base }