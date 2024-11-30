package year2015

import me.koendev.*

fun main() {
    part1(getInput(2015, 6)).println()
    part2(getInput(2015, 6)).println()
}


private fun part1(input: List<String>): Int {
    val grid = MutableList(1000) {MutableList(1000) {false} }

    for (line in input) {
        if (line.startsWith("toggle ")) {
            val splits = line.split(" ")
            val coord1 = splits[1].split(",")
            val coord2 = splits[3].split(",")

            for (x in coord1[0].toInt()..coord2[0].toInt()) {
                for (y in coord1[1].toInt()..coord2[1].toInt()) {
                    grid[x][y] = !grid[x][y]
                }
            }

        } else if (line.startsWith("turn on ")) {
            val splits = line.split(" ")
            val coord1 = splits[2].split(",")
            val coord2 = splits[4].split(",")

            for (x in coord1[0].toInt()..coord2[0].toInt()) {
                for (y in coord1[1].toInt()..coord2[1].toInt()) {
                    grid[x][y] = true
                }
            }
        } else if (line.startsWith("turn off ")) {
            val splits = line.split(" ")
            val coord1 = splits[2].split(",")
            val coord2 = splits[4].split(",")

            for (x in coord1[0].toInt()..coord2[0].toInt()) {
                for (y in coord1[1].toInt()..coord2[1].toInt()) {
                    grid[x][y] = false
                }
            }
        }

    }

    var sum = 0
    for (row in grid) {
        for (light in row) {
            sum += if (light) 1 else 0
        }
    }

    return sum
}


private fun part2(input: List<String>): Int {
    val grid = MutableList(1000) {MutableList(1000) {0} }

    for (line in input) {
        if (line.startsWith("toggle ")) {
            val splits = line.split(" ")
            val coord1 = splits[1].split(",")
            val coord2 = splits[3].split(",")

            for (x in coord1[0].toInt()..coord2[0].toInt()) {
                for (y in coord1[1].toInt()..coord2[1].toInt()) {
                    grid[x][y] += 2
                }
            }

        } else if (line.startsWith("turn on ")) {
            val splits = line.split(" ")
            val coord1 = splits[2].split(",")
            val coord2 = splits[4].split(",")

            for (x in coord1[0].toInt()..coord2[0].toInt()) {
                for (y in coord1[1].toInt()..coord2[1].toInt()) {
                    grid[x][y] += 1
                }
            }
        } else if (line.startsWith("turn off ")) {
            val splits = line.split(" ")
            val coord1 = splits[2].split(",")
            val coord2 = splits[4].split(",")

            for (x in coord1[0].toInt()..coord2[0].toInt()) {
                for (y in coord1[1].toInt()..coord2[1].toInt()) {
                    grid[x][y] -= 1
                    if (grid[x][y] < 0) grid[x][y] = 0
                }
            }
        }

    }

    var sum = 0
    for (row in grid) {
        for (light in row) {
            sum += light
        }
    }

    return sum
}
