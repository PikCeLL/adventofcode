package aoc2025

import framework.tests.ADailyTest

class TestDay1 : ADailyTest<Day1>() {
    override fun createInstance(): Day1 {
        return Day1()
    }

    override fun getInputPuzzle1(): String {
        return "L68\n" +
                "L30\n" +
                "R48\n" +
                "L5\n" +
                "R60\n" +
                "L55\n" +
                "L1\n" +
                "L99\n" +
                "R14\n" +
                "L82"
    }

    override fun getResultPuzzle1(): Long {
        return 3
    }

    override fun getInputPuzzle2(): String {
        return "L68\n" +
                "L30\n" +
                "R48\n" +
                "L5\n" +
                "R60\n" +
                "L55\n" +
                "L1\n" +
                "L99\n" +
                "R14\n" +
                "L82"
    }

    override fun getResultPuzzle2(): Long {
        return 6
    }
}