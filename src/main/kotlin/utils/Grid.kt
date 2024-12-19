package me.koendev.utils

@Suppress("Unused")
class Grid<T>(input: List<List<T>>) {
    private var grid: MutableList<MutableList<T>> = mutableListOf()

    init {
        for (line in input) {
            grid.add(mutableListOf())
            for (char in line) {
                grid.last().add(char)
            }
        }
    }

    fun get(x: Int, y: Int): T {
        if (x < 0 || x > grid[0].size-1) throw IllegalArgumentException("X coordinate $x out of bounds for size ${grid[0].size}")
        if (y < 0 || y > grid.size-1) throw IllegalArgumentException("Y coordinate $y out of bounds for size ${grid.size}")
        return grid[y][x]
    }

    fun getRow(y: Int): List<T> {
        if (y < 0 || y > grid.size-1) throw IllegalArgumentException("Y coordinate $y out of bounds for size ${grid.size}")

        return grid[y]
    }

    fun getColumn(x: Int): List<T> {
        if (x < 0 || x > grid[0].size-1) throw IllegalArgumentException("X coordinate $x out of bounds for size ${grid[0].size}")

        val res = mutableListOf<T>()

        for (row in grid) {
            res.add(row[x])
        }

        return res
    }
}