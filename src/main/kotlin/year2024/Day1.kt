package year2024

import me.koendev.*
import kotlin.math.abs

fun main() {
    part1(getInput(2024, 1)).println()
    part2(getInput(2024, 1)).println()
}


private fun part1(input: List<String>): Int {
    val left: MutableList<Int> = mutableListOf()
    val right: MutableList<Int> = mutableListOf()
    for (line in input) {
        val splits = line.split("   ")
        left.add(splits[0].toInt())
        right.add(splits[1].toInt())
    }
    left.sort()
    right.sort()

    var sum = 0
    for (i in left.indices) {
        sum += abs( left[i] - right[i] )
    }

    return sum
}


private fun part2(input: List<String>): Int {
    val left: MutableList<Int> = mutableListOf()
    val right: MutableList<Int> = mutableListOf()
    for (line in input) {
        val splits = line.split("   ")
        left.add(splits[0].toInt())
        right.add(splits[1].toInt())
    }
    left.sort()

    var sum = 0
    for (i in left.indices) {
        sum += left[i] * right.count { it == left[i] }
    }

    return sum
}
