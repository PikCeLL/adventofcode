package aoc2021

import framework.tests.ADailyTest

class TestDay1 : ADailyTest<Day1>() {
    override fun createInstance(): Day1 {
        return Day1()
    }

    override fun getInputPuzzle1(): String {
        return "199\n" +
                "200\n" +
                "208\n" +
                "210\n" +
                "200\n" +
                "207\n" +
                "240\n" +
                "269\n" +
                "260\n" +
                "263"
    }

    override fun getResultPuzzle1(): Long {
        return 7
    }

    override fun getInputPuzzle2(): String {
        return getInputPuzzle1()
    }

    override fun getResultPuzzle2(): Long {
        return 5
    }
}