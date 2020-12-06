package day6

fun main() {
    val fileContent: String = {}::class.java.getResource("/day6.txt").readText().trim()

    println("Puzzle 1 : " + fileContent.split("\n\n").map { it.replace("\n","").toSet().count() }.sum())
    println("Puzzle 2 : " + fileContent.split("\n\n").map {
        group -> group.lineSequence().map {
            it.toSet()
        }.reduce { inter, set -> inter.intersect(set) }.count()
    }.sum())
}

