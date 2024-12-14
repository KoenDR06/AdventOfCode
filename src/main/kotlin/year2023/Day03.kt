package me.koendev.year2023

import me.koendev.*

fun main() {
    solve(
        2023,
        3,
        ::part1,
        ::part2
    )
}

private data class EnginePart(val value: String, val x: IntRange, val y: Int)

private fun part1(input: List<String>) : Long {
    val engineParts = mutableListOf<EnginePart>()

    for ((y, line) in input.withIndex()) {
        var potentialNumber = ""
        for ((x, char) in line.withIndex()) {
            if (char.digitToIntOrNull() != null) {
                potentialNumber += char
            } else if (char == '.') {
                if (potentialNumber != "") {
                    engineParts.add(EnginePart(potentialNumber, x-potentialNumber.length..< x, y))
                    potentialNumber = ""
                }
            } else {
                if (potentialNumber != "") {
                    engineParts.add(EnginePart(potentialNumber, x-potentialNumber.length..< x, y))
                    potentialNumber = ""
                }
                engineParts.add(EnginePart(char.toString(), x..x, y))
            }
        }
    }

    var sum = 0L

    for (part in engineParts.filter { it.value.toIntOrNull() != null }) {
        val xStart = part.x.first-1
        val xEnd = part.x.last+1

        if (engineParts.any {
            it.value.toIntOrNull() == null &&
            it.y - part.y in -1..1 &&
            it.x.first >= xStart &&
            it.x.last <= xEnd }
        ) sum += part.value.toInt()
    }

    return sum
}

private fun part2(input: List<String>) : Int {


    return 0
}