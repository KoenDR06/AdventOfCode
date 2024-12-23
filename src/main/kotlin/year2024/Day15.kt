package me.koendev.year2024

import me.koendev.*
import me.koendev.utils.println
import me.koendev.utils.wait

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

        var dx = 0
        var dy = 0
        var cell = ' '
        when (move) {
            '<' -> {
                dx = -1
                dy = 0
                cell = left
            }
            '^' -> {
                dx = 0
                dy = -1
                cell = up
            }
            '>' -> {
                dx = 1
                dy = 0
                cell = right
            }
            'v' -> {
                dx = 0
                dy = 1
                cell = down
            }
        }

        if (cell == '.') {
            grid[robotY][robotX] = '.'
            robotX += dx
            robotY += dy
            grid[robotY][robotX] = '@'
        } else if (cell == 'O') {
            var rayX = robotX+dx
            var rayY = robotY+dy
            while (grid[rayY][rayX] == 'O') {
                rayX += dx
                rayY += dy
            }

            if (grid[rayY][rayX] == '.') {
                grid[rayY][rayX] = 'O'
                grid[robotY][robotX] = '.'
                robotX += dx
                robotY += dy
                grid[robotY][robotX] = '@'
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

private fun checkBoxes(grid: MutableList<MutableList<Char>>, x: Int, y: Int, dy: Int): Boolean {
    if (grid[y][x] == '#' || grid[y+dy][x] == '#') return false
    if (grid[y][x] == '.' || grid[y+dy][x] == '.') return true

    val dx = if (grid[y+dy][x] == '[') 1 else -1
    return checkBoxes(grid, x, y+dy, dy) && checkBoxes(grid, x+dx, y+dy, dy)
}

private fun moveBoxes(grid: MutableList<MutableList<Char>>, x: Int, y: Int, dy: Int) {
    if (grid[y+dy][x] in "[]") {
        val dx = if (grid[y+dy][x] == '[') 1 else -1
        moveBoxes(grid, x, y+dy, dy)
        moveBoxes(grid, x+dx, y+dy, dy)
    }

    if (grid[y][x] in "[]" && grid[y+dy][x] == '.') {
        grid[y+dy][x] = grid[y][x]
        grid[y][x] = '.'
    }
}

private fun part2(input: List<String>): Int {
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
                if (it == '@') {
                    grid.last().add(it)
                    grid.last().add('.')
                    robotX = 2*x
                    robotY = y
                } else if (it == 'O') {
                    grid.last().add('[')
                    grid.last().add(']')
                } else {
                    grid.last().add(it)
                    grid.last().add(it)
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
                } else if (left in "[]") {
                    var rayX = robotX-1
                    while (grid[robotY][rayX] in "[]") rayX--

                    if (grid[robotY][rayX] == '.') {
                        grid[robotY][rayX] = ']'
                        while (grid[robotY][rayX] in "[]") {
                            if (grid[robotY][rayX] == '[') grid[robotY][rayX] = ']' else grid[robotY][rayX] = '['
                            rayX++
                        }

                        grid[robotY][robotX] = '.'
                        robotX--
                        grid[robotY][robotX] = '@'
                    }
                }
            }
            '>' -> {
                if (right == '.') {
                    grid[robotY][robotX] = '.'
                    robotX++
                    grid[robotY][robotX] = '@'
                } else if (right in "[]") {
                    var rayX = robotX+1
                    while (grid[robotY][rayX] in "[]") rayX++

                    if (grid[robotY][rayX] == '.') {
                        grid[robotY][rayX] = '['
                        while (grid[robotY][rayX] in "[]") {
                            if (grid[robotY][rayX] == '[') grid[robotY][rayX] = ']' else grid[robotY][rayX] = '['
                            rayX--
                        }

                        grid[robotY][robotX] = '.'
                        robotX++
                        grid[robotY][robotX] = '@'
                    }
                }
            }
            '^' -> {
                if (up == '.') {
                    grid[robotY][robotX] = '.'
                    robotY--
                    grid[robotY][robotX] = '@'
                } else if (up in "[]") {
                    if (checkBoxes(grid, robotX, robotY, -1)) {
                        moveBoxes(grid, robotX, robotY, -1)
                        grid[robotY][robotX] = '.'
                        robotY--
                        grid[robotY][robotX] = '@'
                    }
                }
            }
            'v' -> {
                if (down == '.') {
                    grid[robotY][robotX] = '.'
                    robotY++
                    grid[robotY][robotX] = '@'
                } else if (down in "[]") {
                    if (checkBoxes(grid, robotX, robotY, 1)) {
                        moveBoxes(grid, robotX, robotY, 1)
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
            if (char == '[') {
                sum += x + 100*y
            }
        }
    }

    return sum
}