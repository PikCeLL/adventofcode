package aoc2025

import framework.src.IDailyPuzzle
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sign
import kotlin.math.sqrt
import kotlin.text.toLong
import kotlin.time.Duration.Companion.seconds

fun main() {
    Day8().printResults()
}

data class Box(val x: Long, val y: Long, val z: Long) {
    infix fun simpleDist(other: Box): Double {
        // Should take que sqroot, but we only care about the ordering
        return (x - other.x).toDouble().pow(2) + (y - other.y).toDouble().pow(2) + (z - other.z).toDouble().pow(2)
    }
}

class Day8 : IDailyPuzzle {
    override fun getInputFile(): String {
        return "/day8.txt"
    }

    override fun getResultPuzzle1(input: String): Long {
        val nbShortest = 1000
        val connectedMesh = input.lineSequence().fold(Pair(
            emptySet<Pair<Box, Box>>().toSortedSet { pair1, pair2 ->
                (pair1.first.simpleDist(pair1.second) - pair2.first.simpleDist(pair2.second)).sign.toInt() },
            emptyList<Box>()))
        { folding, newBox ->
                val coords = newBox.split(',')
                val box = Box(coords[0].toLong(), coords[1].toLong(), coords[2].toLong())
                for (oldBox in folding.second) {
                    folding.first.add(Pair(oldBox, box))
                    if (folding.first.size > nbShortest) {
                        folding.first.remove(folding.first.last())
                    }
                }
                Pair(folding.first, folding.second + box)
        }.first

        val groups = connectedMesh.fold(mutableListOf<MutableSet<Box>>()) { sets, boxPair ->
            val newSet = sets.filter { it.contains(boxPair.first) || it.contains(boxPair.second) }.onEach { sets.remove(it) }.flatten().toMutableSet()
            if (newSet.isEmpty()) {
                sets.add(mutableSetOf(boxPair.first, boxPair.second))
            } else {
                newSet.add(boxPair.first)
                newSet.add(boxPair.second)
                sets.add(newSet)
            }
            sets
        }
        groups.sortWith { set1, set2 -> set2.size - set1.size }
        return (groups[0].size * groups[1].size * groups[2].size).toLong()
    }

    override fun getResultPuzzle2(input: String): Long {
        val parsedInput = input.lineSequence().fold(Pair(
            emptySet<Pair<Box, Box>>().toSortedSet { pair1, pair2 ->
                (pair1.first.simpleDist(pair1.second) - pair2.first.simpleDist(pair2.second)).sign.toInt() },
            emptyList<Box>()))
        { folding, newBox ->
            val coords = newBox.split(',')
            val box = Box(coords[0].toLong(), coords[1].toLong(), coords[2].toLong())
            for (oldBox in folding.second) {
                folding.first.add(Pair(oldBox, box))
            }
            Pair(folding.first, folding.second + box)
        }
        val connectedMesh = parsedInput.first

        var result = 0L
        val groups = mutableListOf<MutableSet<Box>>()
        val iterator = connectedMesh.iterator()
        while (result == 0L) {
            val boxPair = iterator.next()
            val newSet = groups.filter { it.contains(boxPair.first) || it.contains(boxPair.second) }.onEach { groups.remove(it) }.flatten().toMutableSet()
            if (newSet.isEmpty()) {
                groups.add(mutableSetOf(boxPair.first, boxPair.second))
            } else {
                newSet.add(boxPair.first)
                newSet.add(boxPair.second)
                groups.add(newSet)
            }
            if (groups.size == 1 && groups[0].size == parsedInput.second.size) {
                result = boxPair.first.x * boxPair.second.x
            }
        }
        return result
    }
}

