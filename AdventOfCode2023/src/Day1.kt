package aoc2023

import framework.src.IDailyPuzzle

fun main() {
    Day1().printResults()
}

class Day1 : IDailyPuzzle {
    override fun getInputFile(): String {
        return "/day1.txt"
    }

    override fun getResultPuzzle1(input: String): Int {
        return input.lineSequence().map { line -> (line.find { it.isDigit() }.toString() + line.findLast { it.isDigit() }).toInt() }.sum()
    }

    private val digitMap = mapOf(Pair("one", "1"),
            Pair("two", "2"),
            Pair("three", "3"),
            Pair("four", "4"),
            Pair("five", "5"),
            Pair("six", "6"),
            Pair("seven", "7"),
            Pair("eight", "8"),
            Pair("nine", "9"))

    override fun getResultPuzzle2(input: String): Int {
        val digitList = digitMap.keys.toMutableList()
        digitList.addAll(digitMap.values)
        return input.lineSequence().map { line -> (literal2numeral(line.findAnyOf(digitList)!!.second) + literal2numeral(line.findLastAnyOf(digitList)!!.second)).toInt() }.sum()
    }

    private fun literal2numeral(value: String): String {
        return digitMap.getOrDefault(value, value)
    }
}

