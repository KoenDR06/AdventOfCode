package year2024

import me.koendev.*

const val TIME = 100
const val WIDTH = 101
const val HEIGHT = 103

fun main() {
solve(
    2024,
    14,
    ::part1,
    ::part2
    )
}

private fun part1(input: List<String>): Long {
    val robots = mutableMapOf<Pair<Int, Int>, Int>()
    for (line in input) {
//        p,0,4,v,3,-3
        val splits = line.split("=", ",", " ")
        val posX = splits[1].toInt()
        val posY = splits[2].toInt()
        val veloX = splits[4].toInt()
        val veloY = splits[5].toInt()

        var endX = (posX + TIME * veloX) % WIDTH
        if (endX < 0) endX += WIDTH

        var endY = (posY + TIME * veloY) % HEIGHT
        if (endY < 0) endY += HEIGHT

        if (Pair(endX,endY) !in robots) robots[Pair(endX, endY)] = 0
        robots[Pair(endX, endY)] = robots[Pair(endX, endY)]!! + 1
    }

    val topLeft = robots.filter { robot ->
        robot.key.first < WIDTH / 2 &&
        robot.key.second < HEIGHT / 2
    }.values.sum().toLong()

    val topRight = robots.filter { robot ->
        robot.key.first > WIDTH / 2 &&
        robot.key.second < HEIGHT / 2
    }.values.sum().toLong()

    val bottomLeft = robots.filter { robot ->
        robot.key.first < WIDTH / 2 &&
        robot.key.second > HEIGHT / 2
    }.values.sum().toLong()

    val bottomRight = robots.filter { robot ->
        robot.key.first > WIDTH / 2 &&
        robot.key.second > HEIGHT / 2
    }.values.sum().toLong()

    return topLeft * topRight * bottomLeft * bottomRight
}


private fun part2(input: List<String>): Int {
    val robots = mutableListOf<List<String>>()
    for (line in input) {
        val splits = line.split("=", ",", " ")
        robots.add(splits)
    }

    var time = 1
    val grid = MutableList(HEIGHT) { MutableList(WIDTH) {' '} }
    while (time < 10000) {
        grid.forEachIndexed { y, line -> line.forEachIndexed { x, _ -> grid[y][x] = ' ' } } // Clear the grid
        for (robot in robots) {
//        p,0,4,v,3,-3
            val splits = robot
            val posX = splits[1].toInt()
            val posY = splits[2].toInt()
            val veloX = splits[4].toInt()
            val veloY = splits[5].toInt()

            var endX = (posX + time * veloX) % WIDTH
            if (endX < 0) endX += WIDTH

            var endY = (posY + time * veloY) % HEIGHT
            if (endY < 0) endY += HEIGHT

            grid[endY][endX] = '#'
        }

        val lines = grid.map { line ->
             String(line.toCharArray())
        }
        if (lines.any { "#    #####################    #" in it }) {
            return time
        }

        time++
    }
    throw Exception("Easter eggs is further away then expected, try to extend the iterations.")
}