package aoc2025

import framework.src.IDailyPuzzle
import java.awt.Polygon
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.text.toLong

fun main() {
    Day9().printResults()
}

class Day9 : IDailyPuzzle {
    override fun getInputFile(): String {
        return "/day9.txt"
    }

    override fun getResultPuzzle1(input: String): Long {
        return input.lineSequence().fold(Pair(emptyList<Pair<Long,Long>>(), 0L)) { acc, line ->
            val stringCoord = line.split(',')
            val square = Pair(stringCoord[0].toLong(), stringCoord[1].toLong())
            var max = acc.second
            for (oldSquare in acc.first) {
                val area = (1 + abs(square.first - oldSquare.first)) * (1 + abs(square.second - oldSquare.second))
                if (area > max) {
                    max = area
                }
            }
            Pair(acc.first + square, max)
        }.second
    }

    override fun getResultPuzzle2(input: String): Long {
        val points = input.lineSequence().map {
            val stringCoord = it.split(',')
            Pair(stringCoord[0].toLong(), stringCoord[1].toLong())
        }.toList()

        return points.fold(0L) { max, square ->
            var newMax = max
            for (oldSquare in points) {
                val area = (1 + abs(square.first - oldSquare.first)) * (1 + abs(square.second - oldSquare.second))
                if (area > newMax && isRectInside(points, Pair(square, oldSquare))) {
                    newMax = area
                }
            }
            newMax
        }
    }

    private fun linesCross(line1: Pair<Pair<Long, Long>, Pair<Long, Long>>, line2: Pair<Pair<Long, Long>, Pair<Long, Long>>): Boolean {
        if (line1.first.first == line1.second.first && line2.first.first == line2.second.first) {
            // two horizontal lines never cross
            return false
        } else if (line1.first.second == line1.second.second && line2.first.second == line2.second.second) {
            // two vertical lines never cross
            return false
        } else {
            if (line1.first.first == line1.second.first) {
                val a = min(line2.first.first, line2.second.first)
                val b = max(line2.first.first, line2.second.first)
                val c = min(line1.first.second, line1.second.second)
                val d = min(line1.first.second, line1.second.second)
                return (line1.first.first in a..b) &&
                        (line2.first.second in c..d)
            } else {
                val a = min(line2.first.second, line2.second.second)
                val b = max(line2.first.second, line2.second.second)
                val c = min(line1.first.first, line1.second.first)
                val d = max(line1.first.first, line1.second.first)
                return (line1.first.second in a..b) &&
                        (line2.first.first in c..d)
            }
        }
    }

    private fun isRectInside(polygon: List<Pair<Long, Long>>, corners: Pair<Pair<Long, Long>, Pair<Long, Long>>): Boolean {
        var prevPoint = polygon.last()
        for (point in polygon) {
            if (linesCross(Pair(prevPoint, point), Pair(corners.first, Pair(corners.first.first, corners.second.second))) ||
                linesCross(Pair(prevPoint, point), Pair(corners.first, Pair(corners.second.first, corners.first.second))) ||
                linesCross(Pair(prevPoint, point), Pair(Pair(corners.first.first, corners.second.second), corners.second)) ||
                linesCross(Pair(prevPoint, point), Pair(Pair(corners.second.first, corners.first.second), corners.second))) {
                return false
            }
            prevPoint = point
        }
        return true
    }
}

