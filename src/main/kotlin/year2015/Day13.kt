package me.koendev.year2015

import me.koendev.*
import me.koendev.utils.permutations

fun main() {
    solve(
        2015,
        13,
        ::part1,
        ::part2
    )
}


private fun part1(input: List<String>): Int {
    val happiness: MutableMap<Pair<String, String>, Int> = mutableMapOf()
    val people: MutableList<String> = mutableListOf()
    for (line in input) {
        // Alice would gain 54 happiness units by sitting next to Bob.
        val splits = line.split(" ", ".")
        val nameA = splits[0]
        val nameB = splits[10]
        val diff = splits[3].toInt() * if (splits[2] == "gain") 1 else -1

        if (nameA !in people) people.add(nameA)
        happiness[Pair(nameA, nameB)] = diff
    }

    var maxHappy = Int.MIN_VALUE
    for (arrangement in people.permutations()) {
        var score = 0
        for (i in 0..< arrangement.size-1) {
            try {
                score += happiness[Pair(arrangement[i], arrangement[i+1])]!!
                score += happiness[Pair(arrangement[i+1], arrangement[i])]!!
            } catch (_: NullPointerException) { }
        }
        try {
            score += happiness[Pair(arrangement[0], arrangement[arrangement.size-1])]!!
            score += happiness[Pair(arrangement[arrangement.size-1], arrangement[0])]!!
        } catch (_: NullPointerException) { }

        if (score > maxHappy) maxHappy = score
    }

    return maxHappy
}


private fun part2(input: List<String>): Int {
    val happiness: MutableMap<Pair<String, String>, Int> = mutableMapOf()
    val people: MutableList<String> = mutableListOf()
    for (line in input) {
        // Alice would gain 54 happiness units by sitting next to Bob.
        val splits = line.split(" ", ".")
        val nameA = splits[0]
        val nameB = splits[10]
        val diff = splits[3].toInt() * if (splits[2] == "gain") 1 else -1

        if (nameA !in people) people.add(nameA)
        happiness[Pair(nameA, nameB)] = diff
    }

    for (person in people) {
        happiness[Pair(person, "Me")] = 0
        happiness[Pair("Me", person)] = 0
    }
    people.add("Me")

    var maxHappy = Int.MIN_VALUE
    for (arrangement in people.permutations()) {
        var score = 0
        for (i in 0..< arrangement.size-1) {
            try {
                score += happiness[Pair(arrangement[i], arrangement[i+1])]!!
                score += happiness[Pair(arrangement[i+1], arrangement[i])]!!
            } catch (_: NullPointerException) { }
        }
        try {
            score += happiness[Pair(arrangement[0], arrangement[arrangement.size-1])]!!
            score += happiness[Pair(arrangement[arrangement.size-1], arrangement[0])]!!
        } catch (_: NullPointerException) { }

        if (score > maxHappy) maxHappy = score
    }

    return maxHappy
}
