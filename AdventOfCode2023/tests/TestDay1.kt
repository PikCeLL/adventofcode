package aoc2023

import framework.tests.ADailyTest
import utils.PuzzleUtils

class TestDay1 : ADailyTest<Day1>() {
    override fun createInstance(): Day1 {
        return Day1()
    }

    override fun getInputPuzzle1(): String {
        return PuzzleUtils.readFileFromSrc("/test-day1.txt")
    }

    override fun getResultPuzzle1(): Int {
        return "coucou".length
    }

    override fun getInputPuzzle2(): String {
        return "This is another dummy test input"
    }

    override fun getResultPuzzle2(): Int {
        return 1
    }
}