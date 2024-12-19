package me.koendev.year2024

import me.koendev.*
import me.koendev.utils.diffs
import kotlin.math.abs
import kotlin.math.sign

fun main() {
    solve(
        2024,
        2,
        ::part1,
        ::part2
    )
}


private fun part1(input: List<String>): Int {
    val arr: MutableList< MutableList<Int> > = mutableListOf()
    for (line in input) {
        val splits = line.split(" ")
        arr.add(mutableListOf())
        for (split in splits) {
            arr.last().add(split.toInt())
        }
    }

    var sum = 0
    for (report in arr) {
        var valid = true
        val sign = report.diffs().first().sign
        for (diff in report.diffs()) {
            if (diff.sign != sign) valid = false
            if (abs(diff) < 1 || abs(diff) > 3) valid = false
        }
        if (valid) sum++
    }

    return sum
}

private fun validate(report: MutableList<Int>): Boolean {
    val sign = report.diffs().first().sign
    for (diff in report.diffs()) {
        if (diff.sign != sign) return false
        if (abs(diff) < 1 || abs(diff) > 3) return false
    }
    return true
}

private fun part2(input: List<String>): Int {
    val arr: MutableList< MutableList<Int> > = mutableListOf()
    for (line in input) {
        val splits = line.split(" ")
        arr.add(mutableListOf())
        for (split in splits) {
            arr.last().add(split.toInt())
        }
    }

    var sum = 0
    for (report in arr) {
        if (validate(report)) sum++
        else {
            for (index in report.indices) {
                val selectedSeries = report.toMutableList()
                selectedSeries.removeAt(index)
                if (validate(selectedSeries)) {
                    sum++
                    break
                }
            }
        }
    }

    return sum
}
