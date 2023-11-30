package aoc2021

import framework.tests.ADailyTest

class TestDay3 : ADailyTest<Day3>() {
    override fun createInstance(): Day3 {
        return Day3()
    }

    override fun getInputPuzzle1(): String {
        return "00100\n" +
                "11110\n" +
                "10110\n" +
                "10111\n" +
                "10101\n" +
                "01111\n" +
                "00111\n" +
                "11100\n" +
                "10000\n" +
                "11001\n" +
                "00010\n" +
                "01010"
    }

    override fun getResultPuzzle1(): Int {
        return 198
    }

    override fun getInputPuzzle2(): String {
        return getInputPuzzle1()
    }

    override fun getResultPuzzle2(): Int {
        return 230
    }
}