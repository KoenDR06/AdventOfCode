package me.koendev.year2015

import me.koendev.*

fun main() {
    solve(
        2015,
        8,
        ::part1,
        ::part2
    )
}


private fun part1(input: List<String>): Int {
    var sum = 0
    for (line in input) {
        sum += 2

        var offset = 0
        for (i in 1..< line.length-2) {
            if (i+offset+1 > line.length) break

            if (line[i+offset] == '\\') {
                if (line[i+1+offset] == 'x') sum += 3
                else if (line[i+1+offset] == '"') sum += 1
                else if (line[i+1+offset] == '\\') {
                    sum += 1
                    offset += 1
                }
            }
        }
    }

    return sum
}


private fun part2(input: List<String>): Int {
    var sum = 0
    for (line in input) {
        sum += 2

        for (char in line) {
            if (char == '"') sum += 1
            else if (char == '\\') sum += 1
        }
    }

    return sum
}
