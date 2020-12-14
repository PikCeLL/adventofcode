package day14

import java.util.*

val memRegex = Regex("mem\\[([0-9]*)\\] = ([0-9]*)")

fun main() {
    val fileContent: String = {}::class.java.getResource("/day14.txt").readText().trim()

    println("Puzzle 1 : " + getMemoryAfterInit1(fileContent).map { it.value }.sum())
    println("Puzzle 2 : " + getMemoryAfterInit2(fileContent).map { it.value }.sum())
}

fun getMemoryAfterInit1(input: String): Map<Long, Long> {
    return input.lineSequence().fold(Pair(mapOf<Long, Long>(), "")) { res, line ->
        when {
            line.startsWith("mask") -> Pair(res.first, line.substring("mask = ".length))
            else -> Pair(res.first.plus(fillMemory(line, res.second)), res.second)
        }
    }.first
}

fun fillMemory(line: String, mask: String): Pair<Long, Long> {
    val regexRes = memRegex.find(line)
    val (memIdx, memValue) = regexRes!!.destructured
    return Pair(memIdx.toLong(), applyValueMask(memValue, mask))
}

fun applyValueMask(value: String, mask: String): Long {
    val binVal = value.toLong().toString(2)
    return mask.mapIndexed { index, c ->
        if (c == 'X') {
            binVal.getOrElse(index - (mask.lastIndex - binVal.lastIndex)) { '0' }
        } else c
    }.joinToString("").toLong(2)
}

fun getMemoryAfterInit2(input: String): Map<Long, Long> {
    return input.lineSequence().fold(Pair(mapOf<Long, Long>(), "")) { res, line ->
        when {
            line.startsWith("mask") -> Pair(res.first, line.substring("mask = ".length))
            else -> Pair(res.first.plus(fillMemories(line, res.second)), res.second)
        }
    }.first
}

fun fillMemories(line: String, mask: String): Map<Long, Long> {
    val regexRes = memRegex.find(line)
    val (memIdx, memValue) = regexRes!!.destructured
    val binIdx = memIdx.toLong().toString(2)

    val maskedIdx = mask.foldIndexed(Pair(emptyArray<Long>(), 0L)) { index, pair, c ->
        val pow = mask.lastIndex - index
        when (c) {
            '1' -> Pair(pair.first, pair.second + (1L shl pow))
            'X' -> Pair(pair.first.plus((1L shl pow).toLong()), pair.second)
            else -> Pair(pair.first, pair.second + (binIdx.getOrElse(index - (mask.lastIndex - binIdx.lastIndex)) { '0' }.toLong() - '0'.toLong() shl pow))
        }
    }

    return IntRange(0, maskedIdx.first.count())
            .fold(mapOf()) { map, m ->
                if (m == 0)
                    map.plus(Pair(maskedIdx.second, memValue.toLong()))
                else
                    map.plus(combinations(maskedIdx.first, m).map { Pair(it.sum() + maskedIdx.second, memValue.toLong()) })
            }
}

// From https://rosettacode.org/wiki/Combinations
fun combinations(values: Array<Long>, m: Int) = sequence {
    val n = values.size
    val result = Array(m) { values[0] }
    var stack = LinkedList<Int>()
    stack.push(0)
    while (stack.isNotEmpty()) {
        var resIndex = stack.size - 1;
        var arrIndex = stack.pop()

        while (arrIndex < n) {
            result[resIndex++] = values[arrIndex++]
            stack.push(arrIndex)

            if (resIndex == m) {
                yield(result)
                break
            }
        }
    }
}