package me.koendev.year2024

import me.koendev.utils.*
import me.koendev.*

fun main() {
    solve(
        2024,
        17,
        ::part1,
        ::part2
    )
}

private fun part1(input: List<String>): String {
    var a = input[0].split(": ")[1].toLong()
    var b = input[1].split(": ")[1].toLong()
    var c = input[2].split(": ")[1].toLong()

    fun operand(n: Long): Long {
        if (n in 0..3) return n
        else if (n == 4L) return a
        else if (n == 5L) return b
        else if (n == 6L) return c
        else throw Exception()
    }

    val program = input[4].split(": ")[1].split(",").map { it.toLong() }
    val out = mutableListOf<Int>()

    var index = 0
    while (true) {
        var jumped = false

        val literal = program[index + 1]
        val combo = operand(literal)
        when (program[index]) {
            0L -> a /= pow(2L, combo)
            1L -> b = b xor combo
            2L -> b = combo % 8L
            3L -> {
                if (a != 0L) {
                    index = literal.toInt()
                    jumped = true
                }
            }
            4L -> b = b xor c
            5L -> out.add( (combo % 8L).toInt() )
            6L -> b = a / pow(2L, combo)
            7L -> c = a / pow(2L, combo)
        }
        if (!jumped) index += 2

        if (index > program.size-1) break
    }

    return out.fold("") { acc, curr -> "$acc,$curr" }.substring(1)
}


private fun part2(input: List<String>): Long {
    /*return input[4]
        .split(": ")[1]
        .replace(",", "")
        .reversed()
        .toLong(8)*/
    /*val programString = input[4].split(": ")[1]
    val outputValue = programString.replace(",", "").reversed().toLong(8)*/

    var a = 1L
    var b = input[1].split(": ")[1].toLong()
    var c = input[2].split(": ")[1].toLong()

    fun operand(n: Long): Long {
        return when (n) {
            in 0..3 -> n
            4L -> a
            5L -> b
            6L -> c
            else -> throw Exception()
        }
    }

    val programString = input[4].split(": ")[1]
    val program = programString.split(",").map { it.toLong() }
    var initialA = programString.replace(",", "").reversed().toLong(8)*2-1//pow(8L, program.size-1L)
    while (true) {
        a = initialA
        val out = mutableListOf<Long>()
        var index = 0
        while (true) {
            var jumped = false

            val literal = program[index + 1]
            val combo = operand(literal)
            when (program[index]) {
                0L -> a /= pow(2L, combo)
                1L -> b = b xor combo
                2L -> b = combo % 8L
                3L -> {
                    if (a != 0L) {
                        index = literal.toInt()
                        jumped = true
                    }
                }
                4L -> b = b xor c
                5L -> {
//                    if (program[out.size] != (combo % 8L)) {
//                        break
//                    }
                    out.add((combo % 8L))
                }
                6L -> b = a / pow(2L, combo)
                7L -> c = a / pow(2L, combo)
            }
            if (!jumped) index += 2

            if (index > program.size - 1) break
        }

//        if (out == program) return initialA
        initialA++

        if (initialA % 10_000_000 == 0L) initialA.println()
    }
}