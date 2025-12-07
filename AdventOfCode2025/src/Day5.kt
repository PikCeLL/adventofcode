package aoc2025

import framework.src.IDailyPuzzle
import kotlin.Pair
import kotlin.collections.plus

fun main() {
    Day5().printResults()
}

class Day5 : IDailyPuzzle {
    override fun getInputFile(): String {
        return "/day5.txt"
    }

    override fun getResultPuzzle1(input: String): Long {
        return input.lineSequence().fold(Pair(emptyList<LongRange>(), 0L)) { pair, line ->
            when {
                line.contains('-') -> {
                    val values = line.split('-')
                    Pair(pair.first +  listOf(values[0].toLong()..values[1].toLong()), pair.second)
                }
                line.isBlank() -> pair
                else -> Pair(pair.first, pair.second + if (pair.first.any { range -> line.toLong() in range }) 1 else 0)
            }
        }.second
    }

    override fun getResultPuzzle2(input: String): Long {
        return input.lineSequence().fold(emptyList<LongRange>()) { ranges, line ->
            when {
                line.contains('-') -> {
                    val values = line.split('-')
                    ranges +  listOf(values[0].toLong()..values[1].toLong())
                }
                else -> ranges
            }
        }.fold(emptyList<LongRange>()) { visitedRanges, range ->
            val updatedList = visitedRanges.toMutableList()
            var updatedRange = range
            var shouldAddRange = true
            for (valid in visitedRanges) {
                when {
                    updatedRange.start >= valid.start && updatedRange.endInclusive <= valid.endInclusive -> {
                        shouldAddRange = false
                        break
                    }
                    updatedRange.start < valid.start && updatedRange.endInclusive > valid.endInclusive -> updatedList.remove(valid)
                    updatedRange.start in valid && updatedRange.endInclusive > valid.endInclusive -> {
                        updatedList.remove(valid)
                        updatedRange = valid.start..updatedRange.endInclusive
                    }
                    updatedRange.start < valid.start && updatedRange.endInclusive in valid -> {
                        updatedList.remove(valid)
                        updatedRange = updatedRange.start..valid.endInclusive
                    }
                }
            }
            if (shouldAddRange) updatedList + listOf(updatedRange) else updatedList
        }.fold(0L) { result, range ->
            result + (range.endInclusive - range.start) + 1
        }
    }
}

