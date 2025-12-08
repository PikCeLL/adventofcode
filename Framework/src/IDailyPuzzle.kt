package framework.src

import utils.PuzzleUtils
import kotlin.time.measureTime

interface IDailyPuzzle {
    fun getInputFile(): String
    fun getResultPuzzle1(input: String): Long
    fun getResultPuzzle2(input: String): Long
    fun printResults() {
        val fileContent = PuzzleUtils.readFileFromSrc(getInputFile())
        println(measureTime { println("Puzzle 1 : " + getResultPuzzle1(fileContent)) })
        println(measureTime { println("Puzzle 2 : " + getResultPuzzle2(fileContent)) })
    }
}