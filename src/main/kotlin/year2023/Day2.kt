package me.koendev.year2023

import me.koendev.*

fun main() {
    part1(getInput(2023, 2)).println()
    part2(getInput(2023, 2)).println()
}


private fun part1(input: List<String>): Int {
    var sum = 0

    val redMax = 12
    val greenMax = 13
    val blueMax = 14

    for((index, line) in input.withIndex()) {
        val roundData = line.split(": ")[1].split("; ")
        val cubeData = mutableListOf<String>()

        roundData.forEach {
            cubeData += it.split(", ")
        }

        var valid = true
        cubeData.forEach {cube ->
            val arr = cube.split(" ")
            if(arr[1] == "red" && arr[0].toInt() > redMax) {
                valid = false
            }else if(arr[1] == "green" && arr[0].toInt() > greenMax) {
                valid = false
            }else if(arr[1] == "blue" && arr[0].toInt() > blueMax) {
                valid = false
            }
        }
        if(valid) {
            sum += index + 1
        }
    }


    return sum
}

private fun part2(input: List<String>): Int {
    var sum = 0

    for(line in input) {
        val roundData = line.split(": ")[1].split("; ")
        val cubeData = mutableListOf<String>()

        roundData.forEach {
            cubeData += it.split(", ")
        }

        var redMin = 0
        var greenMin = 0
        var blueMin = 0
        cubeData.forEach {cube ->

            val arr = cube.split(" ")
            if(arr[1] == "red") {
                if(arr[0].toInt() > redMin) {
                    redMin = arr[0].toInt()
                }
            }else if(arr[1] == "green") {
                if(arr[0].toInt() > greenMin) {
                    greenMin = arr[0].toInt()
                }
            }else if(arr[1] == "blue") {
                if(arr[0].toInt() > blueMin) {
                    blueMin = arr[0].toInt()
                }
            }

        }
        sum += redMin * greenMin * blueMin
    }


    return sum
}