package year2015

import me.koendev.*

fun main() {
    part1(getInput(2015, 10)).println()
    part2(getInput(2015, 10)).println()
}

private fun part1(input: List<String>): Int {
    var inputArray: MutableList<Int> = mutableListOf()
    for (char in input[0]) {
        inputArray.add(char.toString().toInt())
    }

    for (i in 1..40) {
        val tokens: MutableList<MutablePair<Int, Int>> = mutableListOf( MutablePair(inputArray[0], 0) )
        for (char in inputArray) {
            if (tokens.last().first != char) {
                tokens.add(MutablePair(char, 0))
            }
            tokens.last().second++
        }

        val res: MutableList<Int> = mutableListOf()
        for (token in tokens) {
            res.add(token.second)
            res.add(token.first)
        }
        inputArray = res
    }

    return inputArray.size
}


private fun part2(input: List<String>): Int {
    var inputArray: MutableList<Int> = mutableListOf()
    for (char in input[0]) {
        inputArray.add(char.toString().toInt())
    }

    for (i in 1..50) {
        val tokens: MutableList<MutablePair<Int, Int>> = mutableListOf( MutablePair(inputArray[0], 0) )
        for (char in inputArray) {
            if (tokens.last().first != char) {
                tokens.add(MutablePair(char, 0))
            }
            tokens.last().second++
        }

        val res: MutableList<Int> = mutableListOf()
        for (token in tokens) {
            res.add(token.second)
            res.add(token.first)
        }
        inputArray = res
    }

    return inputArray.size
}
