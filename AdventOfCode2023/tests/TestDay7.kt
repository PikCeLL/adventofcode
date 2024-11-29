package aoc2023

import framework.tests.ADailyTest

class TestDay7 : ADailyTest<Day7>() {
    override fun createInstance(): Day7 {
        return Day7()
    }

    override fun getInputPuzzle1(): String {
        return "32T3K 765\n" +
                "T55J5 684\n" +
                "KK677 28\n" +
                "KTJJT 220\n" +
                "QQQJA 483"
    }

    override fun getResultPuzzle1(): Long {
        return 6440
    }

    override fun getInputPuzzle2(): String {
        return "32T3K 765\n" +
                "T55J5 684\n" +
                "KK677 28\n" +
                "KTJJT 220\n" +
                "QQQJA 483"
    }

    override fun getResultPuzzle2(): Long {
        return 5905
    }
}