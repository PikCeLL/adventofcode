package aoc2025

import framework.tests.ADailyTest

class TestDay7 : ADailyTest<Day7>() {
    override fun createInstance(): Day7 {
        return Day7()
    }

    override fun getInputPuzzle1(): String {
        return ".......S.......\n" +
                "...............\n" +
                ".......^.......\n" +
                "...............\n" +
                "......^.^......\n" +
                "...............\n" +
                ".....^.^.^.....\n" +
                "...............\n" +
                "....^.^...^....\n" +
                "...............\n" +
                "...^.^...^.^...\n" +
                "...............\n" +
                "..^...^.....^..\n" +
                "...............\n" +
                ".^.^.^.^.^...^.\n" +
                "..............."
    }

    override fun getResultPuzzle1(): Long {
        return 21
    }

    override fun getInputPuzzle2(): String {
        return ".......S.......\n" +
                "...............\n" +
                ".......^.......\n" +
                "...............\n" +
                "......^.^......\n" +
                "...............\n" +
                ".....^.^.^.....\n" +
                "...............\n" +
                "....^.^...^....\n" +
                "...............\n" +
                "...^.^...^.^...\n" +
                "...............\n" +
                "..^...^.....^..\n" +
                "...............\n" +
                ".^.^.^.^.^...^.\n" +
                "..............."
    }

    override fun getResultPuzzle2(): Long {
        return 40L
    }
}