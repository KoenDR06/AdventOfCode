package me.koendev.year2024

import me.koendev.*

fun main() {
    solve(
        2024,
        4,
        ::part1,
        ::part2
    )
}


private fun part1(input: List<String>): Int {
    val modifiers: List<List<Int>> = listOf(
        listOf( 0, 0, 0,-1,-2,-3), // Left
        listOf( 1, 2, 3,-1,-2,-3), // Down Left
        listOf( 1, 2, 3, 0, 0, 0), // Down
        listOf( 1, 2, 3, 1, 2, 3), // Down Right
        listOf( 0, 0, 0, 1, 2, 3), // Right
        listOf(-1,-2,-3, 1, 2, 3), // Up Right
        listOf(-1,-2,-3, 0, 0, 0), // Up
        listOf(-1,-2,-3,-1,-2,-3), // Up Left
    )

    var sum = 0
    for ((y, line) in input.withIndex()) {
        for ((x, char) in line.withIndex()) {
            if (char != 'X') continue

            for (m in modifiers) {
                try {
                    if (input[y+m[0]][x+m[3]] == 'M' &&
                        input[y+m[1]][x+m[4]] == 'A' &&
                        input[y+m[2]][x+m[5]] == 'S') sum++
                } catch (_: IndexOutOfBoundsException) {}
            }

        }
    }

    return sum
}


private fun part2(input: List<String>): Int {
    var sum = 0
    for ((y, line) in input.withIndex()) {
        for ((x, char) in line.withIndex()) {
            if (char != 'A') continue

            var lineFound = false
            try {
                if (input[y - 1][x - 1] == 'M' && input[y + 1][x + 1] == 'S' ||
                    input[y - 1][x - 1] == 'S' && input[y + 1][x + 1] == 'M'
                ) lineFound = true

                if ((input[y - 1][x + 1] == 'M' && input[y + 1][x - 1] == 'S' ||
                    input[y - 1][x + 1] == 'S' && input[y + 1][x - 1] == 'M') && lineFound
                ) sum++
            } catch (_: IndexOutOfBoundsException) {}
        }
    }

    return sum
}
