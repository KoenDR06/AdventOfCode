package me.koendev.year2023

import me.koendev.println
import me.koendev.getInput

fun main() {
    part1(getInput(2023, 5)).println()
    part2(getInput(2023, 5)).println()
}


private fun part1(input: List<String>): Long {
    val seeds : MutableList<MutableList<Long>> = mutableListOf()
    seeds.add(mutableListOf())
    seeds.add(mutableListOf())
    val maps : MutableList<MutableMap<Pair<Long, Long>, Long>> = mutableListOf()
    var searchNumbers = false

    for(line in input) {
        if(line.split(": ").size == 2) {
            val stringList = line.split(": ")[1].split(" ")
            for(item in stringList) {
                seeds[0].add(item.toLong())
                seeds[1].add(-1)
            }
        }

        else if(line.isEmpty()) {
            searchNumbers = false
        }

        else if(searchNumbers) {
            val mappingData : MutableList<Long> = mutableListOf()

            for(item in line.split(" ")) {
                mappingData.add(item.toLong())
            }

            maps.last()[Pair(mappingData[1], mappingData[1] + mappingData[2])] = mappingData[0] - mappingData[1]
        }

        else if(line.contains("map")) {
            maps.add(mutableMapOf())
            searchNumbers = true
        }
    }

    "Data gathered, starting map conversion now.".println()

    var seedList = 1
    for(map in maps) {
        for(index in seeds[seedList].indices) {
            var seedChecked = false
            for(entry in map) {
                if(entry.key.first <= seeds[1 - seedList][index] && seeds[1 - seedList][index] < entry.key.second) {
                    seeds[seedList][index] = seeds[1 - seedList][index] + entry.value
                    seedChecked = true
                }
            }
            if(!seedChecked) {
                seeds[seedList][index] = seeds[1 - seedList][index]
            }
        }
        if(seedList == 0) {
            seedList = 1
        }
        else if(seedList == 1) {
            seedList = 0
        }
    }


    return seeds[1 - seedList].min()
}

private fun part2(input: List<String>) : Long {
    val minimalLocations : MutableList<Long> = mutableListOf()
    val rangesString = input[0].split(": ")[1].split(" ")
    val ranges = mutableListOf<Long>()
    for(item in rangesString) {
        ranges.add(item.toLong())
    }


    for(i in input[0].split(": ")[1].split(" ").indices step 2) {
        while(ranges[1 + 1] > 10_000_000) {

            if (ranges[1 + 1] <= 10_000_000) {
                minimalLocations.add(getMinOfPart(input, ranges[i], ranges[i + 1]))
            } else {
                minimalLocations.add(getMinOfPart(input, ranges[i], ranges[i + 1] + 10_000_000))
            }

            ranges[i] += 10_000_000L
            ranges[i + 1] -= 10_000_000L

        }
    }

    return minimalLocations.min()
}

private fun getMinOfPart(input: List<String>, min: Long, max: Long) : Long{
    val seeds : MutableList<MutableList<Long>> = mutableListOf()
    seeds.add(mutableListOf())
    seeds.add(mutableListOf())
    val maps : MutableList<MutableMap<Pair<Long, Long>, Long>> = mutableListOf()
    var searchNumbers = false

    for(line in input) {
        if(line.split(": ").size == 2) {
            for(i in min .. min + max) {
                seeds[0].add(i)
                seeds[1].add(-1)
            }
        }

        else if(line.isEmpty()) {
            searchNumbers = false
        }

        else if(searchNumbers) {
            val mappingData : MutableList<Long> = mutableListOf()

            for(item in line.split(" ")) {
                mappingData.add(item.toLong())
            }

            maps.last()[Pair(mappingData[1], mappingData[1] + mappingData[2])] = mappingData[0] - mappingData[1]
        }

        else if(line.contains("map")) {
            maps.add(mutableMapOf())
            searchNumbers = true
        }
    }


    "Data gathered, starting map conversion now.".println()


    var seedList = 1
    for(map in maps) {
        for(index in seeds[seedList].indices) {
            var seedChecked = false
            for(entry in map) {
                if(entry.key.first <= seeds[1 - seedList][index] && seeds[1 - seedList][index] < entry.key.second) {
                    seeds[seedList][index] = seeds[1 - seedList][index] + entry.value
                    seedChecked = true
                }
            }
            if(!seedChecked) {
                seeds[seedList][index] = seeds[1 - seedList][index]
            }
        }
        if(seedList == 0) {
            seedList = 1
        }
        else if(seedList == 1) {
            seedList = 0
        }
    }


    return seeds[1 - seedList].min()
}