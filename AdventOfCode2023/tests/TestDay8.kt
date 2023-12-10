package aoc2023

import framework.tests.ADailyTest
import org.testng.annotations.Test
import kotlin.test.assertEquals

class TestDay8 : ADailyTest<Day8>() {
    override fun createInstance(): Day8 {
        return Day8()
    }

    override fun getInputPuzzle1(): String {
        return "RL\n" +
                "\n" +
                "AAA = (BBB, CCC)\n" +
                "BBB = (DDD, EEE)\n" +
                "CCC = (ZZZ, GGG)\n" +
                "DDD = (DDD, DDD)\n" +
                "EEE = (EEE, EEE)\n" +
                "GGG = (GGG, GGG)\n" +
                "ZZZ = (ZZZ, ZZZ)"
    }

    override fun getResultPuzzle1(): Long {
        return 2
    }

    @Test
    fun testPuzzle1_2() {
        assertEquals(6, instance.getResultPuzzle1("LLR\n" +
                "\n" +
                "AAA = (BBB, BBB)\n" +
                "BBB = (AAA, ZZZ)\n" +
                "ZZZ = (ZZZ, ZZZ)"))
    }

    override fun getInputPuzzle2(): String {
        return "LR\n" +
                "\n" +
                "11A = (11B, XXX)\n" +
                "11B = (XXX, 11Z)\n" +
                "11Z = (11B, XXX)\n" +
                "22A = (22B, XXX)\n" +
                "22B = (22C, 22C)\n" +
                "22C = (22Z, 22Z)\n" +
                "22Z = (22B, 22B)\n" +
                "XXX = (XXX, XXX)"
    }

    override fun getResultPuzzle2(): Long {
        return 6
    }


}