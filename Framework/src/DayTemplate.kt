package aoc2023

import framework.src.IDailyPuzzle

fun main() {
    DayTemplate().printResults()
}

class DayTemplate : IDailyPuzzle {
    override fun getInputFile(): String {
        return "/day0.txt"
    }

    override fun getResultPuzzle1(input: String): Long {
        return input.length.toLong()
    }

    override fun getResultPuzzle2(input: String): Long {
        return 1
    }
}

