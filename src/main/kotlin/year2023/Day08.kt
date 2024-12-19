package me.koendev.year2023

import me.koendev.*
import me.koendev.utils.findLCM

fun main() {
    solve(
        2023,
        8,
        ::part1,
        ::part2
    )
}

private fun part1(input: List<String>): Int {
    val steps = input[0]
    val nodes = mutableMapOf<String, Pair<String, String>>()

    for ((index, line) in input.withIndex()) {
        if (index <= 1) continue

        val lineData = line.substring(0..< line.length-1).split(" = (", ", ")
        nodes[lineData[0]] = Pair(lineData[1], lineData[2])
    }

    var running = true
    var currentNode = "AAA"
    var stepsTaken = 0
    while (running) {
        for (step in steps) {
            if (step == 'L') {
                currentNode = nodes[currentNode]!!.first
            } else if (step == 'R') {
                currentNode = nodes[currentNode]!!.second
            }
            stepsTaken++

            if (currentNode == "ZZZ") running = false
        }
    }

    return stepsTaken
}

private fun part2(input: List<String>): Long {
    val steps = input[0]
    val nodes = mutableMapOf<String, Pair<String, String>>()

    for ((index, line) in input.withIndex()) {
        if (index <= 1) continue

        val lineData = line.substring(0..< line.length-1).split(" = (", ", ")
        nodes[lineData[0]] = Pair(lineData[1], lineData[2])
    }

    val currentNodes = mutableListOf<String>()
    for (node in nodes) {
        if (node.key[2] == 'A') currentNodes.add(node.key)
    }

    var running = true
    var stepsTaken = 0L
    val intervals = mutableListOf<Long>()
    while (running) {
        for (step in steps) {
            if (step == 'L') {
                for (i in currentNodes.indices) {
                    currentNodes[i] = nodes[currentNodes[i]]!!.first
                }
            } else if (step == 'R') {
                for (i in currentNodes.indices) {
                    currentNodes[i] = nodes[currentNodes[i]]!!.second
                }
            }
            stepsTaken++

            var removeIndex = -1
            for ((index, node) in currentNodes.withIndex()) {
                if (node[2] == 'Z') {
                    intervals.add(stepsTaken)
                    removeIndex = index
                }
            }
            if (removeIndex != -1) currentNodes.removeAt(removeIndex)
            if (currentNodes.isEmpty()) running = false
        }
    }

    return findLCM(intervals)
}