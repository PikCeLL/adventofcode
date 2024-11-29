package aoc2023

import framework.src.IDailyPuzzle
import kotlin.math.max

fun main() {
    Day2().printResults()
}

data class Drawing(val red: Int = 0, val green: Int = 0, val blue: Int = 0)
data class Game(val id: Int, val drawings: Collection<Drawing>)

class Day2 : IDailyPuzzle {
    override fun getInputFile(): String {
        return "/day2.txt"
    }

    private fun getGameSequence(input: String) = input.lineSequence().map { gameLine ->
        val splitLine = gameLine.split(':')
        Game(
            splitLine[0].substring(4).trim().toInt(),
            splitLine[1].split(';')
                .map {
                    val colors = it.split(',')
                    Drawing(
                        red = colors.filter { c -> c.endsWith(" red") }
                            .map { red -> red.replace(" red", "").trim().toInt() }.getOrElse(0) { 0 },
                        green = colors.filter { c -> c.endsWith(" green") }
                            .map { red -> red.replace(" green", "").trim().toInt() }.getOrElse(0) { 0 },
                        blue = colors.filter { c -> c.endsWith(" blue") }
                            .map { red -> red.replace(" blue", "").trim().toInt() }.getOrElse(0) { 0 })
                }
                .toList())
    }

    override fun getResultPuzzle1(input: String): Long {
        return getGameSequence(input).filter { game -> game.drawings.all { it.red <= 12 && it.green <= 13 && it.blue <= 14 } }
            .sumOf { it.id }.toLong()
    }

    override fun getResultPuzzle2(input: String): Long {
        return getGameSequence(input).map { game ->
            game.drawings.fold(Triple(0, 0, 0)) { tri, draw ->
                Triple(max(tri.first, draw.red), max(tri.second, draw.green), max(tri.third, draw.blue))
            }
        }.map { it.first * it.second * it.third }.sum().toLong()
    }
}

