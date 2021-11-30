package day9

fun main() {
    val fileContent: String = {}::class.java.getResource("/day9.txt").readText().trim()

    val wrongNumber = findWrongNumber(fileContent, 25)

    println("Puzzle 1 : $wrongNumber")
    println("Puzzle 2 : " + findSumWindowSum(fileContent, wrongNumber))
}

fun findWrongNumber(input: String, preambleLength: Int): Long {
    val intInput = input.lineSequence().map { it.toLong() }.toList()
    return intInput.filterIndexed { idx, value ->
        when {
            idx < preambleLength -> false
            else -> !isSumInList(value, intInput.subList(idx - preambleLength,idx))
        }
    }.first()
}

fun isSumInList(value: Long, intInput: List<Long>): Boolean {
    val set = intInput.toSet()
    return intInput.any { set.contains(value - it) }
}

fun findSumWindowSum(input: String, target: Long): Long {
    val intInput = input.lineSequence().map { it.toLong() }.toList()
    var idxStart = 0
    var idxEnd = 0
    var subList: List<Long> = listOf()
    var sum: Long = 0
    while (sum != target) {
        when {
            sum < target -> ++idxEnd
            sum > target -> ++idxStart
        }
        subList = intInput.subList(idxStart,idxEnd)
        sum = subList.sum()
    }
    return subList.minOrNull()!! + subList.maxOrNull()!!
}