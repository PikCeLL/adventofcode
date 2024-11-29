package aoc2023

import framework.tests.ADailyTest

class TestDay6 : ADailyTest<Day6>() {
    override fun createInstance(): Day6 {
        return Day6()
    }

    override fun getInputPuzzle1(): String {
        return "Time:      7  15   30\n" +
                "Distance:  9  40  200"
    }

    override fun getResultPuzzle1(): Long {
        return 288
    }

    override fun getInputPuzzle2(): String {
        return "Time:      7  15   30\n" +
                "Distance:  9  40  200"
    }

    override fun getResultPuzzle2(): Long {
        return 71503
    }
}