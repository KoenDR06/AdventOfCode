package year2024

import me.koendev.*
import kotlin.contracts.contract

fun main() {
solve(
    2024,
    13,
    ::part1,
    ::part2
    )
}

private data class ClawMachine(
    val ax: Long,
    val ay: Long,
    val bx: Long,
    val by: Long,
    val targetX: Long,
    val targetY: Long
)

private fun part1(input: List<String>): Long {
    val machines = mutableListOf<ClawMachine>()

    var ax = -1L
    var ay = 0L
    var bx = -1L
    var by = 0L
    var targetX = -1L
    var targetY = 0L
    for (line in input) {
        if (line.startsWith("Button A: ")) {
            val splits = line.split("Button A: X+", ", Y+")
            ax = splits[1].toLong()
            ay = splits[2].toLong()
        } else if (line.startsWith("Button B: ")) {
            val splits = line.split("Button B: X+", ", Y+")
            bx = splits[1].toLong()
            by = splits[2].toLong()
        } else if (line.startsWith("Prize: ")) {
            val splits = line.split("Prize: X=", ", Y=")
            targetX = splits[1].toLong()
            targetY = splits[2].toLong()
        }

        if (ax != -1L && bx != -1L && targetX != -1L) {
            machines.add(ClawMachine(ax, ay, bx, by, targetX, targetY))
            ax = -1
            bx = -1
            targetX = -1
        }
    }


    var sum = 0L
    machines.forEach { m ->
        val bPresses = (m.ay*m.targetX - m.ax*m.targetY+0.0) / (m.bx*m.ay - m.ax*m.by+0.0)
        val aPresses = (m.targetX - m.bx*bPresses+0.0) / (m.ax+0.0)

        if (aPresses < 0 || aPresses > 100 || aPresses % 1 != 0.0 || bPresses < 0 || bPresses > 100 || bPresses % 1 != 0.0) return@forEach

        sum += 3*aPresses.toLong()+bPresses.toLong()
    }

    return sum
}


private fun part2(input: List<String>): Long {
    val machines = mutableListOf<ClawMachine>()

    var ax = -1L
    var ay = 0L
    var bx = -1L
    var by = 0L
    var targetX = -1L
    var targetY = 0L
    for (line in input) {
        if (line.startsWith("Button A: ")) {
            val splits = line.split("Button A: X+", ", Y+")
            ax = splits[1].toLong()
            ay = splits[2].toLong()
        } else if (line.startsWith("Button B: ")) {
            val splits = line.split("Button B: X+", ", Y+")
            bx = splits[1].toLong()
            by = splits[2].toLong()
        } else if (line.startsWith("Prize: ")) {
            val splits = line.split("Prize: X=", ", Y=")
            targetX = splits[1].toLong()+10000000000000
            targetY = splits[2].toLong()+10000000000000
        }

        if (ax != -1L && bx != -1L && targetX != -1L) {
            machines.add(ClawMachine(ax, ay, bx, by, targetX, targetY))
            ax = -1
            bx = -1
            targetX = -1
        }
    }


    var sum = 0L
    machines.forEach { m ->
        val bPresses = (m.ay*m.targetX - m.ax*m.targetY+0.0) / (m.bx*m.ay - m.ax*m.by+0.0)
        val aPresses = (m.targetX - m.bx*bPresses+0.0) / (m.ax+0.0)

        if (aPresses % 1 != 0.0 || bPresses % 1 != 0.0) return@forEach
        "$aPresses,$bPresses".println()

        sum += 3*aPresses.toLong()+bPresses.toLong()
    }

    return sum
}
