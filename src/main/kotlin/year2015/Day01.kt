package me.koendev.year2015

import me.koendev.*

fun main() {
    solve(
        2015,
        1,
        ::part1,
        ::part2
    )
}


private fun part1(input: List<String>): Int {
    var floor = 0
    for (char in input[0]) {
        if (char == '(') {
            floor++
        } else {
            floor--
        }
    }
    return floor
}


private fun part2(input: List<String>): Int {
    var floor = 0
    var index = 0

    while (floor != -1) {
        val char = input[0][index++]
        if (char == '(') {
            floor++
        } else {
            floor--
        }
    }

    return index
}
