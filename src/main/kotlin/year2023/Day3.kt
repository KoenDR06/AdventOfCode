package me.koendev.year2023

import me.koendev.*

fun main() {
    part1(getInput(2023, 3)).println()
}


private fun part1(input: List<String>) : Int {
    val sum = 0

    for((y, line) in input.withIndex()) {
        for((x, char) in line.withIndex()) {
            if(char.digitToIntOrNull() is Int) {
                checkSpace(input, Pair(x, y)).println()
                x.println()
                y.println()
                println()

            }
        }
    }

    return sum
}

fun checkSpace(input: List<String>, coords: Pair<Int, Int>) : Boolean {
    var foundCharacter = false

    for(x in coords.first - 1..coords.first) {
        for(y in coords.second - 1..coords.second) {
            try {
                if(input[x][y].digitToIntOrNull() == null && input[x][y] != '.') {
                    foundCharacter = true
                }
            } catch(e:IndexOutOfBoundsException) {}
        }
    }

    return foundCharacter
}
