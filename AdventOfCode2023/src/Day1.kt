package aoc2023

import framework.src.IDailyPuzzle

 fun main() {
    Day1().printResults()
}

 class Day1 : IDailyPuzzle {
    override fun getInputFile(): String {
        return "/day1.txt"
    }

    override fun getResultPuzzle1(input: String): Int {
        return input.length
    }

    override fun getResultPuzzle2(input: String): Int {
        return 1
    }
}

