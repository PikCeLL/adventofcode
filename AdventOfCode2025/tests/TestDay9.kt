package aoc2025

import framework.tests.ADailyTest

class TestDay9 : ADailyTest<Day9>() {
    override fun createInstance(): Day9 {
        return Day9()
    }

    override fun getInputPuzzle1(): String {
        return "7,1\n" +
                "11,1\n" +
                "11,7\n" +
                "9,7\n" +
                "9,5\n" +
                "2,5\n" +
                "2,3\n" +
                "7,3"
    }

    override fun getResultPuzzle1(): Long {
        return 50
    }

    override fun getInputPuzzle2(): String {
        return "7,1\n" +
                "11,1\n" +
                "11,7\n" +
                "9,7\n" +
                "9,5\n" +
                "2,5\n" +
                "2,3\n" +
                "7,3"
    }

    override fun getResultPuzzle2(): Long {
        return 24
    }
}