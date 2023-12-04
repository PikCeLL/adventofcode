package aoc2023

import framework.src.IDailyPuzzle
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.pow

fun main() {
    Day4().printResults()
}


class Day4 : IDailyPuzzle {
    override fun getInputFile(): String {
        return "/day4.txt"
    }


    override fun getResultPuzzle1(input: String): Int {
        return input.lineSequence().map { line ->
            val card = line.split(':')[1].split('|')
            floor(2.0.pow(card[1].trim().split("\\s+".toRegex()).count { card[0].trim().split("\\s+".toRegex()).contains(it) } - 1))
        }.sum().toInt()
    }

    override fun getResultPuzzle2(input: String): Int {
        return input.lineSequence().foldIndexed(mutableMapOf<Int, Int>(Pair(0,1))) { i, nbCards, line ->
            val card = line.split(':')[1].split('|')
            val nbMatches = card[1].trim().split("\\s+".toRegex()).count { card[0].trim().split("\\s+".toRegex()).contains(it) }
            nbCards.putIfAbsent(i, 1)
            for (j in i+1..i+nbMatches) {
                nbCards[j] = nbCards.getOrElse(j) { 1 } + nbCards.getOrElse(i) { 0 }
            }
            nbCards
        }.values.sum()
    }
}

