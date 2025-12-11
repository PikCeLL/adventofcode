package aoc2025

import framework.src.IDailyPuzzle
import kotlin.math.min

fun main() {
    Day10().printResults()
}

class Day10 : IDailyPuzzle {
    override fun getInputFile(): String {
        return "/day10.txt"
    }

    override fun getResultPuzzle1(input: String): Long {
        return input.lineSequence().fold(0L) { res, line ->
            val split = line.split(' ')
            val buttons = mutableListOf<List<Int>>()
            for (idx in 1..< split.lastIndex) {
                buttons.add(split[idx].substring(1, split[idx].lastIndex).mapNotNull { if (it == ',') null else it.digitToInt() }.toList())
            }
            val target = split[0].substring(1, split[0].lastIndex)
            var min = buttons.size
            for (i in 0..(1 shl buttons.size)) {
                val lights = Array(target.length) { 0 }
                for (flag in 0 ..< buttons.size) {
                    if ((i shr flag) % 2 == 1) {
                        for (value in buttons[flag]) {
                            ++lights[value]
                        }
                    }
                }
                if (target == lights.map { if (it%2 == 0) '.' else '#' }.toCharArray().concatToString()) {
                    min = min(min, i.countOneBits())
                }
            }
            res + min
        }
    }

    override fun getResultPuzzle2(input: String): Long {
        return input.lineSequence().fold(0L) { res, line ->
            val split = line.split(' ')
            val buttons = mutableListOf<List<Int>>()
            for (idx in 1..< split.lastIndex) {
                buttons.add(split[idx].substring(1, split[idx].lastIndex).mapNotNull { if (it == ',') null else it.digitToInt() }.toList())
            }
            val target = split[split.lastIndex].substring(1, split[split.lastIndex].lastIndex)
            var step = 0
            // TODO Parcours du graph des press de bouttons : drop la branche si une des valeurs dépasse le target
            res + step
        }
    }
}

