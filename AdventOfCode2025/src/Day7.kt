package aoc2025

import framework.src.IDailyPuzzle
import kotlin.text.toLong
import kotlin.time.Duration.Companion.seconds

fun main() {
    Day7().printResults()
}

class Day7 : IDailyPuzzle {
    override fun getInputFile(): String {
        return "/day7.txt"
    }

    override fun getResultPuzzle1(input: String): Long {
        return input.lineSequence().fold(Pair(emptySet<Int>(), 0L)) { pair, pipeline ->
            val start = pipeline.indexOf('S')
            if (start != -1) {
                Pair(setOf(start), 0L)
            } else {
                var splits = pair.second
                val newBeams = mutableSetOf<Int>()
                for (beam in pair.first) {
                    if (pipeline[beam] == '^') {
                        ++splits
                        newBeams.add(beam - 1)
                        newBeams.add(beam + 1)
                    } else {
                        newBeams.add(beam)
                    }
                }
                Pair(newBeams, splits)
            }
        }.second
    }

    override fun getResultPuzzle2(input: String): Long {
        return return input.lineSequence().fold(emptyMap<Int, Long>()) { beams, pipeline ->
            val start = pipeline.indexOf('S')
            if (start != -1) {
                mapOf(Pair(start, 1))
            } else {
                val newBeams = mutableMapOf<Int, Long>()
                for (beam in beams) {
                    if (pipeline[beam.key] == '^') {
                        newBeams.merge(beam.key - 1, beam.value) { old, new -> old + new }
                        newBeams.merge(beam.key + 1, beam.value) { old, new -> old + new }
                    } else {
                        newBeams.merge(beam.key, beam.value) { old, new -> old + new }
                    }
                }
                newBeams
            }
        }.values.sum()
    }
}

