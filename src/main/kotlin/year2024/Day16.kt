package me.koendev.year2024

import me.koendev.solve
import me.koendev.utils.Grid
import me.koendev.utils.graph.EdgeExistsException
import me.koendev.utils.graph.Graph
import me.koendev.utils.graph.Node
import me.koendev.utils.println
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
    val graph = Graph<Char, Position>()

    grid.filter { (_, _, char) -> char != '#' }.forEach { (x, y, char) ->
        graph.addNode(Node(char), Position(x, y, 0))
        graph.addNode(Node(char), Position(x, y, 1))
        graph.addNode(Node(char), Position(x, y, 2))
        graph.addNode(Node(char), Position(x, y, 3))

    }

    grid.filter { (_, _, char) -> char != '#' }.forEach { (x, y, _) ->
        try {
            if (x > 0 && grid.get(x - 1, y) != '#') graph.addEdge(Position(x - 1, y, 1), Position(x, y, 3))
            if (x < grid.width - 1 && grid.get(x + 1, y) != '#') graph.addEdge(Position(x + 1, y, 3), Position(x, y, 1))
            if (y > 0 && grid.get(x, y - 1) != '#') graph.addEdge(Position(x, y - 1, 2), Position(x, y, 0))
            if (y < grid.height - 1 && grid.get(x, y + 1) != '#') graph.addEdge(
                Position(x, y + 1, 2),
                Position(x, y, 0)
            )
        } catch(e: EdgeExistsException) {
            e.println()
        }
    }

    graph.nodes.forEach { (name, node) ->
        val neighbors = graph.getNeighbors(node)
        if (neighbors.size == 1) {}
    }

    graph.nodes.size.println()
    graph.removeDisconnectedNodes()
    graph.nodes.size.println()

    return 0
}