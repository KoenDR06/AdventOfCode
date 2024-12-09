package year2015

import me.koendev.*

fun main() {
    solve(
        2015,
        3,
        ::part1,
        ::part2
    )
}


private fun part1(input: List<String>): Int {
    val houses = mutableMapOf(Pair(0, 0) to true)

    var x = 0
    var y = 0
    for (char in input[0]) {
        when (char) {
            'v' -> y++
            '<' -> x--
            '^' -> y--
            '>' -> x++
        }

        houses[Pair(x,y)] = true
    }

    return houses.count()
}


private fun part2(input: List<String>): Int {
    val houses = mutableMapOf(Pair(0, 0) to true)

    var x = 0
    var y = 0
    var roboX = 0
    var roboY = 0
    var n = 0
    for (char in input[0]) {
        if (char == 'v') {
            if (n % 2 == 0) y++ else roboY++
        } else if (char == '<') {
            if (n % 2 == 0) x-- else roboX--
        } else if (char == '^') {
            if (n % 2 == 0) y-- else roboY--
        } else if (char == '>') {
            if (n % 2 == 0) x++ else roboX++
        }

        if (n % 2 == 0) houses[Pair(x,y)] = true else houses[Pair(roboX,roboY)] = true
        n++
    }

    return houses.count()
}
