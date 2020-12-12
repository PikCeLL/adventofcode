package day10

import kotlin.collections.HashMap
import kotlin.collections.Set
import kotlin.collections.filter
import kotlin.collections.fold
import kotlin.collections.map
import kotlin.collections.plus
import kotlin.collections.set
import kotlin.collections.sum
import kotlin.collections.toSet


fun main() {
    val fileContent: String = {}::class.java.getResource("/day10.txt").readText().trim()

    val intSet = fileContent.lineSequence().map { it.toInt() }.toSet()
    val diffs = joltDiff(intSet)

    println("Puzzle 1 : " + diffs.first * diffs.second)
    println("Puzzle 2 : " + getPathsToTarget(intSet))
}

fun joltDiff(input: Set<Int>): Pair<Int, Int> {
    return input.plus(0).fold(Pair(0, 0)) { pair, value ->
        when {
            input.contains(value + 1) -> Pair(pair.first + 1, pair.second)
            input.contains(value + 2) -> pair
            input.contains(value + 3) -> Pair(pair.first, pair.second + 1)
            else -> Pair(pair.first, pair.second + 1)
        }
    }
}

fun getAdapterTree(input: Set<Int>): HashMap<Int, Set<Int>> {
    return input.plus(0).fold(HashMap<Int, Set<Int>>()) { map, value ->
                map[value] = input.filter { it-value in 1..3 }.toSet()
                map
            }
}

// Doesn't end on real input !
fun getPathsToTarget(tree: HashMap<Int, Set<Int>>, start: Int, target: Int): Long {
    if (start == target) {
        return 1
    } else {
        return tree[start]!!.map { getPathsToTarget(tree, it, target) }.sum()
    }
}

// Doesn't end on real input !
fun getPathsToTarget(input: Set<Int>): Long {
    val zeroedInput = input.plus(0).sorted()
    val sortedInput = input.sorted()
    return sortedInput.sorted().fold(Array<Long>(1) { 1 }) { array, value -> array.plus(zeroedInput.filter { value-it in 1..3 }.map { inputValue -> array.getOrElse(zeroedInput.indexOf(inputValue)) {1} }.sum()) }.last()
}