package aoc2025

import framework.src.IDailyPuzzle
import kotlin.math.max
import kotlin.math.min

fun main() {
    Day4().printResults()
}

class Day4 : IDailyPuzzle {
    override fun getInputFile(): String {
        return "/day4.txt"
    }

    fun getAdjacenceMap(input: String): MutableList<MutableList<Int>> {
        return input.fold(mutableListOf(mutableListOf<Int>())) { grid, char ->
            val line = grid.last()
            when (char) {
                '\n' -> grid.add(mutableListOf())
                '@' -> {
                    var adj = 0
                    if (line.isNotEmpty() && line[line.lastIndex] > -1) {
                        ++line[line.lastIndex]
                        ++adj
                    }
                    if (grid.size > 1) {
                        for (i in (if (line.lastIndex == -1) 1 else 0)..(if (line.lastIndex == grid[0].lastIndex - 1) 1 else 2)) {
                            if (grid[grid.lastIndex - 1][line.lastIndex + i] > -1) {
                                ++grid[grid.lastIndex - 1][line.lastIndex + i]
                                ++adj
                            }
                        }
                    }
                    line.add(adj)
                }
                else -> line.add(-1)
            }
            grid
        }
    }

    override fun getResultPuzzle1(input: String): Long {
        return getAdjacenceMap(input).flatMap { ints -> ints }.count { i -> i in 0 ..< 4  }.toLong()
    }

    override fun getResultPuzzle2(input: String): Long {
        val adjacentsMap = getAdjacenceMap(input)

        var removedTotal = 0L
        var removedThisRound = 0L
        while (removedTotal == 0L || removedThisRound != 0L) {
            removedThisRound = 0L
            for (i in 0 .. adjacentsMap.lastIndex) {
                for (j in 0 .. adjacentsMap[0].lastIndex) {
                    if (adjacentsMap[i][j] in 0 ..< 4) {
                        ++removedThisRound
                        adjacentsMap[i][j] = -1

                        for (x in max(0, i - 1) .. min(adjacentsMap.lastIndex, i + 1)) {
                            for (y in max(0, j - 1) .. min(adjacentsMap[0].lastIndex, j + 1)) {
                                --adjacentsMap[x][y]
                            }
                        }
                    }
                }
            }
            removedTotal = removedTotal + removedThisRound
        }

        return removedTotal
    }
}

