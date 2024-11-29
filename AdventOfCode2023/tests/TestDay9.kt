package aoc2023

import framework.tests.ADailyTest
import org.testng.annotations.Test
import kotlin.test.assertEquals

class TestDay9 : ADailyTest<Day9>() {
    override fun createInstance(): Day9 {
        return Day9()
    }

    override fun getInputPuzzle1(): String {
        return "0 3 6 9 12 15\n" +
                "1 3 6 10 15 21\n" +
                "10 13 16 21 30 45"
    }

    override fun getResultPuzzle1(): Long {
        return 114
    }

    override fun getInputPuzzle2(): String {
        return "0 3 6 9 12 15\n" +
                "1 3 6 10 15 21\n" +
                "10 13 16 21 30 45"
    }

    override fun getResultPuzzle2(): Long {
        return 2
    }


}