package year2015

import me.koendev.*

fun main() {
    part1(getInput(2015, 12)).println()
}

private fun part1(input: List<String>): Int {
    var sum = 0
    for (token in input[0].split("{","}","[","]",":",",")) {
        if (token != "" && token[0] != '"') sum += token.toInt()
    }

    return sum
}