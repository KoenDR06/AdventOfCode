package me.koendev.year2023

import me.koendev.*
import kotlin.math.abs

fun main() {
    solve(
        2023,
        6,
        ::part1,
        ::part2
    )
}


private fun part1(input: List<String>) : Int {
    val maxTime = input[0].slice(10..< input[0].length).split(" ").filter {it != ""}
    val minThreshold = input[1].slice(10..< input[0].length).split(" ").filter {it != ""}
    var sum = 1

    for(i in maxTime.indices) {
        for(x in 0..maxTime[i].toInt()) {
            if(x * (maxTime[i].toInt() - x) > minThreshold[i].toInt()) {
                sum *= (abs(x - (maxTime[i].toInt() - x)) + 1)
                break
            }
        }
    }
    return sum
}

private fun part2(input: List<String>) : Long {
    val maxTime = input[0].slice(10..< input[0].length).replace(" ", "").toLong()
    val minThreshold = input[1].slice(10..< input[0].length).replace(" ", "").toLong()
    var sum = 0L

    for(x in 0..maxTime) {
        if(x * (maxTime - x) > minThreshold) {
            sum = (abs(x - (maxTime - x)) + 1)
            break
        }
    }
    return sum
}