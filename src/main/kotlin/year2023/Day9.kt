package year2023

import println
import getInput

fun main() {
    part1(getInput(2023, 9)).println()
    part2(getInput(2023, 9)).println()
}

private fun part1(input: List<String>): Int {
    // Parse lines to integers
    val lines = mutableListOf< MutableList<Int> >()
    for (line in input) {
        val lineSplit = line.split(' ')
        val intLine = mutableListOf<Int>()
        for (n in lineSplit) {
            intLine.add(n.toInt())
        }
        lines.add(intLine)
    }

    var sum = 0
    for (line in lines) {
        val diffs = mutableListOf(line)
        while (diffs.last().any { it != diffs.last().first() }) {
            val res = mutableListOf<Int>()
            for (i in 0..< diffs.last().count() - 1) res.add(diffs.last()[i + 1] - diffs.last()[i])
            diffs.add(res)
        }

        while (diffs.count() != 1) {
            val constantDifference = diffs.last().last()

            diffs[diffs.count()-2].add(diffs[diffs.count()-2].last()+constantDifference)

            diffs.removeLast()
        }
        sum += diffs.last().last()
    }


    return sum
}

private fun part2(input: List<String>): Int {
    // Parse lines to integers
    val lines = mutableListOf< MutableList<Int> >()
    for (line in input) {
        val lineSplit = line.split(' ')
        val intLine = mutableListOf<Int>()
        for (n in lineSplit) {
            intLine.add(n.toInt())
        }
        lines.add(intLine)
    }

    var sum = 0
    for (line in lines) {
        val diffs = mutableListOf(line)
        while (diffs.last().any { it != diffs.last().first() }) {
            val res = mutableListOf<Int>()
            for (i in 0..< diffs.last().count() - 1) res.add(diffs.last()[i + 1] - diffs.last()[i])
            diffs.add(res)
        }

        while (diffs.count() != 1) {
            val constantDifference = diffs.last().first()

            diffs[diffs.count()-2].add(0, diffs[diffs.count()-2].first()-constantDifference)

            diffs.removeLast()
        }
        sum += diffs.last().first()
    }


    return sum
}