package me.koendev.year2023

import me.koendev.*
import kotlin.math.abs

fun main() {
    solve(
        2023,
        11,
        ::part1,
        ::part2
    )
}


private fun part1(input: List<String>): Int {
    val mutableInput = mutableListOf< MutableList<Char> >()
    for ((y, line) in input.withIndex()) {
        mutableInput.add(mutableListOf())
        var emptyLine = true
        for ((x, char) in line.withIndex()) {
            if (char == '#') emptyLine = false
            mutableInput.last().add(char)
        }
        if (emptyLine) {
            mutableInput.add(mutableListOf())
            for (char in mutableInput[mutableInput.size-2]) {
                mutableInput.last().add(char)
            }
        }
    }

    var offset = 0
    for (x in 0..< mutableInput[0].count()) {
        var emptyLine = true
        for (y in 0..< mutableInput.count()) {
            val char = mutableInput[y][x+offset]

            if (char == '#') {
                emptyLine = false
            }
        }
        if (emptyLine) {
            for (line in mutableInput) {
                line.add(x+offset+1, '.')
            }
            offset++
        }
    }

    for (line in mutableInput) {
        for (char in line) {
            print(char)
        }
        println()
    }

    val galaxies: MutableList< Pair<Int, Int> > = mutableListOf()
    for ((y, line) in mutableInput.withIndex()) {
        for ((x, char) in line.withIndex()) {
            if (char == '#') galaxies.add(Pair(x, y))
        }
    }


    var sum = 0
    for (a in galaxies) {
        for (b in galaxies) {
            sum += abs(a.first - b.first) + abs(a.second - b.second)
        }
    }

    return sum / 2
}

private fun findEmptyRowDiff(a: Int, b: Int, arr: MutableList<Int>, expansion: Long = 1000000L): Long {
    val clone = mutableListOf<Int>()
    for (i in arr) {
        clone.add(i)
    }

    if (!clone.contains(a)) clone.add(a)
    if (!clone.contains(b)) clone.add(b)
    clone.sort()

    var diff = abs(clone.indexOf(a) - clone.indexOf(b))-1
    if (diff < 0) diff = 0
    return (expansion-1) * diff
}

private fun part2(input: List<String>): Long {
    val mutableInput = mutableListOf< MutableList<Char> >()
    val emptyRows = mutableListOf<Int>()
    val emptyColumns = mutableListOf<Int>()
    for ((y, line) in input.withIndex()) {
        mutableInput.add(mutableListOf())
        var emptyLine = true
        for ((x, char) in line.withIndex()) {
            if (char == '#') emptyLine = false
            mutableInput.last().add(char)
        }
        if (emptyLine) {
            emptyRows.add(y)
        }
    }

    for (x in 0..< mutableInput[0].count()) {
        var emptyLine = true
        for (y in 0..< mutableInput.count()) {
            val char = mutableInput[y][x]

            if (char == '#') {
                emptyLine = false
            }
        }
        if (emptyLine) {
            emptyColumns.add(x)
        }
    }

    for (line in mutableInput) {
        for (char in line) {
            print(char)
        }
        println()
    }

    val galaxies: MutableList< Pair<Int, Int> > = mutableListOf()
    for ((y, line) in mutableInput.withIndex()) {
        for ((x, char) in line.withIndex()) {
            if (char == '#') galaxies.add(Pair(x, y))
        }
    }

//    emptyRows.count().println()
//    emptyColumns.count().println()

    var sum = 0L
    for (a in galaxies) {
        for (b in galaxies) {
            sum += abs(a.first - b.first) + findEmptyRowDiff(a.first, b.first, emptyColumns)
            sum += abs(a.second - b.second)+ findEmptyRowDiff(a.second, b.second, emptyRows)
        }
    }

    return sum / 2L
}