package aoc2025

import framework.src.IDailyPuzzle
import kotlin.text.toLong

fun main() {
    Day6().printResults()
}

class Day6 : IDailyPuzzle {
    override fun getInputFile(): String {
        return "/day6.txt"
    }

    val WHITESPACE = "\\s".toRegex()

    override fun getResultPuzzle1(input: String): Long {
        val formatedInput = input.lineSequence().map { it.split(WHITESPACE).filter { !it.isEmpty() } }.toList()
        var checksum = 0L
        for (col in 0..formatedInput.last().lastIndex) {
            val sign = formatedInput.last()[col]
            checksum += when (sign) {
                            "+" -> {
                                var sum = 0L
                                for (i in 0..formatedInput.lastIndex - 1) {
                                    sum = sum + formatedInput[i][col].toLong()
                                }
                                sum
                            }
                            else -> {
                                var product = 1L
                                for (i in 0..formatedInput.lastIndex - 1) {
                                    product = product * formatedInput[i][col].toLong()
                                }
                                product
                            }
                        }
        }
        return checksum
    }

    override fun getResultPuzzle2(input: String): Long {
        val lines = input.lineSequence().toList()
        var checksum = 0L
        var isSum = true
        var currentResult = 0L
        for (col in 0 .. lines[0].lastIndex) {
            if (col <= lines.last().lastIndex) {
                when (lines.last()[col]) {
                    '+' -> {
                        isSum = true
                        checksum += currentResult
                        currentResult = 0L
                    }
                    '*' -> {
                        isSum = false
                        checksum += currentResult
                        currentResult = 1L
                    }
                }
            }

            var pow = 1
            var number = 0L
            for (i in lines.lastIndex - 1 downTo 0) {
                if (lines[i][col] != ' ') {
                    number += lines[i][col].digitToInt() * pow
                    pow *= 10
                }
            }
            if (number != 0L) {
                if (isSum) {
                    currentResult += number
                } else {
                    currentResult *= number
                }
            }
        }
        return checksum + currentResult
    }
}

