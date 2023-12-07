package aoc2023

import framework.src.IDailyPuzzle
import kotlin.math.*

fun main() {
    Day6().printResults()
}

class Day6 : IDailyPuzzle {
    override fun getInputFile(): String {
        return "/day6.txt"
    }


    override fun getResultPuzzle1(input: String): Int {
        return input.lineSequence().map { line ->
            line.split(':')[1]
            .trim()
            .split("\\s+".toRegex())
            .map { it.toDouble() }
        }.zipWithNext { times, dists ->
            times.zip(dists) { t, d ->
                (ceil((t+(sqrt(t*t-4*d)))/2) - floor((t-(sqrt(t*t-4*d)))/2)).toInt() - 1
            }.reduce { acc, nb -> acc * nb }
        }.first()
    }

    override fun getResultPuzzle2(input: String): Int {
        return input.lineSequence().map { line ->
            line.split(':')[1]
                .replace("\\s+".toRegex(), "").toDouble()
        }.zipWithNext { t, d ->
                (ceil((t+(sqrt(t*t-4*d)))/2) - floor((t-(sqrt(t*t-4*d)))/2)).toInt() - 1
            }.first()
        }
}

