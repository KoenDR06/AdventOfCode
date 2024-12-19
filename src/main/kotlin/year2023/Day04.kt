package me.koendev.year2023

import me.koendev.*
import me.koendev.utils.MutableTriple
import kotlin.math.pow

fun main() {
    solve(
        2023,
        4,
        ::part1,
        ::part2
    )
}


private fun part1(input: List<String>) : Int {
    val data : MutableList<MutableList<Int>> = mutableListOf()

    var sum = 0
    for (line in input) {
        val lineParsed = line.replace("  ", " ").split(": ")[1].split(" | ")

        data.add(mutableListOf())

        val winningNumbers = lineParsed[0].split(" ").map { it.toInt() }
        val myNumbers = lineParsed[1].split(" ").map { it.toInt() }


        var score = 0
        for(n in winningNumbers) {
            if(myNumbers.contains(n)) {
                score += 1
            }
        }
        score = 2.0.pow(score - 1).toInt()
        sum += score
    }
    return sum
}

private fun part2(input: List<String>): Int {
    // Winning Numbers, My Numbers, Count
    val cards : MutableList<MutableTriple<List<Int>, List<Int>, Int>> = mutableListOf()

    for(line in input) {
        val lineParsed = line.replace("  ", " ").split(": ")[1].split(" | ")
        val winningNumbers = lineParsed[0].split(" ").map { it.toInt() }
        val myNumbers = lineParsed[1].split(" ").map { it.toInt() }
        cards.add(MutableTriple(winningNumbers, myNumbers, 1))
    }

    for ((index, card) in cards.withIndex()) {
        var score = 0
        for(n in card.first) {
            if(card.second.contains(n)) {
                score += 1
            }
        }

        for(i in 1 .. score) {
            cards[i+index].third += card.third
        }
    }

    var sum = 0
    cards.forEach { card ->
        sum += card.third
    }
    return sum
}