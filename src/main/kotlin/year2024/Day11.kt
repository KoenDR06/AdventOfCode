package year2024

import me.koendev.*

fun main() {
solve(
    2024,
    11,
    ::part1,
    ::part2
    )
}


private fun part1(input: List<String>): Int {
    val numbers = input[0].split(" ").map { it.toLong() }.toMutableList()

    for (i in 1..25) {
        for (i in numbers.indices) {
            if (numbers[i] == 0L) numbers[i] = 1L
            else if (numbers[i].toString().length % 2 == 0) {
                val str = numbers[i].toString()
                val l = str.length

                numbers[i] = str.slice(0..< l/2).toLong()
                numbers.add(str.slice(l/2..< str.length).toLong())
            }
            else numbers[i] *= 2024L
        }
    }

    return numbers.size
}


private fun part2(input: List<String>): Int {


    return 0
}
