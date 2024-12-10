package year2024

import me.koendev.*

fun main() {
solve(
    2024,
    10,
    ::part1,
    ::part2
    )
}

private fun walkTrails(x: Int, y: Int, grid: List<List<Int>>, peaks: MutableSet<Pair<Int, Int>> = mutableSetOf()): Int {
    val cur = grid[y][x]
    if (cur == 9) {
        if (Pair(x,y) in peaks) return 0
        peaks.add(Pair(x, y))
        return 1
    }

    var sum = 0
    if (y > 0 && grid[y-1][x] == cur + 1) sum += walkTrails(x, y-1, grid, peaks)
    if (y < grid.size-1 && grid[y+1][x] == cur + 1) sum += walkTrails(x, y+1, grid, peaks)
    if (x > 0 && grid[y][x-1] == cur + 1) sum += walkTrails(x-1, y, grid, peaks)
    if (x < grid[0].size-1 && grid[y][x+1] == cur + 1) sum += walkTrails(x+1, y, grid, peaks)
    return sum
}

private fun part1(input: List<String>): Int {
    val mutableGrid = MutableList<MutableList<Int>>(input.size) { mutableListOf() }

    for ((y, line) in input.withIndex()) {
        line.forEach { mutableGrid[y].add(it.digitToInt()) }
    }
    val grid = mutableGrid.map { it.toList() }

    var sum = 0

    for ((y, row) in grid.withIndex()) {
        for ((x, digit) in row.withIndex()) {
            if (digit != 0) continue

            sum += walkTrails(x, y, grid)
        }
    }

    return sum
}

private fun walkTrails2(x: Int, y: Int, grid: List<List<Int>>): Int {
    val cur = grid[y][x]
    if (cur == 9) {
        return 1
    }

    var sum = 0
    if (y > 0 && grid[y-1][x] == cur + 1) sum += walkTrails2(x, y-1, grid)
    if (y < grid.size-1 && grid[y+1][x] == cur + 1) sum += walkTrails2(x, y+1, grid)
    if (x > 0 && grid[y][x-1] == cur + 1) sum += walkTrails2(x-1, y, grid)
    if (x < grid[0].size-1 && grid[y][x+1] == cur + 1) sum += walkTrails2(x+1, y, grid)
    return sum
}

private fun part2(input: List<String>): Int {
    val mutableGrid = MutableList<MutableList<Int>>(input.size) { mutableListOf() }

    for ((y, line) in input.withIndex()) {
        line.forEach { mutableGrid[y].add(it.digitToInt()) }
    }
    val grid = mutableGrid.map { it.toList() }

    var sum = 0

    for ((y, row) in grid.withIndex()) {
        for ((x, digit) in row.withIndex()) {
            if (digit != 0) continue

            sum += walkTrails2(x, y, grid)
        }
    }

    return sum
}
