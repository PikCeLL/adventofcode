package aoc2025

import framework.tests.ADailyTest

class TestDay3 : ADailyTest<Day3>() {
    override fun createInstance(): Day3 {
        return Day3()
    }

    override fun getInputPuzzle1(): String {
        return "987654321111111\n" +
                "811111111111119\n" +
                "234234234234278\n" +
                "818181911112111"
    }

    override fun getResultPuzzle1(): Long {
        return 357
    }

    override fun getInputPuzzle2(): String {
        return "987654321111111\n" +
                "811111111111119\n" +
                "234234234234278\n" +
                "818181911112111"
    }

    override fun getResultPuzzle2(): Long {
        return 3121910778619
    }
}