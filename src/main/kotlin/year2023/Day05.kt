package me.koendev.year2023

import me.koendev.*

fun main() {
    solve(
        2023,
        5,
        ::part1,
        ::part2
    )
}


private fun part1(input: List<String>): Long {
    val currentValues = input[0].substring(7).split(" ").map { MutablePair(it.toLong(), false) }.toMutableList()

    for (line in input.subList(2, input.size)) {
        val splits = line.split(" ")
        if (splits.size == 1) currentValues.forEach { it.second = false }
        if (splits.size != 3) continue

        val dest = splits[0].toLong()
        val source = splits[1].toLong()
        val size = splits[2].toLong()

        for (index in currentValues.indices) {
            val value = currentValues[index].first

            if (currentValues[index].second) continue

            if (value >= source && value < source+size) currentValues[index] = MutablePair(dest + (value-source), true)
        }
    }

    return currentValues.minOf { it.first }
}

private fun part2(input: List<String>) : Long {
    val lines = input.map { it.split(" ").map { item -> item.toLongOrNull() } }.subList(2, input.size).filter { it.size == 1 || it.size == 3 }

    val seedRanges = input[0].substring(7).split(" ").map { it.toLong() }.toMutableList()

    var min = Long.MAX_VALUE

    for (index in 0..< seedRanges.size step 2) {
        for (seed in seedRanges[index]..< seedRanges[index]+seedRanges[index+1]) {
            var value = seed
            var modified = false
            for (line in lines) {
                if (line.size == 1) modified = false
                if (line.size != 3 || modified ) continue

                val dest = line[0]!!
                val source = line[1]!!
                val size = line[2]!!

                if (value >= source && value < source+size) {
                    value = (dest + (value-source))
                    modified = true
                }
            }
            if (value < min) min = value
            if (seed % 1_000_000 == 0L) seed.println()
        }
        index.println()
    }

    return min
}