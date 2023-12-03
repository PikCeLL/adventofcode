package aoc2023

import framework.src.IDailyPuzzle
import kotlin.math.max

fun main() {
    Day3().printResults()
}

data class EnginePart(val partNumber: Int, val location: Pair<Int, IntRange>)

class Day3 : IDailyPuzzle {
    override fun getInputFile(): String {
        return "/day3.txt"
    }

    private val numberRegex = "[0-9]+".toRegex()

    override fun getResultPuzzle1(input: String): Int {
        val schematic = Pair<MutableList<List<Char>>, MutableCollection<EnginePart>>(mutableListOf(), mutableListOf())
        return input.lineSequence().foldIndexed(schematic) { i, schem, line ->
            schem.second.addAll(numberRegex.findAll(line).map { EnginePart(it.value.toInt(), Pair(i, it.range)) })
            schem.first.add(line.toCharArray().asList())
            schem
        }.second.filter { part ->
            var isAdjacent = false
            for (i in part.location.first - 1..part.location.first + 1) {
                for (j in part.location.second.first - 1..part.location.second.last + 1) {
                    if (i in 0..<schematic.first.size && j in 0..<schematic.first[i].size) {
                        val character = schematic.first[i][j]
                        isAdjacent = isAdjacent || (!character.isDigit() && character != '.')
                    }
                }
            }
            isAdjacent
        }.sumOf { it.partNumber }
    }

    override fun getResultPuzzle2(input: String): Int {
        val schematic = Pair<MutableList<List<Char>>, MutableCollection<EnginePart>>(mutableListOf(), mutableListOf())
        input.lineSequence().foldIndexed(schematic) { i, schem, line ->
            schem.second.addAll(numberRegex.findAll(line).map { EnginePart(it.value.toInt(), Pair(i, it.range)) })
            schem.first.add(line.toCharArray().asList())
            schem
        }
        var result = 0
        for (i in 0..<schematic.first.size) {
            for (j in 0..<schematic.first[i].size) {
                if (schematic.first[i][j] == '*') {
                    val adjParts = schematic.second.filter { it.location.first in i - 1..i + 1
                            && ((j - 1..j + 1) intersect it.location.second).isNotEmpty() }
                    if (adjParts.size == 2)
                        result += adjParts[0].partNumber * adjParts[1].partNumber
                }
            }
        }
        return result
    }
}

