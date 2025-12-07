package aoc2025

import framework.tests.ADailyTest

class TestDay6 : ADailyTest<Day6>() {
    override fun createInstance(): Day6 {
        return Day6()
    }

    override fun getInputPuzzle1(): String {
        return "123 328  51 64 \n" +
                " 45 64  387 23 \n" +
                "  6 98  215 314\n" +
                "*   +   *   +  "
    }

    override fun getResultPuzzle1(): Long {
        return 4277556
    }

    override fun getInputPuzzle2(): String {
        return "123 328  51 64 \n" +
                " 45 64  387 23 \n" +
                "  6 98  215 314\n" +
                "*   +   *   +  "
    }

    override fun getResultPuzzle2(): Long {
        return 3263827
    }
}