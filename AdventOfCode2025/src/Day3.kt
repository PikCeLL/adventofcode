package aoc2025

import day1.result
import framework.src.IDailyPuzzle
import java.lang.Math.pow
import kotlin.math.floor
import kotlin.math.log10

fun main() {
    Day3().printResults()
}

class Day3 : IDailyPuzzle {
    override fun getInputFile(): String {
        return "/day3.txt"
    }

    fun getJoltage(input: String, nbBattery: Int): Long {
        return input.lineSequence().map { bank ->
            bank.foldRightIndexed(Array<Char>(nbBattery) { '0' }) { idx, battery, result ->
                if (bank.length - idx < nbBattery) {
                    result[nbBattery - (bank.length - idx)] = battery
                } else  {
                    var i = 0
                    var previous = battery
                    while (i < result.size && result[i] <= previous) {
                        val remainder = result[i]
                        result[i] = previous
                        previous = remainder
                        ++i
                    }
                }
                result
            }.foldIndexed(0L) { idx, combination, battery -> combination + (pow(10.0, (nbBattery - 1 - idx).toDouble()).toLong() * (battery - '0').toLong()) }
        }.sum()
    }

    override fun getResultPuzzle1(input: String): Long {
        return getJoltage(input, 2)
    }

    override fun getResultPuzzle2(input: String): Long {
        return getJoltage(input, 12)
    }
}

