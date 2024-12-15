package me.koendev.year2024

import me.koendev.*

fun main() {
    solve(
        2024,
        15,
        ::part1,
        ::part2
    )
}


private fun part1(input: List<String>): Int {
    val grid = mutableListOf<MutableList<Char>>()
    val moves = mutableListOf<Char>()
    var robotX = 0
    var robotY = 0

    var gridLines = true
    for ((y, line) in input.withIndex()) {
        if (line == "") {
            gridLines = false
            continue
        }

        if (gridLines) {
            grid.add(mutableListOf())
            line.forEachIndexed { x, it ->
                grid.last().add(it)
                if (it == '@') {
                    robotX = x
                    robotY = y
                }
            }
        } else {
            line.forEach { moves.add(it) }
        }
    }

    for (move in moves) {
        val left = grid[robotY][robotX-1]
        val right = grid[robotY][robotX+1]
        val up = grid[robotY-1][robotX]
        val down = grid[robotY+1][robotX]

        when (move) {
            '<' -> {
                if (left == '.') {
                    grid[robotY][robotX] = '.'
                    robotX--
                    grid[robotY][robotX] = '@'
                } else if (left == 'O') {
                    var rayX = robotX-1
                    while (grid[robotY][rayX] == 'O') rayX--

                    if (grid[robotY][rayX] == '.') {
                        grid[robotY][rayX] = 'O'
                        grid[robotY][robotX] = '.'
                        robotX--
                        grid[robotY][robotX] = '@'
                    }
                }
            }
            '^' -> {
                if (up == '.') {
                    grid[robotY][robotX] = '.'
                    robotY--
                    grid[robotY][robotX] = '@'
                } else if (up == 'O') {
                    var rayY = robotY-1
                    while (grid[rayY][robotX] == 'O') rayY--

                    if (grid[rayY][robotX] == '.') {
                        grid[rayY][robotX] = 'O'
                        grid[robotY][robotX] = '.'
                        robotY--
                        grid[robotY][robotX] = '@'
                    }
                }
            }
            '>' -> {
                if (right == '.') {
                    grid[robotY][robotX] = '.'
                    robotX++
                    grid[robotY][robotX] = '@'
                } else if (right == 'O') {
                    var rayX = robotX+1
                    while (grid[robotY][rayX] == 'O') rayX++

                    if (grid[robotY][rayX] == '.') {
                        grid[robotY][rayX] = 'O'
                        grid[robotY][robotX] = '.'
                        robotX++
                        grid[robotY][robotX] = '@'
                    }
                }
            }
            'v' -> {
                if (down == '.') {
                    grid[robotY][robotX] = '.'
                    robotY++
                    grid[robotY][robotX] = '@'
                } else if (down == 'O') {
                    var rayY = robotY+1
                    while (grid[rayY][robotX] == 'O') rayY++

                    if (grid[rayY][robotX] == '.') {
                        grid[rayY][robotX] = 'O'
                        grid[robotY][robotX] = '.'
                        robotY++
                        grid[robotY][robotX] = '@'
                    }
                }
            }
        }
    }

    var sum = 0
    for ((y, line) in grid.withIndex()) {
        for ((x, char) in line.withIndex()) {
            if (char == 'O') {
                sum += x + 100*y
            }
        }
    }

    return sum
}


private fun part2(input: List<String>): Int {


    return 0
}