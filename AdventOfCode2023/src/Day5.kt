package aoc2023

import framework.src.IDailyPuzzle

fun main() {
    Day5().printResults()
}

class Day5 : IDailyPuzzle {
    override fun getInputFile(): String {
        return "/day5.txt"
    }


    override fun getResultPuzzle1(input: String): Int {
        val splitInput = input.split("\\n\\n.*-to-.* map:\\n".toRegex())
        val initSeeds = splitInput[0].substring("seeds: ".length).split(' ').map { it.toLong() }
        return splitInput.drop(1).fold(initSeeds) { seeds, entry ->
            val ranges = entry.lineSequence().map { map -> map.split(' ').map { it.toLong() } }
            seeds.map {seed ->
                val range = ranges.find { seed in it[1]..<it[1] + it[2] }
                if (range != null)  seed - range[1] + range[0] else seed
            }.toList()
        }.min().toInt()
    }

    override fun getResultPuzzle2(input: String): Int {
        val splitInput = input.split("\\n\\n.*-to-.* map:\\n".toRegex())
        val initSeeds = splitInput[0].substring("seeds: ".length)
            .split(' ')
            .map { it.toLong() }
            .windowed(2, 2)
            .map { LongRange(it[0], it[0] + it[1] - 1) }
        splitInput.drop(1).fold(initSeeds) { seeds, entry ->
            val ranges = entry.lineSequence().map { map -> map.split(' ').map { it.toLong() } }
            seeds.flatMap { listOf(LongRange(it.start, ), LongRange(), LongRange(, it.endInclusive)) }
            seeds
        }
        return 0
    }
}

