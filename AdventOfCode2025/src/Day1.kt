package aoc2025

import framework.src.IDailyPuzzle
import kotlin.math.abs

fun main() {
    Day1().printResults()
}

class Day1 : IDailyPuzzle {
    override fun getInputFile(): String {
        return "/day1.txt"
    }

    fun positiveModulo(value: Long, mod: Long): Long {
        return (value % mod + mod) % mod
    }

    override fun getResultPuzzle1(input: String): Long {
        return input.lineSequence().fold(Pair(50L, 0L)) { result, line ->
            val spot = positiveModulo((((if (line.startsWith('L')) -1 else 1) * line.substring(1, line.length).toLong()) + result.first), 100)
            Pair(spot, if (spot == 0L) result.second + 1 else result.second)
        }.second
    }

    override fun getResultPuzzle2(input: String): Long {
        return input.lineSequence().fold(Pair(50L, 0L)) { result, line ->
            val spot = result.first + (if (line.startsWith('L')) -1 else 1) * line.substring(1, line.length).toLong()
            Pair(positiveModulo(spot, 100), result.second + if (spot <= 0) (abs(spot) / 100) + 1 else (spot - 99) / 100)
        }.second
    }
}

