package aoc2023

import framework.src.IDailyPuzzle

fun main() {
    DayTemplate().printResults()
}

class DayTemplate : IDailyPuzzle {
    override fun getInputFile(): String {
        return "/day0.txt"
    }

    override fun getResultPuzzle1(input: String): Int {
        return input.length
    }

    override fun getResultPuzzle2(input: String): Int {
        return 1
    }
}

