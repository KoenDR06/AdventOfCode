package me.koendev

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
    this.println()
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
    else mutableListOf<List<T>>().also{result ->
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
class MutableTriple<A, B, C>(
    var first: A,
    var second: B,
    var third: C

)

// Grid Utils
@Suppress("Unused")
fun List<String>.get(x: Int, y: Int) = this[y][x]

@Suppress("Unused")
fun List<String>.getColumn(col: Int): String {
    var res = ""

    for (line in this) {
        res += line[col]
    }

    return res
}

@Suppress("Unused")
fun List<String>.transpose(): List<String> {
    if (!this.all { it.length == this[0].length }) throw IllegalArgumentException("Input is not a grid.")

    val res = MutableList(this[0].length) { "" }
    for ((y, line) in this.withIndex()) {
        for ((x, char) in line.withIndex()) {
            res[x] = res[x] + char
        }
    }
    return res
}