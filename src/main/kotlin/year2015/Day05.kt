package me.koendev.year2015

import me.koendev.*

fun main() {
    solve(
        2015,
        5,
        ::part1,
        ::part2
    )
}


private fun part1(input: List<String>): Int {
    var sum = 0
    for (line in input) {
        val vowels = Regex("^.*[aeiou].*[aeiou].*[aeiou].*\$").matches(line)
        val doubleChar = Regex("(.)\\1+").containsMatchIn(line)
        val noForbiddenSequence = !Regex("ab|xy|cd|pq").containsMatchIn(line)

        if (vowels && doubleChar && noForbiddenSequence) {
            sum++
        }
    }

    return sum
}


private fun part2(input: List<String>): Int {
    var sum = 0
    for (line in input) {
        val aabbaa = Regex("(..).*\\1").containsMatchIn(line)
        val aba = Regex("(.).\\1+").containsMatchIn(line)

        if (aabbaa && aba) {
            sum++
        }
    }

    return sum
}
