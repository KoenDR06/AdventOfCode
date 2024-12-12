package year2024

import me.koendev.*
import kotlin.math.floor
import kotlin.math.log10

fun main() {
solve(
    2024,
    11,
    ::part1,
    ::part2
    )
}


private fun part1(input: List<String>): Int {
    val numbers = input[0].split(" ").map { it.toLong() }.toMutableList()

    for (unused in 1..25) {
        for (i in numbers.indices) {
            if (numbers[i] == 0L) numbers[i] = 1L
            else if (numbers[i].toString().length % 2 == 0) {
                val str = numbers[i].toString()
                val l = str.length

                numbers[i] = str.slice(0..< l/2).toLong()
                numbers.add(str.slice(l/2..< str.length).toLong())
            }
            else numbers[i] *= 2024L
        }
    }

    return numbers.size
}

private var memo: MutableMap<Pair<Long, Int>, Long> = mutableMapOf()

private fun dfs(n: Long, depth: Int = 0): Long {
    if (Pair(n, depth) in memo) return memo[Pair(n, depth)]!!
    if (depth >= 75) return 1

    val l = floor(log10(n+0.0f)).toInt()+1

    var sum = 0L
    if (n == 0L) {
        memo[Pair(1, depth+1)] = dfs(2024, depth+2)
        sum += memo[Pair(1, depth+1)]!!
    }
    else if (l % 2 == 0) {
        val exp = pow(10, l/2)
        val rest = n % exp

        memo[Pair((n-rest)/exp, depth+1)] = dfs((n - rest) / exp, depth+1)
        sum += memo[Pair((n-rest)/exp, depth+1)]!!
        memo[Pair(rest, depth+1)] = dfs(rest, depth+1)
        sum += memo[Pair(rest, depth+1)]!!
    }
    else {
        sum += dfs(2024 * n, depth+1)
    }

    return sum
}

private fun part2(input: List<String>): Long {
    val numbers = input[0].split(" ").map { it.toLong() }.toMutableList()

    var sum = 0L
    for (n in numbers) {
        sum += dfs(n, 0)
    }

    return sum
}
