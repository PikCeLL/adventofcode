package day13

fun main() {
    val fileContent: String = {}::class.java.getResource("/day13.txt").readText().trim()

    val lines = fileContent.split("\n")
    val bestBus = getBestBus(lines[1], lines[0].toInt())

    println("Puzzle 1 : " + bestBus * getWaitTime(bestBus, lines[0].toInt()))
    println("Puzzle 2 : " + winContest(lines[1]))
}

fun getBestBus(input: String, arrivalTime: Int): Int {
    return input.split(',')
            .mapNotNull { it.toIntOrNull() }
            .minWithOrNull { bus1, bus2 -> getWaitTime(bus1, arrivalTime) - getWaitTime(bus2, arrivalTime) }!!
}

fun getWaitTime(bus: Int, arrival: Int): Int {
    return bus * (arrival / bus + 1) - arrival
}

// Doesn't end
fun findTimestamp(input: String): Long {
    val busses = input.split(',').map { it.toIntOrNull() }
    var res: Long = 0
    while (!busses.withIndex().all { (it.value == null) || (((res + it.index) % it.value!!) == 0L) }) {
        ++res
    }
    return res
}

fun winContest(input: String): Long {
    val coefs = input.split(',').foldIndexed(mapOf<Long, Long>()) { idx, map, bus -> if (bus.toLongOrNull() != null) map.plus(Pair(bus.toLong(), if (idx != 0) bus.toLong() - idx.toLong() else 0L)) else map }
    val adjustment = coefs.minOf { it.value }
    return chineseReminder(coefs.map { Pair(it.key, it.value - adjustment) }.toMap()) + adjustment
}

fun chineseReminder(coefs: Map<Long, Long>): Long {
    return coefs.map { entry ->
        val n = entry.key
        val nChapeau = coefs.filter { it != entry }.map { it.key }.reduce { prod, value -> prod * value }
        val remainder = entry.value
        remainder * modularInverse(nChapeau, n) * nChapeau
    }.sum() % coefs.map { it.key }.reduce { prod, value -> prod * value }
}

// From Wikipedia :
fun modularInverse(a: Long, n: Long): Long {
    var t: Long = 0
    var newT: Long = 1
    var r: Long = n
    var newR: Long = a
    while (newR != 0L) {
        val quotient: Long = r / newR

        val tmpT = newT
        newT = t - quotient * tmpT
        t = tmpT

        val tmpR = newR
        newR = r - quotient * tmpR
        r = tmpR
    }

    if (r > 1) {
        throw UnsupportedOperationException()
    }
    if (t < 0) {
        t += n
    }

    return t
}