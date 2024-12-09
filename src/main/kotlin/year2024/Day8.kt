package year2024

import me.koendev.*

fun main() {
    solve(
        2024,
        8,
        ::part1,
        ::part2
    )
}

private data class Cell(var x: Int, var y: Int, var freq: Int)

private fun part1(input: List<String>): Int {
    val antennas = mutableListOf<Cell>()
    val sizeX = input[0].length
    val sizeY = input.size

    for ((y, line) in input.withIndex()) {
        for ((x, char) in line.withIndex()) {
            if (char == '.') continue

            antennas.add(Cell(x, y, char.code))
        }
    }

    val antinodes = mutableSetOf<Pair<Int,Int>>()
    for (antenna in antennas) {
        val other = antennas.filter { it.freq == antenna.freq && it != antenna }

        for (candidate in other) {
            val antinodeX = 2*candidate.x-antenna.x
            val antinodeY = 2*candidate.y-antenna.y

            if (antinodeX in 0..< sizeX &&
                antinodeY in 0..< sizeY &&
                Pair(antinodeX, antinodeY) !in antinodes) antinodes.add(Pair(antinodeX, antinodeY))
        }
    }

    return antinodes.size
}


private fun part2(input: List<String>): Int {
    val antennas = mutableListOf<Cell>()
    val sizeX = input[0].length
    val sizeY = input.size

    for ((y, line) in input.withIndex()) {
        for ((x, char) in line.withIndex()) {
            if (char == '.') continue

            antennas.add(Cell(x, y, char.code))
        }
    }

    val antinodes = mutableSetOf<Pair<Int,Int>>()
    for (antenna in antennas) {
        val other = antennas.filter { it.freq == antenna.freq && it != antenna }

        for (candidate in other) {
            val dx = candidate.x-antenna.x
            val dy = candidate.y-antenna.y

            var antinodeX = candidate.x
            var antinodeY = candidate.y

            while (antinodeX in 0..< sizeX && antinodeY in 0..< sizeY) {
                if (Pair(antinodeX, antinodeY) !in antinodes) antinodes.add(Pair(antinodeX, antinodeY))
                antinodeX += dx
                antinodeY += dy
            }
        }
    }

    return antinodes.size
}
