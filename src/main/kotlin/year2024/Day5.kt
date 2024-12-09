package year2024

import me.koendev.*

fun main() {
    solve(
        2024,
        5,
        ::part1,
        ::part2
    )
}


private fun part1(input: List<String>): Int {
    val rules: MutableList<Pair<Int, Int>> = mutableListOf()
    var sum = 0
    var writingRules = true
    for (line in input) {
        if (line == "") {
            writingRules = false
            continue
        }

        if (writingRules) {
            val splits = line.split("|").map { it.toInt() }

            rules.add(Pair(splits[0], splits[1]))
            continue
        }

        val pages = line.split(",").map { it.toInt() }
        if (rules.all {
            if (pages.indexOf(it.first) == -1 || pages.indexOf(it.second) == -1) true
            else pages.indexOf(it.first) < pages.indexOf(it.second)
        }) sum += pages[(pages.size - 1) / 2]
    }

    return sum
}


private fun part2(input: List<String>): Int {
    val rules: MutableList<Pair<Int, Int>> = mutableListOf()
    var sum = 0
    var writingRules = true
    for (line in input) {
        if (line == "") {
            writingRules = false
            continue
        }

        if (writingRules) {
            val splits = line.split("|").map { it.toInt() }

            rules.add(Pair(splits[0], splits[1]))
            continue
        }

        val pages = line.split(",").map { it.toInt() }.toMutableList()
        var changeMade = false
        while (!rules.all { rule ->
                if (pages.indexOf(rule.first) == -1 || pages.indexOf(rule.second) == -1) true
                else pages.indexOf(rule.first) < pages.indexOf(rule.second)
            }) {
            for (rule in rules) {
                if (pages.indexOf(rule.first) == -1 || pages.indexOf(rule.second) == -1) continue

                if (pages.indexOf(rule.first) > pages.indexOf(rule.second)) {
                    val temp = pages.removeAt(pages.indexOf(rule.second))
                    pages.add(temp)
                    changeMade = true
                }
            }
        }
        if (changeMade) sum += pages[(pages.size - 1) / 2]
    }

    return sum
}
