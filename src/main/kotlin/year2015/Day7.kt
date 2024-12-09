package year2015

import me.koendev.*

fun main() {
    solve(
        2015,
        7,
        ::part1,
        ::part2
    )
}

private fun followWire(wire: String, wires: MutableMap<String, String>): Int {
    val tokens = wires[wire]!!.split(" ")
    if (tokens.size == 1) {
        wires[wire] = try {
            tokens[0].toInt().toString()
        } catch (e: NumberFormatException) {
            followWire(tokens[0], wires).toString()
        }
        return wires[wire]!!.toInt()
    } else if (tokens.size == 2) {
        wires[wire] = try {
            tokens[1].toInt().inv().toString()
        } catch (e: NumberFormatException) {
            followWire(tokens[1], wires).inv().toString()
        }
        return wires[wire]!!.toInt()
    } else if (tokens.size == 3) {
        val a = try {
            tokens[0].toInt()
        } catch (e: NumberFormatException) {
            followWire(tokens[0], wires)
        }

        val b = try {
            tokens[2].toInt()
        } catch (e: NumberFormatException) {
            followWire(tokens[2], wires)
        }

        wires[wire] = (if (tokens[1] == "AND") {
            a and b
        } else if (tokens[1] == "OR") {
            a or b
        } else if (tokens[1] == "LSHIFT") {
            a shl b
        } else if (tokens[1] == "RSHIFT") {
            a shr b
        } else {
            throw UnsupportedOperationException()
        }).toString()
        return wires[wire]!!.toInt()
    } else {
        throw UnsupportedOperationException()
    }
}

private fun part1(input: List<String>): Int {
    val wires: MutableMap<String, String> = mutableMapOf()

    for (line in input) {
        val splits = line.split(" -> ")
        val operation = splits[0]
        val variable = splits[1]

        wires[variable] = operation
    }

    return followWire("a", wires)
}

private fun part2(input: List<String>): Int {
    var wires: MutableMap<String, String> = mutableMapOf()

    for (line in input) {
        val splits = line.split(" -> ")
        val operation = splits[0]
        val variable = splits[1]

        wires[variable] = operation
    }


    val valueA = followWire("a", wires)

    wires = mutableMapOf()

    for (line in input) {
        val splits = line.split(" -> ")
        val operation = splits[0]
        val variable = splits[1]

        wires[variable] = operation
    }

    wires["b"] = valueA.toString()

    return followWire("a", wires)
}
