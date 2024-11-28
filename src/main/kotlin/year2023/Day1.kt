package year2023

import println
import getInput

fun main() {
    part1(getInput(2023, 1)).println()
    part2(getInput(2023, 1)).println()
}


private fun part1(input: List<String>): Int {
    var sum = 0
    input.forEach {
        val numbers = mutableListOf(-1, -1)
        for (char in it) {
            if (char.digitToIntOrNull() != null) {
                if (numbers[0] == -1) {
                    numbers[0] = char.digitToInt()
                } else {
                    numbers[1] = char.digitToInt()
                }
            }
        }
        sum += if (numbers[1] == -1) {
            11 * numbers[0]
        } else {
            10 * numbers[0] + numbers[1]
        }
    }
    return sum
}


private fun part2(input: List<String>): Int {
    var sum = 0
    input.forEach { line ->
        // Index, Digit
        val digitsFound = mutableListOf<Pair<Int, Int>>()

        val digits = mapOf(
            "one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5,
            "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9, "zero" to 0,
            "1" to 1, "2" to 2, "3" to 3, "4" to 4, "5" to 5,
            "6" to 6, "7" to 7, "8" to 8, "9" to 9, "0" to 0
        )

        for (digit in digits) {
            if (line.indexOf(digit.key) != -1) {
                digitsFound.add(Pair(line.indexOf(digit.key), digit.value))
                digitsFound.add(Pair(line.lastIndexOf(digit.key), digit.value))
            }
        }

        val sortedDigits = digitsFound.sortedBy { pair -> pair.first }

        sum += 10 * sortedDigits.first().second + sortedDigits.last().second
    }
    return sum
}
