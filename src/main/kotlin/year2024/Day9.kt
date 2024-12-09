package year2024

import me.koendev.*

fun main() {
    solve(
        2024,
        9,
        ::part1,
        ::part2
    )
}

private fun part1(input: List<String>): Long {
    val disk = mutableListOf<Long>()
    var fileValue = 0L
    for ((index, char) in input[0].withIndex()) {
        if (index % 2 == 0) {
            for (i in 1..char.digitToInt()) {
                disk.add(fileValue)
            }
            fileValue++
        }

        else {
            for (i in 1..char.digitToInt()) {
                disk.add(-1L)
            }
        }
    }

    var a = 0
    var b = disk.size-1
    var sum = 0L
    while (a < b) {
        if (disk[a] != -1L) {
            sum += a * disk[a]
            a++
        } else {
            while (disk[b] == -1L) b--
            sum += a * disk[b]
            b--
            a++
        }
    }

    return sum
}

private data class File(var range: LongRange, val value: Int)

private fun part2(input: List<String>): Long {
    val disk = mutableListOf<File>()
    var fileValue = 0
    var position = 0L
    for ((index, char) in input[0].withIndex()) {
        if (index % 2 == 0) {
            disk.add(File(position..< position+char.toString().toLong(), fileValue))
            position += char.toString().toLong()
            fileValue++
        }

        else {
            if (char.digitToInt() == 0) continue
            disk.add(File(position..< position+char.toString().toLong(), -1))
            position += char.toString().toLong()
        }
    }

    for (file in disk.filter { it.value != -1 }.reversed()) {
        val emptySpace = disk.find { it.value == -1 && it.range.length() >= file.range.length() && it.range.first < file.range.first }
        if (emptySpace == null) continue

        file.range = emptySpace.range.first..< emptySpace.range.first+file.range.length()

        if (emptySpace.range.length() == file.range.length()) disk.removeIf { it == emptySpace }
        else emptySpace.range = emptySpace.range.first+file.range.length()..emptySpace.range.last
    }

    var sum = 0L
    for (file in disk.filter { it.value != -1 }) {
        for (i in file.range) sum += i * file.value
    }

    return sum
}
