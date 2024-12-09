package year2024

import me.koendev.*
import kotlin.collections.List
import kotlin.collections.mutableMapOf
import kotlin.collections.set
import kotlin.collections.withIndex

fun main() {
    solve(
        2024,
        6,
        ::part1,
        ::part2
    )
}


private fun part1(input: List<String>): Int {
    val visited = mutableMapOf<Pair<Int, Int>, Boolean>()

    var x = 0
    var y = 0
    var direction = 0

    for ((searchY, line)in input.withIndex()) {
        for ((searchX, char) in line.withIndex()) {
            if (char != '^' &&
                char != '>' &&
                char != 'v' &&
                char != '<') continue

            visited[Pair(searchX, searchY)] = true
            x = searchX
            y = searchY
            direction = when(char) {
                '^' -> 0
                '>' -> 1
                'v' -> 2
                '<' -> 3
                else -> throw Exception()
            }
        }
    }

    try {
        while (true) {
            when (direction) {
                0 -> if (input[y - 1][x] == '#') direction = 1
                1 -> if (input[y][x + 1] == '#') direction = 2
                2 -> if (input[y + 1][x] == '#') direction = 3
                3 -> if (input[y][x - 1] == '#') direction = 0
            }

            when (direction) {
                0 -> y--
                1 -> x++
                2 -> y++
                3 -> x--
                else -> throw Exception()
            }
            visited[Pair(x, y)] = true
        }
    } catch (e: IndexOutOfBoundsException) {
        return visited.size
    }
}

private fun pathIsLoop(input: MutableList<String>): Boolean {
    val visited = mutableMapOf<Pair<Int, Int>, Int>()

    var x = 0
    var y = 0
    var direction = 0

    for ((searchY, line)in input.withIndex()) {
        for ((searchX, char) in line.withIndex()) {
            if (char != '^' &&
                char != '>' &&
                char != 'v' &&
                char != '<') continue

            x = searchX
            y = searchY
            direction = when(char) {
                '^' -> 0
                '>' -> 1
                'v' -> 2
                '<' -> 3
                else -> throw Exception()
            }
            visited[Pair(searchX, searchY)] = direction
        }
    }

    when (direction) {
        0 -> if (input[y - 1][x] == '#') direction = 1
        1 -> if (input[y][x + 1] == '#') direction = 2
        2 -> if (input[y + 1][x] == '#') direction = 3
        3 -> if (input[y][x - 1] == '#') direction = 0
    }
    when (direction) {
        0 -> if (input[y - 1][x] == '#') direction = 1
        1 -> if (input[y][x + 1] == '#') direction = 2
        2 -> if (input[y + 1][x] == '#') direction = 3
        3 -> if (input[y][x - 1] == '#') direction = 0
    }
    when (direction) {
        0 -> if (input[y - 1][x] == '#') direction = 1
        1 -> if (input[y][x + 1] == '#') direction = 2
        2 -> if (input[y + 1][x] == '#') direction = 3
        3 -> if (input[y][x - 1] == '#') direction = 0
    }
    when (direction) {
        0 -> if (input[y - 1][x] == '#') direction = 1
        1 -> if (input[y][x + 1] == '#') direction = 2
        2 -> if (input[y + 1][x] == '#') direction = 3
        3 -> if (input[y][x - 1] == '#') direction = 0
    }

    try {
        while (true) {
            when (direction) {
                0 -> y--
                1 -> x++
                2 -> y++
                3 -> x--
                else -> throw Exception()
            }

            when (direction) {
                0 -> if (input[y - 1][x] == '#') direction = 1
                1 -> if (input[y][x + 1] == '#') direction = 2
                2 -> if (input[y + 1][x] == '#') direction = 3
                3 -> if (input[y][x - 1] == '#') direction = 0
            }
            when (direction) {
                0 -> if (input[y - 1][x] == '#') direction = 1
                1 -> if (input[y][x + 1] == '#') direction = 2
                2 -> if (input[y + 1][x] == '#') direction = 3
                3 -> if (input[y][x - 1] == '#') direction = 0
            }
            when (direction) {
                0 -> if (input[y - 1][x] == '#') direction = 1
                1 -> if (input[y][x + 1] == '#') direction = 2
                2 -> if (input[y + 1][x] == '#') direction = 3
                3 -> if (input[y][x - 1] == '#') direction = 0
            }
            when (direction) {
                0 -> if (input[y - 1][x] == '#') direction = 1
                1 -> if (input[y][x + 1] == '#') direction = 2
                2 -> if (input[y + 1][x] == '#') direction = 3
                3 -> if (input[y][x - 1] == '#') direction = 0
            }

            if (visited[Pair(x,y)] == direction) return true
            visited[Pair(x,y)] = direction
        }
    } catch (e: IndexOutOfBoundsException) {
        return false
    }
}

private fun part2(input: List<String>): Int {
    var sum = 0
    for ((y, line) in input.withIndex()) {
        for ((x, char) in line.withIndex()) {
            if (char != '.') continue

            val grid = input.toMutableList()
            grid[y] = grid[y].slice(0..< x) + '#' + grid[y].slice(x+1..< grid[y].length)


            if (pathIsLoop(grid)) sum++
        }
    }
    return sum
}
