package aoc2021

import framework.src.IDailyPuzzle
import java.rmi.UnexpectedException

fun main() {
    Day3().printResults()
}

class Day3 : IDailyPuzzle {
    override fun getInputFile(): String {
        return "/day3.txt"
    }

    private fun getGamma(input: String, width: Int, nbLines: Int): IntArray {
        return input.lineSequence().fold(IntArray(width) { 0 }) { accumulator, line ->
            line.foldIndexed(accumulator) { i, summator, c ->
                summator[i] += if (c == '1') 1 else -1
                summator.copyOf()
            }
        }
    }

    override fun getResultPuzzle1(input: String): Long {
        val lines = input.lines()
        val nbLines = lines.size
        val width = lines[0].length

        return getGamma(input, width, nbLines).joinToString("") { if (it > 0) "1" else "0" }.let { value ->
            value.toInt(2) * value.map { (1 - (it - '0')).toString() }.joinToString("").toLong(2)
        }
    }

    private fun filterO2(values: List<String>, index: Int): List<String> {
        val gamma = getGamma(values.joinToString("\n"), values[0].length, values.size)
        return values.filter { value ->
            when {
                gamma[index] >= 0 -> value[index] == '1'
                gamma[index] < 0 -> value[index] == '0'
                else -> throw UnexpectedException("Value can't be both >= 0 and < 0")
            }
        }
    }

    private fun filterCO2(values: List<String>, index: Int): List<String> {
        val gamma = getGamma(values.joinToString("\n"), values[0].length, values.size)
        return values.filter { value ->
            when {
                gamma[index] >= 0 -> value[index] == '0'
                gamma[index] < 0 -> value[index] == '1'
                else -> throw UnexpectedException("Value can't be both >= 0 and < 0")
            }
        }
    }

    override fun getResultPuzzle2(input: String): Long {
        val lines = input.lines()
        var o2Values = lines.toList()
        var co2Values = lines.toList()
        var i = 0

        while (o2Values.size > 1 || co2Values.size > 1) {
            if (o2Values.size > 1) {
                o2Values = filterO2(o2Values, i)
            }
            if (co2Values.size > 1) {
                co2Values = filterCO2(co2Values, i)
            }
            ++i
        }

        return o2Values.first().toInt(2) * co2Values.first().toLong(2)
    }
}

