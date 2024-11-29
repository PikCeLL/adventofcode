package aoc2023

import framework.tests.ADailyTest

class TestDay1 : ADailyTest<Day1>() {
    override fun createInstance(): Day1 {
        return Day1()
    }

    override fun getInputPuzzle1(): String {
        return "1abc2\n" +
                "pqr3stu8vwx\n" +
                "a1b2c3d4e5f\n" +
                "treb7uchet"
    }

    override fun getResultPuzzle1(): Long {
        return 142
    }

    override fun getInputPuzzle2(): String {
        return "two1nine\n" +
                "eightwothree\n" +
                "abcone2threexyz\n" +
                "xtwone3four\n" +
                "4nineeightseven2\n" +
                "zoneight234\n" +
                "7pqrstsixteen"
    }

    override fun getResultPuzzle2(): Long {
        return 281
    }
}