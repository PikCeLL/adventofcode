package aoc2025

import framework.tests.ADailyTest

class TestDay10 : ADailyTest<Day10>() {
    override fun createInstance(): Day10 {
        return Day10()
    }

    override fun getInputPuzzle1(): String {
        return "[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}\n" +
                "[...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}\n" +
                "[.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}"
    }

    override fun getResultPuzzle1(): Long {
        return 7
    }

    override fun getInputPuzzle2(): String {
        return "[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}\n" +
                "[...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}\n" +
                "[.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}"
    }

    override fun getResultPuzzle2(): Long {
        return 33
    }
}