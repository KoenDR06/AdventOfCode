package year2015

import me.koendev.*

fun main() {
    solve(
        2015,
        9,
        ::part1,
        ::part2
    )
}


private fun part1(input: List<String>): Int {
    val distances: MutableMap<Pair<String, String>, Int> = mutableMapOf()
    val cities: MutableList<String> = mutableListOf()

    for (line in input) {
        val splits = line.split(" ", " = ")
        distances[Pair(splits[0], splits[2])] = splits[4].toInt()
        if (splits[0] !in cities) cities.add(splits[0])
        if (splits[2] !in cities) cities.add(splits[2])
    }

    cities.toList()
    var minDistance = Int.MAX_VALUE
    for (perm in cities.permutations()) {
        var distance = 0

        for (i in 0..< perm.size-1) {
            distance += try {
                distances[Pair(perm[i], perm[i+1])]!!
            } catch (e: NullPointerException) {
                distances[Pair(perm[i+1], perm[i])]!!
            }
        }
        if (distance < minDistance) minDistance = distance
    }

    return minDistance
}


private fun part2(input: List<String>): Int {
    val distances: MutableMap<Pair<String, String>, Int> = mutableMapOf()
    val cities: MutableList<String> = mutableListOf()

    for (line in input) {
        val splits = line.split(" ", " = ")
        distances[Pair(splits[0], splits[2])] = splits[4].toInt()
        if (splits[0] !in cities) cities.add(splits[0])
        if (splits[2] !in cities) cities.add(splits[2])
    }

    cities.toList()
    var maxDistance = Int.MIN_VALUE
    for (perm in cities.permutations()) {
        var distance = 0

        for (i in 0..< perm.size-1) {
            distance += try {
                distances[Pair(perm[i], perm[i+1])]!!
            } catch (e: NullPointerException) {
                distances[Pair(perm[i+1], perm[i])]!!
            }
        }
        if (distance > maxDistance) maxDistance = distance
    }

    return maxDistance
}
