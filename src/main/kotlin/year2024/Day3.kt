package year2024

import me.koendev.*

fun main() {
    solve(
        2024,
        3,
        ::part1,
        ::part2
    )
}


private fun part1(input: List<String>): Int {
    var sum = 0

    val regex = Regex("mul\\(\\d+,\\d+\\)")
    for (line in input) {
        for (match in regex.findAll(line)) {
            val splits = match.value.split("(",")",",")
            sum += splits[1].toInt() * splits[2].toInt()
        }
    }

    return sum
}


private fun part2(input: List<String>): Int {
    var sum = 0
    var enabled = true

    val regex = Regex("mul\\(\\d+,\\d+\\)|don't\\(\\)|do\\(\\)")
    for (line in input) {
        for (match in regex.findAll(line)) {
            if (match.value == "don't()") enabled = false
            else if (match.value == "do()") enabled = true

            else if (enabled) {
                val splits = match.value.split("(", ")", ",")
                sum += splits[1].toInt() * splits[2].toInt()
            }
        }
    }

    return sum
}
