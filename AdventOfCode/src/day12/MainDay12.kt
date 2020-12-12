package day12

import kotlin.math.abs

fun main() {
    val fileContent: String = {}::class.java.getResource("/day12.txt").readText().trim()

    println("Puzzle 1 : " + getManhattanDist(fileContent))
    println("Puzzle 1 : " + getManhattanDistWithWaypoint(fileContent))
}

fun getManhattanDist(input: String): Int {
    return input.lineSequence().fold(Pair(1, Pair(0, 0))) { current, instruction ->
        displaceBoat(instruction, current)
    }.second.toList().map { abs(it) }.sum()
}

fun displaceBoat(instruction: String, current: Pair<Int, Pair<Int, Int>>): Pair<Int, Pair<Int, Int>> {
    return when {
        instruction.startsWith('F') -> Pair(current.first, getNewPos(current.second, current.first, getInstructionValue(instruction)))
        instruction.startsWith('L') -> Pair(getNewDir(current.first, -getInstructionValue(instruction)), current.second)
        instruction.startsWith('R') -> Pair(getNewDir(current.first, getInstructionValue(instruction)), current.second)
        else -> Pair(current.first, getNewPos(current.second, getQuartDir(instruction[0]), getInstructionValue(instruction)))
    }
}

fun getManhattanDistWithWaypoint(input: String): Int {
    return input.lineSequence().fold(Pair(Pair(10, 1), Pair(0, 0))) { current, instruction ->
        displaceStuff(instruction, current)
    }.second.toList().map { abs(it) }.sum()
}

fun displaceStuff(instruction: String, current: Pair<Pair<Int, Int>, Pair<Int, Int>>): Pair<Pair<Int, Int>, Pair<Int, Int>> {
    return when {
        instruction.startsWith('F') -> Pair(current.first,
                Pair(current.second.first + getInstructionValue(instruction) * current.first.first,
                        current.second.second + getInstructionValue(instruction) * current.first.second))
        getInstructionValue(instruction) == 180 -> Pair(Pair(-current.first.first, -current.first.second), current.second)
        instruction == "L90" || instruction == "R270" -> Pair(Pair(-current.first.second, current.first.first), current.second)
        instruction == "R90" || instruction == "L270" -> Pair(Pair(current.first.second, -current.first.first), current.second)
        else -> Pair(getNewPos(current.first, getQuartDir(instruction[0]), getInstructionValue(instruction)), current.second)
    }
}

/*
Directions :
North = 0
East = 1
South = 2
West = 3
 */
fun getNewDir(current: Int, angle: Int): Int {
    val quartAngle = angle / 90
    return (4 + current + quartAngle) % 4
}

fun getQuartDir(c: Char): Int {
    return when (c) {
        'N' -> 0
        'E' -> 1
        'S' -> 2
        'W' -> 3
        else -> throw UnsupportedOperationException()
    }
}

fun getNewPos(current: Pair<Int, Int>, dir: Int, value: Int): Pair<Int, Int> {
    return Pair(current.first + ((dir % 2) * (2 - dir)) * value, current.second + ((1 - dir) % 2) * value)
}

fun getInstructionValue(instruction: String): Int {
    return instruction.substring(1 until instruction.length).toInt()
}