package day15

fun main() {
    val fileContent: String = {}::class.java.getResource("/day15.txt").readText().trim()

    val start = System.currentTimeMillis()
    println("Puzzle 1 : " + getNthNumberLoop(fileContent, 2020))
    println("Puzzle 2 : " + getNthNumberLoop(fileContent, 30000000))
    println("ExecTime : " + (System.currentTimeMillis() - start) / 1000.0)
}

// fold doesn't end on second input
fun getNthNumberFun(input: String, n: Int): Int {
    val array = input.split(',')
    return ((array.size) until n).fold(Pair(array[array.lastIndex].toInt(), mapOf<Int, Int>().plus(array.subList(0, array.lastIndex).mapIndexed { idx, value -> Pair(value.toInt(), idx + 1) }))) { memory, turn ->
        Pair(turn - memory.second.getOrDefault(memory.first, turn), memory.second.plus(Pair(memory.first, turn)))
    }.first
}

fun getNthNumberLoop(input: String, n: Int): Int {
    val array = input.split(',')
    var spokenMap = mutableMapOf<Int, Int>()
    spokenMap.putAll(array.subList(0, array.lastIndex).mapIndexed { idx, value -> Pair(value.toInt(), idx + 1) })
    var lastSpoken = array[array.lastIndex].toInt()

    for (i in ((array.size) until n)) {
        val toSpeak = i - spokenMap.getOrDefault(lastSpoken, i)
        spokenMap[lastSpoken] = i
        lastSpoken = toSpeak
    }
    return lastSpoken
}