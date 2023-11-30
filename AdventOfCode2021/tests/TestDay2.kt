package aoc2021

import framework.tests.ADailyTest

class TestDay2 : ADailyTest<Day2>() {
    override fun createInstance(): Day2 {
        return Day2()
    }

    override fun getInputPuzzle1(): String {
        return "forward 5\n" +
                "down 5\n" +
                "forward 8\n" +
                "up 3\n" +
                "down 8\n" +
                "forward 2"
    }

    override fun getResultPuzzle1(): Int {
        return 150
    }

    override fun getInputPuzzle2(): String {
        return getInputPuzzle1()
    }

    override fun getResultPuzzle2(): Int {
        return 900
    }
}