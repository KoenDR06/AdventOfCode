package year2015

import me.koendev.*

fun main() {
    part1(getInput(2015, 14)).println()
    part2(getInput(2015, 14)).println()
}


private fun part1(input: List<String>): Int {
    var max = Int.MIN_VALUE
    for (line in input) {
        // Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
        val splits = line.split(" ")
        val speed = splits[3].toInt()
        val flyTime = splits[6].toInt()
        val restTime = splits[13].toInt()

        val distance = ((2503 / (flyTime+restTime) + if (2503 % (flyTime+restTime) >= flyTime) 1 else 0) * (speed*flyTime))

        if (distance > max) max = distance

    }

    return max
}

private class Reindeer(val speed: Int, val flyTime: Int, val restTime: Int) {
    var distance = 0
    var score = 0
}


private fun part2(input: List<String>): Int {
    val reindeerList: MutableList<Reindeer> = mutableListOf()

    for (line in input) {
        // Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
        val splits = line.split(" ")
        val speed = splits[3].toInt()
        val flyTime = splits[6].toInt()
        val restTime = splits[13].toInt()

        reindeerList.add(Reindeer(speed, flyTime, restTime))
    }

    for (i in 0..< 2503) {
        for (reindeer in reindeerList) {
            if (i % (reindeer.flyTime + reindeer.restTime) < reindeer.flyTime) {
                reindeer.distance += reindeer.speed
            }
        }
        val maxDistance = reindeerList.maxBy { it.distance }.distance
        for (reindeer in reindeerList) {
            if (reindeer.distance == maxDistance) {
                reindeer.score++
            }
        }
    }

    return reindeerList.maxBy { it.score }.score
}
