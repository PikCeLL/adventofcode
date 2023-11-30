import aoc2023.DayTemplate
import framework.tests.ADailyTest
import utils.PuzzleUtils

class TestDayTemplate : ADailyTest<DayTemplate>() {
    override fun createInstance(): DayTemplate {
        return DayTemplate()
    }

    override fun getInputPuzzle1(): String {
        return PuzzleUtils.readFileFromSrc("/day0.txt")
    }

    override fun getResultPuzzle1(): Int {
        return "coucou".length
    }

    override fun getInputPuzzle2(): String {
        return "This is another dummy test input"
    }

    override fun getResultPuzzle2(): Int {
        return 1
    }
}