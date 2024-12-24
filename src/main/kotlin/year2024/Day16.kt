package me.koendev.year2024

import me.koendev.utils.*
import me.koendev.*
import kotlin.math.min

fun main() {
    solve(
        2024,
        16,
        ::part1,
        ::part2
    )
}

private data class Position(val x: Int, val y: Int, val direction: Int)

private fun part1(input: List<String>): Int {
    val grid = Grid(input.map { it.toCharArray().toList() })

    val (startX, startY) = grid.find('S')!!

    val visited: MutableMap<Position, Int> = mutableMapOf()

    val queue: MutableMap<Position, Int> = mutableMapOf(
        Position(startX, startY, 1) to 0
    )

    while (queue.isNotEmpty()) {
        val (p, score) = queue.minBy { it.value }
        queue.remove(p)

        if (grid.get(p.x, p.y) == 'E') return score

        if (visited.contains(p)) {
            visited[p] = min(visited[p]!!, score)
            continue
        } else visited[p] = score

        val (dx, dy) = when(p.direction) {
            0 -> Pair(0,-1)
            1 -> Pair(1,0)
            2 -> Pair(0,1)
            3 -> Pair(-1,0)
            else -> throw Exception("Unreachable")
        }

        if (grid.get(p.x+dx, p.y+dy) != '#') queue[Position(p.x+dx, p.y+dy, p.direction)] = score+1
        if (grid.get(p.x-dy, p.y+dx) != '#') queue[Position(p.x, p.y, (p.direction+1)%4)] = score+1000
        if (grid.get(p.x+dy, p.y-dx) != '#') queue[Position(p.x, p.y, (p.direction+3)%4)] = score+1000
    }

    throw Exception("Unreachable")
}

private fun part2(input: List<String>): Int {
    val minScore = part1(input)

    val grid = Grid(input.map { it.toCharArray().toList() })

    val (startX, startY) = grid.find('S')!!

    val minPathCells: MutableSet<Pair<Int, Int>> = mutableSetOf()

    fun dfs(grid: Grid<Char>, pair: Pair<Int, List<Position>>) {
        val score = pair.first
        val path = pair.second
        val p = path.last()

        if (score > minScore) return

        if (grid.get(p.x, p.y) == 'E') {
            minPathCells.addAll(path.map { Pair(it.x, it.y) })
            println("Path Found!")
        }

        val (dx, dy) = when(p.direction) {
            0 -> Pair(0,-1)
            1 -> Pair(1,0)
            2 -> Pair(0,1)
            3 -> Pair(-1,0)
            else -> throw Exception("Unreachable")
        }

        if (grid.get(p.x+dx, p.y+dy) != '#') { dfs(grid, Pair(score+1, path.copy()+Position(p.x+dx, p.y+dy, p.direction))) }
        if (grid.get(p.x-dy, p.y+dx) != '#') dfs(grid, Pair(score+1001, path.copy()+Position(p.x-dy, p.y+dx, (p.direction+1)%4)))
        if (grid.get(p.x+dy, p.y-dx) != '#') dfs(grid, Pair(score+1001, path.copy()+Position(p.x+dy, p.y-dx, (p.direction+3)%4)))
    }

    dfs(grid, Pair(0, listOf(Position(startX, startY, 1))))

    return minPathCells.size
}