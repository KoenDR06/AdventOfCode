package me.koendev.year2024

import me.koendev.*

fun main() {
    solve(
        2024,
        7,
        ::part1,
        ::part2
    )
}

private fun solveCalibration(target: Long, values: MutableList<Long>): Long {
    if (values.size == 1) return values[0]

    val a = values.removeAt(0)

    val addValues = values.toMutableList()
    addValues[0] += a
    if (solveCalibration(target, addValues) == target) return target
    val mulValues = values.toMutableList()
    mulValues[0] *= a
    if (solveCalibration(target, mulValues) == target) return target

    return 0
}

private fun part1(input: List<String>): Long {
    val calibrations = mutableListOf<Pair<Long, List<Long>>>()
    for (line in input) {
        val splits = line.split(": ")
        val target = splits[0].toLong()
        val values = splits[1].split(" ").map { it.toLong() }

        calibrations.add(Pair(target, values))
    }

    var sum = 0L
    for (calibration in calibrations) {
        val target = calibration.first
        val values = calibration.second

        sum += solveCalibration(target, values.toMutableList())
    }

    return sum
}

private fun solveCalibration2(target: Long, values: MutableList<Long>): Long {
    if (values.size == 1) return values[0]

    val a = values.removeAt(0)

    val addValues = values.toMutableList()
    addValues[0] += a
    if (solveCalibration2(target, addValues) == target) return target

    val mulValues = values.toMutableList()
    mulValues[0] *= a
    if (solveCalibration2(target, mulValues) == target) return target

    val concatValues = values.toMutableList()
    concatValues[0] = (a.toString() + concatValues[0].toString()).toLong()
    if (solveCalibration2(target, concatValues) == target) return target

    return 0
}

private fun part2(input: List<String>): Long {
    val calibrations = mutableListOf<Pair<Long, List<Long>>>()
    for (line in input) {
        val splits = line.split(": ")
        val target = splits[0].toLong()
        val values = splits[1].split(" ").map { it.toLong() }

        calibrations.add(Pair(target, values))
    }

    var sum = 0L
    for (calibration in calibrations) {
        val target = calibration.first
        val values = calibration.second

        sum += solveCalibration2(target, values.toMutableList())
    }

    return sum
}
