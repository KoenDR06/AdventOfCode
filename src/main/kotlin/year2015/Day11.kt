package year2015

import me.koendev.*

fun main() {
    solve(
        2015,
        11,
        ::part1,
        ::part2
    )
}

private fun convertToBase26(input: String, chars: String = "abcdefghijklmnopqrstuvwxyz"): MutableList<Int> {
    val res: MutableList<Int> = mutableListOf()

    for (char in input) {
        res.add(
            chars.indexOf(char)
        )
    }

    return res
}

private fun convertFromBase26(input: MutableList<Int>, chars: String = "abcdefghijklmnopqrstuvwxyz"): String {
    var res = ""

    for (index in input) {
        res += chars[index]
    }

    return res
}

private fun part1(input: List<String>): String {
    val currentPassword: MutableList<Int> = convertToBase26(input[0])

    var noForbiddenChars = false
    var stair = false
    var count = 0

    while (!(noForbiddenChars && stair && count>=2)) {
        noForbiddenChars = true
        for (item in currentPassword) {
            if (item == 8 || item == 11 || item == 14) {
                noForbiddenChars = false
                break
            }
        }

        stair = false
        for (i in 0..< currentPassword.size-2) {
            if (currentPassword[i] - currentPassword[i+1] == -1 && currentPassword[i+1] - currentPassword[i+2] == -1) {
                stair = true
                break
            }
        }

        count = 0
        var offset = 0
        for (i in 0..< currentPassword.size-1) {
            if (i+offset+1 >= currentPassword.size) break

            if (currentPassword[i+offset] == currentPassword[i+1+offset]) {
                offset++
                count++
            }
        }

        if (noForbiddenChars && stair && count>=2) break

        var index = currentPassword.size-1
        currentPassword[index] += 1
        while (currentPassword[index] == 26) {
            currentPassword[index] = 0
            try {
                currentPassword[index - 1] += 1
            } catch (e: IndexOutOfBoundsException) {break}
            index--
        }
    }

    return convertFromBase26(currentPassword)
}


private fun part2(input: List<String>): String {
    val currentPassword: MutableList<Int> = convertToBase26(input[0])

    var noForbiddenChars = false
    var stair = false
    var count = 0

    while (!(noForbiddenChars && stair && count>=2)) {
        noForbiddenChars = true
        for (item in currentPassword) {
            if (item == 8 || item == 11 || item == 14) {
                noForbiddenChars = false
                break
            }
        }

        stair = false
        for (i in 0..< currentPassword.size-2) {
            if (currentPassword[i] - currentPassword[i+1] == -1 && currentPassword[i+1] - currentPassword[i+2] == -1) {
                stair = true
                break
            }
        }

        count = 0
        var offset = 0
        for (i in 0..< currentPassword.size-1) {
            if (i+offset+1 >= currentPassword.size) break

            if (currentPassword[i+offset] == currentPassword[i+1+offset]) {
                offset++
                count++
            }
        }

        var index = currentPassword.size-1
        currentPassword[index] += 1
        while (currentPassword[index] == 26) {
            currentPassword[index] = 0
            try {
                currentPassword[index - 1] += 1
            } catch (e: IndexOutOfBoundsException) {break}
            index--
        }
    }

    noForbiddenChars = false
    stair = false
    count = 0

    while (!(noForbiddenChars && stair && count>=2)) {
        noForbiddenChars = true
        for (item in currentPassword) {
            if (item == 8 || item == 11 || item == 14) {
                noForbiddenChars = false
                break
            }
        }

        stair = false
        for (i in 0..< currentPassword.size-2) {
            if (currentPassword[i] - currentPassword[i+1] == -1 && currentPassword[i+1] - currentPassword[i+2] == -1) {
                stair = true
                break
            }
        }

        count = 0
        var offset = 0
        for (i in 0..< currentPassword.size-1) {
            if (i+offset+1 >= currentPassword.size) break

            if (currentPassword[i+offset] == currentPassword[i+1+offset]) {
                offset++
                count++
            }
        }

        if (noForbiddenChars && stair && count>=2) break

        var index = currentPassword.size-1
        currentPassword[index] += 1
        while (currentPassword[index] == 26) {
            currentPassword[index] = 0
            try {
                currentPassword[index - 1] += 1
            } catch (e: IndexOutOfBoundsException) {break}
            index--
        }
    }

    return convertFromBase26(currentPassword)
}
