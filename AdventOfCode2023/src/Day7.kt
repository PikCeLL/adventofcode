package aoc2023

import framework.src.IDailyPuzzle

fun main() {
    Day7().printResults()
}

class Day7 : IDailyPuzzle {
    override fun getInputFile(): String {
        return "/day7.txt"
    }

    private val cards1 = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')
    private val cards2 = listOf('A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J')

    override fun getResultPuzzle1(input: String): Long {
        return input.lineSequence().sortedWith { a, b ->
            val aCards = cards1.map { card -> a.substring(0, 5).count { it == card } }.filter { it != 0 }.sorted()
            val bCards = cards1.map { card -> b.substring(0, 5).count { it == card } }.filter { it != 0 }.sorted()

            if (bCards.size != aCards.size) {
                bCards.size - aCards.size
            } else {
                if (aCards.last() != bCards.last()) {
                    aCards.last() - bCards.last()
                } else {
                    a.zip(b) { aChar, bChar -> cards1.indexOf(bChar) - cards1.indexOf(aChar) }.first { it != 0 }
                }
            }
        }.foldIndexed(0) { i, sum, card ->
            sum + ((i + 1) * card.substring(6).toInt())
        }
    }

    override fun getResultPuzzle2(input: String): Long {
        return input.lineSequence().sortedWith { a, b ->
            val aCards = cards2.map { card -> a.substring(0, 5).count { it == card } }.toList()
            val bCards = cards2.map { card -> b.substring(0, 5).count { it == card } }.toList()

            val aJ = aCards.last()
            val bJ = bCards.last()

            val adjACards = aCards.dropLast(1).sorted().toMutableList()
            val adjBCards = bCards.dropLast(1).sorted().toMutableList()

            adjACards[adjACards.size - 1] += aJ
            adjBCards[adjBCards.size - 1] += bJ

            adjACards.removeIf { it == 0 }
            adjBCards.removeIf { it == 0 }

            if (adjBCards.size != adjACards.size) {
                adjBCards.size - adjACards.size
            } else {
                if (adjACards.last() != adjBCards.last()) {
                    adjACards.last() - adjBCards.last()
                } else {
                    a.zip(b) { aChar, bChar -> cards2.indexOf(bChar) - cards2.indexOf(aChar) }.first { it != 0 }
                }
            }
        }.toList()
            .foldIndexed(0) { i, sum, card ->
            sum + ((i + 1) * card.substring(6).toInt())
        }
    }
}

