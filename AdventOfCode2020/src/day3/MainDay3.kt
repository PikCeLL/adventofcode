package day3

val slope: Pair<Int,Int> = Pair(3,1)
val slopes: List<Pair<Int,Int>> = listOf(Pair(1,1), Pair(3,1), Pair(5,1), Pair(7,1), Pair(1,2))

fun main() {
    val fileContent: String = {}::class.java.getResource("/day3.txt").readText()
    val forest: List<BooleanArray> = createForest(fileContent)
    println("Puzzle 1 : " + countEncounteredTrees(forest, slope))
    println("Puzzle 2 : " + slopes.fold(1) { res: Long, currentSlope -> res * countEncounteredTrees(forest, currentSlope) })
}

fun createForest(input: String): List<BooleanArray> {
    return input.lineSequence().filter { line -> line.isNotBlank() }.map { it.asSequence().map { char -> char=='#' }.toList().toBooleanArray()}.toList()
}

fun countEncounteredTrees(forest: List<BooleanArray>, baseSlope: Pair<Int,Int>): Long {
    return forest.filterIndexed { idx: Int, _ -> idx % baseSlope.second == 0 }
                 .fold(Pair<Long, Int>(0,0))
                 { sumSlope, line -> sumSlope add Pair(if (line[sumSlope.second%line.size]) 1 else 0,baseSlope.first) }.first
}

infix fun Pair<Long,Int>.add(op:Pair<Long,Int>): Pair<Long,Int> {
    return Pair(this.first + op.first,this.second + op.second)
}