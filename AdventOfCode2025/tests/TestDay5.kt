package aoc2025

import framework.tests.ADailyTest

class TestDay5 : ADailyTest<Day5>() {
    override fun createInstance(): Day5 {
        return Day5()
    }

    override fun getInputPuzzle1(): String {
        return "3-5\n" +
                "10-14\n" +
                "16-20\n" +
                "12-18\n" +
                "\n" +
                "1\n" +
                "5\n" +
                "8\n" +
                "11\n" +
                "17\n" +
                "32"
    }

    override fun getResultPuzzle1(): Long {
        return 3
    }

    override fun getInputPuzzle2(): String {
        return "3-5\n" +
                "10-14\n" +
                "16-20\n" +
                "12-18\n" +
                "\n" +
                "1\n" +
                "5\n" +
                "8\n" +
                "11\n" +
                "17\n" +
                "32"
    }

    override fun getResultPuzzle2(): Long {
        return 14
    }
}