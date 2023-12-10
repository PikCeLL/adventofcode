package aoc2023

import framework.src.IDailyPuzzle

fun main() {
    Day8().printResults()
}

class Day8 : IDailyPuzzle {
    override fun getInputFile(): String {
        return "/day8.txt"
    }

    private val nodeRegex = "[0-9A-Z]{3}".toRegex()
    override fun getResultPuzzle1(input: String): Long {
        val splitput = input.split("\\n+".toRegex())
        val completeMap = splitput.drop(1).fold(mutableMapOf<String, Pair<String, String>>()) { map, line ->
            val nodes = nodeRegex.findAll(line).toList()
            map[nodes[0].value] = Pair(nodes[1].value, nodes[2].value)
            map
        }

        var curNode = "AAA"
        var steps = 0
        do {
            curNode = when (splitput[0][steps % splitput[0].length]) {
                'R' -> completeMap[curNode]!!.second
                'L' -> completeMap[curNode]!!.first
                else -> ""
            }
            ++steps
        } while (curNode != "ZZZ")
        return steps.toLong()
    }

    override fun getResultPuzzle2(input: String): Long {
        val splitput = input.split("\\n+".toRegex())
        val completeMap = splitput.drop(1).fold(mutableMapOf<String, Pair<String, String>>()) { map, line ->
            val nodes = nodeRegex.findAll(line).toList()
            map[nodes[0].value] = Pair(nodes[1].value, nodes[2].value)
            map
        }

        val startNodes = completeMap.keys.filter { it.endsWith('A') }.toList()
        return startNodes.map { node ->
            var curNode = node
            var steps = 0
            do {
                curNode = when (splitput[0][steps % splitput[0].length]) {
                    'R' -> completeMap[curNode]!!.second
                    'L' -> completeMap[curNode]!!.first
                    else -> ""
                }
                ++steps
            } while (!curNode.endsWith("Z"))
            steps.toLong()
        }.fold(1L) { ppcm, value ->
            ppcm(ppcm, value)
        }
    }

    private fun ppcm(a:Long, b: Long): Long {
        val max = if (a > b) a else b
        val maxPPCM = a * b
        var ppcm = max
        while (ppcm < maxPPCM) {
            if (ppcm % a == 0L && ppcm % b == 0L) {
                return ppcm
            }
            ppcm += max
        }
        return maxPPCM
    }
}

