package aoc2025

import framework.src.IDailyPuzzle
import java.lang.Math.pow
import kotlin.math.floor
import kotlin.math.log10

fun main() {
    Day2().printResults()
}

class Day2 : IDailyPuzzle {
    override fun getInputFile(): String {
        return "/day2.txt"
    }

    override fun getResultPuzzle1(input: String): Long {
        val intervals = input.split(',')
        return intervals.fold(0L) { result, sInterval ->
            val interval = sInterval.split('-').map { s -> s.toDouble() }.toDoubleArray()
            val pow = pow(10.0, floor(log10(interval[0])/2)+1)
            var prefix = floor(interval[0] / pow)
            var invalids = 0L
            while (prefix * pow <= interval[1]) {
                val value = prefix * pow + prefix
                if (value in interval[0] .. interval[1] && log10(value).toInt() % 2 == 1 ) {
                    invalids += value.toLong()
                }
                ++prefix
            }
            result + invalids
        }
    }

    override fun getResultPuzzle2(input: String): Long {
        val intervals = input.split(',')
        return intervals.fold(0L) { result, sInterval ->
            val interval = sInterval.split('-').map { s -> s.toLong() }.toLongArray()
            var invalids = 0L
            for (i in interval[0] .. interval[1]) {
                if (i.toString().matches(Regex("([0-9]+)\\1+\$"))) {
                    invalids = invalids + i
                }
            }
            result + invalids
        }
    }
}

