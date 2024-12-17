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
data class MutableTriple<A, B, C>(
    var first: A,
    var second: B,
    var third: C

)

@Suppress("Unused")
class Grid<T>(input: List<List<T>>) {
    private var grid: MutableList<MutableList<T>> = mutableListOf()

    init {
        for (line in input) {
            grid.add(mutableListOf())
            for (char in line) {
                grid.last().add(char)
            }
        }
    }

    fun get(x: Int, y: Int): T {
        if (x < 0 || x > grid[0].size-1) throw IllegalArgumentException("X coordinate $x out of bounds for size ${grid[0].size}")
        if (y < 0 || y > grid.size-1) throw IllegalArgumentException("Y coordinate $y out of bounds for size ${grid.size}")
        return grid[y][x]
    }

    fun getRow(y: Int): List<T> {
        if (y < 0 || y > grid.size-1) throw IllegalArgumentException("Y coordinate $y out of bounds for size ${grid.size}")

        return grid[y]
    }

    fun getColumn(x: Int): List<T> {
        if (x < 0 || x > grid[0].size-1) throw IllegalArgumentException("X coordinate $x out of bounds for size ${grid[0].size}")

        val res = mutableListOf<T>()

        for (row in grid) {
            res.add(row[x])
        }

        return res
    }
}

@Suppress("Unused")
fun LongRange.length() = this.last - this.first + 1

@Suppress("Unused")
fun pow(base: Int, exp: Int): Int {
    var res = 1
    for (i in 1..exp) {
        res *= base
    }
    return res
}