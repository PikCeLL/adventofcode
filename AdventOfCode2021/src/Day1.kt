package aoc2021

import framework.src.IDailyPuzzle

fun main() {
    Day1().printResults()
}

class Day1 : IDailyPuzzle {
    override fun getInputFile(): String {
        return "/day1.txt"
    }

    override fun getResultPuzzle1(input: String): Long {
        return input.lineSequence().map { it.toInt() }.windowed(2) { (a, b) -> a < b }.count { it }.toLong()
    }

    override fun getResultPuzzle2(input: String): Long {
        return input.lineSequence().map { it.toInt() }.windowed(3) { it.sum() }
            .windowed(2) { (a, b) -> a < b }.count { it }.toLong()
    }
}

