package aoc2025

import framework.src.IDailyPuzzle
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sign
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
        // Very specific solution tailored to this problem
        return when {
            line1.first.first == line1.second.first && line2.first.first == line2.second.first -> false
            line1.first.second == line1.second.second && line2.first.second == line2.second.second -> false
            // Ambiguous cases
            line2.first.first == line2.second.first && line1.first.first == line2.first.first
                    && line1.first.second in min(line2.first.second, line2.second.second)+1 ..< max(line2.first.second, line2.second.second)-> {
                // The rectangle side is vertical and the first point on the other segment belongs to the rectangle
                (line2.second.second - line2.first.second).sign != (line1.first.first - line1.second.first).sign
            }
            line2.first.first == line2.second.first && line1.second.first == line2.first.first
                    && line1.second.second in min(line2.first.second, line2.second.second)+1 ..< max(line2.first.second, line2.second.second) -> {
                // The rectangle side is vertical and the second point on the other segment belongs to the rectangle
                (line2.second.second - line2.first.second).sign != (line1.second.first - line1.first.first).sign
            }
            line2.first.second == line2.second.second && line1.first.second == line2.first.second
                    && line1.first.first in min(line2.first.first, line2.second.first)+1 ..< max(line2.first.first, line2.second.first) -> {
                // The rectangle side is horizontal and the first point on the other segment belongs to the rectangle
                (line2.second.first - line2.first.first).sign != (line1.second.second - line1.first.second).sign
            }
            line2.first.second == line2.second.second && line1.second.second == line2.first.second
                    && line1.second.first in min(line2.first.first, line2.second.first)+1 ..< max(line2.first.first, line2.second.first) -> {
                // The rectangle side is horizontal and the second point on the other segment belongs to the rectangle
                (line2.second.first - line2.first.first).sign != (line1.first.second - line1.second.second).sign
            }
            line1.first.first == line1.second.first -> {
                line1.first.first in min(line2.first.first, line2.second.first)+1 ..< max(line2.first.first, line2.second.first) &&
                        line2.first.second in min(line1.first.second, line1.second.second)+1 ..< max(line1.first.second, line1.second.second)
            }
            line1.first.second == line1.second.second -> {
                line1.first.second in min(line2.first.second, line2.second.second)+1 ..< max(line2.first.second, line2.second.second) &&
                        line2.first.first in min(line1.first.first, line1.second.first)+1 ..< max(line1.first.first, line1.second.first)
            }
            else -> false
        }
    }

    private fun isRectInside(polygon: List<Pair<Long, Long>>, corners: Pair<Pair<Long, Long>, Pair<Long, Long>>): Boolean {
        // Sorting corners to make sure the orientation is correct
        val topLeft: Pair<Long, Long>
        val topRight: Pair<Long, Long>
        val botRight: Pair<Long, Long>
        val botLeft: Pair<Long, Long>
        when {
            corners.first.first <= corners.second.first && corners.first.second <= corners.second.second -> {
                // First is bottom left and second top right
                botLeft = corners.first
                topRight = corners.second
                topLeft = Pair(corners.first.first, corners.second.second)
                botRight = Pair(corners.second.first, corners.first.second)
            }
            corners.first.first > corners.second.first && corners.first.second > corners.second.second -> {
                // First is top right and second bottom left
                botLeft = corners.second
                topRight = corners.first
                topLeft = Pair(corners.second.first, corners.first.second)
                botRight = Pair(corners.first.first, corners.second.second)
            }
            corners.first.first <= corners.second.first && corners.first.second > corners.second.second -> {
                // First is top left and second bottom right
                topLeft = corners.first
                botRight = corners.second
                botLeft = Pair(corners.first.first, corners.second.second)
                topRight = Pair(corners.second.first, corners.first.second)
            }
            else -> {
                // First is bottom right and second top left
                botRight = corners.first
                topLeft = corners.second
                topRight = Pair(corners.first.first, corners.second.second)
                botLeft = Pair(corners.second.first, corners.first.second)
            }
        }

        var prevPoint = polygon.last()
        for (point in polygon) {
            if (linesCross(Pair(prevPoint, point), Pair(botLeft, topLeft)) ||
                linesCross(Pair(prevPoint, point), Pair(topLeft, topRight)) ||
                linesCross(Pair(prevPoint, point), Pair(topRight, botRight)) ||
                linesCross(Pair(prevPoint, point), Pair(botRight, botLeft))) {
                return false
            }
            prevPoint = point
        }
        return true
    }
}

