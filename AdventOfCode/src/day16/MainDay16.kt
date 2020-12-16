package day16

val ruleRegex = Regex("([\\s\\w]*): (\\d+)-(\\d+) or (\\d+)-(\\d+)")
val ticketRegex = Regex("(\\d+,)*\\d+")

class Rule(val name: String, private val ranges: Set<IntRange>) {
    fun isSatisfied(fieldValue: Int): Boolean {
        return this.ranges.any { it.contains(fieldValue) }
    }
}

data class Ticket(val fields: IntArray)

fun main() {
    val fileContent: String = {}::class.java.getResource("/day16.txt").readText().trim()

    val rules = extractRules(fileContent)
    val myTicket = getMyTicket(fileContent)
    val othersTickets = getOthersTickets(fileContent, myTicket)
    val validTickets = othersTickets
            .filter { ticket -> ticket.fields.all { value -> rules.any { it.isSatisfied(value) } } }
            .toSet()
            .plus(myTicket)

    val start = System.currentTimeMillis()
    println("Puzzle 1 : " + othersTickets
            .map { ticket -> ticket.fields.filter { value -> !rules.any { it.isSatisfied(value) } }.sum() }
            .sum())
    println("Puzzle 2 : " + getRuleAssociation(validTickets, rules)
            .filter { it.key.name.startsWith("departure") }
            .map { myTicket.fields[it.value] }
            .fold(1L) { product, value -> (product * value) })
    println("ExecTime : " + (System.currentTimeMillis() - start) / 1000.0)
}

fun extractRules(input: String): Set<Rule> {
    return input.lineSequence().takeWhile { it != "your ticket:" }.map {
        if (ruleRegex.matches(it)) {
            val (name, start1, end1, start2, end2) = ruleRegex.find(it)!!.destructured
            Rule(name, setOf(IntRange(start1.toInt(), end1.toInt()), IntRange(start2.toInt(), end2.toInt())))
        } else {
            null
        }
    }.filterNotNull().toSet()
}

fun getMyTicket(input: String): Ticket {
    return Ticket(input.lineSequence().find { ticketRegex.matches(it) }!!.split(',').map { field -> field.toInt() }.toIntArray())
}

fun getOthersTickets(input: String, myTicket: Ticket): Set<Ticket> {
    return input
            .lineSequence()
            .filter { ticketRegex.matches(it) }
            .map { Ticket(it.split(',').map { field -> field.toInt() }.toIntArray()) }
            .filter { it != (myTicket) }
            .toSet()
}

fun getRuleAssociation(tickets: Set<Ticket>, rules: Set<Rule>): Map<Rule, Int> {
    val ruleToPossibleField = rules.fold(mapOf<Rule, Set<Int>>()) { acc, rule ->
        acc.plus(Pair(rule, (0..tickets.elementAt(0).fields.lastIndex)
                .filter { tickets.all { ticket -> rule.isSatisfied(ticket.fields[it]) } }
                .toSet()))
    }.toMutableMap()

    val validatedRules = mutableMapOf<Rule, Int>()

    while (ruleToPossibleField.isNotEmpty()) {
        val okRules = ruleToPossibleField.filter { it.value.size == 1 }.map { Pair(it.key, it.value.first()) }
        validatedRules.putAll(okRules)
        ruleToPossibleField.minusAssign(okRules.map { it.first })
        ruleToPossibleField.forEach { ruleToPossibleField[it.key] = it.value.minus(okRules.map { ok -> ok.second }) }


        ruleToPossibleField.putAll(ruleToPossibleField.map { entry ->
            Pair(entry.key, entry.value
                    .filter { field -> ruleToPossibleField.minus(entry.key).all { !it.value.contains(field) } }
                    .toSet()
                    .ifEmpty { entry.value })
        })
    }

    return validatedRules
}