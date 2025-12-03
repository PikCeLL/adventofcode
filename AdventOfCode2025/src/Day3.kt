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

    override fun getResultPuzzle1(input: String): Long {
        return input.lineSequence().map { bank ->
            bank.foldRightIndexed(Pair('0', '0')) { idx, battery, result ->
                var tens = result.first
                var units = result.second
                if (idx == bank.length - 1) {
                    units = battery
                }
                if (idx < bank.length - 1 && battery >= result.first) {
                    tens = battery
                    if (result.first > result.second) {
                        units = result.first
                    }
                }
                Pair(tens, units)
            }.let { pair -> (pair.first - '0') * 10 + (pair.second - '0').toLong() }
        }.sum()
    }

    override fun getResultPuzzle2(input: String): Long {
        // TODO Pareil que la p1 mais avec un tableau plutôt qu'une pair et une cascade via une boucle
        return 0
    }
}

