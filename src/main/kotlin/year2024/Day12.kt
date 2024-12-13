package year2024

import me.koendev.*

fun main() {
solve(
    2024,
    12,
    ::part1,
    ::part2
    )
}

private fun floodFill(x: Int, y: Int, input: List<String>, visited: MutableSet<Pair<Int, Int>> = mutableSetOf()): Triple<Int, Int, MutableSet<Pair<Int, Int>>> {
    if (Pair(x,y) in visited) return Triple(0, 0, visited)

    visited.add(Pair(x, y))

    val char = input[y][x]

    var area = 1
    var perimeter = 0

    if (x>0 && char == input[y][x-1]) {
        val region = floodFill(x-1, y, input, visited)
        area += region.first
        perimeter += region.second
    } else perimeter++
    if (x<input[y].length-1 && char == input[y][x+1]) {
        val region = floodFill(x+1, y, input, visited)
        area += region.first
        perimeter += region.second
    } else perimeter++
    if (y>0 && char == input[y-1][x]) {
        val region = floodFill(x, y-1, input, visited)
        area += region.first
        perimeter += region.second
    } else perimeter++
    if (y<input.size-1 && char == input[y+1][x]) {
        val region = floodFill(x, y+1, input, visited)
        area += region.first
        perimeter += region.second
    } else perimeter++

    return Triple(area, perimeter, visited)
}

private fun part1(input: List<String>): Int {
    val regions = mutableSetOf<Set<Pair<Int, Int>>>()

    var sum = 0
    for (y in input.indices) {
        for (x in input[y].indices) {
            val (area, perimeter, region) = floodFill(x, y, input)
            if (region in regions) continue
            regions.add(region)

            sum += area*perimeter
        }
    }

    return sum
}

private fun floodFillCorner(x: Int, y: Int, input: List<String>, visited: MutableSet<Pair<Int, Int>> = mutableSetOf()): Triple<Int, Int, MutableSet<Pair<Int, Int>>> {
    if (Pair(x,y) in visited) return Triple(0, 0, visited)

    visited.add(Pair(x, y))

    val char = input[y][x]

    var area = 1
    var corners = 0

    if (x>0 && char == input[y][x-1]) {
        val region = floodFillCorner(x-1, y, input, visited)
        area += region.first
        corners += region.second
    }
    if (x<input[y].length-1 && char == input[y][x+1]) {
        val region = floodFillCorner(x+1, y, input, visited)
        area += region.first
        corners += region.second
    }
    if (y>0 && char == input[y-1][x]) {
        val region = floodFillCorner(x, y-1, input, visited)
        area += region.first
        corners += region.second
    }
    if (y<input.size-1 && char == input[y+1][x]) {
        val region = floodFillCorner(x, y+1, input, visited)
        area += region.first
        corners += region.second
    }

    val left = if (x>0) input[y][x-1] else ' '
    val right = if (x<input[0].length-1) input[y][x+1] else ' '
    val up = if (y>0) input[y-1][x] else ' '
    val down = if (y<input.size-1) input[y+1][x] else ' '
    if (char != left && char != up) corners++
    if (char != right && char != up) corners++
    if (char != right && char != down) corners++
    if (char != left && char != down) corners++

    val leftUp = if (x>0&&y>0) input[y-1][x-1] else ' '
    val rightUp = if (x<input[0].length-1&&y>0) input[y-1][x+1] else ' '
    val leftDown = if (x>0&&y<input.size-1) input[y+1][x-1] else ' '
    val rightDown = if (x<input[0].length-1&&y<input.size-1) input[y+1][x+1] else ' '
    if (char == left && char == up && char != leftUp) corners++
    if (char == right && char == up && char != rightUp) corners++
    if (char == right && char == down && char != rightDown) corners++
    if (char == left && char == down && char != leftDown) corners++

    return Triple(area, corners, visited)
}

private fun part2(input: List<String>): Int {
    val regions = mutableSetOf<Set<Pair<Int, Int>>>()

    var sum = 0
    for (y in input.indices) {
        for (x in input[y].indices) {
            val (area, corners, region) = floodFillCorner(x, y, input)
            if (region in regions) continue
            regions.add(region)

            sum += area*corners
        }
    }

    return sum
}