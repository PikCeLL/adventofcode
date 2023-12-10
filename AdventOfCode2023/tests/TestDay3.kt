package aoc2023

import framework.tests.ADailyTest

class TestDay3 : ADailyTest<Day3>() {
    override fun createInstance(): Day3 {
        return Day3()
    }

    override fun getInputPuzzle1(): String {
        return "467..114..\n" +
                "...*......\n" +
                "..35..633.\n" +
                "......#...\n" +
                "617*......\n" +
                ".....+.58.\n" +
                "..592.....\n" +
                "......755.\n" +
                "...\$.*....\n" +
                ".664.598.."
    }

    override fun getResultPuzzle1(): Long {
        return 4361
    }

    override fun getInputPuzzle2(): String {
        return "467..114..\n" +
                "...*......\n" +
                "..35..633.\n" +
                "......#...\n" +
                "617*......\n" +
                ".....+.58.\n" +
                "..592.....\n" +
                "......755.\n" +
                "...\$.*....\n" +
                ".664.598.."
    }

    override fun getResultPuzzle2(): Long {
        return 467835
    }
}