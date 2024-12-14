package me.koendev.year2015

import me.koendev.*

fun main() {
    solve(
        2015,
        2,
        ::part1,
        ::part2
    )
}


private fun part1(input: List<String>): Int {
    var sum = 0
    for (line in input) {
        val dimensions = line.split("x")
        val l = dimensions[0].toInt()
        val w = dimensions[1].toInt()
        val h = dimensions[2].toInt()

        sum += 2*l*w
        sum += 2*w*h
        sum += 2*h*l

        if (listOf(l*w,w*h,h*l).min() == l*w) {
            sum += l*w
        } else if (listOf(l*w,w*h,h*l).min() == w*h) {
            sum += w*h
        } else if (listOf(l*w,w*h,h*l).min() == l*h) {
            sum += l*h
        }
    }

    return sum
}


private fun part2(input: List<String>): Int {
    var sum = 0
    for (line in input) {
        val dimensions = line.split("x")
        val l = dimensions[0].toInt()
        val w = dimensions[1].toInt()
        val h = dimensions[2].toInt()

        sum += w*h*l

        if (listOf(l,w,h).max() == l) {
            sum += h+h+w+w
        } else if (listOf(l,w,h).max() == w) {
            sum += l+l+h+h
        } else if (listOf(l,w,h).max() == h) {
            sum += l+l+w+w
        }
    }

    return sum
}
