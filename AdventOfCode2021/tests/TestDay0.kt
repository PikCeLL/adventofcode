import utils.PuzzleUtils

class TestDay0 : ADailyTest<Day0>() {
    override fun createInstance(): Day0 {
        return Day0()
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