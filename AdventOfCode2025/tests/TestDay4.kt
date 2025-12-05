package aoc2025

import framework.tests.ADailyTest

class TestDay4 : ADailyTest<Day4>() {
    override fun createInstance(): Day4 {
        return Day4()
    }

    override fun getInputPuzzle1(): String {
        return "..@@.@@@@.\n" +
                "@@@.@.@.@@\n" +
                "@@@@@.@.@@\n" +
                "@.@@@@..@.\n" +
                "@@.@@@@.@@\n" +
                ".@@@@@@@.@\n" +
                ".@.@.@.@@@\n" +
                "@.@@@.@@@@\n" +
                ".@@@@@@@@.\n" +
                "@.@.@@@.@."
    }

    override fun getResultPuzzle1(): Long {
        return 13
    }

    override fun getInputPuzzle2(): String {
        return "..@@.@@@@.\n" +
                "@@@.@.@.@@\n" +
                "@@@@@.@.@@\n" +
                "@.@@@@..@.\n" +
                "@@.@@@@.@@\n" +
                ".@@@@@@@.@\n" +
                ".@.@.@.@@@\n" +
                "@.@@@.@@@@\n" +
                ".@@@@@@@@.\n" +
                "@.@.@@@.@."
    }

    override fun getResultPuzzle2(): Long {
        return 43
    }
}