package framework.src

import utils.PuzzleUtils

interface IDailyPuzzle {
    fun getInputFile(): String
    fun getResultPuzzle1(input: String): Int
    fun getResultPuzzle2(input: String): Int
    fun printResults() {
        val fileContent = PuzzleUtils.readFileFromSrc(getInputFile())
        println("Puzzle 1 : " + getResultPuzzle1(fileContent))
        println("Puzzle 2 : " + getResultPuzzle2(fileContent))
    }
}