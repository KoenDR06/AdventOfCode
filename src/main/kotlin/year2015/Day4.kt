package year2015

import me.koendev.*
import me.koendev.md5
import me.koendev.wait

fun main() {
    part1(getInput(2015, 4)).println()
    part2(getInput(2015, 4)).println()
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
