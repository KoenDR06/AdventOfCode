package me.koendev.year2015

import me.koendev.*

fun main() {
    solve(
        2015,
        4,
        ::part1,
        ::part2
    )
}


private fun part1(input: List<String>): Int {
    var n = 0
    while (true) {
        val md5Hash = (input[0]+n.toString()).md5()
        if (md5Hash.length == 27) {
            return n
        }
        n++
    }
}


private fun part2(input: List<String>): Int {
    var n = 0
    while (true) {
        val md5Hash = (input[0]+n.toString()).md5()
        if (md5Hash.length == 26) {
            return n
        }
        n++
    }
}
