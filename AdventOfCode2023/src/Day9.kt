package aoc2023

import framework.src.IDailyPuzzle
import kotlin.math.abs

fun main() {
    Day9().printResults()
}

class Day9 : IDailyPuzzle {
    override fun getInputFile(): String {
        return "/day9.txt"
    }
    override fun getResultPuzzle1(input: String): Long {
        return input.lineSequence().map { line ->
            val lastValues: MutableList<Long> = mutableListOf()
            var values = line.split(" ").map { it.toLong() }
            do {
                lastValues.add(values.last())
                values = values.windowed(2).map { it[1] - it[0] }
            } while (values.any { it != 0L })
            lastValues.foldRight(0L) { newVal, lastVal ->
                lastVal + newVal
            }
        }.sum()
    }

    override fun getResultPuzzle2(input: String): Long {
        return input.lineSequence().map { line ->
            val firstValues: MutableList<Long> = mutableListOf()
            var values = line.split(" ").map { it.toLong() }
            do {
                firstValues.add(values.first())
                values = values.windowed(2).map { it[1] - it[0] }
            } while (values.any { it != 0L })
            firstValues.foldRight(0L) { newVal, firstVal ->
                newVal - firstVal
            }
        }.sum()
    }
}

