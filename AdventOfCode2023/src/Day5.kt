package aoc2023

import framework.src.IDailyPuzzle
import kotlin.math.min

fun main() {
    Day5().printResults()
}

class Day5 : IDailyPuzzle {
    override fun getInputFile(): String {
        return "/day5.txt"
    }


    override fun getResultPuzzle1(input: String): Long {
        val splitInput = input.split("\\r?\\n\\r?\\n.*-to-.* map:\\r?\\n".toRegex())
        val initSeeds = splitInput[0].substring("seeds: ".length).split(' ').map { it.toLong() }
        return splitInput.drop(1).fold(initSeeds) { seeds, entry ->
            val ranges = entry.lineSequence().map { map -> map.split(' ').map { it.toLong() } }
            seeds.map {seed ->
                val range = ranges.find { seed in it[1]..<it[1] + it[2] }
                if (range != null)  seed - range[1] + range[0] else seed
            }.toList()
        }.min().toLong()
    }

    override fun getResultPuzzle2(input: String): Long {
        val splitInput = input.split("\\r?\\n\\r?\\n.*-to-.* map:\\r?\\n".toRegex())
        val rangesMap = splitInput.drop(1).map { entry -> entry.lineSequence().map { map -> map.split(' ').map { it.toLong() } } }
        return splitInput[0].substring("seeds: ".length)
                .split(' ')
                .map { it.toLong() }
                .windowed(2, 2)
                .map {
                    var rangeMin = Long.MAX_VALUE
                    for (i in it[0]..<it[0] + it[1]) {
                        rangeMin = min(rangeMin, rangesMap.fold(i) { seed, entries ->
                            val range = entries.find { seed in it[1]..<it[1] + it[2] }
                            if (range != null) seed - range[1] + range[0] else seed
                        })
                    }
                    rangeMin
                }.min().toLong()
    }
}

