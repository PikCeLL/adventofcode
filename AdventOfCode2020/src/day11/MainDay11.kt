package day11

import kotlin.collections.sum
import kotlin.math.max
import kotlin.math.min

val norm = IntRange(-1,1)

fun main() {
    val fileContent: String = {}::class.java.getResource("/day11.txt").readText().trim()

    println("Puzzle 1 : " + getStabilizedSeating(fileContent, 4) {
        x: Int, y: Int, current: Char, seating: Array<String> -> countAdjOccupiedSeats(x, y, current, seating)
    }.count { it == '#' })
    println("Puzzle 1 : " + getStabilizedSeating(fileContent, 5) {
        x: Int, y: Int, current: Char, seating: Array<String> -> countSightedOccupiedSeats(x, y, current, seating)
    }.count { it == '#' })
}

fun getStabilizedSeating(input: String, threshold:Int, adjRule: (Int, Int, Char, Array<String>) -> Int): String {
    var base = input.split("\n").toList().toTypedArray()
    var dest = base
    do {
        base = dest
        dest = base.mapIndexed { y, line -> line.mapIndexed { x, c ->
            if(c=='.') {
                '.'
            } else {
                val occupiedAdjSeats = adjRule(x, y, c, base)
                when {
                    c == 'L' && occupiedAdjSeats == 0 -> '#'
                    c == '#' && occupiedAdjSeats >= threshold -> 'L'
                    else -> c
                }
            }
        }.joinToString("")}.toTypedArray()
    } while (!base.contentEquals(dest))
    return dest.joinToString("\n")
}

fun countAdjOccupiedSeats(x: Int, y: Int, current: Char, seating: Array<String>): Int {
    return seating.sliceArray(max(0, y-1)..min(y+1, seating.size-1))
            .map { line -> line.slice(max(0, x-1)..min(x+1, line.length-1)).count { it == '#'} }
            .sum() - if(current == '#') 1 else 0
}

fun countSightedOccupiedSeats(x: Int, y: Int, current: Char, seating: Array<String>): Int {
    return norm.map { valY -> norm.map { valX -> isOccupiedSeatInSight(x, y, valX, valY, seating) }.count {it} }.sum()
}

fun isOccupiedSeatInSight(x: Int, y: Int, dX: Int, dY: Int, seating: Array<String>): Boolean {
    if (dX == 0 && dY == 0) {
        return false
    }
    var varX = x + dX
    var varY = y + dY
    var res = false
    while (!res && varX in seating[0].indices && varY in seating.indices && seating[varY][varX] != 'L') {
        res = res || (seating[varY][varX] == '#')
        varY += dY
        varX += dX
    }
    return res
}