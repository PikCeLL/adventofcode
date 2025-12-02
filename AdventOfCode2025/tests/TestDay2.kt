package aoc2025

import framework.tests.ADailyTest

class TestDay2 : ADailyTest<Day2>() {
    override fun createInstance(): Day2 {
        return Day2()
    }

    override fun getInputPuzzle1(): String {
        return "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124"
    }

    override fun getResultPuzzle1(): Long {
        return 1227775554
    }

    override fun getInputPuzzle2(): String {
        return "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124"
    }

    override fun getResultPuzzle2(): Long {
        return 4174379265
    }
}