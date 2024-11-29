package year2015

import me.koendev.*

fun main() {
    part1(getInput(2015, 1)).println()
    part2(getInput(2015, 1)).println()
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
