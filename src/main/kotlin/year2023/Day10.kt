package me.koendev.year2023

import me.koendev.println
import me.koendev.getInput

fun main() {
    part1(getInput(2023, 10)).println()
}

private fun part1(input: List<String>): Int {
    // Locate animal
    var animalX = -1
    var animalY = -1
    for ((index, line) in input.withIndex()) {
        if (line.contains('S')) {
            animalX = line.indexOf('S')
            animalY = index

        }
    }

    var x = animalX
    var y = animalY
    var direction: Int // 0 up, 1 right, 2 down, 3 left

    if (input[y-1][x] == 'F' || input[y-1][x] == '7' || input[y-1][x] == '|') {
        direction = 0
        y--
    } else if (input[y][x+1] == '7' || input[y][x+1] == 'J' || input[y][x-1] == '-') {
        direction = 1
        x++
    } else if (input[y+1][x] == 'J' || input[y+1][x] == 'L' || input[y+1][x] == '|') {
        direction = 2
        y++
    } else if (input[y][x-1] == 'L' || input[y][x-1] == 'F' || input[y][x+1] == '-') {
        direction = 3
        x--
    } else {
        throw Exception("Unreachable")
    }

    var steps = 1
    while (input[y][x] != 'S') {
        val ch = input[y][x]

        if (ch == 'F') {
            when (direction) {
                0 -> { x++; direction = 1 }
                3 -> { y++; direction = 2 }
            }
        } else if (ch == '7') {
            when (direction) {
                0 -> { x--; direction = 3 }
                1 -> { y++; direction = 2 }
            }
        } else if (ch == '|') {
            when (direction) {
                0 -> { y--; direction = 0 }
                2 -> { y++; direction = 2 }
            }
        } else if (ch == 'L') {
            when (direction) {
                2 -> { x++; direction = 1 }
                3 -> { y--; direction = 0 }
            }
        } else if (ch == 'J') {
            when (direction) {
                2 -> { x--; direction = 3 }
                1 -> { y--; direction = 0 }
            }
        } else if (ch == '-') {
            when (direction) {
                1 -> { x++; direction = 1 }
                3 -> { x--; direction = 3 }
            }
        }
        steps++
    }


    return steps / 2
}